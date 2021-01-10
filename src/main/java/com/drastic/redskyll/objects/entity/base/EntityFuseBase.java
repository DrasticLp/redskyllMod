package com.drastic.redskyll.objects.entity.base;

import java.util.UUID;

import javax.annotation.Nullable;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.util.enums.EnumFuseType;
import com.drastic.redskyll.util.handlers.LootTableHandler;
import com.drastic.redskyll.util.handlers.SoundsHandler;
import com.drastic.redskyll.util.interfaces.IMana;
import com.drastic.redskyll.util.provider.ManaProvider;

import net.minecraft.block.SoundType;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityDonkey;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMule;
import net.minecraft.entity.passive.HorseArmorType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackData;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.client.event.sound.SoundSetupEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityFuseBase extends EntityHorse
{
    private static boolean isLaunched = false;
    private String customName;

    public EntityFuseBase(World worldIn)
    {
        super(worldIn);
    }

    public EntityFuseBase(World worldIn, double x, double y, double z)
    {
        super(worldIn);
        this.setPosition(x, y, z);
        this.setCustomNameTag(this.getCustomName());
    }

    public EnumFuseType getFuseType()
    {
        return EnumFuseType.NONE;
    }

    public String getCustomName()
    {
        return customName;
    }
    
    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        boolean flag = !itemstack.isEmpty();

        IMana mana = player.getCapability(ManaProvider.MANA_CAP, null);

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
            if(mana.getMana() >= 5)
            {
                this.mount(player);
                player.playSound(SoundsHandler.FUSE_LAUNCH, 9.0F, 0);
                mana.setMana(mana.getMana() - 5);

            }
            else
            {
                player.sendMessage(new TextComponentString("Vous n'avez pas assez de Mana"));
            }
        }
        else
        {
            return super.processInteract(player, hand);
        }

        return true;
    }

    @Override
    protected ResourceLocation getLootTable()
    {
        return LootTableHandler.FUSE;
    }

    private void mount(EntityPlayer player)
    {
        this.setRearing(false);
        if(!world.isRemote)
        {
            player.startRiding(this);
        }
        isLaunched = true;
    }

    protected void entityInit()
    {
        super.entityInit();
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
        // TODO Auto-generated method stub
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
        }

        if(isLaunched)
        {
            this.addVelocity(0, 1, 0);
        }

        world.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY, this.posZ, this.motionX, this.motionY, this.motionZ, 0);

    }

    @Override
    public boolean attackable()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean canBeAttackedWithItem()
    {
        // TODO Auto-generated method stub
        return false;
    }
}