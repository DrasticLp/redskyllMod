package com.drastic.redskyll.util.provider;

import com.drastic.redskyll.util.interfaces.IHasMinion;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class MinionProvider implements ICapabilitySerializable<NBTBase>
{

    @CapabilityInject(IHasMinion.class)
    public static final Capability<IHasMinion> MINION_CAP = null;
    
    private IHasMinion instance = MINION_CAP.getDefaultInstance();
    
    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return capability == MINION_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        return capability == MINION_CAP ? MINION_CAP.<T> cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return MINION_CAP.getStorage().writeNBT(MINION_CAP, instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt)
    {
        MINION_CAP.getStorage().readNBT(MINION_CAP, this.instance, null, nbt);
    }

}
