package com.drastic.redskyll.objects.entity;

import com.drastic.redskyll.objects.entity.base.EntityFuseBase;
import com.drastic.redskyll.util.enums.EnumFuseType;

import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class EntityFuseT1 extends EntityFuseBase
{
    public EntityFuseT1(World worldIn)
    {
        super(worldIn);
    }

    public EntityFuseT1(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }
    
    @Override
    public EnumFuseType getFuseType()
    {
        return EnumFuseType.T1;
    }
    
    @Override
    public String getCustomName()
    {
        return TextFormatting.WHITE + "Tier 1";
    }

}
