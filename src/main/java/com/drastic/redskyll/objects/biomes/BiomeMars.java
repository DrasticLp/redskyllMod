package com.drastic.redskyll.objects.biomes;

import com.drastic.redskyll.init.BlockInit;
import com.drastic.redskyll.objects.entity.EntityScorpion;
import com.drastic.redskyll.util.Reference;

import net.minecraft.world.biome.Biome;

public class BiomeMars extends Biome
{

    public BiomeMars()
    {
        super(new BiomeProperties("Mars").setBaseHeight(1.0F).setHeightVariation(.15F).setTemperature(0.4F).setRainDisabled());
        
        this.topBlock = BlockInit.MARS_SAND.getDefaultState();
        this.fillerBlock = BlockInit.MARS_ROCK.getDefaultState();
                
        this.setRegistryName(Reference.MODID, "mars");
        
        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(EntityScorpion.class, 50, 1, 1));
        
    }
}
