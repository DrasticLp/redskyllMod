package com.drastic.redskyll.client.renderer.entity;

import com.drastic.redskyll.client.models.entity.ModelMinion;
import com.drastic.redskyll.objects.entity.EntityMinionDiamond;
import com.drastic.redskyll.util.Reference;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderMinion extends RenderLiving<EntityMinionDiamond>
{
    protected static ModelBase modelFuse = new ModelMinion();

    public static final ResourceLocation TEXTURES1 = new ResourceLocation(Reference.MODID + ":textures/entity/minion.png");

    public RenderMinion(RenderManager renderManager)
    {
        super(renderManager, modelFuse, 0.5f);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityMinionDiamond entity)
    {
            return TEXTURES1;
    }
}
