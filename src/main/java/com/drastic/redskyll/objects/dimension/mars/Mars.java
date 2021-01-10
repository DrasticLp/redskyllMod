package com.drastic.redskyll.objects.dimension.mars;

import com.drastic.redskyll.init.BiomeInit;
import com.drastic.redskyll.init.DimensionInit;

import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;

public class Mars extends WorldProvider
{

    @Override
    protected void init()
    {
        this.biomeProvider = new BiomeProviderSingle(BiomeInit.MARS);
        this.doesWaterVaporize = true;
        this.hasSkyLight = false;
    }

    @Override
    public DimensionType getDimensionType()
    {
        return DimensionInit.MARS;
    }

    @Override
    public Vec3d getFogColor(float p_76562_1_, float p_76562_2_)
    {
        return new Vec3d(0.6196078431372549, 0.2470588235294118, 0);
    }

    @Override
    public boolean isSurfaceWorld()
    {
        return false;
    }

    @Override
    public boolean canCoordinateBeSpawn(int x, int z)
    {
        return super.canCoordinateBeSpawn(x, z);
    }

    @Override
    public boolean canRespawnHere()
    {
        return false;
    }

    @Override
    public boolean doesXZShowFog(int x, int z)
    {
        return Math.abs(x) <= 256 && Math.abs(z) <= 256;
    }
    
    @Override
    public IChunkGenerator createChunkGenerator()
    {
        return new ChunkGeneratorMars(world, true, this.getSeed());
    }
}
