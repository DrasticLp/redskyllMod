package com.drastic.redskyll.init;

import java.util.ArrayList;
import java.util.List;

import com.drastic.redskyll.objects.blocks.BlockActivator;
import com.drastic.redskyll.objects.blocks.BlockBasic;
import com.drastic.redskyll.objects.blocks.BlockCar;
import com.drastic.redskyll.objects.blocks.BlockMagicalCauldron;
import com.drastic.redskyll.objects.blocks.BlockChair;
import com.drastic.redskyll.objects.blocks.BlockEgg;
import com.drastic.redskyll.objects.blocks.BlockEntityKiller;
import com.drastic.redskyll.objects.blocks.BlockFluid;
import com.drastic.redskyll.objects.blocks.BlockForge;
import com.drastic.redskyll.objects.blocks.BlockIncubator;
import com.drastic.redskyll.objects.blocks.BlockLantern;
import com.drastic.redskyll.objects.blocks.BlockMarsSand;
import com.drastic.redskyll.objects.blocks.BlockOilingMachine;
import com.drastic.redskyll.objects.blocks.BlockPlantKiwi;
import com.drastic.redskyll.objects.blocks.BlockPlantMyrtille;
import com.drastic.redskyll.objects.blocks.BlockPlantOrange;
import com.drastic.redskyll.objects.blocks.BlockRadio;
import com.drastic.redskyll.objects.blocks.BlockSacrifice;
import com.drastic.redskyll.objects.blocks.BlockTotem;
import com.drastic.redskyll.objects.blocks.BlockTrophy;
import com.drastic.redskyll.objects.blocks.BlockUraniumOre;
import com.drastic.redskyll.objects.blocks.fuses.BlockFuse;
import com.drastic.redskyll.objects.blocks.fuses.BlockFuseT2;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

public class BlockInit
{
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    // Ores
    public static final Block ORE_REDASIA = new BlockBasic("redasia_ore", Material.ROCK);
    public static final Block ORE_KRAMAZIA = new BlockBasic("kramazia_ore", Material.ROCK);
    public static final Block DRAGONITE_ORE = new BlockBasic("dragonite_ore", Material.ROCK).setHardness(5);
    public static final Block ARMADIUM_ORE = new BlockBasic("armadium_ore", Material.ROCK).setHardness(5);
    public static final Block MERCURE_ORE = new BlockBasic("mercure_ore", Material.ROCK).setHardness(7);
    public static final Block URANIUM_ORE = new BlockUraniumOre("uranium_ore", Material.ROCK).setHardness(7);

    // Blocks
    public static final Block REDASIA_BLOCK = new BlockBasic("redasia_block", Material.ROCK);
    public static final Block MOON_ROCK = new BlockBasic("moon_rock", Material.ROCK);
    public static final Block KRAMAZIA_BLOCK = new BlockBasic("kramazia_block", Material.ROCK);
    public static final Block TROPHY = new BlockTrophy("trophy", Material.IRON);
    public static final Block CHAIR = new BlockChair("chair", Material.WOOD);
    public static final Block RADIO = new BlockRadio("radio", Material.ROCK);
    public static final Block TOTEM = new BlockTotem("totem", Material.ROCK);
    public static final Block MAGICAL_CAULDRON = new BlockMagicalCauldron("magical_cauldron", Material.ROCK);
    public static final Block OILING_MACHINE = new BlockOilingMachine("oiling_machine", Material.ROCK);
    public static final Block FORGE = new BlockForge("forge", Material.ROCK);
    public static final Block MARS_SAND = new BlockMarsSand("mars_sand", Material.SAND);
    public static final Block MARS_ROCK = new BlockBasic("mars_rock", Material.ROCK).setHardness(2);

    public static final Block HARVESTER = new BlockBasic("harvester", Material.WOOD);

    // Fusée
    public static final Block BLOCK_FUSE = new BlockFuse("block_fuse", Material.IRON);
    public static final Block BLOCK_FUSE_T2 = new BlockFuseT2("fuse_t2", Material.IRON);

    // Fluids
    public static final Block OIL = new BlockFluid("oil", FluidInit.OIL, Material.WATER);

    // Vehicles
    public static final Block CAR_1 = new BlockCar("car_1", Material.IRON);

    // Crops
    public static final Block CROP_MYRTILLE = new BlockPlantMyrtille("crop_myrtille", ItemInit.MYRTILLE, 35);
    public static final Block CROP_ORANGE = new BlockPlantOrange("crop_orange", ItemInit.ORANGE, 30);
    public static final Block CROP_KIWI = new BlockPlantKiwi("crop_kiwi", ItemInit.KIWI, 25);

    // Other
    public static final Block ENTITY_KILLER = new BlockEntityKiller("entity_killer", Material.ROCK);
    public static final Block ACTIVATOR = new BlockActivator("activator", Material.IRON);
    public static final Block LANTERN = new BlockLantern("lantern");
    public static final Block SACRIFICE_POOL = new BlockSacrifice("sacrifice_pool", Material.IRON);

    // Eggs
    public static final Block EGG_EARTH = new BlockEgg("egg_earth", Material.ROCK).setHardness(2);
    public static final Block EGG_ICE = new BlockEgg("egg_ice", Material.ROCK).setHardness(2);
    public static final Block EGG_FIRE = new BlockEgg("egg_fire", Material.ROCK).setHardness(2);
    public static final Block INCUBATOR = new BlockIncubator("incubator", Material.WOOD).setHardness(2);
}
