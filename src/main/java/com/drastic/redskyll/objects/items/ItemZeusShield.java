package com.drastic.redskyll.objects.items;

import java.util.Map;

import javax.annotation.Nullable;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.init.EnchantmentInit;
import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.objects.entity.EntityShield;
import com.drastic.redskyll.util.interfaces.IHasModel;
import com.drastic.redskyll.util.interfaces.IRelic;
import com.drastic.redskyll.util.provider.RelicProvider;

import net.minecraft.block.BlockDispenser;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemZeusShield extends Item implements IHasModel
{
    public boolean isLoaded;
    public int timer;

    public ItemZeusShield(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.REDSKYLL_TAB);
        this.setMaxDamage(555);
        setMaxStackSize(1);
        this.addPropertyOverride(new ResourceLocation("blocking"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, ItemArmor.DISPENSER_BEHAVIOR);
        ItemInit.ITEMS.add(this);
    }

    @Override
    public boolean isEnchantable(ItemStack stack)
    {
        return true;
    }

    @Override
    public int getItemEnchantability()
    {
        return 25;
    }
    
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment)
    {
        return super.canApplyAtEnchantingTable(stack, enchantment);
    }
    
    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRederer(this, 0);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        return super.getItemStackDisplayName(stack);
    }

    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BLOCK;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 92000;
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(playerIn.getHeldItem(handIn));
        
        int level = 0;
        
        if(enchantments.containsKey(EnchantmentInit.DEMON_BREAKER))
        {
            level = enchantments.get(EnchantmentInit.DEMON_BREAKER);
        }
        
        if(playerIn.isSneaking())
        {
            if(this.isLoaded)
            {

                IRelic relic = playerIn.getCapability(RelicProvider.RELIC_CAP, null);

                if(relic.get() != 1)
                {
                    if(worldIn.isRemote)
                    {
                        playerIn.sendMessage(new TextComponentString(TextFormatting.RED + "Le dieu de ce miracle ne vous a pas choisi"));
                        return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
                    }
                    return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
                }
                else
                {
                    if(!worldIn.isRemote)
                    {
                        EntityShield shield = new EntityShield(worldIn, playerIn.posX, playerIn.posY + 1, playerIn.posZ, playerIn, level);
                        shield.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0, 2, 0);
                        worldIn.spawnEntity(shield);
                        itemstack.shrink(1);
                        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
                    }
                    else
                    {
                        return new ActionResult<ItemStack>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
                    }
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
        else
        {
            IRelic relic = playerIn.getCapability(RelicProvider.RELIC_CAP, null);

            if(relic.get() != 1)
            {
                playerIn.sendMessage(new TextComponentString(TextFormatting.RED + "Le dieu de ce miracle ne vous a pas choisi"));
                return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
            }
            else
            {
                playerIn.setActiveHand(handIn);
            }
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
        }

    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        if(!this.isLoaded)
        {
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

    public void reload(int i)
    {
        this.timer = i;
        this.isLoaded = false;
    }
}
