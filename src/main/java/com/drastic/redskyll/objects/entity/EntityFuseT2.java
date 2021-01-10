package com.drastic.redskyll.objects.entity;

import com.drastic.redskyll.objects.entity.base.EntityFuseBase;
import com.drastic.redskyll.util.enums.EnumFuseType;

import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class EntityFuseT2 extends EntityFuseBase
{
    public EntityFuseT2(World worldIn)
    {
        super(worldIn);
    }

    public EntityFuseT2(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }
    
    @Override
    public EnumFuseType getFuseType()
    {
        return EnumFuseType.T2;
    }
    
    @Override
    public String getCustomName()
    {
        return TextFormatting.RED + "Tier 2";
    }
}
