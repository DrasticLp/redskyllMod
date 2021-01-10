package com.drastic.redskyll.objects.entity;

import com.drastic.redskyll.objects.entity.base.EntityBabyDragonBase;
import com.drastic.redskyll.util.enums.EnumDragonType;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

public class EntityBabyFireDragon extends EntityBabyDragonBase
{

    public EntityBabyFireDragon(World worldIn)
    {
        super(worldIn);
    }    
    
    public EntityBabyFireDragon(World worldIn, double x, double y, double z)
    {
        super(worldIn);
        this.setPosition(x, y, z);
    }
    
    @Override
    public EnumDragonType getDragonType()
    {
        return EnumDragonType.FIRE;
    }
}
