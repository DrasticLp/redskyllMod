package com.drastic.redskyll.objects.blocks.recipes;

import java.util.Map;
import java.util.Map.Entry;

import com.drastic.redskyll.init.BlockInit;
import com.drastic.redskyll.init.FluidInit;
import com.drastic.redskyll.init.ItemInit;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

public class SinteringFurnaceRecipes
{
    private static final SinteringFurnaceRecipes INSTANCE = new SinteringFurnaceRecipes();
    private final Table<ItemStack, ItemStack, ItemStack> smeltingList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
    private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();

    public static SinteringFurnaceRecipes getInstance()
    {
        return INSTANCE;
    }

    private SinteringFurnaceRecipes()
    {
        addSinteringRecipe(new ItemStack(Blocks.DIAMOND_BLOCK), new ItemStack(Blocks.REDSTONE_BLOCK), new ItemStack(ItemInit.REDASIA_INGOT), 5.0F);
        addSinteringRecipe(new ItemStack(BlockInit.REDASIA_BLOCK), new ItemStack(Blocks.OBSIDIAN), new ItemStack(ItemInit.KRAMAZIA_INGOT), 5.0F);
        addSinteringRecipe(new ItemStack(BlockInit.KRAMAZIA_BLOCK), new ItemStack(Items.APPLE), new ItemStack(ItemInit.MYRTILLE), 5.0F);
        addSinteringRecipe(new ItemStack(BlockInit.REDASIA_BLOCK), new ItemStack(Items.APPLE), new ItemStack(ItemInit.ORANGE), 5.0F);
        addSinteringRecipe(new ItemStack(Blocks.EMERALD_BLOCK), new ItemStack(Items.APPLE), new ItemStack(ItemInit.KIWI), 5.0F);
        addSinteringRecipe(new ItemStack(ItemInit.REDASIA_BOOTS), new ItemStack(ItemInit.COMPRESSED_KRAMAZIA), new ItemStack(ItemInit.SULFURITE_BOOTS), 5.0F);
        addSinteringRecipe(new ItemStack(ItemInit.REDASIA_CHESTPLATE), new ItemStack(ItemInit.COMPRESSED_KRAMAZIA), new ItemStack(ItemInit.SULFURITE_CHESTPLATE), 5.0F);
        addSinteringRecipe(new ItemStack(ItemInit.REDASIA_LEGGINGS), new ItemStack(ItemInit.COMPRESSED_KRAMAZIA), new ItemStack(ItemInit.SULFURITE_LEGGINGS), 5.0F);
        addSinteringRecipe(new ItemStack(ItemInit.REDASIA_HELMET), new ItemStack(ItemInit.COMPRESSED_KRAMAZIA), new ItemStack(ItemInit.SULFURITE_HELMET), 5.0F);
        addSinteringRecipe(new ItemStack(ItemInit.REDASIA_SWORD), new ItemStack(ItemInit.COMPRESSED_KRAMAZIA), new ItemStack(ItemInit.SULFURITE_SWORD), 5.0F);
        addSinteringRecipe(new ItemStack(ItemInit.REDASIA_SHOVEL), new ItemStack(ItemInit.COMPRESSED_KRAMAZIA), new ItemStack(ItemInit.SULFURITE_SHOVEL), 5.0F);
        addSinteringRecipe(new ItemStack(ItemInit.REDASIA_PICKAXE), new ItemStack(ItemInit.COMPRESSED_KRAMAZIA), new ItemStack(ItemInit.SULFURITE_PICKAXE), 5.0F);
        addSinteringRecipe(new ItemStack(ItemInit.REDASIA_AXE), new ItemStack(ItemInit.COMPRESSED_KRAMAZIA), new ItemStack(ItemInit.SULFURITE_AXE), 5.0F);
        addSinteringRecipe(new ItemStack(Items.STICK), new ItemStack(ItemInit.REDASIA_INGOT), new ItemStack(ItemInit.WRENCH), 5.0F);
        addSinteringRecipe(new ItemStack(Items.BLAZE_POWDER), new ItemStack(BlockInit.DRAGONITE_ORE), new ItemStack(ItemInit.DRAGONITE_DUST, 4), 5.0F);
        addSinteringRecipe(new ItemStack(BlockInit.KRAMAZIA_BLOCK), new ItemStack(Items.WATER_BUCKET), FluidUtil.getFilledBucket(new FluidStack(FluidInit.OIL, 1000)), 5.0F);
        addSinteringRecipe(new ItemStack(BlockInit.MAGICAL_CAULDRON), new ItemStack(ItemInit.REDASIA_INGOT), new ItemStack(BlockInit.OILING_MACHINE), 5.0F);
        addSinteringRecipe(new ItemStack(ItemInit.KRAMAZIA_CHESTPLATE), new ItemStack(ItemInit.MAGMA_BALL), new ItemStack(ItemInit.JETPACK), 5.0F);
        addSinteringRecipe(new ItemStack(ItemInit.ARMADIUM_INGOT), new ItemStack(ItemInit.MERCURE_MARBLE), new ItemStack(ItemInit.MERIUM_INGOT), 5.0F);
   }

    public void addSinteringRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience)
    {
        if(getSinteringResult(input1, input2) != ItemStack.EMPTY)
            return;
        this.smeltingList.put(input1, input2, result);
        this.experienceList.put(result, Float.valueOf(experience));
    }

    public ItemStack getSinteringResult(ItemStack input1, ItemStack input2)
    {
        for(Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.smeltingList.columnMap().entrySet())
        {
            if(this.compareItemStacks(input1, (ItemStack)entry.getKey()))
            {
                for(Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet())
                {
                    if(this.compareItemStacks(input2, (ItemStack)ent.getKey()))
                    {
                        return (ItemStack)ent.getValue();
                    }
                }
            }
        }
        return ItemStack.EMPTY;
    }

    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

    public Table<ItemStack, ItemStack, ItemStack> getDualSmeltingList()
    {
        return this.smeltingList;
    }

    public float getSinteringExperience(ItemStack stack)
    {
        for(Entry<ItemStack, Float> entry : this.experienceList.entrySet())
        {
            if(this.compareItemStacks(stack, (ItemStack)entry.getKey()))
            {
                return ((Float)entry.getValue()).floatValue();
            }
        }
        return 0.0F;
    }
}
