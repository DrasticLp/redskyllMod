package com.drastic.redskyll.objects.entity;

import java.util.UUID;

import javax.annotation.Nullable;

import com.drastic.redskyll.init.BlockInit;
import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.util.handlers.LootTableHandler;
import com.drastic.redskyll.util.handlers.SoundsHandler;
import com.drastic.redskyll.util.interfaces.IHasMinion;
import com.drastic.redskyll.util.interfaces.IMana;
import com.drastic.redskyll.util.provider.ManaProvider;
import com.drastic.redskyll.util.provider.MinionProvider;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class EntityMinionIron extends EntityTameable
{
    private static final DataParameter<Float> DATA_HEALTH_ID = EntityDataManager.<Float>createKey(EntityWolf.class, DataSerializers.FLOAT);

    
    public EntityMinionIron(World worldIn)
    {
        super(worldIn);
        this.setSize(0.6F, 0.85F);
        this.setTamed(false);
    }


    public EntityMinionIron(World worldIn, double x, double y, double z)
    {
        super(worldIn);
        this.setSize(0.6F, 0.85F);
        this.setTamed(false);
        this.setPosition(x, y, z);
    }
    
    protected void initEntityAI()
    {
        this.aiSit = new EntityAISit(this);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(6, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(7, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(10, new EntityAILookIdle(this));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);

        if(this.isTamed())
        {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        }
        else
        {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
        }

        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
    }

    protected void updateAITasks()
    {
        this.dataManager.set(DATA_HEALTH_ID, Float.valueOf(this.getHealth()));
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(DATA_HEALTH_ID, Float.valueOf(this.getHealth()));
    }

    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundsHandler.IDLE_FUSE, 0.15F, 1.0F);
    }

    public static void registerFixesWolf(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityWolf.class);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
    }

    protected SoundEvent getAmbientSound()
    {
        return SoundsHandler.IDLE_FUSE;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundsHandler.IDLE_FUSE;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundsHandler.IDLE_FUSE;
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return 0.4F;
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        if(isTamed())
        {
            return LootTableHandler.MINION3;
        }
        else
        {
            return LootTableHandler.MINION;
        }
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();
    }

    /*
     * public float getEyeHeight()
     * {
     * return this.height * 0.8F;
     * }
     */

    public int getVerticalFaceSpeed()
    {
        return this.isSitting() ? 20 : super.getVerticalFaceSpeed();
    }

    public void setTamed(boolean tamed)
    {
        super.setTamed(tamed);

        if(tamed)
        {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        }
        else
        {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
        }

        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    }

    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if(this.isTamed())
        {               
            IMana mana = player.getCapability(ManaProvider.MANA_CAP, null);

            if(this.isOwner(player) && !world.isRemote && itemstack.getItem() == ItemInit.MINION_RECUPERATOR)
            {     
    
    
                if(mana.getMana() != 0)
                {
                    this.setDead();
                    itemstack.shrink(1);
                    mana.setMana(mana.getMana()-1);

                    player.addItemStackToInventory(new ItemStack(ItemInit.MINION_IRON)); 
                }
                else
                {
                    player.sendMessage(new TextComponentString("Vous n'avez pas assez de Mana")); 
                }
            }
        }
        else if(itemstack.getItem() == Item.getItemFromBlock(BlockInit.KRAMAZIA_BLOCK))
        {

            if(!player.capabilities.isCreativeMode)
            {
                itemstack.shrink(1);
            }

            if(!this.world.isRemote)
            {
                IHasMinion hasMinion = player.getCapability(MinionProvider.MINION_CAP, null);

                if(this.rand.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player))
                {
                    if(hasMinion.get() != 0)
                    {
                        this.playTameEffect(false);
                        this.world.setEntityState(this, (byte)6);
                        this.setDead();
                    }
                    else
                    {
                        this.setTamedBy(player);
                        this.navigator.clearPath();
                        this.setAttackTarget((EntityLivingBase)null);
                        this.setHealth(20.0F);
                        this.playTameEffect(true);
                        this.world.setEntityState(this, (byte)7);
                        this.setCustomNameTag(this.getOwner().getName() + "'s Minion");

                        hasMinion.set(3);

                    }
                }
                else
                {
                    this.playTameEffect(false);
                    this.world.setEntityState(this, (byte)6);
                }
            }

            return true;
        }

        return super.processInteract(player, hand);
    }

    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    public int getMaxSpawnedInChunk()
    {
        return 8;
    }

    @Override
    public EntityMinionIron createChild(EntityAgeable ageable)
    {
        EntityMinionIron entitywolf = new EntityMinionIron(this.world);
        UUID uuid = this.getOwnerId();

        if(uuid != null)
        {
            entitywolf.setOwnerId(uuid);
            entitywolf.setTamed(true);
        }

        return entitywolf;
    }

    /**
     * Returns true if the mob is currently able to mate with the specified mob.
     */
    public boolean canMateWith(EntityAnimal otherAnimal)
    {
        return false;
    }

    class AIAvoidEntity<T extends Entity> extends EntityAIAvoidEntity<T>
    {
        private final EntityMinionIron wolf;

        public AIAvoidEntity(EntityMinionIron wolfIn, Class<T> p_i47251_3_, float p_i47251_4_, double p_i47251_5_, double p_i47251_7_)
        {
            super(wolfIn, p_i47251_3_, p_i47251_4_, p_i47251_5_, p_i47251_7_);
            this.wolf = wolfIn;
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            if(super.shouldExecute() && this.closestLivingEntity instanceof EntityLlama)
            {
                return !this.wolf.isTamed() && this.avoidLlama((EntityLlama)this.closestLivingEntity);
            }
            else
            {
                return false;
            }
        }

        private boolean avoidLlama(EntityLlama p_190854_1_)
        {
            return p_190854_1_.getStrength() >= EntityMinionIron.this.rand.nextInt(5);
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting()
        {
            EntityMinionIron.this.setAttackTarget((EntityLivingBase)null);
            super.startExecuting();
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask()
        {
            EntityMinionIron.this.setAttackTarget((EntityLivingBase)null);
            super.updateTask();
        }
    }
    
    @Override
    public void setDead()
    {
        super.setDead();

        if(this.getOwner() != null)
        {
            IHasMinion hasMinion = this.getOwner().getCapability(MinionProvider.MINION_CAP, null);

            hasMinion.set(0);
        }
    }
}
