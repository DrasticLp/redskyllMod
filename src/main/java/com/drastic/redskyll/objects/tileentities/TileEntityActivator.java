package com.drastic.redskyll.objects.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityActivator extends TileEntity
{
    private static int type;

    public int getType()
    {
        return this.type;
    }

    public void setType(int type)
    {

        this.type = type;
        markDirty();

    }
    
    @Override
    public void readFromNBT(NBTTagCompound compound)
    {

        this.type = compound.getInteger("type_block");
        super.readFromNBT(compound);

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        //super.writeToNBT(compound);
        compound.setInteger("type_block", this.type);
        return compound;

    }
}
