package com.drastic.redskyll.objects.items;

import java.util.Random;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.objects.entity.EntityMinionDiamond;
import com.drastic.redskyll.util.interfaces.IHasMinion;
import com.drastic.redskyll.util.interfaces.IHasModel;
import com.drastic.redskyll.util.provider.MinionProvider;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemMinionDiamond extends Item implements IHasModel
{
    public ItemMinionDiamond(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.REDSKYLL_TAB);
        setMaxStackSize(1);
        setMaxDamage(29);
        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRederer(this, 0);
    }
    
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        Random random = new Random();
        
        IHasMinion hasMinion = player.getCapability(MinionProvider.MINION_CAP, null);

        if(!worldIn.isRemote)
        {
            if(hasMinion.get() == 0)
            {               
                EntityMinionDiamond minion = new EntityMinionDiamond(worldIn, pos.getX(), pos.getY() + 1, pos.getZ());
                
                minion.setTamedBy(player);
                
                hasMinion.set(1);
                
                minion.setCustomNameTag(player.getName() + "'s Minion");
                
                worldIn.spawnEntity(minion);
                
                player.getHeldItem(hand).shrink(1);
            }

            else
            {
                player.sendMessage(new TextComponentTranslation("message.minion"));
            }
        }
        return EnumActionResult.SUCCESS;
    }
}
