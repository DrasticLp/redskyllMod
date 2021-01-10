package com.drastic.redskyll.objects.items.tools;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.util.interfaces.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemPickaxe;

public class PickMod extends ItemPickaxe implements IHasModel
{

    public PickMod(String name, ToolMaterial material)
    {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.REDSKYLL_TAB);
        ItemInit.ITEMS.add(this);
    }
    
    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRederer(this, 0);
    }
    
}
