package com.drastic.redskyll.init;

import com.drastic.redskyll.objects.biomes.BiomeMars;
import com.drastic.redskyll.objects.biomes.BiomeMoon;
import com.drastic.redskyll.util.Reference;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(Reference.MODID)
public class BiomeInit
{
    public static final Biome MOON = new BiomeMoon();
    public static final Biome MARS = new BiomeMars();

    @EventBusSubscriber(modid = Reference.MODID)
    public static class Register
    {
        @SubscribeEvent
        public static void registerBiomes(final RegistryEvent.Register<Biome> event)
        {
            final Biome[] biomes = {MOON, MARS};

            event.getRegistry().registerAll(biomes);

            spawnBiomes();
        }

        private static void spawnBiomes()
        {
            /*BiomeManager.addBiome(BiomeType.DESERT, new BiomeManager.BiomeEntry(MOON, 50));
            BiomeManager.addSpawnBiome(MOON);
            BiomeManager.addBiome(BiomeType.DESERT, new BiomeManager.BiomeEntry(MARS, 51));
            BiomeManager.addSpawnBiome(MARS);*/
        }
    }
}
