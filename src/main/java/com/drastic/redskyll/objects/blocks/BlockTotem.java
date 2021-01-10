package com.drastic.redskyll.objects.blocks;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.init.BlockInit;
import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.objects.entity.EntityRedskyll;
import com.drastic.redskyll.util.interfaces.IHasBoss;
import com.drastic.redskyll.util.interfaces.IHasModel;
import com.drastic.redskyll.util.interfaces.IMana;
import com.drastic.redskyll.util.provider.BossProvider;
import com.drastic.redskyll.util.provider.ManaProvider;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class BlockTotem extends Block implements IHasModel
{
    public static final PropertyDirection FACING = BlockHorizontal.FACING;

    public BlockTotem(String name, Material material)
    {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.REDSKYLL_TAB);
        setHardness(4.0F);
        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRederer(Item.getItemFromBlock(this), 0);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        IHasBoss hasMinion = playerIn.getCapability(BossProvider.BOSS_CAP, null);
        IMana mana = playerIn.getCapability(ManaProvider.MANA_CAP, null);

        if(playerIn.getHeldItem(hand).getItem() == Item.getItemFromBlock(BlockInit.KRAMAZIA_BLOCK) && hasMinion.get() == 0 && mana.getMana() == 20)
        {
            if(!worldIn.isRemote)
            {
                // System.out.println("good");
                if(pos.getX() == 0 && pos.getZ() == 0 && playerIn.dimension == 2)
                {
                    EntityRedskyll entity = new EntityRedskyll(worldIn, pos.getX(), pos.getY(), pos.getZ());
                    entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ItemInit.KRAMAZIA_SWORD));
                    entity.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ItemInit.KRAMAZIA_HELMET));
                    entity.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ItemInit.KRAMAZIA_CHESTPLATE));
                    entity.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(Items.DIAMOND_LEGGINGS));
                    entity.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(Items.DIAMOND_BOOTS));
                    playerIn.sendMessage(new TextComponentString(TextFormatting.GREEN + "Vous venez de signer votre arret de mort..."));
                    worldIn.setBlockToAir(pos);
                    worldIn.createExplosion(playerIn, pos.getX(), pos.getY(), pos.getZ(), 1, true);
                    playerIn.getHeldItem(hand).shrink(1);
                    worldIn.spawnEntity(entity);
                    hasMinion.set(1);
                    mana.setMana(0);
                }
                else
                {
                    if(!worldIn.isRemote)
                        playerIn.sendMessage(new TextComponentString(TextFormatting.RED + "Vous ne pouvez pas invoquer ce DEMON ici..."));
                }

            }
            else
            {
                // System.out.println("nuuuuuuuuuuuul");
            }

            return true;
        }
        else if(mana.getMana() != 20 && playerIn.getHeldItem(hand).getItem() == Item.getItemFromBlock(BlockInit.KRAMAZIA_BLOCK))
        {
            playerIn.sendMessage(new TextComponentString(TextFormatting.RED + "Vous n'avez pas assez de " + TextFormatting.BLUE + "MANA"));
            return true;
        }
        else if(!worldIn.isRemote && playerIn.getHeldItem(hand).getItem() == Item.getItemFromBlock(BlockInit.KRAMAZIA_BLOCK) && hasMinion.get() != 0)
        {
            worldIn.setBlockToAir(pos);
            worldIn.createExplosion(playerIn, pos.getX(), pos.getY(), pos.getZ(), 1, true);
            playerIn.sendMessage(new TextComponentString("Désolé, vous avez déjà eu votre Boss"));
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
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
    public boolean isFullCube(IBlockState state)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
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
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }
}