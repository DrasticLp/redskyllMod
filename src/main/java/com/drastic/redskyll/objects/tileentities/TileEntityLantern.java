package com.drastic.redskyll.objects.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityLantern extends TileEntity
{
    private static boolean hanging;

    public boolean getHanging()
    {
        return this.getHanging();
    }

    public void setHanging(boolean hangingIn)
    {

        this.hanging = hangingIn;
        markDirty();

    }
    
    @Override
    public void readFromNBT(NBTTagCompound compound)
    {

        this.hanging = compound.getBoolean("hanging");
        super.readFromNBT(compound);

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        //super.writeToNBT(compound);
        compound.setBoolean("hanging", this.hanging);
        return compound;

    }
}
