package com.drastic.redskyll.objects.enchantments;

import com.drastic.redskyll.init.EnchantmentInit;
import com.drastic.redskyll.util.Reference;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;

public class EnchantmentDemonBreaker extends Enchantment
{

    public EnchantmentDemonBreaker()
    {
        super(Rarity.VERY_RARE, EnchantmentInit.SHIELDS, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
        this.setName("demon_breaker");
        this.setRegistryName(new ResourceLocation(Reference.MODID + ":demon_breaker"));

        EnchantmentInit.ENCHANTEMENTS.add(this);
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel)
    {
        return 8 * enchantmentLevel;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel)
    {
        return this.getMinEnchantability(enchantmentLevel) + 22;
    }
    
    @Override
    public int getMaxLevel()
    {
        return 5;
    }
}
