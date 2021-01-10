package com.drastic.redskyll.objects.dimension.moon;

import com.drastic.redskyll.init.BiomeInit;
import com.drastic.redskyll.init.DimensionInit;
import com.drastic.redskyll.util.Reference;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;

public class Moon extends WorldProvider
{

    public Moon()
    {
        this.biomeProvider = new BiomeProviderSingle(BiomeInit.MOON);
        this.hasSkyLight = true;
    }

    @Override
    public DimensionType getDimensionType()
    {
        return DimensionInit.MOON;
    }

    @Override
    public boolean canRespawnHere()
    {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    public IChunkGenerator createChunkGenerator()
    {
        return new ChunkGeneratorMoon(world, true, world.getSeed(), new BlockPos(0, 150, 0));
    }

    @Override
    public BiomeProvider getBiomeProvider()
    {
        return new BiomeProviderSingle(BiomeInit.MOON);
    }
    
    @Override
    public boolean isSurfaceWorld()
    {
        // TODO Auto-generated method stub
        return false;
    }
}
