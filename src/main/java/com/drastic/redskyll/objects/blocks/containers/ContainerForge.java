package com.drastic.redskyll.objects.blocks.containers;

import java.util.List;

import com.drastic.redskyll.init.BlockInit;
import com.drastic.redskyll.objects.blocks.BlockMagicalCauldron;
import com.drastic.redskyll.objects.blocks.BlockForge;
import com.drastic.redskyll.objects.blocks.recipes.ForgeRecipes;
import com.drastic.redskyll.objects.blocks.recipes.SinteringFurnaceRecipes;
import com.drastic.redskyll.objects.tileentities.TileEntityForge;
import com.drastic.redskyll.objects.tileentities.TileEntitySinteringFurnace;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import scala.reflect.internal.Trees.This;

public class ContainerForge extends Container
{
    private final TileEntityForge tileentity;
    private int cookTime, totalCookTime, burnTime, currentBurnTime;

    public ContainerForge(InventoryPlayer player, TileEntityForge tileentity)
    {
        this.tileentity = tileentity;
        IItemHandler handler = this.tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

        this.addSlotToContainer(new SlotItemHandler(handler, 0, 26, 11));
        this.addSlotToContainer(new SlotItemHandler(handler, 1, 26, 59));
        this.addSlotToContainer(new SlotItemHandler(handler, 2, 7, 35));
        this.addSlotToContainer(new SlotItemHandler(handler, 3, 81, 36));

        for(int y = 0; y < 3; y++)
        {
            for(int x = 0; x < 9; x++)
            {
                this.addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
            }
        }

        for(int x = 0; x < 9; x++)
        {
            this.addSlotToContainer(new Slot(player, x, 8 + x * 18, 142));
        }
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for(int i = 0; i < this.listeners.size(); ++i)
        {
            IContainerListener listener = (IContainerListener)this.listeners.get(i);

            if(this.cookTime != this.tileentity.getField(2))
                listener.sendWindowProperty(this, 2, this.tileentity.getField(2));
            if(this.burnTime != this.tileentity.getField(0))
                listener.sendWindowProperty(this, 0, this.tileentity.getField(0));
            if(this.currentBurnTime != this.tileentity.getField(1))
                listener.sendWindowProperty(this, 1, this.tileentity.getField(1));
            if(this.totalCookTime != this.tileentity.getField(3))
                listener.sendWindowProperty(this, 3, this.tileentity.getField(3));
        }

        if(this.tileentity.getBlock() == BlockInit.FORGE)
        {
            ((BlockForge)this.tileentity.getBlock()).stack0 = this.inventorySlots.get(0).getStack();
            ((BlockForge)this.tileentity.getBlock()).stack1 = this.inventorySlots.get(1).getStack();
            ((BlockForge)this.tileentity.getBlock()).stack2 = this.inventorySlots.get(2).getStack();
            ((BlockForge)this.tileentity.getBlock()).stack3 = this.inventorySlots.get(3).getStack();
        }
        this.cookTime = this.tileentity.getField(2);
        this.burnTime = this.tileentity.getField(0);
        this.currentBurnTime = this.tileentity.getField(1);
        this.totalCookTime = this.tileentity.getField(3);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data)
    {
        this.tileentity.setField(id, data);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.tileentity.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = (Slot)this.inventorySlots.get(index);

        if(slot != null && slot.getHasStack())
        {
            ItemStack stack1 = slot.getStack();
            stack = stack1.copy();

            if(index == 3)
            {
                if(!this.mergeItemStack(stack1, 4, 40, true))
                    return ItemStack.EMPTY;
                slot.onSlotChange(stack1, stack);
            }
            else if(index != 2 && index != 1 && index != 0)
            {
                Slot slot1 = (Slot)this.inventorySlots.get(index + 1);

                if(!ForgeRecipes.getInstance().getSinteringResult(stack1, slot1.getStack()).isEmpty())
                {
                    if(!this.mergeItemStack(stack1, 0, 2, false))
                    {
                        return ItemStack.EMPTY;
                    }
                    else if(TileEntityForge.isItemFuel(stack1))
                    {
                        if(!this.mergeItemStack(stack1, 2, 3, false))
                            return ItemStack.EMPTY;
                    }
                    else if(TileEntityForge.isItemFuel(stack1))
                    {
                        if(!this.mergeItemStack(stack1, 2, 3, false))
                            return ItemStack.EMPTY;
                    }
                    else if(TileEntityForge.isItemFuel(stack1))
                    {
                        if(!this.mergeItemStack(stack1, 2, 3, false))
                            return ItemStack.EMPTY;
                    }
                    else if(index >= 4 && index < 31)
                    {
                        if(!this.mergeItemStack(stack1, 31, 40, false))
                            return ItemStack.EMPTY;
                    }
                    else if(index >= 31 && index < 40 && !this.mergeItemStack(stack1, 4, 31, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
            }
            else if(!this.mergeItemStack(stack1, 4, 40, false))
            {
                return ItemStack.EMPTY;
            }
            if(stack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();

            }
            if(stack1.getCount() == stack.getCount())
                return ItemStack.EMPTY;
            slot.onTake(playerIn, stack1);
        }
        return stack;
    }

}
