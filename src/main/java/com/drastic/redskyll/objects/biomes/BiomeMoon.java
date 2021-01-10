package com.drastic.redskyll.objects.biomes;

import com.drastic.redskyll.init.BlockInit;
import com.drastic.redskyll.util.Reference;

import net.minecraft.world.biome.Biome;

public class BiomeMoon extends Biome
{

    public BiomeMoon()
    {
        super(new BiomeProperties("Moon").setBaseHeight(1.0F).setHeightVariation(0.2F).setTemperature(0.1F).setRainDisabled());
        
        topBlock = BlockInit.MOON_ROCK.getDefaultState();
        fillerBlock = BlockInit.MOON_ROCK.getDefaultState();
        
        this.setRegistryName(Reference.MODID, "moon");
        
        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        
    }

}
