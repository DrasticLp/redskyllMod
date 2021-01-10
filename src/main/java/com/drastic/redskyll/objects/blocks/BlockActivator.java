package com.drastic.redskyll.objects.blocks;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Random;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.init.BlockInit;
import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.objects.items.ItemWrench;
import com.drastic.redskyll.objects.tileentities.TileEntityActivator;
import com.drastic.redskyll.util.SeatUtil;
import com.drastic.redskyll.util.handlers.EnumHandler;
import com.drastic.redskyll.util.interfaces.IHasModel;
import com.drastic.redskyll.util.interfaces.IMetaName;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChorusFlower;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockRedstoneDiode;
import net.minecraft.block.BlockRedstoneLight;
import net.minecraft.block.BlockRedstoneRepeater;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockActivator extends Block implements IHasModel
{
    private boolean canPower = false;

    public static final PropertyInteger BLOCK_TYPE = PropertyInteger.create("power", 0, 6);
    public static final PropertyDirection FACING = BlockHorizontal.FACING;

    private static int type = 0;

    public BlockActivator(String name, Material material)
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
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        this.setDefaultFacing(worldIn, pos, state);
        this.setDefaultState(this.blockState.getBaseState().withProperty(this.BLOCK_TYPE, 0));
    }

    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state)
    {
        if(!worldIn.isRemote)
        {
            IBlockState iblockstate = worldIn.getBlockState(pos.north());
            IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
            IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
            IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
            EnumFacing enumfacing = (EnumFacing)state.getValue(this.FACING);

            if(enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock())
            {
                enumfacing = EnumFacing.SOUTH;
            }
            else if(enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock())
            {
                enumfacing = EnumFacing.NORTH;
            }
            else if(enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock())
            {
                enumfacing = EnumFacing.EAST;
            }
            else if(enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock())
            {
                enumfacing = EnumFacing.WEST;
            }

            worldIn.setBlockState(pos, state.withProperty(this.FACING, enumfacing).withProperty(this.BLOCK_TYPE, 0), 2);
        }
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(this.FACING, placer.getHorizontalFacing().getOpposite()).withProperty(this.BLOCK_TYPE, 0);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        //TileEntityActivator machine = (TileEntityActivator)worldIn.getTileEntity(pos);;
        //machine.setType(0);
        worldIn.setBlockState(pos, state.withProperty(this.FACING, placer.getHorizontalFacing().getOpposite()).withProperty(this.BLOCK_TYPE, 0), 2);
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(this.FACING, rot.rotate((EnumFacing)state.getValue(this.FACING)));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(this.FACING)).getIndex();
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if(enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(this.FACING, enumfacing);
    }

    @Override
    public boolean hasComparatorInputOverride(IBlockState state)
    {
        return true;
    }

    @Override
    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return SeatUtil.isSomeoneSitting(worldIn, pos.getX(), pos.getY(), pos.getZ()) ? 1 : 0;
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(this.FACING)));
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {this.FACING, this.BLOCK_TYPE});
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullBlock(IBlockState state)
    {
        return false;
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRederer(Item.getItemFromBlock(this), 0);
    }

    @Override
    public boolean canProvidePower(IBlockState state)
    {
        return this.canPower;
    }

    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        if(!this.canPower)
        {
            return 15;
        }
        else
        {
            return 15;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    /*@Override
    public boolean hasTileEntity()
    {
        return true;
    }*/

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        //TileEntityActivator tE = (TileEntityActivator)worldIn.getTileEntity(pos);
        //tE.setType(this.type);
        // System.out.println(tE.getType());
        return state.withProperty(FACING, (EnumFacing)state.getValue(this.FACING)).withProperty(this.BLOCK_TYPE, this.type);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        Item item = playerIn.getHeldItem(hand).getItem();
        if(item instanceof ItemWrench)
        {
            //TileEntityActivator tE = (TileEntityActivator)worldIn.getTileEntity(pos);
            this.type = ((ItemWrench)item).getType();
            worldIn.setBlockState(pos, this.getDefaultState().withProperty(this.FACING, (EnumFacing)state.getValue(this.FACING)).withProperty(this.BLOCK_TYPE, this.type));
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean requiresUpdates()
    {
        return true;
    }

    @Override
    public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return !this.canPower ? 0 : blockState.getWeakPower(blockAccess, pos, side);
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        //super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
        if(worldIn.isBlockPowered(pos.west()))
        {
            this.canPower = true;
        }
        else
        {
            this.canPower = false;
        }
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        //super.updateTick(worldIn, pos, state, rand);
        //TileEntityActivator tE = (TileEntityActivator)worldIn.getTileEntity(pos);
        worldIn.setBlockState(pos, this.getDefaultState().withProperty(this.FACING, (EnumFacing)state.getValue(this.FACING)).withProperty(this.BLOCK_TYPE, this.type));
    }

    /*@Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityActivator();
    }*/
}