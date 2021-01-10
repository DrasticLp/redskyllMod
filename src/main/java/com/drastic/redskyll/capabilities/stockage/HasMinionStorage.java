package com.drastic.redskyll.capabilities.stockage;

import com.drastic.redskyll.util.interfaces.IHasMinion;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class HasMinionStorage implements IStorage<IHasMinion>
{

    @Override
    public NBTBase writeNBT(Capability<IHasMinion> capability, IHasMinion instance, EnumFacing side)
    {
        return new NBTTagInt(instance.get());
    }

    @Override
    public void readNBT(Capability<IHasMinion> capability, IHasMinion instance, EnumFacing side, NBTBase nbt)
    {
        instance.set(((NBTPrimitive)nbt).getInt());
    }

}
