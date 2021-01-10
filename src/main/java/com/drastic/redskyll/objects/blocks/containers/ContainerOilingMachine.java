package com.drastic.redskyll.objects.blocks.containers;

import java.util.List;

import com.drastic.redskyll.init.BlockInit;
import com.drastic.redskyll.objects.blocks.BlockMagicalCauldron;
import com.drastic.redskyll.objects.blocks.BlockOilingMachine;
import com.drastic.redskyll.objects.blocks.recipes.SinteringFurnaceRecipes;
import com.drastic.redskyll.objects.tileentities.TileEntityOilingMachine;
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

public class ContainerOilingMachine extends Container
{
    private final TileEntityOilingMachine tileentity;
    private int cookTime, totalCookTime, burnTime, currentBurnTime;

    public ContainerOilingMachine(InventoryPlayer player, TileEntityOilingMachine tileentity)
    {
        this.tileentity = tileentity;
        IItemHandler handler = this.tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

        this.addSlotToContainer(new SlotItemHandler(handler, 0, 49, 36));
        this.addSlotToContainer(new SlotItemHandler(handler, 1, 104, 36));
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

        if(this.tileentity.getBlock() == BlockInit.OILING_MACHINE)
        {
            ((BlockOilingMachine)this.tileentity.getBlock()).stack0 = this.inventorySlots.get(0).getStack();
            ((BlockOilingMachine)this.tileentity.getBlock()).stack1 = this.inventorySlots.get(1).getStack();
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

        return ItemStack.EMPTY;
    }
}
