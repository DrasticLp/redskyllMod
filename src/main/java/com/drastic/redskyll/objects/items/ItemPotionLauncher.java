package com.drastic.redskyll.objects.items;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.util.interfaces.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemPotionLauncher extends Item implements IHasModel
{    
    public ItemPotionLauncher(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setMaxStackSize(1);
        setCreativeTab(Main.REDSKYLL_TAB);
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
        for(int i = 0; i < 36; i++)
        {
            if(playerIn.inventory.getStackInSlot(i).getItem() == Items.SPLASH_POTION)
            {
                ItemStack itemstack = playerIn.inventory.getStackInSlot(i);
                ItemStack itemstack1 = playerIn.capabilities.isCreativeMode ? itemstack.copy() : itemstack.splitStack(1);
                worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SPLASH_POTION_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

                if (!worldIn.isRemote)
                {
                    EntityPotion entitypotion = new EntityPotion(worldIn, playerIn, itemstack1);
                    entitypotion.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, -20.0F, 0.5F, 1.0F);
                    worldIn.spawnEntity(entitypotion);
                }
                
                return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
            }
        }
        
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
