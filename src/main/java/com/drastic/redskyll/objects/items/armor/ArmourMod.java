package com.drastic.redskyll.objects.items.armor;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.capabilities.Mana;
import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.util.interfaces.IHasModel;
import com.drastic.redskyll.util.interfaces.IMana;
import com.drastic.redskyll.util.provider.ManaProvider;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ArmourMod extends ItemArmor implements IHasModel
{
    private int timer = 20 * 10;

    public ArmourMod(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn)
    {
        super(materialIn, renderIndexIn, equipmentSlotIn);
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
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
    {          
        IMana mana = player.getCapability(ManaProvider.MANA_CAP, null);

        if(isFullSet(player) && world.isRemote)
        {
            if(mana.getMana() != 0)
            {
                player.capabilities.allowFlying = true;
                player.capabilities.setFlySpeed(0.11f);
                //player.capabilities.isFlying = true;
          }
            if(mana.getMana() == 0)
            {
                player.capabilities.allowFlying = false;
                player.capabilities.isFlying = false;
                player.capabilities.setFlySpeed(0.1f);
    }
        }
        if(isFullSet(player))
        {
            player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 10, 1));
            player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 10, 1));
            //player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 500, 1));
            player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 10, 1));
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 10, 2));

            if(player.isBurning())
            {
                player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 1, 0));

            }
            if(player.isWet())
            {
                player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 1, 0));
            }
        }
        else if(!isFullSet(player) && world.isRemote)
        {
            player.capabilities.allowFlying = false;
            player.capabilities.isFlying = false;
        }
    }
    
    public static boolean isFullSet(EntityPlayer player)
    {
        ItemStack head = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
        ItemStack chest = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        ItemStack legs = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
        ItemStack feet = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);

        return !head.isEmpty() && head.getItem() instanceof ArmourMod && !chest.isEmpty() && chest.getItem() instanceof ArmourMod && !legs.isEmpty() && legs.getItem() instanceof ArmourMod && !feet.isEmpty() && feet.getItem() instanceof ArmourMod;
    }
}