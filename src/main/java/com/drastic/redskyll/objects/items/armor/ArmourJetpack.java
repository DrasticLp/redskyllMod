package com.drastic.redskyll.objects.items.armor;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.client.models.armor.ModelJetpack;
import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.util.interfaces.IHasModel;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ArmourJetpack extends ItemArmor implements IHasModel
{
    public ArmourJetpack(String name, ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn)
    {
        super(materialIn, 1, equipmentSlotIn);
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

    @SideOnly(Side.CLIENT)
    @Override
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default)
    {
        if(itemStack != ItemStack.EMPTY)
        {
            if(itemStack.getItem() instanceof ArmourJetpack)
            {
                ModelJetpack model = new ModelJetpack();

                model.bipedHead.showModel = armorSlot == EntityEquipmentSlot.CHEST;

                model.isChild = _default.isChild;
                model.isRiding = _default.isRiding;
                model.isSneak = _default.isSneak;
                model.rightArmPose = _default.rightArmPose;
                model.leftArmPose = _default.leftArmPose;

                return model;
            }
        }

        return null;
    }
    
    public void flyUser(EntityPlayer user, ItemStack stack, ArmourJetpack item, boolean flyKeyDown)
    {
        System.out.println(user);
        System.out.println(stack);
        System.out.println(item);
        System.out.println(flyKeyDown);

        if(stack.getItem() instanceof ArmourJetpack)
        {
            if(flyKeyDown)
            {
                user.motionY = 5;
            }
        }
    }
}