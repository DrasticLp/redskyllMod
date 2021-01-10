package com.drastic.redskyll.objects.entity;

import com.drastic.redskyll.objects.entity.base.EntityBabyDragonBase;
import com.drastic.redskyll.util.enums.EnumDragonType;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

public class EntityBabyIceDragon extends EntityBabyDragonBase
{

    public EntityBabyIceDragon(World worldIn)
    {
        super(worldIn);
    }    
    
    public EntityBabyIceDragon(World worldIn, double x, double y, double z)
    {
        super(worldIn);
        this.setPosition(x, y, z);
    }
    
    @Override
    public EnumDragonType getDragonType()
    {
        return EnumDragonType.ICE;
    }
}
