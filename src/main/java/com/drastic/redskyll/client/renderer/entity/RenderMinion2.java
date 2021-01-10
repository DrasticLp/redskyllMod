package com.drastic.redskyll.client.renderer.entity;

import com.drastic.redskyll.client.models.entity.ModelMinion;
import com.drastic.redskyll.objects.entity.EntityMinionGold;
import com.drastic.redskyll.util.Reference;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderMinion2 extends RenderLiving<EntityMinionGold>
{
    protected static ModelBase modelFuse = new ModelMinion();

    public static final ResourceLocation TEXTURES1 = new ResourceLocation(Reference.MODID + ":textures/entity/minion2.png");

    public RenderMinion2(RenderManager renderManager)
    {
        super(renderManager, modelFuse, 0.5f);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityMinionGold entity)
    {
            return TEXTURES1;
    }
}
