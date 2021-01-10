package com.drastic.redskyll.objects.items;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.util.interfaces.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemParachute extends Item implements IHasModel
{
    public ItemParachute(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.REDSKYLL_TAB);
        setMaxStackSize(1);
        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRederer(this, 0);
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        if(!worldIn.isRemote)
        {
            if(playerIn.getItemStackFromSlot(EntityEquipmentSlot.HEAD) != null)
            {
                playerIn.getHeldItem(handIn).shrink(1);
                playerIn.addItemStackToInventory(playerIn.getItemStackFromSlot(EntityEquipmentSlot.HEAD));
                playerIn.getItemStackFromSlot(EntityEquipmentSlot.HEAD).shrink(1);
                playerIn.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ItemInit.PARACHUTE));
            }
            else if(playerIn.getItemStackFromSlot(EntityEquipmentSlot.HEAD) == null)
            {
                playerIn.getHeldItem(handIn).shrink(1);
                playerIn.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ItemInit.PARACHUTE));
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
