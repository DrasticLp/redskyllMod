package com.drastic.redskyll.capabilities.stockage;

import com.drastic.redskyll.util.interfaces.IRelic;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class RelicStorage implements IStorage<IRelic>
{

    @Override
    public NBTBase writeNBT(Capability<IRelic> capability, IRelic instance, EnumFacing side)
    {
        return new NBTTagInt(instance.get());
    }

    @Override
    public void readNBT(Capability<IRelic> capability, IRelic instance, EnumFacing side, NBTBase nbt)
    {
        instance.set(((NBTPrimitive)nbt).getInt());
    }

}
