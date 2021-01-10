package com.drastic.redskyll.objects.items;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.util.interfaces.IHasModel;
import com.drastic.redskyll.util.interfaces.IMana;
import com.drastic.redskyll.util.provider.ManaProvider;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemStaff extends Item implements IHasModel
{
    private int timer = 20 * 1 * 60;
    private boolean isEnabled = false;

    public ItemStaff(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setMaxStackSize(1);
        setCreativeTab(Main.REDSKYLL_TAB);
        setMaxDamage(4);
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
        IMana mana = playerIn.getCapability(ManaProvider.MANA_CAP, null);

        if(mana.getMana() >= 5)
        {
            if(!this.isEnabled)
            {
                playerIn.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 20 * 5, 2));

                if(!worldIn.isRemote)
                {
                    playerIn.setAbsorptionAmount(20);
                    this.isEnabled = true;
                    playerIn.getHeldItem(handIn).damageItem(1, playerIn);
                }
                mana.shrinkMana(5);

                return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
            }

            else if(this.isEnabled)
            {

                if(worldIn.isRemote)
                {
                    playerIn.sendMessage(new TextComponentString("Veuillez patienter encore " + (int)(this.timer / 20) + " secondes"));
                }
                return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
            }
            else
            {
                return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
            }
        }
        else if(mana.getMana() <= 5)
        {
            if(worldIn.isRemote)
            {
                playerIn.sendMessage(new TextComponentString("Vous n'avez pas assez de Mana"));
            }
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }
        else
        {
            return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
        }

    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        if(this.isEnabled)
        {
            if(this.timer != 0)
            {
                this.timer--;
            }
            else if(this.timer == 0)
            {
                this.timer = 20 * 1 * 60;
                this.isEnabled = false;
            }
        }
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
    }
}
