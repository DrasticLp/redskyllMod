package com.drastic.redskyll.objects.entity;

import com.drastic.redskyll.init.EnchantmentInit;
import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.objects.items.ItemZeusShield;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;

public class EntityShield extends EntityThrowable
{
    private EntityPlayer owner;
    private int level;
    
    public EntityShield(World worldIn)
    {
        super(worldIn);
        this.setSize(1.4F, 0.9F);

    }

    public EntityShield(World worldIn, double x, double y, double z, EntityPlayer player, int level)
    {
        super(worldIn, x, y, z);
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        this.owner = player;
        this.level = level;
        this.setSize(1.4F, 0.9F);

    }

    @Override
    protected void onImpact(RayTraceResult result)
    {
        if(result.typeOfHit != Type.MISS)
        {
            if(this.owner != null)
            {
                if(result.entityHit != this.owner)
                {
                    if(result.typeOfHit == Type.ENTITY)
                    {
                        result.entityHit.attackEntityFrom(DamageSource.causePlayerDamage(this.owner), 5 * (this.level + 1));
                    }
                    ItemStack stack = new ItemStack(ItemInit.ZEUS_SHIELD);
                    if(this.level != 0)
                    {
                        stack.addEnchantment(EnchantmentInit.DEMON_BREAKER, this.level);
                    }
                    
                    this.owner.setHeldItem(EnumHand.OFF_HAND, stack);
                    this.setDead();
                }
            }
        }
    }

    @Override
    public void onUpdate()
    {
        if(this.posY == 1000 && this.owner != null)
        {
            ItemStack stack = new ItemStack(ItemInit.ZEUS_SHIELD);
            this.owner.addItemStackToInventory(stack);
            this.setDead();
        }
        super.onUpdate();
    }
}
