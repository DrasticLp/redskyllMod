package com.drastic.redskyll.objects.enchantments;

import com.drastic.redskyll.init.EnchantmentInit;
import com.drastic.redskyll.util.Reference;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;

public class EnchantmentThunderLaw extends Enchantment
{

    public EnchantmentThunderLaw()
    {
        super(Rarity.VERY_RARE, EnchantmentInit.HAMMER, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
        this.setName("thunder_law");
        this.setRegistryName(new ResourceLocation(Reference.MODID + ":thunder_law"));

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
        return 3;
    }
}
