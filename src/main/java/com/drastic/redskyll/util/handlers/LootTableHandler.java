package com.drastic.redskyll.util.handlers;

import com.drastic.redskyll.util.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class LootTableHandler
{
    public static final ResourceLocation FUSE = LootTableList.register(new ResourceLocation(Reference.MODID, "fuse"));
    public static final ResourceLocation MINION = LootTableList.register(new ResourceLocation(Reference.MODID, "minion"));
    public static final ResourceLocation MINION2 = LootTableList.register(new ResourceLocation(Reference.MODID, "minion2"));
    public static final ResourceLocation MINION3 = LootTableList.register(new ResourceLocation(Reference.MODID, "minion3"));
    public static final ResourceLocation MINION4 = LootTableList.register(new ResourceLocation(Reference.MODID, "minion4"));
    public static final ResourceLocation MINION5 = LootTableList.register(new ResourceLocation(Reference.MODID, "minion5"));
    public static final ResourceLocation MINION_TAMED = LootTableList.register(new ResourceLocation(Reference.MODID, "minion_tamed"));
    public static final ResourceLocation REDSKYLL = LootTableList.register(new ResourceLocation(Reference.MODID, "redskyll"));
    public static final ResourceLocation SCORPION = LootTableList.register(new ResourceLocation(Reference.MODID, "scorpion"));
}
