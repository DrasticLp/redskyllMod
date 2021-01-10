package com.drastic.redskyll.objects.items;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.util.interfaces.IHasMinion;
import com.drastic.redskyll.util.interfaces.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemRecuperator extends Item implements IHasModel
{
    public ItemRecuperator(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.REDSKYLL_TAB);
        setMaxStackSize(1);
        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRederer(this, 0);
    }
}
