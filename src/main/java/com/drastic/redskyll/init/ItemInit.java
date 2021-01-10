package com.drastic.redskyll.init;

import java.util.ArrayList;
import java.util.List;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.objects.items.ItemAthenaSpear;
import com.drastic.redskyll.objects.items.ItemBase;
import com.drastic.redskyll.objects.items.ItemBlueberryJuice;
import com.drastic.redskyll.objects.items.ItemColor;
import com.drastic.redskyll.objects.items.ItemOrange;
import com.drastic.redskyll.objects.items.ItemOrangeJuice;
import com.drastic.redskyll.objects.items.ItemEggDiamond;
import com.drastic.redskyll.objects.items.ItemEggGold;
import com.drastic.redskyll.objects.items.ItemEggIron;
import com.drastic.redskyll.objects.items.ItemFlameThrower;
import com.drastic.redskyll.objects.items.ItemHammer;
import com.drastic.redskyll.objects.items.ItemKiwi;
import com.drastic.redskyll.objects.items.ItemKiwiJuice;
import com.drastic.redskyll.objects.items.ItemMagicHoe;
import com.drastic.redskyll.objects.items.ItemMagicalGelly;
import com.drastic.redskyll.objects.items.ItemManaGiver;
import com.drastic.redskyll.objects.items.ItemMinionDiamond;
import com.drastic.redskyll.objects.items.ItemMinionGold;
import com.drastic.redskyll.objects.items.ItemMinionIron;
import com.drastic.redskyll.objects.items.ItemMoney;
import com.drastic.redskyll.objects.items.ItemMyrtille;
import com.drastic.redskyll.objects.items.ItemParachute;
import com.drastic.redskyll.objects.items.ItemPotionLauncher;
import com.drastic.redskyll.objects.items.ItemRecuperator;
import com.drastic.redskyll.objects.items.ItemScorpionTelson;
import com.drastic.redskyll.objects.items.ItemStaff;
import com.drastic.redskyll.objects.items.ItemWrench;
import com.drastic.redskyll.objects.items.ItemZeusShield;
import com.drastic.redskyll.objects.items.armor.ArmourAnt;
import com.drastic.redskyll.objects.items.armor.ArmourBase;
import com.drastic.redskyll.objects.items.armor.ArmourJetpack;
import com.drastic.redskyll.objects.items.armor.ArmourMod;
import com.drastic.redskyll.objects.items.armor.ArmourScorpion;
import com.drastic.redskyll.objects.items.tools.AxeMod;
import com.drastic.redskyll.objects.items.tools.ItemBowMod;
import com.drastic.redskyll.objects.items.tools.PickMod;
import com.drastic.redskyll.objects.items.tools.SpoonMod;
import com.drastic.redskyll.objects.items.tools.SwordMod;
import com.drastic.redskyll.util.Reference;
import com.drastic.redskyll.util.handlers.SoundsHandler;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSoup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ItemInit
{
    public static final List<Item> ITEMS = new ArrayList<Item>();

    // Ingot
    public static final Item REDASIA_INGOT = new ItemBase("redasia_ingot");
    public static final Item KRAMAZIA_INGOT = new ItemBase("kramazia_ingot");
    public static final Item ARMADIUM_INGOT = new ItemBase("armadium_ingot");
    public static final Item MERCURE_MARBLE = new ItemBase("mercure_marble");
    public static final Item URANIUM_STICK = new ItemBase("uranium_stick");
    public static final Item MERIUM_INGOT = new ItemBase("merium_ingot");
    public static final Item DRAGONITE_DUST = new ItemBase("dragonite_dust");
    public static final Item SCORPION_TELSON = new ItemScorpionTelson("scorpion_telson");

    
    public static final Item COMPRESSED_KRAMAZIA = new ItemBase("compressed_kramazia");
    public static final Item COMPRESSED_DRAGONITE = new ItemBase("compressed_dragonite");
    
    public static final ToolMaterial REDASIA_TOOL = new EnumHelper().addToolMaterial("redasia", 8, 3500, 24, 12.0F, 25);
    public static final ToolMaterial REDASIA_AXE_TOOL = new EnumHelper().addToolMaterial("redasia_axe", 8, 3500, 24, 1.0F, 25);
    public static final ToolMaterial KRAMAZIA_TOOL = new EnumHelper().addToolMaterial("kramazia", 8, 5000, 28, 14.0F, 25);
    public static final ToolMaterial KRAMAZIA_AXE_TOOL = new EnumHelper().addToolMaterial("kramazia_axe", 8, 5000, 28, 1.0F, 25);
    public static final ToolMaterial SULFURITE_TOOL = new EnumHelper().addToolMaterial("sulfurite", 8, 6000, 28, 17.0F, 25);
    public static final ToolMaterial SULFURITE_AXE_TOOL = new EnumHelper().addToolMaterial("sulfurite_axe", 8, 6000, 34, 1.0F, 25);
    public static final ToolMaterial ZIZI = new EnumHelper().addToolMaterial("zizi", 8, 15000000, 55, 18181818181.0F, 5418);

    public static final ArmorMaterial KRAMAZIA_ARMOR = new EnumHelper().addArmorMaterial("armor_kramazia", Reference.MODID + ":kramazia", 950, new int[] {4, 7, 9, 5}, 25, SoundsHandler.AMOR_EQUIP_KRAMAZIA, 9.0f).setRepairItem(new ItemStack(KRAMAZIA_INGOT));
    public static final ArmorMaterial SULFURITE_ARMOR = new EnumHelper().addArmorMaterial("armor_sulfurite", Reference.MODID + ":sulfurite", 1250, new int[] {5, 8, 9, 5}, 25, SoundsHandler.AMOR_EQUIP_KRAMAZIA, 9.0f).setRepairItem(new ItemStack(COMPRESSED_KRAMAZIA));
    public static final ArmorMaterial REDASIA_ARMOR = new EnumHelper().addArmorMaterial("armor_redasia", Reference.MODID + ":redasia", 800, new int[] {4, 6, 9, 5}, 25, SoundsHandler.AMOR_EQUIP_KRAMAZIA, 5.0f).setRepairItem(new ItemStack(REDASIA_INGOT));
    public static final ArmorMaterial SCORPION_ARMOR = new EnumHelper().addArmorMaterial("armor_scorpion", Reference.MODID + ":scorpion", 800, new int[] {4, 6, 9, 5}, 25, SoundsHandler.AMOR_EQUIP_KRAMAZIA, 5.0f).setRepairItem(new ItemStack(SCORPION_TELSON));
    public static final ArmorMaterial ANT_ARMOR = new EnumHelper().addArmorMaterial("armor_ant", Reference.MODID + ":ant", 950, new int[] {2, 7, 9, 2}, 25, SoundsHandler.AMOR_EQUIP_KRAMAZIA, 9.0f);
    public static final ArmorMaterial JETPACK_ARMOR = new EnumHelper().addArmorMaterial("armor_jetpack", Reference.MODID + ":jetpack", 825, new int[] {5, 8, 9, 5}, 25, SoundsHandler.AMOR_EQUIP_KRAMAZIA, 9.0f);
    public static final ArmorMaterial MERIUM_ARMOR = new EnumHelper().addArmorMaterial("merium_sulfurite", Reference.MODID + ":merium", 122, new int[] {6, 9, 10, 6}, 25, SoundsHandler.AMOR_EQUIP_KRAMAZIA, 2.0f).setRepairItem(new ItemStack(MERIUM_INGOT));
    public static final ArmorMaterial ATHENA = new EnumHelper().addArmorMaterial("athena", Reference.MODID + ":athena_helmet", 122, new int[] {1, 1, 1, 1}, 25, SoundsHandler.AMOR_EQUIP_KRAMAZIA, 0.0f);
    public static final ArmorMaterial ZEUS = new EnumHelper().addArmorMaterial("zeus", Reference.MODID + ":zeus_helmet", 122, new int[] {1, 1, 1, 1}, 25, SoundsHandler.AMOR_EQUIP_KRAMAZIA, 0.0f);
    public static final ArmorMaterial THOR = new EnumHelper().addArmorMaterial("thor", Reference.MODID + ":thor_helmet", 122, new int[] {1, 1, 1, 1}, 25, SoundsHandler.AMOR_EQUIP_KRAMAZIA, 0.0f);

    // Tools
    public static final Item MAGICAL_HOE = new ItemMagicHoe("magical_hoe", REDASIA_TOOL);

    public static final Item KRAMAZIA_SWORD = new SwordMod("kramazia_sword", KRAMAZIA_TOOL);
    public static final Item KRAMAZIA_SHOVEL = new SpoonMod("kramazia_shovel", KRAMAZIA_AXE_TOOL);
    public static final Item KRAMAZIA_PICKAXE = new PickMod("kramazia_pickaxe", KRAMAZIA_AXE_TOOL);
    public static final Item KRAMAZIA_AXE = new AxeMod("kramazia_axe", KRAMAZIA_AXE_TOOL);
    public static final Item KRAMZIA_BOW = new ItemBowMod("kramazia_bow");

    public static final Item REDASIA_SWORD = new SwordMod("redasia_sword", REDASIA_TOOL);
    public static final Item REDASIA_SHOVEL = new SpoonMod("redasia_shovel", REDASIA_AXE_TOOL);
    public static final Item REDASIA_PICKAXE = new PickMod("redasia_pickaxe", REDASIA_AXE_TOOL);
    public static final Item REDASIA_AXE = new AxeMod("redasia_axe", REDASIA_AXE_TOOL);

    public static final Item SULFURITE_SWORD = new SwordMod("sulfurite_sword", SULFURITE_TOOL);
    public static final Item SULFURITE_SHOVEL = new SpoonMod("sulfurite_shovel", SULFURITE_AXE_TOOL);
    public static final Item SULFURITE_PICKAXE = new PickMod("sulfurite_pickaxe", SULFURITE_AXE_TOOL);
    public static final Item SULFURITE_AXE = new AxeMod("sulfurite_axe", SULFURITE_AXE_TOOL);

    // Armor
    public static final Item KRAMAZIA_HELMET = new ArmourBase("kramazia_helmet", KRAMAZIA_ARMOR, 1, EntityEquipmentSlot.HEAD);
    public static final Item KRAMAZIA_CHESTPLATE = new ArmourBase("kramazia_chestplate", KRAMAZIA_ARMOR, 1, EntityEquipmentSlot.CHEST);
    public static final Item KRAMAZIA_LEGGINGS = new ArmourBase("kramazia_leggings", KRAMAZIA_ARMOR, 2, EntityEquipmentSlot.LEGS);
    public static final Item KRAMAZIA_BOOTS = new ArmourBase("kramazia_boots", KRAMAZIA_ARMOR, 1, EntityEquipmentSlot.FEET);
    
    public static final Item MERIUM_HELMET = new ArmourBase("merium_helmet", MERIUM_ARMOR, 1, EntityEquipmentSlot.HEAD);
    public static final Item MERIUM_CHESTPLATE = new ArmourBase("merium_chestplate", MERIUM_ARMOR, 1, EntityEquipmentSlot.CHEST);
    public static final Item MERIUM_LEGGINGS = new ArmourBase("merium_leggings", MERIUM_ARMOR, 2, EntityEquipmentSlot.LEGS);
    public static final Item MERIUM_BOOTS = new ArmourBase("merium_boots", MERIUM_ARMOR, 1, EntityEquipmentSlot.FEET);

    public static final Item REDASIA_HELMET = new ArmourBase("redasia_helmet", REDASIA_ARMOR, 1, EntityEquipmentSlot.HEAD);
    public static final Item REDASIA_CHESTPLATE = new ArmourBase("redasia_chestplate", REDASIA_ARMOR, 1, EntityEquipmentSlot.CHEST);
    public static final Item REDASIA_LEGGINGS = new ArmourBase("redasia_leggings", REDASIA_ARMOR, 2, EntityEquipmentSlot.LEGS);
    public static final Item REDASIA_BOOTS = new ArmourBase("redasia_boots", REDASIA_ARMOR, 1, EntityEquipmentSlot.FEET);

    public static final Item SULFURITE_HELMET = new ArmourMod("sulfurite_helmet", SULFURITE_ARMOR, 1, EntityEquipmentSlot.HEAD);
    public static final Item SULFURITE_CHESTPLATE = new ArmourMod("sulfurite_chestplate", SULFURITE_ARMOR, 1, EntityEquipmentSlot.CHEST);
    public static final Item SULFURITE_LEGGINGS = new ArmourMod("sulfurite_leggings", SULFURITE_ARMOR, 2, EntityEquipmentSlot.LEGS);
    public static final Item SULFURITE_BOOTS = new ArmourMod("sulfurite_boots", SULFURITE_ARMOR, 1, EntityEquipmentSlot.FEET);
   
    //public static final Item SCORPION_CHESTPLATE = new ArmourScorpion("scorpion_chestplate", SCORPION_ARMOR, 1, EntityEquipmentSlot.CHEST);
    public static final Item SCORPION_BOOTS = new ArmourScorpion("scorpion_boots", SCORPION_ARMOR, 1, EntityEquipmentSlot.FEET);

    public static final Item JETPACK = new ArmourJetpack("jetpack", JETPACK_ARMOR, EntityEquipmentSlot.CHEST);

    public static final Item ANT_HELMET = new ArmourAnt("ant_helmet", ANT_ARMOR, EntityEquipmentSlot.HEAD);

    // Other
    public static final Item MINION = new ItemMinionDiamond("minion_diamond");
    public static final Item MINION_GOLD = new ItemMinionGold("minion_gold");
    public static final Item MINION_IRON = new ItemMinionIron("minion_iron");
    public static final Item MINION_RECUPERATOR = new ItemRecuperator("minion_recuperator");
    public static final Item STAFF = new ItemStaff("staff");
    public static final Item PARACHUTE = new ItemParachute("parachute");
    public static final Item MONEY = new ItemMoney("money");
    public static final Item POTION_LAUNCHER = new ItemPotionLauncher("potion_launcher");

    // public static final Item FLAME_THROWER = new ItemFlameThrower("flame_thrower");
    public static final Item CHROMA_CRYSTAL = new ItemColor("chroma_crystal");
    public static final Item WRENCH = new ItemWrench("wrench");
    public static final Item MAGMA_BALL = new ItemBase("magma_ball");
    public static final Item ICE_ATTACK = new ItemBase("ice_attack");
    public static final Item EARTH_ATTACK = new ItemBase("earth_attack");

    // Eggs
    public static final Item EGG_DIAMOND = new ItemEggDiamond("egg_diamond");
    public static final Item EGG_GOLD = new ItemEggGold("egg_gold");
    public static final Item EGG_IRON = new ItemEggIron("egg_iron");

    // Admin
    public static final Item MANA_GIVER = new ItemManaGiver("mana_giver");

    // Crops
    public static final Item MYRTILLE = new ItemMyrtille("blueberry", 5, false);
    public static final Item KIWI = new ItemKiwi("kiwi", 5, false);
    public static final Item ORANGE = new ItemOrange("orange", 5, false);

    // Foods
    public static final Item MAGICAL_GELLY = new ItemMagicalGelly("magical_gelly", 5, true);

    // Drinks
    public static final Item KIWI_JUICE = new ItemKiwiJuice("kiwi_juice", 2, false);
    public static final Item BLUEBERRY_JUICE = new ItemBlueberryJuice("blueberry_juice", 2, false);
    public static final Item ORANGE_JUICE = new ItemOrangeJuice("orange_juice", 2, false);
    
    // Powers
    public static final Item THOR_HAMMER = new ItemHammer("hammer");
    public static final Item ZEUS_SHIELD = new ItemZeusShield("zeus_shield");
    public static final Item ATHENA_SPEAR = new ItemAthenaSpear("athena_spear", SULFURITE_TOOL);
    public static final Item KNIFE = new ItemBase("knife").setMaxStackSize(1);
    public static final Item ATHENA_HELMET = new ArmourBase("athena_helmet", ATHENA, 1, EntityEquipmentSlot.HEAD);
    public static final Item ZEUS_HELMET = new ArmourBase("zeus_helmet", ZEUS, 1, EntityEquipmentSlot.HEAD);
    public static final Item THOR_HELMET = new ArmourBase("thor_helmet", THOR, 1, EntityEquipmentSlot.HEAD);
}
