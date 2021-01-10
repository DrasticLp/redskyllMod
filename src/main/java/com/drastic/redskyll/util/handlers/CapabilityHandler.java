package com.drastic.redskyll.util.handlers;

import com.drastic.redskyll.util.Reference;
import com.drastic.redskyll.util.provider.BossProvider;
import com.drastic.redskyll.util.provider.ManaProvider;
import com.drastic.redskyll.util.provider.MinionProvider;
import com.drastic.redskyll.util.provider.MoneyProvider;
import com.drastic.redskyll.util.provider.RelicProvider;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityHandler
{
    public static final ResourceLocation MINION_CAP = new ResourceLocation(Reference.MODID, "minion");
    public static final ResourceLocation BOSS_CAP = new ResourceLocation(Reference.MODID, "boss");
    public static final ResourceLocation MANA_CAP = new ResourceLocation(Reference.MODID, "mana");
    public static final ResourceLocation MONEY_CAP = new ResourceLocation(Reference.MODID, "money");
    public static final ResourceLocation RELIC_CAP = new ResourceLocation(Reference.MODID, "relic");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if(!(event.getObject() instanceof EntityPlayer))
            return;

        event.addCapability(MINION_CAP, new MinionProvider());
        event.addCapability(BOSS_CAP, new BossProvider());
        event.addCapability(MANA_CAP, new ManaProvider());
        event.addCapability(MONEY_CAP, new MoneyProvider());
        event.addCapability(RELIC_CAP, new RelicProvider());
    }
}
