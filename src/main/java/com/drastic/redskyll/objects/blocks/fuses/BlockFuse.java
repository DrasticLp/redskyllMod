package com.drastic.redskyll.objects.blocks.fuses;

import java.util.Random;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.init.BlockInit;
import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.objects.dimension.CustomTeleporter;
import com.drastic.redskyll.objects.entity.EntityFuseT1;
import com.drastic.redskyll.objects.entity.base.EntityFuseBase;
import com.drastic.redskyll.util.interfaces.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockFuse extends Block implements IHasModel
{
    private static int timer;
    private static boolean isFlying = false;

    public BlockFuse(String name, Material material)
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
    public boolean isOpaqueCube(IBlockState state)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        Block block = iblockstate.getBlock();

        Random random = new Random();

        EntityFuseT1 entitytntprimed = new EntityFuseT1(worldIn, (double)((float)pos.getX() + 0.5F), (double)pos.getY(), (double)((float)pos.getZ() + 0.5F));

        if(!worldIn.isRemote && !isFlying)
        {

            // tpPlayer0(worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 2, false), worldIn);
            worldIn.setBlockToAir(pos);
            worldIn.spawnEntity(entitytntprimed);

        }
        return true;
    }

    private static void tpPlayer0(EntityPlayer playerIn, World worldIn)
    {
        if(!worldIn.isRemote)
            CustomTeleporter.teleportToDimension(playerIn, 0, playerIn.posX, 100, playerIn.posZ);
        isFlying = false;
    }

    private static void tpPlayer2(EntityPlayer playerIn, World worldIn)
    {
        if(!worldIn.isRemote)
            CustomTeleporter.teleportToDimension(playerIn, 2, 0, 100, 0);
        isFlying = false;
    }
}
