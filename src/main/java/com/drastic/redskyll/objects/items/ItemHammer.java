package com.drastic.redskyll.objects.items;

import java.util.Map;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.init.EnchantmentInit;
import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.util.interfaces.IHasModel;
import com.drastic.redskyll.util.interfaces.IRelic;
import com.drastic.redskyll.util.provider.RelicProvider;

import net.minecraft.client.model.ModelWolf;
import net.minecraft.client.renderer.entity.RenderVillager;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemHammer extends Item implements IHasModel
{
    public boolean isLoaded;
    public int timer;
    public boolean isJumpLoaded;

    public ItemHammer(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.REDSKYLL_TAB);
        this.isLoaded = true;
        this.isJumpLoaded = true;
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
        RayTraceResult result = playerIn.rayTrace(100, 1F);

        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(playerIn.getHeldItem(handIn));

        int level = 0;

        if(enchantments.containsKey(EnchantmentInit.THUNDER_LAW))
        {
            level = enchantments.get(EnchantmentInit.THUNDER_LAW);
        }

        if(this.isLoaded)
        {

            IRelic relic = playerIn.getCapability(RelicProvider.RELIC_CAP, null);

            if(relic.get() != 0)
            {

                if(!worldIn.isRemote)
                {
                    EntityLightningBolt lightningBolt = new EntityLightningBolt(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, false);
                    worldIn.addWeatherEffect(lightningBolt);
                }
                else
                {
                    playerIn.sendMessage(new TextComponentString(TextFormatting.RED + "Le dieu de ce miracle ne vous a pas choisi"));
                    return new ActionResult<ItemStack>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
                }
                return new ActionResult<ItemStack>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
            }
            else
            {
                if(!worldIn.isRemote)
                {
                    EntityLightningBolt lightningBolt = new EntityLightningBolt(worldIn, result.getBlockPos().getX(), result.getBlockPos().getY(), result.getBlockPos().getZ(), false);

                    if(level == 0)
                    {
                        worldIn.addWeatherEffect(lightningBolt);
                    }
                    else if(level == 1)
                    {
                        worldIn.addWeatherEffect(lightningBolt);
                        worldIn.addWeatherEffect(lightningBolt);
                    }
                    else if(level == 2)
                    {
                        worldIn.addWeatherEffect(lightningBolt);
                        worldIn.addWeatherEffect(lightningBolt);
                        worldIn.addWeatherEffect(lightningBolt);
                    }
                    else
                    {
                        worldIn.addWeatherEffect(lightningBolt);
                        worldIn.addWeatherEffect(lightningBolt);
                        worldIn.addWeatherEffect(lightningBolt);
                        worldIn.addWeatherEffect(lightningBolt);
                    }
                    this.reload(15 * 20);
                }
                return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
            }

        }
        else
        {
            if(worldIn.isRemote)
            {
                playerIn.sendMessage(new TextComponentString(TextFormatting.RED + "Le pouvoir de cette relique se recharge..."));
                playerIn.sendMessage(new TextComponentString(TextFormatting.RED + "Il devrait être prêt dans " + this.timer / 20 + " secondes"));
            }
            return new ActionResult<ItemStack>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
        }

    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        if(!this.isLoaded)
        {
            if(this.timer == 7 * 20)
            {
                this.isJumpLoaded = true;
            }

            if(this.timer > 0)
            {
                this.timer--;
            }
            else
            {
                this.isLoaded = true;
            }
        }
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
    }

    private void reload(int i)
    {
        this.timer = i;
        this.isLoaded = false;
    }
}
