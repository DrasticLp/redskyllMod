package com.drastic.redskyll.util.handlers;

import java.util.ArrayList;
import java.util.List;

import com.drastic.redskyll.util.Reference;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//@Config(modid = Reference.MODID, name = "itemPriceList", type = Type.INSTANCE)
public class ConfigMod
{
    public static String[] iron_ingot = {"iron_ingot", 75 + "", 45 + "", "" + 265};
    public static String[] diamond = {"diamond", 200 + "", 100 + "", "" + 264};
    public static String[] gold_ingot = {"gold_ingot", 150 + "", 75 + "", "" + 266};
    public static String[] coal = {"coal", 25 + "", 10 + "", "" + 263};
    public static String[] string = {"string", 35 + "", 17 + "", "" + 287};
    public static String[] feather = {"feather", 30 + "", 20 + "", "" + 288};
    public static String[] gunpowder = {"gunpowder", 40 + "", 25 + "", "" + 289};
    public static String[] seeds = {"wheat_seeds", 5 + "", 2 + "", "" + 295};
    public static String[] wheat = {"wheat", 15 + "", 5 + "", "" + 296};
    public static String[] flint = {"flint", 10 + "", 3 + "", "" + 318};
    public static String[] leather = {"leather", 50 + "", 20 + "", "" + 334};
    public static String[] clay = {"clay", 7 + "", 2 + "", "" + 337};
    public static String[] sugar_cane = {"reeds", 7 + "", 3 + "", "" + 338};
    public static String[] slime_ball = {"slime_ball", 45 + "", 22 + "", "" + 341};
    public static String[] glowstone_dust = {"glowstone_dust", 15 + "", 5 + "", "" + 348};
    public static String[] bone = {"bone", 25 + "", 12 + "", "" + 352};
    public static String[] pumpkin_seeds = {"pumpkin_seeds", 15 + "", 7 + "", "" + 361};
    public static String[] beetroot_seeds = {"beetroot_seeds", 15 + "", 5 + "", "" + 435};
    public static String[] melon_seeds = {"melon_seeds", 15 + "", 7 + "", "" + 362};
    public static String[] ender_pearl = {"ender_pearl", 43 + "", 20 + "", "" + 368};
    public static String[] blaze_rod = {"blaze_rod", 38 + "", 25 + "", "" + 369};
    public static String[] emerald = {"emerald", 250 + "", 138 + "", "" + 388};
    public static String[] quartz = {"quartz", 7 + "", 2 + "", "" + 406};
    public static String[] nether_star = {"nether_star", 300 + "", 175 + "", "" + 399};
    public static String[] shulker_shell = {"shulker_shell", 100 + "", 60 + "", "" + 450};
    public static String[] cooked_beef = {"cooked_beef", 5 + "", 2 + "", "" + 364};
    public static String[] golden_apple = {"golden_apple", 800 + "", 350 + "", "" + 322};
    public static String[] beetroot = {"beetroot", 7 + "", 3 + "", "" + 434};
    public static String[] potato = {"potato", 6 + "", 2 + "", "" + 392};
    public static String[] carrot = {"carrot", 7 + "", 2 + "", "" + 391};
    public static String[] melon = {"melon", 15 + "", 2 + "", "" + 360};
    public static String[] pumpkin = {"pumpkin", 30 + "", 5 + "", "" + 86};
    public static String[] name_tag = {"name_tag", 150 + "", 0 + "", "" + 421};
    public static String[] ghast_tear = {"ghast_tear", 183 + "", 52 + "", "" + 370};
    public static String[] magma_cream = {"magma_cream", 72 + "", 29 + "", "" + 378};
    public static String[] rabbit_foot = {"rabbit_foot", 15 + "", 6 + "", "" + 414};
    public static String[] redstone = {"redstone", 4 + "", 1 + "", "" + 331};
    public static String[] cactus = {"cactus", 19 + "", 7 + "", "" + 81};
    public static String[] stone = {"stone", 4 + "", 1 + "", "" + 1};
    public static String[] cobblestone = {"cobblestone", 1 + "", 0 + "", "" + 4};
    public static String[] dirt = {"dirt", 3 + "", 1 + "", "" + 3};
    public static String[] log = {"log", 18 + "", 7 + "", "" + 17};
    public static String[] log2 = {"log2", 18 + "", 7 + "", "" + 162};
    public static String[] obsidian = {"obsidian", 38 + "", 12 + "", "" + 49};
    public static String[] end_stone = {"end_stone", 16 + "", 6 + "", "" + 121};
    public static String[] redasia_ingot = {"redskyll:redasia_ingot", 3250 + "", 689 + "", "" + 4096};
    public static String[] kramazia_ingot = {"redskyll:kramazia_ingot", 10325 + "", 1573 + "", "" + 4097};
    public static String[] moon_rock = {"redskyll:moon_rock", 7 + "", 2 + "", "" + 4125};
    public static String[] blueberry = {"redskyll:blueberry", 7502 + "", 594 + "", "" + 4154};
    public static String[] orange = {"redskyll:orange", 5279 + "", 436 + "", "" + 4134};
    public static String[] kiwi = {"redskyll:kiwi", 2365 + "", 229 + "", "" + 4133};
    public static String[] experience_bottle = {"experience_bottle", 234 + "", 124 + "", "" + 384};
    public static String[] rotten_flesh = {"rotten_flesh", 15 + "", 4 + "", "" + 384};

    public static String[][] prices = {rotten_flesh, experience_bottle, blueberry, orange, kiwi, moon_rock, kramazia_ingot, redasia_ingot, end_stone, obsidian, log2, log, dirt, cobblestone, stone, cactus, redstone, rabbit_foot, magma_cream, ghast_tear, name_tag, pumpkin, melon, carrot, potato, beetroot, golden_apple, cooked_beef, shulker_shell, nether_star, quartz, emerald, blaze_rod, ender_pearl, melon_seeds, beetroot_seeds, pumpkin_seeds, bone, glowstone_dust, iron_ingot, diamond, gold_ingot, coal, string, feather, gunpowder, seeds, wheat, flint, leather, clay, sugar_cane, slime_ball};

}
