package com.drastic.redskyll.util.handlers;

import java.awt.Color;

import com.drastic.redskyll.util.Reference;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Type;

@Config(modid = Reference.MODID, name = "f3Color", type = Type.INSTANCE)
public class ConfigColor
{
    public static String primary = "orange";

    public static String secondary = "yellow";

    public static int pimaryInt = Color.orange.getRGB();
    public static int secondaryInt = Color.yellow.getRGB();
    
}
