package com.drastic.redskyll.objects.blocks;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.init.BlockInit;
import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.util.SeatUtil;
import com.drastic.redskyll.util.interfaces.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityStructureRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.command.CommandKill;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockEntityKiller extends Block implements IHasModel
{
    private static boolean isParticles = true;

    public static final PropertyDirection FACING = BlockHorizontal.FACING;

    public static final AxisAlignedBB FAABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 2.0D, 1.0D);

    public BlockEntityKiller(String name, Material material)
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
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {      
        AxisAlignedBB[] liste = {FAABB};
        addCollisionBoxToList(pos, FAABB, Arrays.asList(liste), FAABB);

        this.setDefaultFacing(worldIn, pos, state);
    }

    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state)
    {
        if(!worldIn.isRemote)
        {
            IBlockState iblockstate = worldIn.getBlockState(pos.north());
            IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
            IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
            IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);

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

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if(enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    @Override
    public boolean hasComparatorInputOverride(IBlockState state)
    {
        return true;
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return FAABB;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return FAABB;
    }
    
    @Override
    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return SeatUtil.isSomeoneSitting(worldIn, pos.getX(), pos.getY(), pos.getZ()) ? 1 : 0;
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }
    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    public boolean isFullCube(IBlockState state)
    {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    public boolean isFullBlock(IBlockState state)
    {
        // TODO Auto-generated method stub
        return false;
    }
    
    private void killEntitiesWithoutParticles(World worldIn, BlockPos pos, AxisAlignedBB aabb)
    {
        List e = worldIn.getEntitiesWithinAABB(EntityMob.class, aabb);

        if(e.size() > 0)
        {
            for(int i = 0; i <= e.size() - 1; i++)
            {
                EntityMob em = (EntityMob)e.get(i);
                em.onKillCommand();
                
            }
        }
    }
    
    private void killEntities(World worldIn, BlockPos pos, AxisAlignedBB aabb)
    {
        List e = worldIn.getEntitiesWithinAABB(EntityMob.class, aabb);

        if(e.size() > 0)
        {
            for(int i = 0; i <= e.size() - 1; i++)
            {
                EntityMob em = (EntityMob)e.get(i);
                em.onKillCommand();
                
            }
        }
        
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX, aabb.minY, aabb.minZ, 0, 0, 0, 0);

        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.maxX, aabb.minY, aabb.minZ, 0, 0, 0, 0);

        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX + 1, aabb.minY, aabb.minZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX + 2, aabb.minY, aabb.minZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX + 3, aabb.minY, aabb.minZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX + 4, aabb.minY, aabb.minZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX + 5, aabb.minY, aabb.minZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX + 6, aabb.minY, aabb.minZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX + 7, aabb.minY, aabb.minZ, 0, 0, 0, 0);

        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.maxX, aabb.minY, aabb.minZ + 1, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.maxX, aabb.minY, aabb.minZ + 2, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.maxX, aabb.minY, aabb.minZ + 3, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.maxX, aabb.minY, aabb.minZ + 4, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.maxX, aabb.minY, aabb.minZ + 5, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.maxX, aabb.minY, aabb.minZ + 6, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.maxX, aabb.minY, aabb.minZ + 7, 0, 0, 0, 0);

        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX + 1, aabb.minY, aabb.maxZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX + 2, aabb.minY, aabb.maxZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX + 3, aabb.minY, aabb.maxZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX + 4, aabb.minY, aabb.maxZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX + 5, aabb.minY, aabb.maxZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX + 6, aabb.minY, aabb.maxZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX + 7, aabb.minY, aabb.maxZ, 0, 0, 0, 0);

        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX, aabb.minY, aabb.minZ + 1, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX, aabb.minY, aabb.minZ + 2, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX, aabb.minY, aabb.minZ + 3, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX, aabb.minY, aabb.minZ + 4, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX, aabb.minY, aabb.minZ + 5, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX, aabb.minY, aabb.minZ + 6, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX, aabb.minY, aabb.minZ + 7, 0, 0, 0, 0);

        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX + 1, aabb.maxY, aabb.minZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX + 2, aabb.maxY, aabb.minZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX + 3, aabb.maxY, aabb.minZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX + 4, aabb.maxY, aabb.minZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX + 5, aabb.maxY, aabb.minZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX + 6, aabb.maxY, aabb.minZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX + 7, aabb.maxY, aabb.minZ, 0, 0, 0, 0);

        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.maxX, aabb.maxY, aabb.minZ + 1, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.maxX, aabb.maxY, aabb.minZ + 2, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.maxX, aabb.maxY, aabb.minZ + 3, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.maxX, aabb.maxY, aabb.minZ + 4, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.maxX, aabb.maxY, aabb.minZ + 5, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.maxX, aabb.maxY, aabb.minZ + 6, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.maxX, aabb.maxY, aabb.minZ + 7, 0, 0, 0, 0);

        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX + 1, aabb.maxY, aabb.maxZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX + 2, aabb.maxY, aabb.maxZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX + 3, aabb.maxY, aabb.maxZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX + 4, aabb.maxY, aabb.maxZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX + 5, aabb.maxY, aabb.maxZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX + 6, aabb.maxY, aabb.maxZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX + 7, aabb.maxY, aabb.maxZ, 0, 0, 0, 0);

        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX, aabb.maxY, aabb.minZ + 1, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX, aabb.maxY, aabb.minZ + 2, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX, aabb.maxY, aabb.minZ + 3, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX, aabb.maxY, aabb.minZ + 4, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX, aabb.maxY, aabb.minZ + 5, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX, aabb.maxY, aabb.minZ + 6, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX, aabb.maxY, aabb.minZ + 7, 0, 0, 0, 0);

        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX, aabb.minY, aabb.maxZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.maxX, aabb.minY, aabb.maxZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX, aabb.maxY, aabb.minZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.maxX, aabb.maxY, aabb.minZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX, aabb.maxY, aabb.maxZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.maxX, aabb.maxY, aabb.maxZ, 0, 0, 0, 0);

        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.maxX, aabb.minY + 1, aabb.maxZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.maxX, aabb.minY + 2, aabb.maxZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.maxX, aabb.minY + 3, aabb.maxZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.maxX, aabb.minY + 4, aabb.maxZ, 0, 0, 0, 0);

        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX, aabb.minY + 1, aabb.maxZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX, aabb.minY + 2, aabb.maxZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX, aabb.minY + 3, aabb.maxZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX, aabb.minY + 4, aabb.maxZ, 0, 0, 0, 0);

        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX, aabb.minY + 1, aabb.minZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX, aabb.minY + 2, aabb.minZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX, aabb.minY + 3, aabb.minZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.minX, aabb.minY + 4, aabb.minZ, 0, 0, 0, 0);

        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.maxX, aabb.minY + 1, aabb.minZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.maxX, aabb.minY + 2, aabb.minZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.maxX, aabb.minY + 3, aabb.minZ, 0, 0, 0, 0);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, aabb.maxX, aabb.minY + 4, aabb.minZ, 0, 0, 0, 0);
    }
    
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        AxisAlignedBB aabb = new AxisAlignedBB(pos.getX() - 3, pos.getY(), pos.getZ() - 3, (pos.getX() + 4), (pos.getY() + 5), (pos.getZ() + 4));

        killEntities(worldIn, pos, aabb);
        return true;

    }
    
    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if(worldIn.isBlockPowered(pos) || worldIn.isBlockPowered(pos.up()))
        {
            AxisAlignedBB aabb = new AxisAlignedBB(pos.getX() - 3, pos.getY(), pos.getZ() - 3, (pos.getX() + 4), (pos.getY() + 5), (pos.getZ() + 4));

            killEntitiesWithoutParticles(worldIn, pos, aabb);
        }
    }
}