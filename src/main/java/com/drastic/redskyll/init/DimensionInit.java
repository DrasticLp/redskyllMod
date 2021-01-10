package com.drastic.redskyll.init;

import com.drastic.redskyll.util.Reference;
import com.drastic.redskyll.objects.dimension.mars.Mars;
import com.drastic.redskyll.objects.dimension.moon.Moon;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class DimensionInit
{
    public static final DimensionType MOON = DimensionType.register("MOON", "_moon", Reference.MOON, Moon.class, false);
    public static final DimensionType MARS = DimensionType.register("MARS", "_mars", Reference.MARS, Mars.class, false);

    public static void registerDimensions()
    {
        DimensionManager.registerDimension(Reference.MOON, MOON);
        DimensionManager.registerDimension(Reference.MARS, MARS);
   }
}
