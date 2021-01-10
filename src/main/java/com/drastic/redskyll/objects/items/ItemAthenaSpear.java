package com.drastic.redskyll.objects.items;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.init.FluidInit;
import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.objects.entity.EntityRedskyll;
import com.drastic.redskyll.util.interfaces.IHasModel;
import com.drastic.redskyll.util.interfaces.IRelic;
import com.drastic.redskyll.util.provider.RelicProvider;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

public class ItemAthenaSpear extends ItemSword implements IHasModel
{
    public ItemAthenaSpear(String name, ToolMaterial material)
    {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.REDSKYLL_TAB);
        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRederer(this, 0);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
        IRelic relic = player.getCapability(RelicProvider.RELIC_CAP, null);

        if(relic.get() != 2)
        {
            if(player.world.isRemote)
            {
                player.sendMessage(new TextComponentString(TextFormatting.RED + "Le dieu de ce miracle ne vous a pas choisi"));
            }
            player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20 * 5, 1));
        }
        else if(entity instanceof EntityLivingBase)
        {
            EntityLivingBase e = (EntityLivingBase)entity;
            e.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20 * 5, 1));
        }
        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {

        if(entityIn instanceof EntityPlayer && !worldIn.isRemote)
        {
            EntityPlayer player = (EntityPlayer)entityIn;

            IRelic relic = player.getCapability(RelicProvider.RELIC_CAP, null);
            
            if(relic.get() != 2)
            {
                player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 20 * 5, 1));
            }
            else
            {
                player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 0, 2));
            }
        }

        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
    }
}
