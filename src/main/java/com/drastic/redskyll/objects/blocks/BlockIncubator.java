package com.drastic.redskyll.objects.blocks;

import java.util.Random;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.init.BlockInit;
import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.objects.entity.EntityBabyEarthDragon;
import com.drastic.redskyll.objects.entity.EntityBabyFireDragon;
import com.drastic.redskyll.objects.entity.EntityBabyIceDragon;
import com.drastic.redskyll.util.enums.EnumDragonType;
import com.drastic.redskyll.util.interfaces.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.SoundSetupEvent;

public class BlockIncubator extends Block implements IHasModel
{
    private static EnumDragonType dragonType = EnumDragonType.NONE;
    private static int timer;

    private static World world;
    private static BlockPos pos;

    public BlockIncubator(String name, Material material)
    {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.REDSKYLL_TAB);
        setHardness(8.0F);
        this.setTickRandomly(true);
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
        super.onBlockAdded(worldIn, pos, state);
        this.world = worldIn;
        this.pos = pos;
    }

    public EnumDragonType getDragonType()
    {
        return this.dragonType;
    }

    public void setDragonType(EnumDragonType typeIn)
    {
        this.dragonType = typeIn;
    }

    @Override
    public boolean requiresUpdates()
    {
        return true;
    }

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

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        this.timer = 60 * 4;
        worldIn.scheduleBlockUpdate(pos, state.getBlock(), 10, 100);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        super.updateTick(worldIn, pos, state, rand);
        // System.out.println(this.getDragonType());
        // System.out.println(this.timer);
        if(this.getDragonType() == EnumDragonType.NONE)
        {
            worldIn.spawnParticle(EnumParticleTypes.PORTAL, pos.getX(), pos.getY() + 2, pos.getZ(), 0, 0, 0, 0);
        }
        else if(this.getDragonType() == EnumDragonType.EARTH)
        {
            worldIn.spawnParticle(EnumParticleTypes.SLIME, pos.getX(), pos.getY() + 2, pos.getZ(), 0, 0, 0, 0);

            if(this.timer > 1)
            {
                this.timer--;
            }
            if(this.timer == 1)
            {
                worldIn.setBlockToAir(pos.up());
                worldIn.spawnEntity(new EntityBabyEarthDragon(worldIn, pos.getX(), pos.getY(), pos.getZ()));
                worldIn.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 0.1f, true);

                worldIn.setBlockToAir(pos);
            }

        }
        else if(this.getDragonType() == EnumDragonType.FIRE)
        {
            worldIn.spawnParticle(EnumParticleTypes.FLAME, pos.getX(), pos.getY() + 2, pos.getZ(), 0, 0, 0, 0);

            if(this.timer > 1)
            {
                this.timer--;
            }
            if(this.timer == 1)
            {
                worldIn.setBlockToAir(pos.up());
                worldIn.spawnEntity(new EntityBabyFireDragon(worldIn, pos.getX(), pos.getY(), pos.getZ()));
                worldIn.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 0.1f, true);

                worldIn.setBlockToAir(pos);
            }

        }
        else if(this.getDragonType() == EnumDragonType.ICE)
        {
            worldIn.spawnParticle(EnumParticleTypes.SNOWBALL, pos.getX(), pos.getY() + 2, pos.getZ(), 0, 0, 0, 0);

            if(this.timer > 1)
            {
                this.timer--;
            }
            if(this.timer == 1)
            {
                worldIn.setBlockToAir(pos.up());
                worldIn.spawnEntity(new EntityBabyIceDragon(worldIn, pos.getX(), pos.getY(), pos.getZ()));
                worldIn.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 0.1f, true);

                worldIn.setBlockToAir(pos);
            }

        }
        worldIn.scheduleBlockUpdate(pos, state.getBlock(), 10, 100);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {

        if(this.getDragonType() == EnumDragonType.NONE)
        {
            if(worldIn.getBlockState(pos.up()).getBlock() == Blocks.DRAGON_EGG)
            {
                if(playerIn.getHeldItem(hand).getItem() == ItemInit.EARTH_ATTACK)
                {
                    playerIn.getHeldItem(hand).shrink(1);
                    worldIn.setBlockState(pos.up(), BlockInit.EGG_EARTH.getDefaultState());
                    if(worldIn.isRemote)
                    {
                        playerIn.sendMessage(new TextComponentString(TextFormatting.GREEN + "Vous venez choisi votre type de dragon en : " + TextFormatting.GREEN + "EARTH"));
                    }
                    this.setDragonType(EnumDragonType.EARTH);
                    return true;
                }
                else if(playerIn.getHeldItem(hand).getItem() == ItemInit.ICE_ATTACK)
                {
                    playerIn.getHeldItem(hand).shrink(1);
                    worldIn.setBlockState(pos.up(), BlockInit.EGG_ICE.getDefaultState());
                    if(worldIn.isRemote)
                    {
                        playerIn.sendMessage(new TextComponentString(TextFormatting.GREEN + "Vous venez choisi votre type de dragon en : " + TextFormatting.AQUA + "ICE"));
                    }
                    this.setDragonType(EnumDragonType.ICE);
                    return true;
                }
                else if(playerIn.getHeldItem(hand).getItem() == ItemInit.MAGMA_BALL)
                {
                    playerIn.getHeldItem(hand).shrink(1);
                    worldIn.setBlockState(pos.up(), BlockInit.EGG_FIRE.getDefaultState());
                    if(worldIn.isRemote)
                    {
                        playerIn.sendMessage(new TextComponentString(TextFormatting.GREEN + "Vous venez choisi votre type de dragon en : " + TextFormatting.RED + "FIRE"));
                    }
                    this.setDragonType(EnumDragonType.FIRE);
                    return true;
                }
                else
                {
                    if(worldIn.isRemote)
                    {
                        playerIn.sendMessage(new TextComponentString(TextFormatting.RED + "Vous tenir un : " + TextFormatting.RED + "I" + TextFormatting.GOLD + "T" + TextFormatting.YELLOW + "E" + TextFormatting.GREEN + "M" + TextFormatting.DARK_GREEN + " E" + TextFormatting.DARK_AQUA + "L" + TextFormatting.AQUA + "E" + TextFormatting.BLUE + "M" + TextFormatting.DARK_BLUE + "E" + TextFormatting.DARK_PURPLE + "N" + TextFormatting.LIGHT_PURPLE + "T" + TextFormatting.RED + "A" + TextFormatting.GOLD + "I" + TextFormatting.YELLOW + "R" + TextFormatting.GREEN + "E"));
                    }
                    return false;
                }
            }
            else
            {
                playerIn.sendMessage(new TextComponentString(TextFormatting.RED + "Veuillez poser un " + TextFormatting.DARK_PURPLE + "OEUF DE DRAGON" + TextFormatting.RED + " avant"));
                return true;
            }
        }
        else
        {
            if(playerIn.getHeldItem(hand).getItem() == ItemInit.EARTH_ATTACK || playerIn.getHeldItem(hand).getItem() == ItemInit.ICE_ATTACK || playerIn.getHeldItem(hand).getItem() == ItemInit.MAGMA_BALL)
            {
                if(this.getDragonType() == EnumDragonType.EARTH)
                {
                    if(worldIn.isRemote)
                        playerIn.sendMessage(new TextComponentString(TextFormatting.RED + "Vous avez deja choisi votre type : " + TextFormatting.GREEN + "EARTH"));
                }
                else if(this.getDragonType() == EnumDragonType.ICE)
                {
                    if(worldIn.isRemote)
                        playerIn.sendMessage(new TextComponentString(TextFormatting.RED + "Vous avez deja choisi votre type : " + TextFormatting.AQUA + "ICE"));
                }
                else if(this.getDragonType() == EnumDragonType.FIRE)
                {
                    if(worldIn.isRemote)
                        playerIn.sendMessage(new TextComponentString(TextFormatting.RED + "Vous avez deja choisi votre type : " + TextFormatting.DARK_RED + "FIRE"));
                }
            }
            return false;
        }

    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
        if(worldIn.getBlockState(pos.up()).getBlock() != BlockInit.EGG_EARTH && worldIn.getBlockState(pos.up()).getBlock() != BlockInit.EGG_FIRE && worldIn.getBlockState(pos.up()).getBlock() != BlockInit.EGG_ICE && worldIn.getBlockState(pos.up()).getBlock() != Blocks.DRAGON_EGG)
        {
            this.setDragonType(EnumDragonType.NONE);
        }
    }

}