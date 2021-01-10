package com.drastic.redskyll.client.models.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelFuse extends ModelBase
{
    private final ModelRenderer bone;

    public ModelFuse()
    {
        textureWidth = 64;
        textureHeight = 64;

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 24.0F, -8.0F);
        bone.cubeList.add(new ModelBox(bone, 0, 29, -3.0F, -29.0F, 5.0F, 6, 29, 6, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 24, 59, -2.0F, -30.0F, 6.0F, 4, 1, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 24, 55, -1.0F, -32.0F, 7.0F, 2, 2, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 56, 48, -1.0F, -25.0F, 11.0F, 2, 13, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 48, 36, -1.0F, -23.0F, 1.0F, 2, 10, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 48, 36, -1.0F, -21.0F, -1.0F, 2, 10, 2, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 48, 48, -1.0F, -25.0F, 3.0F, 2, 13, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 56, 36, -1.0F, -23.0F, 13.0F, 2, 10, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 56, 36, -1.0F, -21.0F, 15.0F, 2, 10, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 2, 2, 3.0F, -25.0F, 7.0F, 2, 13, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 56, 12, -7.0F, -23.0F, 7.0F, 2, 10, 2, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 56, 0, -9.0F, -21.0F, 7.0F, 2, 10, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 2, 2, -5.0F, -25.0F, 7.0F, 2, 13, 2, 0.0F, true));
        bone.cubeList.add(new ModelBox(bone, 56, 24, 7.0F, -21.0F, 7.0F, 2, 10, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 56, 12, 5.0F, -23.0F, 7.0F, 2, 10, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 40, 58, 3.0F, -6.0F, 7.0F, 2, 4, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 40, 52, -7.0F, -4.0F, 7.0F, 2, 4, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 40, 58, -5.0F, -6.0F, 7.0F, 2, 4, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 40, 52, 5.0F, -4.0F, 7.0F, 2, 4, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 40, 58, -1.0F, -6.0F, 3.0F, 2, 4, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 40, 52, -1.0F, -4.0F, 1.0F, 2, 4, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 40, 58, -1.0F, -6.0F, 11.0F, 2, 4, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 40, 52, -1.0F, -4.0F, 13.0F, 2, 4, 2, 0.0F, false));
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        bone.render(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
        
    }
}