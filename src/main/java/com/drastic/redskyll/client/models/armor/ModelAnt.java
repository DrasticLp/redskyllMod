package com.drastic.redskyll.client.models.armor;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelAnt - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
public class ModelAnt extends ModelBiped
{
    public ModelRenderer Head;
    public ModelRenderer ant1;
    public ModelRenderer ant2;
    public ModelRenderer lunnette;
    public ModelRenderer lunnette_1;
    public ModelRenderer lunnette_2;
    public ModelRenderer lunnette_3;
    public ModelRenderer shape24;
    public ModelRenderer shape24_1;
    public ModelRenderer shape24_2;
    public ModelRenderer shape24_3;

    public ModelAnt()
    {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.ant2 = new ModelRenderer(this, 60, 0);
        this.ant2.setRotationPoint(-3.5F, -12.0F, -2.3F);
        this.ant2.addBox(0.0F, 0.0F, 0.0F, 2, 4, 2, 0.0F);
        this.shape24 = new ModelRenderer(this, 0, 120);
        this.shape24.setRotationPoint(1.0F, -6.0F, -6.0F);
        this.shape24.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
        this.lunnette = new ModelRenderer(this, 0, 120);
        this.lunnette.setRotationPoint(0.0F, -5.0F, -6.0F);
        this.lunnette.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.shape24_2 = new ModelRenderer(this, 0, 120);
        this.shape24_2.setRotationPoint(-3.0F, -3.0F, -6.0F);
        this.shape24_2.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
        this.lunnette_1 = new ModelRenderer(this, 0, 120);
        this.lunnette_1.setRotationPoint(-1.0F, -5.0F, -6.0F);
        this.lunnette_1.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.lunnette_3 = new ModelRenderer(this, 0, 120);
        this.lunnette_3.setRotationPoint(-4.0F, -5.0F, -6.0F);
        this.lunnette_3.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(-1.0F, 0.0F, -1.0F);
        this.Head.addBox(-4.0F, -8.0F, -4.0F, 10, 10, 10, 0.0F);
        this.ant1 = new ModelRenderer(this, 60, 0);
        this.ant1.setRotationPoint(1.5F, -12.0F, -2.3F);
        this.ant1.addBox(0.0F, 0.0F, 0.0F, 2, 4, 2, 0.0F);
        this.shape24_1 = new ModelRenderer(this, 0, 120);
        this.shape24_1.setRotationPoint(1.0F, -3.0F, -6.0F);
        this.shape24_1.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
        this.lunnette_2 = new ModelRenderer(this, 0, 120);
        this.lunnette_2.setRotationPoint(3.0F, -5.0F, -6.0F);
        this.lunnette_2.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.shape24_3 = new ModelRenderer(this, 0, 120);
        this.shape24_3.setRotationPoint(-3.0F, -6.0F, -6.0F);
        this.shape24_3.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
        
        this.bipedHead.addChild(ant1);
        this.bipedHead.addChild(ant2);
        this.bipedHead.addChild(lunnette);
        this.bipedHead.addChild(lunnette_1);
        this.bipedHead.addChild(lunnette_2);
        this.bipedHead.addChild(lunnette_3);
        this.bipedHead.addChild(shape24);
        this.bipedHead.addChild(shape24_1);
        this.bipedHead.addChild(shape24_2);
        this.bipedHead.addChild(shape24_3);
        this.bipedHead.addChild(Head);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {        
        super.render(entity, f, f1, f2, f3, f4, f5);
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
}
