package com.drastic.redskyll.objects.entity;

import com.drastic.redskyll.objects.entity.base.EntityBabyDragonBase;
import com.drastic.redskyll.objects.entity.base.EntityProjectileBase;
import com.drastic.redskyll.util.enums.EnumDragonType;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class EntityIceBlock extends EntityProjectileBase
{
    public EntityIceBlock(World par1World)
    {
        super(par1World);
    }

    public EntityIceBlock(World par1World, Entity par2Entity)
    {
        super(par1World, par2Entity);
    }
    
    public EntityIceBlock(World par1World, EntityBabyDragonBase par3EntityPlayer)
    {
        super(par1World, par3EntityPlayer);
    }
    
    public EntityIceBlock(World par1World, EntityBabyDragonBase par3EntityPlayer, Entity target)
    {
        super(par1World, par3EntityPlayer, target);
    }
    
    @Override
    public EnumDragonType getDragonType()
    {
        return EnumDragonType.ICE;
    }
}
