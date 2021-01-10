package com.drastic.redskyll.objects.tileentities;

import java.util.ArrayList;
import java.util.List;

import com.drastic.redskyll.init.BlockInit;
import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.objects.blocks.BlockMagicalCauldron;
import com.drastic.redskyll.objects.blocks.recipes.SinteringFurnaceRecipes;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
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
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityForge extends TileEntity implements ITickable
{
    public ItemStackHandler handler = new ItemStackHandler(4);
    private String customName;
    private ItemStack smelting = ItemStack.EMPTY;

    private int burnTime;
    private int currentBurnTime;
    private int cookTime;
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
        return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation("container.forge");
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
        this.burnTime = compound.getInteger("BurnTime");
        this.cookTime = compound.getInteger("CookTime");
        this.totalCookTime = compound.getInteger("CookTimeTotal");
        this.currentBurnTime = this.getItemBurnTime((ItemStack)this.handler.getStackInSlot(2));

        if(compound.hasKey("CustomName", 8))
            this.setCustomName(compound.getString("CustomName"));
    }

    public List<ItemStack> getDrops(Block block)
    {
        List<ItemStack> list = new ArrayList<ItemStack>();
        list.add(this.handler.getStackInSlot(0));
        list.add(this.handler.getStackInSlot(1));
        list.add(this.handler.getStackInSlot(2));
        list.add(this.handler.getStackInSlot(3));

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

    public boolean isBurning()
    {
        return this.burnTime > 0;
    }

    @SideOnly(Side.CLIENT)
    public static boolean isBurning(TileEntityForge te)
    {
        return te.getField(0) > 0;
    }

    public void update()
    {
        if(this.isBurning())
        {
            --this.burnTime;
            BlockMagicalCauldron.setState(true, world, pos);
        }

        ItemStack[] inputs = new ItemStack[] {this.handler.getStackInSlot(0), this.handler.getStackInSlot(1)};
        ItemStack fuel = this.handler.getStackInSlot(2);

        if(this.isBurning() || !fuel.isEmpty() && !this.handler.getStackInSlot(0).isEmpty() || this.handler.getStackInSlot(1).isEmpty())
        {
            if(!this.isBurning() && this.canSmelt())
            {
                this.burnTime = this.getItemBurnTime(fuel);
                this.currentBurnTime = this.burnTime;

                if(this.isBurning() && !fuel.isEmpty())
                {
                    Item item = fuel.getItem();
                    fuel.shrink(1);

                    if(fuel.isEmpty())
                    {
                        ItemStack item1 = item.getContainerItem(fuel);
                        this.handler.setStackInSlot(2, item1);
                    }
                }
            }
        }

        if(this.isBurning() && this.cookTime > 0)
        {
            this.cookTime++;
            if(this.cookTime == this.totalCookTime)
            {
                if(handler.getStackInSlot(3).getCount() > 0)
                {
                    this.handler.getStackInSlot(3).grow(this.smelting.getCount());
                }
                else
                {
                    this.handler.insertItem(3, this.smelting, false);
                }

                this.smelting = ItemStack.EMPTY;
                this.cookTime = 0;
                return;
            }
        }
        else
        {
            if(this.canSmelt() && this.isBurning())
            {
                ItemStack output = SinteringFurnaceRecipes.getInstance().getSinteringResult(inputs[0], inputs[1]);
                if(!output.isEmpty())
                {
                    this.smelting = output;
                    this.cookTime++;
                    inputs[0].shrink(1);
                    inputs[1].shrink(1);
                    this.handler.setStackInSlot(0, inputs[0]);
                    this.handler.setStackInSlot(1, inputs[1]);
                    if(!this.world.isRemote)
                    {
                        this.world.spawnEntity(new EntityXPOrb(world, this.pos.getX(), this.pos.getY(), this.pos.getZ(), (int)SinteringFurnaceRecipes.getInstance().getSinteringExperience(smelting)));
                    }
                }
            }
        }
    }

    private boolean canSmelt()
    {
        if(((ItemStack)this.handler.getStackInSlot(0)).isEmpty() || ((ItemStack)this.handler.getStackInSlot(1)).isEmpty())
            return false;
        else
        {
            ItemStack result = SinteringFurnaceRecipes.getInstance().getSinteringResult((ItemStack)this.handler.getStackInSlot(0), (ItemStack)this.handler.getStackInSlot(1));
            if(result.isEmpty())
                return false;
            else
            {
                ItemStack output = (ItemStack)this.handler.getStackInSlot(3);
                if(output.isEmpty())
                    return true;
                if(!output.isItemEqual(result))
                    return false;
                int res = output.getCount() + result.getCount();
                return res <= 64 && res <= output.getMaxStackSize();
            }
        }
    }

    public static int getItemBurnTime(ItemStack fuel)
    {
        if(fuel.isEmpty())
            return 0;
        else
        {
            Item item = fuel.getItem();

            if(item == ItemInit.URANIUM_STICK)
                return 2000;

            return GameRegistry.getFuelValue(fuel);
        }
    }

    public static boolean isItemFuel(ItemStack fuel)
    {
        return getItemBurnTime(fuel) > 0;
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