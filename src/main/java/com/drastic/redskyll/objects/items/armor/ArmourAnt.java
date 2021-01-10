package com.drastic.redskyll.objects.items.armor;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.client.models.armor.ModelAnt;
import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.util.interfaces.IHasModel;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ArmourAnt extends ItemArmor implements IHasModel
{

    public ArmourAnt(String name, ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn)
    {
        super(materialIn, 1, equipmentSlotIn);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.REDSKYLL_TAB); 
        setMaxStackSize(1);
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
            if(itemStack.getItem() instanceof ItemArmor)
            {
                ModelAnt model = new ModelAnt();

                model.bipedHead.showModel = armorSlot == EntityEquipmentSlot.HEAD;

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

}
