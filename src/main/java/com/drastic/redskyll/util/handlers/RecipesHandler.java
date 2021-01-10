package com.drastic.redskyll.util.handlers;

import com.drastic.redskyll.init.BlockInit;
import com.drastic.redskyll.init.ItemInit;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipesHandler
{
    public static void init()
    {
        GameRegistry.addSmelting(BlockInit.ORE_REDASIA, new ItemStack(ItemInit.REDASIA_INGOT, 1), 5.0F);
        GameRegistry.addSmelting(BlockInit.ORE_KRAMAZIA, new ItemStack(ItemInit.KRAMAZIA_INGOT, 1), 5.0F);
        GameRegistry.addSmelting(BlockInit.ARMADIUM_ORE, new ItemStack(ItemInit.ARMADIUM_INGOT, 1), 5.0F);   
        GameRegistry.addSmelting(BlockInit.MERCURE_ORE, new ItemStack(ItemInit.MERCURE_MARBLE, 1), 5.0F);   
   }
}
