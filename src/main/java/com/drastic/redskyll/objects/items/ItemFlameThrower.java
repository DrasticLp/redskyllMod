package com.drastic.redskyll.objects.items;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.util.interfaces.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemFlameThrower extends Item implements IHasModel
{
    public ItemFlameThrower(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.REDSKYLL_TAB);
        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRederer(this, 0);
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        if(!worldIn.isRemote)
        {
            double d1 = 4.0D;

            Vec3d vec3d = playerIn.getLook(1.0F);
            double d2 = playerIn.posX - (playerIn.posX + vec3d.x * 4.0D);
            double d3 = playerIn.getEntityBoundingBox().minY + (double)(playerIn.height / 2.0F) - (0.5D + playerIn.posY + (double)(playerIn.height / 2.0F));
            double d4 = playerIn.posZ - (playerIn.posZ + vec3d.z * 4.0D);
            EntityLargeFireball entitylargefireball = new EntityLargeFireball(worldIn, playerIn, d2, d3, d4);
            entitylargefireball.explosionPower = 1;
            entitylargefireball.posX = playerIn.posX + vec3d.x * 4.0D;
            entitylargefireball.posY = playerIn.posY + (double)(playerIn.height) + 0.5D;
            entitylargefireball.posZ = playerIn.posZ + vec3d.z * 4.0D;
            worldIn.spawnEntity(entitylargefireball);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
