package com.drastic.redskyll.capabilities.stockage;

import com.drastic.redskyll.util.interfaces.IHasMinion;
import com.drastic.redskyll.util.interfaces.IMana;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class ManaStorage implements IStorage<IMana>
{

    @Override
    public NBTBase writeNBT(Capability<IMana> capability, IMana instance, EnumFacing side)
    {
        return new NBTTagInt(instance.getMana());
    }

    @Override
    public void readNBT(Capability<IMana> capability, IMana instance, EnumFacing side, NBTBase nbt)
    {
        instance.setMana(((NBTPrimitive)nbt).getInt());
    }

}
