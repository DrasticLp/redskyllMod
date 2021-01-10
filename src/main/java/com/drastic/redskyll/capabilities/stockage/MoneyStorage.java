package com.drastic.redskyll.capabilities.stockage;

import com.drastic.redskyll.util.interfaces.IHasMinion;
import com.drastic.redskyll.util.interfaces.IMana;
import com.drastic.redskyll.util.interfaces.IMoney;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class MoneyStorage implements IStorage<IMoney>
{

    @Override
    public NBTBase writeNBT(Capability<IMoney> capability, IMoney instance, EnumFacing side)
    {
        return new NBTTagInt(instance.getMoney());
    }

    @Override
    public void readNBT(Capability<IMoney> capability, IMoney instance, EnumFacing side, NBTBase nbt)
    {
        instance.setMoney(((NBTPrimitive)nbt).getInt());
    }

}
