package com.drastic.redskyll.client.renderer.entity;

import org.lwjgl.opengl.GL11;

import com.drastic.redskyll.client.models.entity.zeus_shield;
import com.drastic.redskyll.util.Reference;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.ResourceLocation;

public class RenderShield extends Render<EntityThrowable>
{
    protected static ModelBase model = new zeus_shield();

    public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/items/shield.png");

    private final float scale;

    public RenderShield(RenderManager renderManager, float scaleIn)
    {
        super(renderManager);
        this.scale = scaleIn;
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityThrowable entity)
    {
        return TEXTURES;
    }

    @Override
    public void doRender(EntityThrowable entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
        GL11.glPushMatrix();
        bindTexture(TEXTURES);
        GL11.glTranslated(x, y, z);
        GL11.glRotatef(180, 0, 0, 1);
        model.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }
}
