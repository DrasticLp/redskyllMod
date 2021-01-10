package com.drastic.redskyll.client.models.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.12
// Paste this class into your mod and generate all required imports

public class athena_spear extends ModelBase
{
    private final ModelRenderer bone;

    public athena_spear()
    {
        textureWidth = 64;
        textureHeight = 64;

        bone = new ModelRenderer(this);
        bone.setRotationPoint(8.0F, 7.0F, -10.0F);
        setRotationAngle(bone, -0.6545F, 0.0F, 0.0F);
        bone.cubeList.add(new ModelBox(bone, 20, 53, -10.0F, -2.0F, 6.0F, 4, 2, 4, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 12, 27, -9.0F, -21.0F, 7.0F, 2, 19, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 10, -9.0F, -22.0F, 7.0F, 2, 1, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 10, -9.0F, -25.0F, 7.0F, 2, 3, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 8, 20, -9.0F, -22.0F, 6.0F, 2, 5, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 7, 16, -9.0F, -22.0F, 9.0F, 2, 5, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 10, 19, -7.0F, -22.0F, 7.0F, 1, 5, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 20, 21, -10.0F, -22.0F, 7.0F, 1, 5, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 10, -7.0F, -19.0F, 6.0F, 1, 3, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 10, -7.0F, -19.0F, 9.0F, 1, 3, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 10, -10.0F, -19.0F, 9.0F, 1, 3, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 10, -10.0F, -19.0F, 6.0F, 1, 3, 1, 0.0F, false));
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