package com.drastic.redskyll.objects.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.init.BlockInit;
import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.util.interfaces.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPlantOrange extends BlockCrops implements IHasModel
{
    private static Item drop;
    private static int chance;

    private static final AxisAlignedBB[] cannabis = new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.1875D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.4375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5625D, 1.0D)};

    public BlockPlantOrange(String name, Item itemIn, int dropChanceIn)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        this.drop = itemIn;
        this.chance = dropChanceIn;
        // setCreativeTab(Main.REDSKYLL_TAB);
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
        return list;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        Random rand = new Random();

        if(this.isMaxAge(state) && !worldIn.isRemote)
        {
            worldIn.setBlockState(pos, this.withAge(0));
            if(rand.nextInt(this.chance) == 0)
            {
                if(rand.nextInt(5) == 0)
                {
                    worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ItemInit.ORANGE, 3)));
                }
                else if(rand.nextInt(5) == 1 || rand.nextInt(5) == 2)
                {
                    worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ItemInit.ORANGE, 2)));
                }
                else
                {
                    worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ItemInit.ORANGE, 1)));
                }
                return true;
            }
            else
            {
                if(rand.nextInt(3) == 0)
                {
                    if(rand.nextInt(5) == 0)
                    {
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.BEETROOT, 3)));
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.BEETROOT_SEEDS, 1)));

                    }
                    else if(rand.nextInt(5) == 1 || rand.nextInt(5) == 2)
                    {
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.BEETROOT, 2)));
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.BEETROOT_SEEDS, 1)));
                    }
                    else
                    {
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.BEETROOT, 1)));
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.BEETROOT_SEEDS, 1)));
                    }

                }
                else if(rand.nextInt(3) == 1)
                {
                    if(rand.nextInt(5) == 0)
                    {
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.POTATO, 4)));
                    }
                    else if(rand.nextInt(5) == 1 || rand.nextInt(5) == 2)
                    {
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.POTATO, 3)));
                    }
                    else
                    {
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.POTATO, 2)));
                    }
                }
                else if(rand.nextInt(3) == 2)
                {
                    if(rand.nextInt(5) == 0)
                    {
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.CARROT, 4)));
                    }
                    else if(rand.nextInt(5) == 1 || rand.nextInt(5) == 2)
                    {
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.CARROT, 3)));
                    }
                    else
                    {
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.CARROT, 2)));
                    }
                }
                else if(rand.nextInt(3) == 3)
                {
                    if(rand.nextInt(5) == 0)
                    {
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.WHEAT, 3)));
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.WHEAT_SEEDS, 1)));

                    }
                    else if(rand.nextInt(5) == 1 || rand.nextInt(5) == 2)
                    {
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.WHEAT, 2)));
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.WHEAT_SEEDS, 1)));
                    }
                    else
                    {
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.WHEAT, 1)));
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.WHEAT_SEEDS, 1)));
                    }
                }
                return true;

            }
        }
        return false;
    }

    @Override
    protected Item getSeed()
    {
        // TODO Auto-generated method stub
        return this.drop;
    }

    @Override
    protected Item getCrop()
    {
        // TODO Auto-generated method stub
        return this.drop;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return this.cannabis[((Integer)state.getValue(this.getAgeProperty())).intValue()];
    }

    protected static float getGrowthingChance(Block blockIn, World worldIn, BlockPos pos)
    {
        float f = 1.0f;;

        BlockPos blockpos = pos.down();

        for(int i = -1; i <= 1; ++i)
        {
            if(worldIn.getBlockState(pos.up()).getBlock() == BlockInit.HARVESTER)
            {
                f = 15.0f;
            }

            for(int j = -1; j <= 1; ++j)
            {
                float f1 = 0.0F;
                IBlockState iblockstate = worldIn.getBlockState(blockpos.add(i, 0, j));

                if(iblockstate.getBlock().canSustainPlant(iblockstate, worldIn, blockpos.add(i, 0, j), net.minecraft.util.EnumFacing.UP, (net.minecraftforge.common.IPlantable)blockIn))
                {
                    f1 = 1.0F;

                    if(iblockstate.getBlock().isFertile(worldIn, blockpos.add(i, 0, j)))
                    {
                        if(worldIn.getBlockState(pos.up()).getBlock() == BlockInit.HARVESTER)
                        {
                            f1 = 15.0f;
                        }
                        else
                        {
                            f1 = 3.0F;
                        }
                    }
                    else if(worldIn.getBlockState(pos.up()).getBlock() == BlockInit.HARVESTER)
                    {
                        f1 = 4.0f;
                    }
                }

                if(i != 0 || j != 0)
                {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        BlockPos blockpos1 = pos.north();
        BlockPos blockpos2 = pos.south();
        BlockPos blockpos3 = pos.west();
        BlockPos blockpos4 = pos.east();
        boolean flag = blockIn == worldIn.getBlockState(blockpos3).getBlock() || blockIn == worldIn.getBlockState(blockpos4).getBlock();
        boolean flag1 = blockIn == worldIn.getBlockState(blockpos1).getBlock() || blockIn == worldIn.getBlockState(blockpos2).getBlock();

        if(flag && flag1)
        {
            f /= 2.0F;
        }
        else
        {
            boolean flag2 = blockIn == worldIn.getBlockState(blockpos3.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.south()).getBlock() || blockIn == worldIn.getBlockState(blockpos3.south()).getBlock();

            if(flag2)
            {
                f /= 2.0F;
            }
        }

        return f;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if(!worldIn.isAreaLoaded(pos, 1))
            return;

        int i = this.getAge(state);

        if(i < this.getMaxAge())
        {
            float f = getGrowthingChance(this, worldIn, pos);

            if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt((int)(25.0F / f) + 1) == 0))
            {
                worldIn.setBlockState(pos, this.withAge(i + 1), 2);
                net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
            }
        }

    }

    @Override
    public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state)
    {
        Random rand = new Random();

        if(this.isMaxAge(state) && !worldIn.isRemote)
        {
            if(rand.nextInt(this.chance) == 0)
            {
                if(rand.nextInt(5) == 0)
                {
                    worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ItemInit.MYRTILLE, 4)));
                }
                else if(rand.nextInt(5) == 1 || rand.nextInt(5) == 2)
                {
                    worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ItemInit.MYRTILLE, 3)));
                }
                else
                {
                    worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ItemInit.MYRTILLE, 2)));
                }
            }
            else
            {
                if(rand.nextInt(3) == 0)
                {
                    if(rand.nextInt(5) == 0)
                    {
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.BEETROOT, 4)));
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.BEETROOT_SEEDS, 2)));

                    }
                    else if(rand.nextInt(5) == 1 || rand.nextInt(5) == 2)
                    {
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.BEETROOT, 3)));
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.BEETROOT_SEEDS, 2)));
                    }
                    else
                    {
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.BEETROOT, 2)));
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.BEETROOT_SEEDS, 2)));
                    }
                }
                else if(rand.nextInt(3) == 1)
                {
                    if(rand.nextInt(5) == 0)
                    {
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.POTATO, 5)));
                    }
                    else if(rand.nextInt(5) == 1 || rand.nextInt(5) == 2)
                    {
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.POTATO, 4)));
                    }
                    else
                    {
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.POTATO, 3)));
                    }
                }
                else if(rand.nextInt(3) == 2)
                {
                    if(rand.nextInt(5) == 0)
                    {
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.CARROT, 5)));
                    }
                    else if(rand.nextInt(5) == 1 || rand.nextInt(5) == 2)
                    {
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.CARROT, 4)));
                    }
                    else
                    {
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.CARROT, 3)));
                    }
                }
                else if(rand.nextInt(3) == 3)
                {
                    if(rand.nextInt(5) == 0)
                    {
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.WHEAT, 4)));
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.WHEAT_SEEDS, 2)));

                    }
                    else if(rand.nextInt(5) == 1 || rand.nextInt(5) == 2)
                    {
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.WHEAT, 3)));
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.WHEAT_SEEDS, 2)));
                    }
                    else
                    {
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.WHEAT, 2)));
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.WHEAT_SEEDS, 3)));
                    }
                }
            }
        }
    }
}
