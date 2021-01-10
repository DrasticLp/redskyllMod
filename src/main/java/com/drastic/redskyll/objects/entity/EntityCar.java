package com.drastic.redskyll.objects.entity;

import com.drastic.redskyll.init.BlockInit;
import com.drastic.redskyll.util.handlers.SoundsHandler;
import com.drastic.redskyll.util.interfaces.IMana;
import com.drastic.redskyll.util.provider.ManaProvider;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityCar extends EntityHorse
{

    public EntityCar(World worldIn)
    {
        super(worldIn);
        setSize(width, height - 1.5f);
    }

    @Override
    public boolean isTame()
    {
        return true;
    }
    
    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {        
        ItemStack itemstack = player.getHeldItem(hand);
        boolean flag = !itemstack.isEmpty();

        if(!this.isChild())
        {
            if(this.isBeingRidden())
            {
                return super.processInteract(player, hand);
            }
        }

        if(flag)
        {
            if(itemstack.interactWithEntity(player, this, hand))
            {
                return true;
            }
        }
        if(!this.isChild())
        {

                this.mountTo(player);
                player.playSound(SoundsHandler.FUSE_LAUNCH, 9.0F, 0);
                return true;
        }
        else
        {
            return super.processInteract(player, hand);
        }
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata)
    {
        return super.onInitialSpawn(difficulty, livingdata);
    }

    protected void entityInit()
    {
        super.entityInit();
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.34000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(0.5D);

    }

    protected SoundEvent getAmbientSound()
    {
        return SoundsHandler.IDLE_FUSE;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundsHandler.IDLE_FUSE;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundsHandler.IDLE_FUSE;
    }

    protected SoundEvent getAngrySound()
    {
        return SoundsHandler.IDLE_FUSE;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {

    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {

    }

    @Override
    public boolean isAIDisabled()
    {
        return true;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();

        if(this.world.isRemote && this.dataManager.isDirty())
        {
            this.dataManager.setClean();
            if(this.getRidingEntity() != null)
            {
                this.rotationYaw = this.getRidingEntity().rotationYaw;
                this.rotationPitch = this.getRidingEntity().rotationPitch;
          }
        }

        world.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY, this.posZ, this.motionX, this.motionY, this.motionZ, 0);
    }

    @Override
    public boolean attackable()
    {
        return true;
    }

    @Override
    public boolean canBeAttackedWithItem()
    {
         return true;
    }
}
