package com.drastic.redskyll.util.handlers;

import com.drastic.redskyll.objects.tileentities.TileEntityActivator;
import com.drastic.redskyll.objects.tileentities.TileEntityForge;
import com.drastic.redskyll.objects.tileentities.TileEntityLantern;
import com.drastic.redskyll.objects.tileentities.TileEntityOilingMachine;
import com.drastic.redskyll.objects.tileentities.TileEntitySinteringFurnace;
import com.drastic.redskyll.util.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler
{
    public static void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntitySinteringFurnace.class, new ResourceLocation(Reference.MODID + ":magical_cauldron"));
        //GameRegistry.registerTileEntity(TileEntityActivator.class, new ResourceLocation(Reference.MODID + ":activator"));
        GameRegistry.registerTileEntity(TileEntityLantern.class, new ResourceLocation(Reference.MODID + ":lantern"));
        GameRegistry.registerTileEntity(TileEntityOilingMachine.class, new ResourceLocation(Reference.MODID + ":oil_machine"));
        GameRegistry.registerTileEntity(TileEntityForge.class, new ResourceLocation(Reference.MODID + ":forge"));
   }
}
