package com.drastic.redskyll.client.models.armor;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.12
// Paste this class into your mod and generate all required imports

public class ModelJetpack extends ModelBiped
{
    private final ModelRenderer leftCanister;
    private final ModelRenderer rightCanister;
    private final ModelRenderer leftTip1;
    private final ModelRenderer leftTip2;
    private final ModelRenderer rightTip1;
    private final ModelRenderer rightTip2;
    private final ModelRenderer leftExhaust1;
    private final ModelRenderer leftExhaust2;
    private final ModelRenderer rightExhaust1;
    private final ModelRenderer rightExhaust2;
    private final ModelRenderer middle;

    public ModelJetpack()
    {
        super(1.0F, 0, 64, 64);

        this.bipedHeadwear.showModel = false;
        this.bipedHead.showModel = false;
        this.bipedBody.showModel = true;
        this.bipedLeftArm.showModel = true;
        this.bipedRightArm.showModel = true;
        this.bipedLeftLeg.showModel = false;
        this.bipedRightLeg.showModel = false;

        leftCanister = new ModelRenderer(this);
        leftCanister.setRotationPoint(0.0F, 0.0F, 0.0F);
        leftCanister.cubeList.add(new ModelBox(leftCanister, 15, 39, 0.5F, 2.0F, 2.6F, 4, 7, 4, 0.0F, true));

        rightCanister = new ModelRenderer(this);
        rightCanister.setRotationPoint(0.0F, 0.0F, 0.0F);
        rightCanister.cubeList.add(new ModelBox(rightCanister, 16, 44, -4.5F, 2.0F, 2.6F, 4, 7, 4, 0.0F, true));

        leftTip1 = new ModelRenderer(this);
        leftTip1.setRotationPoint(0.0F, 0.0F, 0.0F);
        leftTip1.cubeList.add(new ModelBox(leftTip1, 47, 13, 1.0F, 1.0F, 3.1F, 3, 1, 3, 0.0F, true));

        leftTip2 = new ModelRenderer(this);
        leftTip2.setRotationPoint(0.0F, 0.0F, 0.0F);
        leftTip2.cubeList.add(new ModelBox(leftTip2, 50, 44, 1.5F, -1.0F, 3.6F, 2, 2, 2, 0.0F, true));

        rightTip1 = new ModelRenderer(this);
        rightTip1.setRotationPoint(0.0F, 0.0F, 0.0F);
        rightTip1.cubeList.add(new ModelBox(rightTip1, 42, 5, -4.0F, 1.0F, 3.1F, 3, 1, 3, 0.0F, true));

        rightTip2 = new ModelRenderer(this);
        rightTip2.setRotationPoint(0.0F, 0.0F, 0.0F);
        rightTip2.cubeList.add(new ModelBox(rightTip2, 55, 47, -3.5F, -1.0F, 3.6F, 2, 2, 2, 0.0F, true));

        leftExhaust1 = new ModelRenderer(this);
        leftExhaust1.setRotationPoint(0.0F, 0.0F, 0.0F);
        leftExhaust1.cubeList.add(new ModelBox(leftExhaust1, 46, 9, 1.0F, 9.0F, 3.1F, 3, 1, 3, 0.0F, true));

        leftExhaust2 = new ModelRenderer(this);
        leftExhaust2.setRotationPoint(0.0F, 0.0F, 0.0F);
        leftExhaust2.cubeList.add(new ModelBox(leftExhaust2, 15, 42, 0.5F, 10.0F, 2.6F, 4, 2, 4, 0.0F, true));
        leftExhaust2.cubeList.add(new ModelBox(leftExhaust2, 1, 7, 0.5F, 12.0F, 2.6F, 4, 1, 4, 0.0F, true));

        rightExhaust1 = new ModelRenderer(this);
        rightExhaust1.setRotationPoint(0.0F, 0.0F, 0.0F);
        rightExhaust1.cubeList.add(new ModelBox(rightExhaust1, 43, 7, -4.0F, 9.0F, 3.1F, 3, 1, 3, 0.0F, true));

        rightExhaust2 = new ModelRenderer(this);
        rightExhaust2.setRotationPoint(-5.0F, 0.0F, 0.0F);
        rightExhaust2.cubeList.add(new ModelBox(rightExhaust2, 15, 42, 0.5F, 10.0F, 2.6F, 4, 2, 4, 0.0F, true));
        rightExhaust2.cubeList.add(new ModelBox(rightExhaust2, 1, 7, 0.5F, 12.0F, 2.6F, 4, 1, 4, 0.0F, true));

        middle = new ModelRenderer(this);
        middle.setRotationPoint(0.0F, 0.0F, 0.0F);
        middle.cubeList.add(new ModelBox(middle, 0, 54, -2.0F, 3.0F, 3.6F, 4, 5, 2, 0.0F, true));

        this.bipedBody.addChild(middle);
        this.bipedBody.addChild(leftCanister);
        this.bipedBody.addChild(rightCanister);
        this.bipedBody.addChild(leftTip1);
        this.bipedBody.addChild(leftTip2);
        this.bipedBody.addChild(rightTip1);
        this.bipedBody.addChild(rightTip2);
        this.bipedBody.addChild(leftExhaust1);
        this.bipedBody.addChild(leftExhaust2);
        this.bipedBody.addChild(rightExhaust1);
        this.bipedBody.addChild(rightExhaust2);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        middle.render(scale);
        leftCanister.render(scale);
        rightCanister.render(scale);
        leftTip1.render(scale);
        leftTip2.render(scale);
        rightTip1.render(scale);
        rightTip2.render(scale);
        leftExhaust1.render(scale);
        leftExhaust2.render(scale);
        rightExhaust1.render(scale);
        rightExhaust2.render(scale);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}