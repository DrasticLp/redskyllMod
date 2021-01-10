package com.drastic.redskyll.objects.entity.base;

import java.util.List;

import com.drastic.redskyll.util.enums.EnumDragonType;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityProjectileBase extends EntityThrowable
{
    public boolean homing = true;
    private int ticksAlive = 0;
    protected EntityLivingBase target;
    protected EntityBabyDragonBase shootingEntity;
    protected Entity predefTarget;
    public boolean shouldUpdateTarget = true;

    private static EnumDragonType dragonType;

    public EntityProjectileBase(World par1World)
    {
        super(par1World);
    }

    public EntityProjectileBase(World par1World, Entity par2Entity)
    {
        super(par1World);

    }

    public EntityProjectileBase(World par1World, EntityBabyDragonBase par3EntityPlayer)
    {
        super(par1World, par3EntityPlayer);
        this.shootingEntity = par3EntityPlayer;
    }

    public EntityProjectileBase(World par1World, EntityBabyDragonBase par3EntityPlayer, Entity target)
    {
        super(par1World, par3EntityPlayer);
        this.posY = par3EntityPlayer.posY + 1;
        this.shootingEntity = par3EntityPlayer;
        this.predefTarget = target;
        this.shouldUpdateTarget = false;
    }

    public EnumDragonType getDragonType()
    {
        return EnumDragonType.NONE;
    }

    protected void entityInit()
    {}

    protected float getGravityVelocity()
    {
        return 0.0F;
    }

    protected float func_70182_d()
    {
        return 1.2F;
    }

    protected float func_70183_g()
    {
        return -0.0F;
    }

    public void onUpdate()
    {
        super.onUpdate();
        this.ticksAlive += 1;
        if(this.ticksAlive >= 300)
        {
            setDead();
            this.ticksAlive = 0;
        }

        if(predefTarget != null)
        {
            this.target = (EntityLivingBase)predefTarget;
        }

        // TODO - Fix the spawn particle
        // this.world.spawnParticle("smoke", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        // this.worldObj.spawnParticle("flame", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);

        if(this.ticksAlive >= 5)
        {
            if((this.target == null) || (this.target.velocityChanged) || (!this.target.canEntityBeSeen(this)) || this.target.isDead || this.target.getEntityData().getInteger("missilesTargeting") != this.hashCode())
            {
                if(shouldUpdateTarget)
                    this.target = this.getNearestEntity();
            }
        }

        if(this.target != null)
        {
            double d = this.target.getEntityBoundingBox().minX + (this.target.getEntityBoundingBox().maxX - this.target.getEntityBoundingBox().minX) / 2.0D - this.posX;
            double d1 = this.target.getEntityBoundingBox().minY + (this.target.getEntityBoundingBox().maxY - this.target.getEntityBoundingBox().minY) / 2.0D - this.posY;
            double d2 = this.target.getEntityBoundingBox().minZ + (this.target.getEntityBoundingBox().maxZ - this.target.getEntityBoundingBox().minZ) / 2.0D - this.posZ;
            this.shoot(d, d1, d2, 1.9F, 0.0F);
            this.posY += (0.5 * Math.sin(this.ticksAlive));
            this.posX += (0.5 * Math.cos(this.ticksAlive));
            if(this.getDragonType() == EnumDragonType.FIRE)
            {
                this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY, this.posZ, 0, 0, 0, 0);
            }
            else if(this.getDragonType() == EnumDragonType.ICE)
            {
                this.world.spawnParticle(EnumParticleTypes.SNOWBALL, this.posX, this.posY, this.posZ, 0, 0, 0, 0);
            }
            else if(this.getDragonType() == EnumDragonType.EARTH)
            {
                this.world.spawnParticle(EnumParticleTypes.SLIME, this.posX, this.posY, this.posZ, 0, 0, 0, 0);
            }
        }

        float f4 = 0.99F;
        float f6 = 0.05F;

        if(!this.homing)
        {
            this.motionX *= f4;
            this.motionY *= f4;
            this.motionZ *= f4;
            this.motionY -= f6;
        }

    }

    private EntityLiving getTarget(double d, double d1, double d2, double d3)
    {
        double d4 = -1.0D;
        EntityLiving entityliving = null;
        List list = this.world.getEntitiesWithinAABBExcludingEntity(getThrower(), this.getEntityBoundingBox().expand(16.0D, 16.0D, 16.0D));

        for(int i = 0; i < list.size(); i++)
        {
            EntityLiving entityliving1 = (EntityLiving)list.get(i);

            if(entityliving1 != getThrower())
            {
                double d5 = entityliving1.getDistance(d, d1, d2);

                if(((d3 < 0.0D) || (d5 < d3 * d3)) && ((d4 == -1.0D) || (d5 < d4)) && (entityliving1.canEntityBeSeen(this)))
                {
                    d4 = d5;
                    entityliving = entityliving1;
                }
            }
        }

        return entityliving;
    }

    public boolean validTarget(EntityLiving entityliving)
    {
        if(entityliving.equals(getThrower()))
        {
            return false;
        }

        return true;
    }

    protected void onImpact(RayTraceResult movingobjectposition)
    {
        if(movingobjectposition.entityHit != null)
        {
            if(movingobjectposition.entityHit != this.getThrower())
            {
                movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.shootingEntity), ((EntityBabyDragonBase)this.getThrower()).getDragonDamage());

                if(this.getDragonType() == EnumDragonType.FIRE && !this.world.isRemote)
                {
                    movingobjectposition.entityHit.setFire(4);
                }
                else if(this.getDragonType() == EnumDragonType.ICE && !this.world.isRemote)
                {
                    ((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20 * 4, 1));
                    ((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 20 * 4, 0));
                }
                else if(this.getDragonType() == EnumDragonType.EARTH && !this.world.isRemote)
                {
                    ((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 20 * 4, 0));
                    ((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(MobEffects.HUNGER, 20 * 4, 0));
                    ((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(MobEffects.POISON, 20 * 4, 0));
                }
                if(!this.world.isRemote)
                {
                    this.setDead();
                }
            }
            if(movingobjectposition.entityHit.getEntityData().getInteger("missilesTargeting") != 0)
            {
                movingobjectposition.entityHit.getEntityData().setInteger("missilesTargeting", 0);
            }
        }
        else if(!this.world.isRemote)
        {
            this.setDead();
        }
    }

    private EntityLiving getNearestEntity()
    {
        EntityLiving target = null;
        float explosionSize = 10.0F;
        explosionSize *= 2.0F;
        int i = MathHelper.floor(this.posX - explosionSize - 1.0D);
        int j = MathHelper.floor(this.posX + explosionSize + 1.0D);
        int k = MathHelper.floor(this.posY - explosionSize - 1.0D);
        int l1 = MathHelper.floor(this.posY + explosionSize + 1.0D);
        int i2 = MathHelper.floor(this.posZ - explosionSize - 1.0D);
        int j2 = MathHelper.floor(this.posZ + explosionSize + 1.0D);

        if(!this.world.isRemote)
        {
            List list = this.world.getEntitiesWithinAABBExcludingEntity(getThrower(), new AxisAlignedBB(i, k, i2, j, l1, j2));

            for(int k2 = 0; k2 < list.size(); k2++)
            {
                Entity entity = (Entity)list.get(k2);
                if(((entity instanceof EntityLiving)) && (((EntityLiving)entity).canEntityBeSeen(this)))
                {
                    target = (EntityLiving)entity;
                    if(target.getEntityData().getInteger("missilesTargeting") == 0)
                    {
                        target.getEntityData().setInteger("missilesTargeting", this.hashCode());
                        return target;
                    }
                }
            }
        }
        return target;
    }
}