package com.drastic.redskyll.objects.entity.AI;

import com.drastic.redskyll.objects.entity.base.EntityBabyDragonBase;
import com.google.common.base.Predicates;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EntitySelectors;

public class EntityAIWatchTarget extends EntityAIBase
{
    protected EntityLiving entity;
    /** The closest entity which is being watched by this one. */
    protected Entity closestEntity;
    /** This is the Maximum distance that the AI will look for the Entity */
    protected float maxDistanceForPlayer;
    private int lookTime;
    
    public EntityAIWatchTarget(EntityBabyDragonBase entityIn, float maxDistance)
    {
        this.entity = entityIn;
        this.maxDistanceForPlayer = maxDistance;
        this.setMutexBits(2);
    }

    public boolean shouldExecute()
    {
        if(this.entity.getAttackTarget() != null)
        {
            this.closestEntity = this.entity.getAttackTarget();
        }
        return this.closestEntity != null;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting()
    {
        if(!this.closestEntity.isEntityAlive())
        {
            return false;
        }
        else if(this.entity.getDistanceSq(this.closestEntity) > (double)(this.maxDistanceForPlayer * this.maxDistanceForPlayer))
        {
            return false;
        }
        else
        {
            return this.lookTime > 0;
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.lookTime = 40 + this.entity.getRNG().nextInt(40);
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask()
    {
        this.closestEntity = null;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void updateTask()
    {
        this.entity.getLookHelper().setLookPosition(this.closestEntity.posX, this.closestEntity.posY + (double)this.closestEntity.getEyeHeight(), this.closestEntity.posZ, (float)this.entity.getHorizontalFaceSpeed(), (float)this.entity.getVerticalFaceSpeed());
        --this.lookTime;
    }
}