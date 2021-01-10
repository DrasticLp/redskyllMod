package com.drastic.redskyll.client.models.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench 3.7.3

// Exported for Minecraft version 1.12
// Paste this class into your mod and generate all required imports

public class IceBlock extends ModelBase
{
    private final ModelRenderer bb_main;

    public IceBlock()
    {
        textureWidth = 32;
        textureHeight = 32;

        bb_main = new ModelRenderer(this);
        bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
        bb_main.cubeList.add(new ModelBox(bb_main, 4, 4, -3.0F, -11.0F, -3.0F, 6, 6, 6, 0.0F, false));
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        bb_main.render(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        this.bb_main.rotateAngleX = 0.5f * limbSwingAmount;
        this.bb_main.rotateAngleY = 0.5f * limbSwingAmount;
        this.bb_main.rotateAngleZ = 0.5f * limbSwingAmount;
    }
}