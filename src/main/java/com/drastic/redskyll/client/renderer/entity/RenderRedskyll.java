package com.drastic.redskyll.client.renderer.entity;

import com.drastic.redskyll.objects.entity.EntityRedskyll;
import com.drastic.redskyll.util.Reference;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderRedskyll extends RenderLiving<EntityRedskyll>
{

    protected static ModelBiped modelFuse = new ModelBiped();

    public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/redskyll.png");

    public RenderRedskyll(RenderManager renderManager)
    {
        super(renderManager, modelFuse, 0.5f);
        bindTexture(TEXTURES);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityRedskyll entity)
    {
        return TEXTURES;
    }
}
