package com.drastic.redskyll.objects.blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.init.BlockInit;
import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.util.interfaces.IHasModel;
import com.drastic.redskyll.util.interfaces.IRelic;
import com.drastic.redskyll.util.provider.RelicProvider;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSacrifice extends Block implements IHasModel
{
    public static final PropertyInteger LEVEL = PropertyInteger.create("level", 0, 1);

    private static final AxisAlignedBB AABB_LEGS = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0625, 1.0D);
    private static final AxisAlignedBB AABB_WALL_NORTH = new AxisAlignedBB(0.0D, 0.625D, 0.0D, 1.0D, 0.375D, 0.125D);
    private static final AxisAlignedBB AABB_WALL_SOUTH = new AxisAlignedBB(0.0D, 0.625D, 0.875D, 1.0D, 0.375D, 1.0D);
    private static final AxisAlignedBB AABB_WALL_EAST = new AxisAlignedBB(0.875D, 0.625D, 0.0D, 1.0D, 0.375D, 1.0D);
    private static final AxisAlignedBB AABB_WALL_WEST = new AxisAlignedBB(0.0D, 0.625D, 0.0D, 0.125D, 0.375D, 1.0D);

    private static final AxisAlignedBB AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.4375D, 1.0D);
    private boolean isFilled;
    private Divinity divinity_ = Divinity.NONE;

    public BlockSacrifice(String name, Material material)
    {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.REDSKYLL_TAB);
        setHardness(2.0F);
        this.isFilled = false;
        this.setDefaultState(this.blockState.getBaseState().withProperty(this.LEVEL, Integer.valueOf(0)));
        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRederer(Item.getItemFromBlock(this), 0);
    }

    public Divinity getDivinity()
    {
        return this.divinity_;
    }

    public void setDivinity(Divinity d)
    {
        this.divinity_ = d;
    }

    public int getMetaFromState(IBlockState state)
    {
        return ((Integer)state.getValue(this.LEVEL)).intValue();
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {this.LEVEL});
    }

    public boolean hasComparatorInputOverride(IBlockState state)
    {
        return true;
    }

    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return ((Integer)blockState.getValue(this.LEVEL)).intValue();
    }

    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(this.LEVEL, Integer.valueOf(meta));
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState)
    {
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_LEGS);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_WEST);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_NORTH);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_EAST);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_SOUTH);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return AABB;
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
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
        return true;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(!worldIn.isRemote)
        {
            if(playerIn.getHeldItem(hand).getItem() == ItemInit.KNIFE)
            {
                if(playerIn.inventory.hasItemStack(new ItemStack(ItemInit.THOR_HAMMER)) || playerIn.inventory.hasItemStack(new ItemStack(ItemInit.ATHENA_SPEAR)) || playerIn.inventory.hasItemStack(new ItemStack(ItemInit.ZEUS_SHIELD)))
                {
                    IRelic relic = (playerIn).getCapability(RelicProvider.RELIC_CAP, null);
                    
                    playerIn.getHeldItem(hand).shrink(1);
                    playerIn.attackEntityFrom(DamageSource.MAGIC, 15);
                    this.setBloodLevel(worldIn, pos, state, 1);
                    if(playerIn.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == ItemInit.ATHENA_HELMET)
                    {
                        this.setDivinity(Divinity.ATHENA);
                        for(int i = 0; i < 35; i++)
                        {
                            if(playerIn.inventory.getStackInSlot(i).getItem().equals(ItemInit.ATHENA_SPEAR) || playerIn.inventory.getStackInSlot(i).getItem().equals(ItemInit.ZEUS_SHIELD) || playerIn.inventory.getStackInSlot(i).getItem().equals(ItemInit.THOR_HAMMER))
                            {
                                playerIn.inventory.getStackInSlot(i).shrink(1);
                                playerIn.addItemStackToInventory(new ItemStack(ItemInit.ATHENA_SPEAR));
                            }
                        }
                    }
                    else if(playerIn.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == ItemInit.THOR_HELMET)
                    {
                        this.setDivinity(Divinity.THOR);
                        for(int i = 0; i < 35; i++)
                        {
                            if(playerIn.inventory.getStackInSlot(i).getItem().equals(ItemInit.ATHENA_SPEAR) || playerIn.inventory.getStackInSlot(i).getItem().equals(ItemInit.ZEUS_SHIELD) || playerIn.inventory.getStackInSlot(i).getItem().equals(ItemInit.THOR_HAMMER))
                            {
                                playerIn.inventory.getStackInSlot(i).shrink(1);
                                playerIn.addItemStackToInventory(new ItemStack(ItemInit.THOR_HAMMER));
                            }
                        }
                    }
                    else if(playerIn.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == ItemInit.ZEUS_HELMET)
                    {
                        this.setDivinity(Divinity.ZEUS);
                        for(int i = 0; i < 35; i++)
                        {
                            if(playerIn.inventory.getStackInSlot(i).getItem().equals(ItemInit.ATHENA_SPEAR) || playerIn.inventory.getStackInSlot(i).getItem().equals(ItemInit.ZEUS_SHIELD) || playerIn.inventory.getStackInSlot(i).getItem().equals(ItemInit.THOR_HAMMER))
                            {
                                playerIn.inventory.getStackInSlot(i).shrink(1);
                                playerIn.addItemStackToInventory(new ItemStack(ItemInit.ZEUS_SHIELD));
                            }
                        }
                    }
                    playerIn.sendMessage(new TextComponentTranslation("text.release"));
                    playerIn.sendMessage(new TextComponentTranslation("text.newgod").appendSibling(new TextComponentString(TextFormatting.GREEN + " " + this.getDivinity().name())));
                    int i = this.getDivinity() == Divinity.ATHENA ? 2 : this.getDivinity() == Divinity.ZEUS ? 1 : 0;
                    relic.set(i);

                }
            }
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    public void setBloodLevel(World worldIn, BlockPos pos, IBlockState state, int level)
    {
        worldIn.setBlockState(pos, state.withProperty(this.LEVEL, Integer.valueOf(level)), 2);
        worldIn.updateComparatorOutputLevel(pos, this);
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        if(face == EnumFacing.UP)
        {
            return BlockFaceShape.BOWL;
        }
        else
        {
            return face == EnumFacing.DOWN ? BlockFaceShape.UNDEFINED : BlockFaceShape.SOLID;
        }
    }

    private enum Divinity
    {
        ATHENA,
        ZEUS,
        THOR,
        NONE
    }
}