package com.drastic.redskyll.objects.blocks;

import java.util.ArrayList;
import java.util.List;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.init.BlockInit;
import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.objects.tileentities.TileEntityLantern;
import com.drastic.redskyll.util.handlers.EnumHandler;
import com.drastic.redskyll.util.interfaces.IHasModel;
import com.drastic.redskyll.util.interfaces.IMetaName;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLever;
import net.minecraft.block.BlockRedstoneLight;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLantern extends Block implements IHasModel
{
    protected static final AxisAlignedBB LANTERN_AABB = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.6000000238418579D, 0.75D);

    public static final PropertyBool HANGING = PropertyBool.create("hanging");

    private static boolean hanging;

    public BlockLantern(String name)
    {
        super(Material.GLASS);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.REDSKYLL_TAB);
        setHardness(2.0F);
        this.setLightLevel(8.0f);
        //BlockRedstoneLight
        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {this.HANGING});
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
    {

        if(facing == EnumFacing.DOWN)
        {
            // ((TileEntityLantern)world.getTileEntity(pos)).setHanging(true);
            this.setHanging(true);
            return this.getDefaultState().withProperty(this.HANGING, true);
        }
        else
        {
            // ((TileEntityLantern)world.getTileEntity(pos)).setHanging(false);
            this.setHanging(false);
            return this.getDefaultState().withProperty(this.HANGING, false);
        }

    }

    public boolean getHanging()
    {
        return this.hanging;
    }

    public void setHanging(boolean hangingIn)
    {
        this.hanging = hangingIn;
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        if(worldIn.getBlockState(pos.up()).getBlock() != Blocks.AIR)
        {
            return this.getDefaultState().withProperty(this.HANGING, true);
        }
        else
        {
            return this.getDefaultState().withProperty(this.HANGING, false);
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        switch(meta)
        {
            case 0:
                return this.getDefaultState().withProperty(this.HANGING, true);
            case 1:
                return this.getDefaultState().withProperty(this.HANGING, false);
        }
        return this.getDefaultState();
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        if(this.getHanging())
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileEntityLantern();
    }

    @Override
    public boolean hasTileEntity()
    {
        return true;
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRederer(Item.getItemFromBlock(this), 0);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return LANTERN_AABB;
    }

    /*
     * @Override
     * public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
     * {
     * return LANTERN_AABB;
     * }
     */

    @Override
    public boolean isFullBlock(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

}