package com.drastic.redskyll.init;

import java.util.ArrayList;
import java.util.List;

import com.drastic.redskyll.objects.enchantments.EnchantmentDemonBreaker;
import com.drastic.redskyll.objects.enchantments.EnchantmentThunderLaw;
import com.drastic.redskyll.objects.items.ItemHammer;
import com.drastic.redskyll.objects.items.ItemZeusShield;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class EnchantmentInit
{
    public static final EnumEnchantmentType SHIELDS = EnumHelper.addEnchantmentType("shields", (item) -> (item instanceof ItemZeusShield));
    public static final EnumEnchantmentType HAMMER = EnumHelper.addEnchantmentType("hammer", (item) -> (item instanceof ItemHammer));
   public static final List<Enchantment> ENCHANTEMENTS = new ArrayList<Enchantment>(); 
    
    public static final Enchantment DEMON_BREAKER = new EnchantmentDemonBreaker();
    public static final Enchantment THUNDER_LAW = new EnchantmentThunderLaw();

    @SubscribeEvent
    public static void enchantmentsEvents(LivingUpdateEvent e)
    {
        /*EntityLivingBase living = e.getEntityLiving();
        int level = EnchantmentHelper.getMaxEnchantmentLevel(DEMON_BREAKER, living);
        BlockPos pos = living.getPosition();
        World world = e.getEntity().world;*/
    }
}
