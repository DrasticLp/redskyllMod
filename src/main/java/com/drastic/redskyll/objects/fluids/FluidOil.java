package com.drastic.redskyll.objects.fluids;

import net.minecraft.client.particle.ParticleDrip.WaterFactory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidOil extends Fluid
{

    public FluidOil(String fluidName, ResourceLocation still, ResourceLocation flowing)
    {
        super(fluidName, still, flowing);
        this.setUnlocalizedName(fluidName);
        this.setViscosity(3);
        this.setGaseous(true);
    }

}
