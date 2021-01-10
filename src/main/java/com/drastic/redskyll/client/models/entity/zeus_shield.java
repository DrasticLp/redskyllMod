package com.drastic.redskyll.client.models.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class zeus_shield extends ModelBase
{
    private final ModelRenderer boneex;

    private final ModelRenderer bone2;
    private final ModelRenderer bone;
    private final ModelRenderer hexadecagon;
    private final ModelRenderer hexadecagon_r1;
    private final ModelRenderer hexadecagon_r2;
    private final ModelRenderer hexadecagon_r3;
    private final ModelRenderer hexadecagon_r4;
    private final ModelRenderer hexadecagon_r5;
    private final ModelRenderer hexadecagon_r6;
    private final ModelRenderer hexadecagon_r7;
    private final ModelRenderer hexadecagon_r8;

    public zeus_shield()
    {
        textureWidth = 16;
        textureHeight = 16;

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(8.0F, -17.0F, -7.0F);
        setRotationAngle(bone2, -1.5708F, 0.0F, 0.0F);

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone2.addChild(bone);
        bone.cubeList.add(new ModelBox(bone, 0, 1, -9.5F, -12.0F, 5.5F, 1, 4, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 1, -7.5F, -8.0F, 5.5F, 1, 4, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 1, -8.5F, -10.0F, 5.5F, 1, 4, 1, 0.0F, false));

        hexadecagon = new ModelRenderer(this);
        hexadecagon.setRotationPoint(-8.0F, -8.0F, 8.0F);
        bone2.addChild(hexadecagon);
        hexadecagon.cubeList.add(new ModelBox(hexadecagon, 0, 0, -1.3924F, -7.0F, -1.5F, 2, 14, 1, 0.0F, false));
        hexadecagon.cubeList.add(new ModelBox(hexadecagon, 0, 0, -7.0F, -1.3924F, -1.5F, 14, 2, 1, 0.0F, false));
        hexadecagon.cubeList.add(new ModelBox(hexadecagon, 4, 5, -1.5913F, -8.0F, -0.5F, 3, 16, 1, 0.0F, false));
        hexadecagon.cubeList.add(new ModelBox(hexadecagon, 4, 5, -8.0F, -1.5913F, -0.5F, 16, 3, 1, 0.0F, false));

        hexadecagon_r1 = new ModelRenderer(this);
        hexadecagon_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        hexadecagon.addChild(hexadecagon_r1);
        setRotationAngle(hexadecagon_r1, 0.0F, 0.0F, 0.3927F);
        hexadecagon_r1.cubeList.add(new ModelBox(hexadecagon_r1, 0, 0, -7.0F, -1.3924F, -1.5F, 14, 2, 1, 0.0F, false));
        hexadecagon_r1.cubeList.add(new ModelBox(hexadecagon_r1, 0, 0, -1.3924F, -7.0F, -1.5F, 2, 14, 1, 0.0F, false));

        hexadecagon_r2 = new ModelRenderer(this);
        hexadecagon_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
        hexadecagon.addChild(hexadecagon_r2);
        setRotationAngle(hexadecagon_r2, 0.0F, 0.0F, -0.3927F);
        hexadecagon_r2.cubeList.add(new ModelBox(hexadecagon_r2, 0, 0, -7.0F, -1.3924F, -1.5F, 14, 2, 1, 0.0F, false));
        hexadecagon_r2.cubeList.add(new ModelBox(hexadecagon_r2, 0, 0, -1.3924F, -7.0F, -1.5F, 2, 14, 1, 0.0F, false));

        hexadecagon_r3 = new ModelRenderer(this);
        hexadecagon_r3.setRotationPoint(0.0F, 0.0F, 0.0F);
        hexadecagon.addChild(hexadecagon_r3);
        setRotationAngle(hexadecagon_r3, 0.0F, 0.0F, 0.7854F);
        hexadecagon_r3.cubeList.add(new ModelBox(hexadecagon_r3, 0, 0, -1.3924F, -7.0F, -1.5F, 2, 14, 1, 0.0F, false));

        hexadecagon_r4 = new ModelRenderer(this);
        hexadecagon_r4.setRotationPoint(0.0F, 0.0F, 0.0F);
        hexadecagon.addChild(hexadecagon_r4);
        setRotationAngle(hexadecagon_r4, 0.0F, 0.0F, -0.7854F);
        hexadecagon_r4.cubeList.add(new ModelBox(hexadecagon_r4, 0, 0, -1.3924F, -7.0F, -1.5F, 2, 14, 1, 0.0F, false));

        hexadecagon_r5 = new ModelRenderer(this);
        hexadecagon_r5.setRotationPoint(0.0F, 0.0F, 1.0F);
        hexadecagon.addChild(hexadecagon_r5);
        setRotationAngle(hexadecagon_r5, 0.0F, 0.0F, 0.3927F);
        hexadecagon_r5.cubeList.add(new ModelBox(hexadecagon_r5, 4, 5, -8.0F, -1.5913F, -1.5F, 16, 3, 1, 0.0F, false));
        hexadecagon_r5.cubeList.add(new ModelBox(hexadecagon_r5, 4, 5, -1.5913F, -8.0F, -1.5F, 3, 16, 1, 0.0F, false));

        hexadecagon_r6 = new ModelRenderer(this);
        hexadecagon_r6.setRotationPoint(0.0F, 0.0F, 1.0F);
        hexadecagon.addChild(hexadecagon_r6);
        setRotationAngle(hexadecagon_r6, 0.0F, 0.0F, -0.3927F);
        hexadecagon_r6.cubeList.add(new ModelBox(hexadecagon_r6, 4, 5, -8.0F, -1.5913F, -1.5F, 16, 3, 1, 0.0F, false));
        hexadecagon_r6.cubeList.add(new ModelBox(hexadecagon_r6, 4, 5, -1.5913F, -8.0F, -1.5F, 3, 16, 1, 0.0F, false));

        hexadecagon_r7 = new ModelRenderer(this);
        hexadecagon_r7.setRotationPoint(0.0F, 0.0F, 1.0F);
        hexadecagon.addChild(hexadecagon_r7);
        setRotationAngle(hexadecagon_r7, 0.0F, 0.0F, 0.7854F);
        hexadecagon_r7.cubeList.add(new ModelBox(hexadecagon_r7, 4, 5, -1.5913F, -8.0F, -1.5F, 3, 16, 1, 0.0F, false));

        hexadecagon_r8 = new ModelRenderer(this);
        hexadecagon_r8.setRotationPoint(0.0F, 0.0F, 1.0F);
        hexadecagon.addChild(hexadecagon_r8);
        setRotationAngle(hexadecagon_r8, 0.0F, 0.0F, -0.7854F);
        hexadecagon_r8.cubeList.add(new ModelBox(hexadecagon_r8, 4, 5, -1.5913F, -8.0F, -1.5F, 3, 16, 1, 0.0F, false));
        
        boneex = new ModelRenderer(this);
        boneex.addChild(bone);
        boneex.addChild(bone2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        bone2.render(f5);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        boneex.rotateAngleX = ageInTicks * 0.5f;
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
    }
    
    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}