package com.drastic.redskyll.blockhit.common.handler;

import com.drastic.redskyll.blockhit.config.ConfigBuildHandler;
import com.drastic.redskyll.blockhit.util.ReflectionHelper;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClassicCombatHandler
{

    @SubscribeEvent
    public void onAttackEntity(final AttackEntityEvent evt)
    {

        // disable cooldown right before every attack
        if(ConfigBuildHandler.classicCombatConfig.removeCooldown)
        {

            ReflectionHelper.setTicksSinceLastSwing(evt.getEntityPlayer(), (int)Math.ceil(evt.getEntityPlayer().getCooldownPeriod()));
        }
    }

    @SubscribeEvent
    public void onCriticalHit(final CriticalHitEvent evt)
    {

        // prevent sweeping from taking effect unless the enchantment is in place, onGround flag is reset the next tick anyways
        if(ConfigBuildHandler.classicCombatConfig.sweepingRequired && EnchantmentHelper.getSweepingDamageRatio(evt.getEntityPlayer()) == 0.0F)
        {
            evt.getEntityPlayer().onGround = false;
        }
    }

}
