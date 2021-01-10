package com.drastic.redskyll.world.generation;

import java.util.Random;

import com.drastic.redskyll.init.BlockInit;

import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenCustomOres implements IWorldGenerator
{
    private WorldGenerator redasia_ore, kramazia_ore, dragonite_ore, oil, armadium_ore, mercure_ore, uranium_ore;

    public WorldGenCustomOres()
    {
        redasia_ore = new WorldGenMinable(BlockInit.ORE_REDASIA.getDefaultState(), 3, BlockMatcher.forBlock(Blocks.STONE));
        kramazia_ore = new WorldGenMinable(BlockInit.ORE_KRAMAZIA.getDefaultState(), 7, BlockMatcher.forBlock(BlockInit.MOON_ROCK));
        dragonite_ore = new WorldGenMinable(BlockInit.DRAGONITE_ORE.getDefaultState(), 7, BlockMatcher.forBlock(Blocks.END_STONE));
        oil = new WorldGenMinable(BlockInit.OIL.getDefaultState(), 6, BlockMatcher.forBlock(BlockInit.MARS_ROCK));
        armadium_ore = new WorldGenMinable(BlockInit.ARMADIUM_ORE.getDefaultState(), 3, BlockMatcher.forBlock(BlockInit.MARS_ROCK));
        mercure_ore = new WorldGenMinable(BlockInit.MERCURE_ORE.getDefaultState(), 3, BlockMatcher.forBlock(BlockInit.MARS_ROCK));
        uranium_ore = new WorldGenMinable(BlockInit.URANIUM_ORE.getDefaultState(), 3, BlockMatcher.forBlock(BlockInit.MARS_ROCK));
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        switch(world.provider.getDimension())
        {
            case -1:
                break;
            case 0:
                runGenerator(redasia_ore, world, random, chunkX, chunkZ, 15, 0, 13);
                break;
            case 1:
                runGenerator(dragonite_ore, world, random, chunkX, chunkZ, 75, 0, 200);
                break;
            case 2:
                runGenerator(kramazia_ore, world, random, chunkX, chunkZ, 75, 0, 200);
                break;
            case 58:
                runGenerator(mercure_ore, world, random, chunkX, chunkZ, 25, 12, 23);
                runGenerator(armadium_ore, world, random, chunkX, chunkZ, 50, 23, 55);
                runGenerator(uranium_ore, world, random, chunkX, chunkZ, 25, 55, 70);
                runGenerator(oil, world, random, chunkX, chunkZ, 1, 0, 12);
                break;
        }
    }

    private void runGenerator(WorldGenerator gen, World world, Random rand, int ChunkX, int ChunkZ, int chance, int minHeight, int maxHeight)
    {
        if(minHeight > maxHeight || minHeight < 0 || maxHeight > 256)
            throw new IllegalArgumentException("Ore generated out of bounds");

        int heightDiff = maxHeight - minHeight + 1;

        for(int i = 0; i < chance; i++)
        {
            int x = ChunkX * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightDiff);
            int z = ChunkZ * 16 + rand.nextInt(16);

            gen.generate(world, rand, new BlockPos(x, y, z));
        }
    }
}