package com.drastic.redskyll.tabs;

import com.drastic.redskyll.init.ItemInit;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabRedskyll extends CreativeTabs
{

    public TabRedskyll(String label)
    {
        super(label);
        this.setBackgroundImageName(label + ".png");
    }

    @Override
    public ItemStack getTabIconItem()
    {
        return new ItemStack(ItemInit.KRAMAZIA_CHESTPLATE);
    }
}
