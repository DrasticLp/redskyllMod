package com.drastic.redskyll.client.renderer.entity;

import com.drastic.redskyll.client.models.ModelScorpion;
import com.drastic.redskyll.objects.entity.EntityScorpion;
import com.drastic.redskyll.util.Reference;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderScorpion<T extends EntityScorpion> extends RenderLiving<T>
{
    private static final ResourceLocation SPIDER_TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/scorpion.png");

    public RenderScorpion(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelScorpion(), 1.0F);
    }

    protected float getDeathMaxRotation(T entityLivingBaseIn)
    {
        return 180.0F;
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(T entity)
    {
        return SPIDER_TEXTURES;
    }
}