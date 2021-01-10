package com.drastic.redskyll.objects.entity.base;

import java.util.UUID;

import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.objects.entity.EntityDirtBlock;
import com.drastic.redskyll.objects.entity.EntityIceBlock;
import com.drastic.redskyll.objects.entity.EntityMagmaBall;
import com.drastic.redskyll.objects.entity.AI.EntityAIWatchTarget;
import com.drastic.redskyll.util.enums.EnumDragonType;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSnowball;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class EntityBabyDragonBase extends EntityTameable
{
    private static int damage;

    private static int timer = 20 * 5;

    public EntityBabyDragonBase(World worldIn)
    {
        super(worldIn);
        this.setSize(1.0F, 0.5F);
        this.setTamed(false);
    }

    public EntityBabyDragonBase(World worldIn, double x, double y, double z)
    {
        super(worldIn);
        this.setPosition(x, y, z);
    }

    @Override
    protected void initEntityAI()
    {
        this.aiSit = new EntityAISit(this);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        // this.tasks.addTask(3, new EntityWolf.AIAvoidEntity(this, EntityLlama.class, 24.0F, 1.5D, 1.5D));
        this.tasks.addTask(4, new EntityAILeapAtTarget(this, 0.4F));
        // this.tasks.addTask(5, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(6, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(7, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWanderAvoidWater(this, 1.0D));
        // this.tasks.addTask(9, new EntityAIBeg(this, 8.0F));

        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityPlayer.class, 20.0F));

        this.tasks.addTask(10, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));

        // this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, AbstractSkeleton.class, false));
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        if(this.getAttackTarget() != null)
        {
            if(!this.world.isRemote)
            {
                if(this.getDragonType() == EnumDragonType.FIRE)
                {
                    // System.out.println(this.timer);
                    if(this.timer >= 1)
                    {
                        this.timer--;
                    }
                    else
                    {
                        EntityMagmaBall magmaBall = new EntityMagmaBall(this.world, this, this.getAttackTarget());
                        this.world.spawnEntity(magmaBall);
                        this.timer = 20 * 5;
                    }
                }
                if(this.getDragonType() == EnumDragonType.ICE)
                {
                    if(this.timer >= 1)
                    {
                        this.timer--;
                    }
                    else
                    {
                        EntityIceBlock magmaBall = new EntityIceBlock(this.world, this, this.getAttackTarget());
                        this.world.spawnEntity(magmaBall);
                        this.timer = 20 * 5;
                    }
                }
                if(this.getDragonType() == EnumDragonType.EARTH)
                {
                    // System.out.println(this.timer);
                    if(this.timer >= 1)
                    {
                        this.timer--;
                    }
                    else
                    {
                        EntityDirtBlock magmaBall = new EntityDirtBlock(this.world, this, this.getAttackTarget());
                        this.world.spawnEntity(magmaBall);
                        this.timer = 20 * 5;
                    }
                }
            }
        }
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        if(this.getDragonType() == EnumDragonType.FIRE)
        {
            this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.29000001192092896D);

            if(this.isTamed())
            {
                this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(29.0D);
            }
            else
            {
                this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
            }

            this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(17.0D);
            this.damage = 17;
        }
        else if(this.getDragonType() == EnumDragonType.ICE)
        {
            this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3500001192092896D);

            if(this.isTamed())
            {
                this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(37.0D);
            }
            else
            {
                this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
            }

            this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(12.0D);
            this.damage = 12;
        }
        else if(this.getDragonType() == EnumDragonType.EARTH)
        {
            this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2500001192092896D);

            if(this.isTamed())
            {
                this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
            }
            else
            {
                this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
            }

            this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(15.0D);
            this.damage = 15;
        }
    }

    public EnumDragonType getDragonType()
    {
        return EnumDragonType.NONE;
    }

    @Override
    public void setTamedBy(EntityPlayer player)
    {
        super.setTamedBy(player);
        if(this.getDragonType() == EnumDragonType.FIRE)
        {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(29.0D);
        }
        else if(this.getDragonType() == EnumDragonType.ICE)
        {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(37.0D);
        }
        else if(this.getDragonType() == EnumDragonType.EARTH)
        {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
        }
    }

    public int getDragonDamage()
    {
        return this.damage;
    }

    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if(this.isTamed())
        {
            if(!itemstack.isEmpty())
            {
                if(itemstack.getItem() instanceof ItemFood)
                {
                    ItemFood itemfood = (ItemFood)itemstack.getItem();

                    if(itemfood == ItemInit.MAGICAL_GELLY && this.getHealth() < 20.0f)
                    {
                        if(!player.capabilities.isCreativeMode)
                        {
                            itemstack.shrink(1);
                        }
                        this.playTameEffect(true);

                        this.heal((float)itemfood.getHealAmount(itemstack));
                        return true;
                    }
                }
            }

            if(this.isOwner(player) && !this.world.isRemote)
            {

                this.aiSit.setSitting(!this.isSitting());
                this.isJumping = false;
                this.navigator.clearPath();
                this.setAttackTarget((EntityLivingBase)null);
            }
        }
        else
        {
            if(this.getDragonType() == EnumDragonType.ICE)
            {
                if(itemstack.getItem() == Item.getItemFromBlock(Blocks.PACKED_ICE))
                {
                    if(!player.capabilities.isCreativeMode)
                    {
                        itemstack.shrink(1);
                    }

                    if(!this.world.isRemote)
                    {
                        if(this.rand.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player))
                        {
                            this.setTamedBy(player);
                            this.navigator.clearPath();
                            this.setAttackTarget((EntityLivingBase)null);
                            this.aiSit.setSitting(true);
                            this.setHealth(35.0F);
                            this.playTameEffect(true);
                            this.world.setEntityState(this, (byte)7);
                        }
                        else
                        {
                            this.playTameEffect(false);
                            this.world.setEntityState(this, (byte)6);
                        }
                    }
                }
            }
            else if(this.getDragonType() == EnumDragonType.FIRE)
            {
                if(itemstack.getItem() == Item.getItemFromBlock(Blocks.MAGMA))
                {
                    if(!player.capabilities.isCreativeMode)
                    {
                        itemstack.shrink(1);
                    }

                    if(!this.world.isRemote)
                    {
                        if(this.rand.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player))
                        {
                            this.setTamedBy(player);
                            this.navigator.clearPath();
                            this.setAttackTarget((EntityLivingBase)null);
                            this.aiSit.setSitting(true);
                            this.setHealth(33.0F);
                            this.playTameEffect(true);
                            this.world.setEntityState(this, (byte)7);
                        }
                        else
                        {
                            this.playTameEffect(false);
                            this.world.setEntityState(this, (byte)6);
                        }
                    }
                }
            }
            else if(this.getDragonType() == EnumDragonType.EARTH)
            {
                if(itemstack.getItem() == Item.getItemFromBlock(Blocks.DIRT))
                {
                    if(!player.capabilities.isCreativeMode)
                    {
                        itemstack.shrink(1);
                    }

                    if(!this.world.isRemote)
                    {
                        if(this.rand.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player))
                        {
                            this.setTamedBy(player);
                            this.navigator.clearPath();
                            this.setAttackTarget((EntityLivingBase)null);
                            this.aiSit.setSitting(true);
                            this.setHealth(50.0F);
                            this.playTameEffect(true);
                            this.world.setEntityState(this, (byte)7);
                        }
                        else
                        {
                            this.playTameEffect(false);
                            this.world.setEntityState(this, (byte)6);
                        }
                    }
                }
            }
        }

        return super.processInteract(player, hand);
    }

    @Override
    public EntityAgeable createChild(EntityAgeable ageable)
    {
        EntityWolf entitywolf = new EntityWolf(this.world);
        UUID uuid = this.getOwnerId();

        if(uuid != null)
        {
            entitywolf.setOwnerId(uuid);
            entitywolf.setTamed(true);
        }

        return entitywolf;
    }
}
