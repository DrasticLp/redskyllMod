package com.drastic.redskyll.objects.tileentities;

import java.util.ArrayList;
import java.util.List;

import com.drastic.redskyll.init.BlockInit;
import com.drastic.redskyll.init.FluidInit;
import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.objects.blocks.BlockMagicalCauldron;
import com.drastic.redskyll.objects.blocks.BlockOilingMachine;
import com.drastic.redskyll.objects.blocks.recipes.SinteringFurnaceRecipes;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityOilingMachine extends TileEntity implements ITickable
{
    public ItemStackHandler handler = new ItemStackHandler(2);
    private String customName;
    private ItemStack smelting = ItemStack.EMPTY;

    private int burnTime;
    private int currentBurnTime;
    private int cookTime = 0;
    private int totalCookTime = 200;

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return true;
        else
            return false;
    }

    public Block getBlock()
    {
        return world.getBlockState(this.pos).getBlock();
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return (T)this.handler;
        return super.getCapability(capability, facing);
    }

    public boolean hasCustomName()
    {
        return this.customName != null && !this.customName.isEmpty();
    }

    public void setCustomName(String customName)
    {
        this.customName = customName;
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation("container.oiling_machine");
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
        this.burnTime = compound.getInteger("BurnTime");
        this.cookTime = compound.getInteger("CookTime");
        this.totalCookTime = compound.getInteger("CookTimeTotal");
        // this.currentBurnTime = this.getItemBurnTime((ItemStack)this.handler.getStackInSlot(2));

        if(compound.hasKey("CustomName", 8))
            this.setCustomName(compound.getString("CustomName"));
    }

    public List<ItemStack> getDrops(Block block)
    {
        List<ItemStack> list = new ArrayList<ItemStack>();
        list.add(this.handler.getStackInSlot(0));
        list.add(this.handler.getStackInSlot(1));

        return list;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("BurnTime", (short)this.burnTime);
        compound.setInteger("CookTime", (short)this.cookTime);
        compound.setInteger("CookTimeTotal", (short)this.totalCookTime);
        compound.setTag("Inventory", this.handler.serializeNBT());

        if(this.hasCustomName())
            compound.setString("CustomName", this.customName);
        return compound;
    }

    @SideOnly(Side.CLIENT)
    public static boolean isBurning(TileEntityOilingMachine te)
    {
        return te.getField(0) > 0;
    }

    public void update()
    {
        ItemStack[] inputs = new ItemStack[] {this.handler.getStackInSlot(0), this.handler.getStackInSlot(1)};

        if(this.cookTime > 0)
        {
            this.cookTime++;
            if(this.canSmelt())
            {
                if(this.cookTime == this.totalCookTime)
                {
                    if((this.handler.getStackInSlot(1).getMaxDamage() - this.handler.getStackInSlot(1).getItemDamage()) <= this.handler.getStackInSlot(1).getMaxDamage() - 1000)
                    {
                        this.handler.getStackInSlot(1).setItemDamage(this.handler.getStackInSlot(1).getItemDamage() - 1000);
                    }
                    else
                    {
                        this.handler.getStackInSlot(1).setItemDamage(0);
                    }
                    this.handler.getStackInSlot(0).shrink(1);
                    this.cookTime = 0;
                    return;
                }
            }
            else
            {
                this.cookTime = 0;
            }
        }
        else
        {
            if(this.canSmelt())
            {
                this.cookTime++;
            }

        }
    }

    private boolean canSmelt()
    {
        if(((ItemStack)this.handler.getStackInSlot(0)).isEmpty() || ((ItemStack)this.handler.getStackInSlot(1)).isEmpty())
            return false;
        else
        {
            if(this.handler.getStackInSlot(1).getItem() instanceof ItemArmor && this.handler.getStackInSlot(0).getItem() == FluidUtil.getFilledBucket(new FluidStack(FluidInit.OIL, 1000)).getItem())
            {
                if(this.handler.getStackInSlot(1).getItemDamage() > 1)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
    }

    public boolean isUsableByPlayer(EntityPlayer player)
    {
        return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
    }

    public int getField(int id)
    {
        switch(id)
        {
            case 0:
                return this.burnTime;
            case 1:
                return this.currentBurnTime;
            case 2:
                return this.cookTime;
            case 3:
                return this.totalCookTime;
            default:
                return 0;
        }
    }

    public void setField(int id, int value)
    {
        switch(id)
        {
            case 0:
                this.burnTime = value;
                break;
            case 1:
                this.currentBurnTime = value;
                break;
            case 2:
                this.cookTime = value;
                break;
            case 3:
                this.totalCookTime = value;
        }
    }
}