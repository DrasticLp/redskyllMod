package com.drastic.redskyll.objects.items;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.util.Reference;
import com.drastic.redskyll.util.interfaces.IHasModel;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class ItemColor extends Item implements IHasModel
{
    public ItemColor(String name)
    {
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

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        if(FMLCommonHandler.instance().getSide().isClient())
        {
            playerIn.openGui(Reference.MODID, 1, worldIn, 0, 0, 0);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
