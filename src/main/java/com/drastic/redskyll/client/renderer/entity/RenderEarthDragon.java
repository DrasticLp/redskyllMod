package com.drastic.redskyll.client.renderer.entity;

import com.drastic.redskyll.client.models.entity.DragonCustom;
import com.drastic.redskyll.objects.entity.EntityBabyEarthDragon;
import com.drastic.redskyll.objects.entity.EntityBabyFireDragon;
import com.drastic.redskyll.objects.entity.EntityBabyIceDragon;
import com.drastic.redskyll.util.Reference;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderEarthDragon extends RenderLiving<EntityBabyEarthDragon> 
{
    protected static ModelBase modelFuse = new DragonCustom();

    public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/dragon_earth.png");

    public RenderEarthDragon(RenderManager renderManager)
    {
        super(renderManager, modelFuse, 0.5f);
        bindTexture(TEXTURES);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityBabyEarthDragon entity)
    {
        return TEXTURES;
    }   
}
