package com.drastic.redskyll.init;

import com.drastic.redskyll.objects.fluids.FluidOil;
import com.drastic.redskyll.util.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidInit
{
    public static final Fluid OIL = new FluidOil("oil", new ResourceLocation("redskyll:blocks/oil_still"), new ResourceLocation("redskyll:blocks/oil_flow"));
    
    public static void registerFluids()
    {
        registerFluid(OIL);
    }
    
    public static void registerFluid(Fluid fluid)
    {
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);
    }
}
