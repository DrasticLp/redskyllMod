package com.drastic.redskyll.objects.entity;

import com.drastic.redskyll.objects.entity.base.EntityBabyDragonBase;
import com.drastic.redskyll.util.enums.EnumDragonType;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

public class EntityBabyEarthDragon extends EntityBabyDragonBase
{

    public EntityBabyEarthDragon(World worldIn)
    {
        super(worldIn);
    }    
    
    public EntityBabyEarthDragon(World worldIn, double x, double y, double z)
    {
        super(worldIn);
        this.setPosition(x, y, z);
    }
    
    @Override
    public EnumDragonType getDragonType()
    {
        return EnumDragonType.EARTH;
    }
}
