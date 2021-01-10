package com.drastic.redskyll.util.provider;

import com.drastic.redskyll.util.interfaces.IHasBoss;
import com.drastic.redskyll.util.interfaces.IHasMinion;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class BossProvider implements ICapabilitySerializable<NBTBase>
{

    @CapabilityInject(IHasBoss.class)
    public static final Capability<IHasBoss> BOSS_CAP = null;
    
    private IHasBoss instance = BOSS_CAP.getDefaultInstance();
    
    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return capability == BOSS_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        return capability == BOSS_CAP ? BOSS_CAP.<T> cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return BOSS_CAP.getStorage().writeNBT(BOSS_CAP, instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt)
    {
        BOSS_CAP.getStorage().readNBT(BOSS_CAP, this.instance, null, nbt);
    }

}
