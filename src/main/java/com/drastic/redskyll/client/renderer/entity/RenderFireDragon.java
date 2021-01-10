package com.drastic.redskyll.client.renderer.entity;

import com.drastic.redskyll.client.models.entity.DragonCustom;
import com.drastic.redskyll.client.models.entity.ModelFuse;
import com.drastic.redskyll.objects.entity.EntityBabyFireDragon;
import com.drastic.redskyll.objects.entity.base.EntityFuseBase;
import com.drastic.redskyll.util.Reference;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelDragon;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderFireDragon extends RenderLiving<EntityBabyFireDragon> 
{
    protected static ModelBase modelFuse = new DragonCustom();

    public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/dragon_fire.png");

    public RenderFireDragon(RenderManager renderManager)
    {
        super(renderManager, modelFuse, 0.5f);
        bindTexture(TEXTURES);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityBabyFireDragon entity)
    {
        return TEXTURES;
    }   
}
