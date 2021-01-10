package com.drastic.redskyll.objects.entity;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.util.handlers.LootTableHandler;
import com.drastic.redskyll.util.handlers.SoundsHandler;
import com.drastic.redskyll.util.interfaces.IMana;
import com.drastic.redskyll.util.interfaces.IRelic;
import com.drastic.redskyll.util.provider.ManaProvider;
import com.drastic.redskyll.util.provider.RelicProvider;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIZombieAttack;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentKeybind;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityRedskyll extends EntityMob
{
    /** The attribute which determines the chance that this mob will spawn reinforcements */
    protected static final IAttribute SPAWN_REINFORCEMENTS_CHANCE = (new RangedAttribute((IAttribute)null, "zombie.spawnReinforcements", 0.0D, 0.0D, 1.0D)).setDescription("Spawn Reinforcements Chance");
    private static final UUID BABY_SPEED_BOOST_ID = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
    private static final AttributeModifier BABY_SPEED_BOOST = new AttributeModifier(BABY_SPEED_BOOST_ID, "Baby speed boost", 0.5D, 1);
    private static final DataParameter<Boolean> IS_CHILD = EntityDataManager.<Boolean>createKey(EntityRedskyll.class, DataSerializers.BOOLEAN);
    private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.PURPLE, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);
    /**
     * Was the type of villager for zombie villagers prior to 1.11. Now unused. Use {@link
     * EntityZombieVillager#PROFESSION} instead.
     */
    private static final DataParameter<Integer> VILLAGER_TYPE = EntityDataManager.<Integer>createKey(EntityRedskyll.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> ARMS_RAISED = EntityDataManager.<Boolean>createKey(EntityRedskyll.class, DataSerializers.BOOLEAN);
    private final EntityAIBreakDoor breakDoor = new EntityAIBreakDoor(this);
    private boolean isBreakDoorsTaskSet;
    /** The width of the entity */
    private float zombieWidth = -1.0F;
    /** The height of the the entity. */
    private float zombieHeight;

    public EntityRedskyll(World worldIn)
    {
        super(worldIn);
        this.setSize(0.6F, 1.95F);
    }

    public EntityRedskyll(World worldIn, double x, double y, double z)
    {
        super(worldIn);
        this.setSize(0.6F, 1.95F);
        this.setPosition(x, y, z);
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.tasks.addTask(3, new EntityAIAttackMelee(this, 1.0, false));
        this.applyEntityAI();
    }

    protected void applyEntityAI()
    {
        this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.33000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(50.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(125.0D);
        this.getAttributeMap().registerAttribute(SPAWN_REINFORCEMENTS_CHANCE).setBaseValue(this.rand.nextDouble() * net.minecraftforge.common.ForgeModContainer.zombieSummonBaseChance);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.getDataManager().register(IS_CHILD, Boolean.valueOf(false));
        this.getDataManager().register(VILLAGER_TYPE, Integer.valueOf(0));
        this.getDataManager().register(ARMS_RAISED, Boolean.valueOf(false));
    }

    public void setArmsRaised(boolean armsRaised)
    {
        this.getDataManager().set(ARMS_RAISED, Boolean.valueOf(armsRaised));
    }

    @SideOnly(Side.CLIENT)
    public boolean isArmsRaised()
    {
        return ((Boolean)this.getDataManager().get(ARMS_RAISED)).booleanValue();
    }

    public boolean isBreakDoorsTaskSet()
    {
        return this.isBreakDoorsTaskSet;
    }

    /**
     * Sets or removes EntityAIBreakDoor task
     */
    public void setBreakDoorsAItask(boolean enabled)
    {
        if(this.isBreakDoorsTaskSet != enabled)
        {
            this.isBreakDoorsTaskSet = enabled;
            ((PathNavigateGround)this.getNavigator()).setBreakDoors(enabled);

            if(enabled)
            {
                this.tasks.addTask(1, this.breakDoor);
            }
            else
            {
                this.tasks.removeTask(this.breakDoor);
            }
        }
    }

    /**
     * If Animal, checks if the age timer is negative
     */
    public boolean isChild()
    {
        return ((Boolean)this.getDataManager().get(IS_CHILD)).booleanValue();
    }

    /**
     * Get the experience points the entity currently has.
     */
    protected int getExperiencePoints(EntityPlayer player)
    {
        if(this.isChild())
        {
            this.experienceValue = (int)((float)this.experienceValue * 2.5F);
        }

        return super.getExperiencePoints(player);
    }

    /**
     * Set whether this zombie is a child.
     */
    public void setChild(boolean childZombie)
    {
        this.getDataManager().set(IS_CHILD, Boolean.valueOf(childZombie));

        if(this.world != null && !this.world.isRemote)
        {
            IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
            iattributeinstance.removeModifier(BABY_SPEED_BOOST);

            if(childZombie)
            {
                iattributeinstance.applyModifier(BABY_SPEED_BOOST);
            }
        }

        this.setChildSize(childZombie);
    }

    public void notifyDataManagerChange(DataParameter<?> key)
    {
        if(IS_CHILD.equals(key))
        {
            this.setChildSize(this.isChild());
        }

        super.notifyDataManagerChange(key);
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        if(this.world.isDaytime() && !this.world.isRemote && !this.isChild() && this.shouldBurnInDay())
        {
            float f = this.getBrightness();

            if(f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.world.canSeeSky(new BlockPos(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ)))
            {
                boolean flag = true;
                ItemStack itemstack = this.getItemStackFromSlot(EntityEquipmentSlot.HEAD);

                if(!itemstack.isEmpty())
                {
                    if(itemstack.isItemStackDamageable())
                    {
                        itemstack.setItemDamage(itemstack.getItemDamage() + this.rand.nextInt(2));

                        if(itemstack.getItemDamage() >= itemstack.getMaxDamage())
                        {
                            this.renderBrokenItemStack(itemstack);
                            this.setItemStackToSlot(EntityEquipmentSlot.HEAD, ItemStack.EMPTY);
                        }
                    }

                    flag = false;
                }

                if(flag)
                {
                    this.setFire(8);
                }
            }
        }

        super.onLivingUpdate();
    }

    protected boolean shouldBurnInDay()
    {
        return true;
    }

    public boolean attackEntityAsMob(Entity entityIn)
    {
        boolean flag = super.attackEntityAsMob(entityIn);

        if(flag)
        {
            float f = this.world.getDifficultyForLocation(new BlockPos(this)).getAdditionalDifficulty();

            if(this.getHeldItemMainhand().isEmpty() && this.isBurning() && this.rand.nextFloat() < f * 0.3F)
            {
                entityIn.setFire(2 * (int)f);
            }
        }

        return flag;
    }

    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_ZOMBIE_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundsHandler.RAGE1;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundsHandler.RAGE2;
    }

    protected SoundEvent getStepSound()
    {
        return SoundEvents.ENTITY_ZOMBIE_STEP;
    }

    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }
    
    @Nullable
    protected ResourceLocation getLootTable()
    {
        return /* LootTableHandler.REDSKYLL */ null;
    }

    public static void registerFixesZombie(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityZombie.class);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);

        if(this.isChild())
        {
            compound.setBoolean("IsBaby", true);
        }

        compound.setBoolean("CanBreakDoors", this.isBreakDoorsTaskSet());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);

        if(compound.getBoolean("IsBaby"))
        {
            this.setChild(true);
        }

        if(this.hasCustomName())
        {
            this.bossInfo.setName(this.getDisplayName());
        }

        this.setBreakDoorsAItask(compound.getBoolean("CanBreakDoors"));
    }

    public void setCustomNameTag(String name)
    {
        super.setCustomNameTag(name);
        this.bossInfo.setName(this.getDisplayName());
    }

    /**
     * This method gets called when the entity kills another one.
     */
    public void onKillEntity(EntityLivingBase entityLivingIn)
    {
        super.onKillEntity(entityLivingIn);

        if((this.world.getDifficulty() == EnumDifficulty.NORMAL || this.world.getDifficulty() == EnumDifficulty.HARD) && entityLivingIn instanceof EntityVillager)
        {
            if(this.world.getDifficulty() != EnumDifficulty.HARD && this.rand.nextBoolean())
            {
                return;
            }

            EntityVillager entityvillager = (EntityVillager)entityLivingIn;
            EntityZombieVillager entityzombievillager = new EntityZombieVillager(this.world);
            entityzombievillager.copyLocationAndAnglesFrom(entityvillager);
            this.world.removeEntity(entityvillager);
            entityzombievillager.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(entityzombievillager)), new EntityRedskyll.GroupData(false));
            entityzombievillager.setProfession(entityvillager.getProfession());
            entityzombievillager.setChild(entityvillager.isChild());
            entityzombievillager.setNoAI(entityvillager.isAIDisabled());

            if(entityvillager.hasCustomName())
            {
                entityzombievillager.setCustomNameTag(entityvillager.getCustomNameTag());
                entityzombievillager.setAlwaysRenderNameTag(entityvillager.getAlwaysRenderNameTag());
            }

            this.world.spawnEntity(entityzombievillager);
            this.world.playEvent((EntityPlayer)null, 1026, new BlockPos(this), 0);
        }
    }

    public float getEyeHeight()
    {
        float f = 1.74F;

        if(this.isChild())
        {
            f = (float)((double)f - 0.81D);
        }

        return f;
    }

    @Override
    protected void updateAITasks()
    {
        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());

        super.updateAITasks();
    }

    public void addTrackingPlayer(EntityPlayerMP player)
    {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    /**
     * Removes the given player from the list of players tracking this entity. See {@link Entity#addTrackingPlayer} for
     * more information on tracking.
     */
    public void removeTrackingPlayer(EntityPlayerMP player)
    {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    protected boolean canEquipItem(ItemStack stack)
    {
        return stack.getItem() == Items.EGG && this.isChild() && this.isRiding() ? false : super.canEquipItem(stack);
    }

    /**
     * Called only once on an entity when first time spawned, via egg, mob spawner, natural spawning etc, but not called
     * when entity is reloaded from nbt. Mainly used for initializing attributes and inventory
     */
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        float f = difficulty.getClampedAdditionalDifficulty();
        this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * f);

        if(this.rand.nextInt(4) == 0)
        {
            this.setCustomNameTag("Pue Au Pvp");
        }

        if(livingdata == null)
        {
            livingdata = new EntityRedskyll.GroupData(this.world.rand.nextFloat() < net.minecraftforge.common.ForgeModContainer.zombieBabyChance);
        }

        if(livingdata instanceof EntityRedskyll.GroupData)
        {
            EntityRedskyll.GroupData entityzombie$groupdata = (EntityRedskyll.GroupData)livingdata;

            if(entityzombie$groupdata.isChild)
            {
                this.setChild(true);

                if((double)this.world.rand.nextFloat() < 0.05D)
                {
                    List<EntityChicken> list = this.world.<EntityChicken>getEntitiesWithinAABB(EntityChicken.class, this.getEntityBoundingBox().grow(5.0D, 3.0D, 5.0D), EntitySelectors.IS_STANDALONE);

                    if(!list.isEmpty())
                    {
                        EntityChicken entitychicken = list.get(0);
                        entitychicken.setChickenJockey(true);
                        this.startRiding(entitychicken);
                    }
                }
                else if((double)this.world.rand.nextFloat() < 0.05D)
                {
                    EntityChicken entitychicken1 = new EntityChicken(this.world);
                    entitychicken1.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
                    entitychicken1.onInitialSpawn(difficulty, (IEntityLivingData)null);
                    entitychicken1.setChickenJockey(true);
                    this.world.spawnEntity(entitychicken1);
                    this.startRiding(entitychicken1);
                }
            }
        }

        this.setBreakDoorsAItask(this.rand.nextFloat() < f * 0.1F);
        this.setEquipmentBasedOnDifficulty(difficulty);
        this.setEnchantmentBasedOnDifficulty(difficulty);

        if(this.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty())
        {
            Calendar calendar = this.world.getCurrentDate();

            if(calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.rand.nextFloat() < 0.25F)
            {
                this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.LIT_PUMPKIN : Blocks.PUMPKIN));
                this.inventoryArmorDropChances[EntityEquipmentSlot.HEAD.getIndex()] = 0.0F;
            }
        }

        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextDouble() * 0.05000000074505806D, 0));
        double d0 = this.rand.nextDouble() * 1.5D * (double)f;

        if(d0 > 1.0D)
        {
            this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).applyModifier(new AttributeModifier("Random zombie-spawn bonus", d0, 2));
        }

        if(this.rand.nextFloat() < f * 0.05F)
        {
            this.getEntityAttribute(SPAWN_REINFORCEMENTS_CHANCE).applyModifier(new AttributeModifier("Leader zombie bonus", this.rand.nextDouble() * 0.25D + 0.5D, 0));
            this.setBreakDoorsAItask(true);
        }

        return livingdata;
    }

    /**
     * sets the size of the entity to be half of its current size if true.
     */
    public void setChildSize(boolean isChild)
    {
        this.multiplySize(isChild ? 0.5F : 1.0F);
    }

    /**
     * Sets the width and height of the entity.
     */
    protected final void setSize(float width, float height)
    {
        boolean flag = this.zombieWidth > 0.0F && this.zombieHeight > 0.0F;
        this.zombieWidth = width;
        this.zombieHeight = height;

        if(!flag)
        {
            this.multiplySize(1.0F);
        }
    }

    /**
     * Multiplies the height and width by the provided float.
     */
    protected final void multiplySize(float size)
    {
        super.setSize(this.zombieWidth * size, this.zombieHeight * size);
    }

    /**
     * Returns the Y Offset of this entity.
     */
    public double getYOffset()
    {
        return this.isChild() ? 0.0D : -0.45D;
    }

    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource cause)
    {
        super.onDeath(cause);

        if(cause.getTrueSource() instanceof EntityCreeper)
        {
            EntityCreeper entitycreeper = (EntityCreeper)cause.getTrueSource();

            if(entitycreeper.getPowered() && entitycreeper.ableToCauseSkullDrop())
            {
                entitycreeper.incrementDroppedSkulls();
                ItemStack itemstack = this.getSkullDrop();

                if(!itemstack.isEmpty())
                {
                    this.entityDropItem(itemstack, 0.0F);
                }
            }
        }
    }

    protected ItemStack getSkullDrop()
    {
        return new ItemStack(Items.SKULL, 1, 2);
    }

    class GroupData implements IEntityLivingData
    {
        public boolean isChild;

        private GroupData(boolean p_i47328_2_)
        {
            this.isChild = p_i47328_2_;
        }
    }

    @Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ItemInit.KRAMAZIA_SWORD));
        this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ItemInit.KRAMAZIA_HELMET));
        this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ItemInit.KRAMAZIA_CHESTPLATE));
        this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(Items.DIAMOND_LEGGINGS));
        this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(Items.DIAMOND_BOOTS));
    }

    @Override
    public boolean isNonBoss()
    {
        // TODO Auto-generated method stub
        return false;
    }
}
