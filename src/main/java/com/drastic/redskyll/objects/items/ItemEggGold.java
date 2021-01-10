package com.drastic.redskyll.objects.items;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.objects.entity.EntityMinionGold;
import com.drastic.redskyll.util.interfaces.IHasModel;
import com.drastic.redskyll.util.interfaces.IMana;
import com.drastic.redskyll.util.provider.ManaProvider;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemEggGold extends Item implements IHasModel
{
    public ItemEggGold(String name)
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

        IMana mana = player.getCapability(ManaProvider.MANA_CAP, null);

        if(!worldIn.isRemote)
        {
            if(mana.getMana() > 0)
            {
                ItemStack stack = player.getHeldItem(hand);
                
                EntityMinionGold minion = new EntityMinionGold(worldIn, pos.getX(), pos.getY() + 1, pos.getZ());
                
                stack.shrink(1);
                
                worldIn.spawnEntity(minion);
            }
            else
            {
               player.sendMessage(new TextComponentString("Vous n'avez pas assez de Mana")); 
            }

        }
        if(mana.getMana() != 0)
        {
            mana.shrinkMana(1);

            // Main.network.sendTo(new SyncManaData(player.getCapability(ManaProvider.MANA_CAP, null)), (EntityPlayerMP) player);
        }
        else if(worldIn.isRemote)
        {
            player.sendMessage(new TextComponentString("Vous n'avez pas assez de Mana"));
        }
        return EnumActionResult.SUCCESS;
    }
}
