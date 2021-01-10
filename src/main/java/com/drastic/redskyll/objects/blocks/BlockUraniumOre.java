package com.drastic.redskyll.objects.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.init.BlockInit;
import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.util.handlers.EnumHandler;
import com.drastic.redskyll.util.interfaces.IHasModel;
import com.drastic.redskyll.util.interfaces.IMetaName;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockUraniumOre extends Block implements IHasModel
{

    public BlockUraniumOre(String name, Material material)
    {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.REDSKYLL_TAB);
        setHardness(8.0F);
        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRederer(Item.getItemFromBlock(this), 0);
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        List<ItemStack> list = new ArrayList<ItemStack>();
        Random rand = new Random();
        int random = rand.nextInt(3);
        if(random == 0)
        {
            list.add(new ItemStack(ItemInit.URANIUM_STICK, 4));
        }
        else if(random == 1)
        {
            list.add(new ItemStack(ItemInit.URANIUM_STICK, 3));
        }
        else if(random == 2)
        {
            list.add(new ItemStack(ItemInit.URANIUM_STICK, 2));
        }
        else if(random == 3)
        {
            list.add(new ItemStack(ItemInit.URANIUM_STICK, 1));
        }
        return list;
    }
}