package com.drastic.redskyll.client.models.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelMinion - DrasticLp
 * Created using Tabula 7.0.0
 */
public class ModelMinion extends ModelBase
{
    public ModelRenderer body;
    public ModelRenderer legRight;
    public ModelRenderer armLeft;
    public ModelRenderer legLeft;
    public ModelRenderer armLeft_1;

    public ModelMinion()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.legRight = new ModelRenderer(this, 0, 0);
        this.legRight.setRotationPoint(-1.0F, 19.9F, -4.0F);
        this.legRight.addBox(0.0F, 0.0F, 0.0F, 2, 4, 2, 0.0F);
        this.armLeft = new ModelRenderer(this, 0, 0);
        this.armLeft.setRotationPoint(-1.0F, 13.9F, 4.0F);
        this.armLeft.addBox(0.0F, 0.0F, 0.0F, 2, 5, 2, 0.0F);
        this.armLeft_1 = new ModelRenderer(this, 0, 0);
        this.armLeft_1.setRotationPoint(-1.0F, 13.9F, -6.0F);
        this.armLeft_1.addBox(0.0F, 0.0F, 0.0F, 2, 5, 2, 0.0F);
        this.legLeft = new ModelRenderer(this, 0, 0);
        this.legLeft.setRotationPoint(-1.0F, 19.9F, 2.0F);
        this.legLeft.addBox(0.0F, 0.0F, 0.0F, 2, 4, 2, 0.0F);
        this.body = new ModelRenderer(this, 0, 16);
        this.body.setRotationPoint(-4.0F, 11.9F, -4.0F);
        this.body.addBox(0.0F, 0.0F, 0.0F, 8, 8, 8, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        this.legRight.render(f5);
        this.armLeft.render(f5);
        this.armLeft_1.render(f5);
        this.legLeft.render(f5);
        this.body.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        this.legLeft.rotateAngleZ = MathHelper.cos(limbSwing * 0.6462f) * 1.4f * limbSwingAmount;
        this.armLeft.rotateAngleZ = MathHelper.cos(limbSwing * 0.6462f) * 1.4f * limbSwingAmount;
        this.armLeft_1.rotateAngleZ = MathHelper.cos(limbSwing * 0.6462f) * 1.4f * limbSwingAmount;
        this.legRight.rotateAngleZ = MathHelper.cos(limbSwing * 0.6462f) * 1.4f * limbSwingAmount;
    }
}
