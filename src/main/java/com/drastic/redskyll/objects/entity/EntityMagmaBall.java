package com.drastic.redskyll.objects.entity;

import com.drastic.redskyll.objects.entity.base.EntityBabyDragonBase;
import com.drastic.redskyll.objects.entity.base.EntityProjectileBase;
import com.drastic.redskyll.util.enums.EnumDragonType;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class EntityMagmaBall extends EntityProjectileBase
{
    public EntityMagmaBall(World par1World)
    {
        super(par1World);
    }

    public EntityMagmaBall(World par1World, Entity par2Entity)
    {
        super(par1World, par2Entity);
    }
    
    public EntityMagmaBall(World par1World, EntityBabyDragonBase par3EntityPlayer)
    {
        super(par1World, par3EntityPlayer);
    }
    
    public EntityMagmaBall(World par1World, EntityBabyDragonBase par3EntityPlayer, Entity target)
    {
        super(par1World, par3EntityPlayer, target);
    }
    
    @Override
    public EnumDragonType getDragonType()
    {
        return EnumDragonType.FIRE;
    }
}
