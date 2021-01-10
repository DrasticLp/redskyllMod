package com.drastic.redskyll.util.provider;

import com.drastic.redskyll.util.interfaces.IRelic;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class RelicProvider implements ICapabilitySerializable<NBTBase>
{

    @CapabilityInject(IRelic.class)
    public static final Capability<IRelic> RELIC_CAP = null;
    
    private IRelic instance = RELIC_CAP.getDefaultInstance();
    
    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return capability == RELIC_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        return capability == RELIC_CAP ? RELIC_CAP.<T> cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return RELIC_CAP.getStorage().writeNBT(RELIC_CAP, instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt)
    {
        RELIC_CAP.getStorage().readNBT(RELIC_CAP, this.instance, null, nbt);
    }

}
