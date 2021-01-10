package com.drastic.redskyll.client.models.entity;
// Made with Blockbench 3.7.2

import com.drastic.redskyll.objects.entity.base.EntityBabyDragonBase;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

// Exported for Minecraft version 1.12
// Paste this class into your mod and generate all required imports

public class DragonCustom extends ModelBase
{
    private final ModelRenderer root;
    private final ModelRenderer PatteArriereGauche;
    private final ModelRenderer pattegauche7;
    private final ModelRenderer pattegauche6;
    private final ModelRenderer pattegauche5;
    private final ModelRenderer pattegauche4;
    private final ModelRenderer pattegauche3;
    private final ModelRenderer pattegauche2;
    private final ModelRenderer pattegauche1orange;
    private final ModelRenderer PatteArriereDroite;
    private final ModelRenderer pattedroite7;
    private final ModelRenderer pattedroite6;
    private final ModelRenderer pattedroite5;
    private final ModelRenderer pattedroite4;
    private final ModelRenderer pattedroite3;
    private final ModelRenderer pattedoite2;
    private final ModelRenderer pattedroite1orange;
    private final ModelRenderer PatteAvantDroite;
    private final ModelRenderer pattedroiteavant1;
    private final ModelRenderer pattedroiteavant2;
    private final ModelRenderer pattedroiteavant3;
    private final ModelRenderer pattedroiteavant4;
    private final ModelRenderer pattedroiteavant5;
    private final ModelRenderer pattedroiteavant6;
    private final ModelRenderer PatteAvantGauche;
    private final ModelRenderer pattegaucheavant6;
    private final ModelRenderer pattegaucheavant5;
    private final ModelRenderer pattegaucheavant4;
    private final ModelRenderer pattegaucheavant3;
    private final ModelRenderer pattegaucheavant2;
    private final ModelRenderer pattegaucheavant1;
    private final ModelRenderer Tete;
    private final ModelRenderer tete1;
    private final ModelRenderer tete2;
    private final ModelRenderer tete3;
    private final ModelRenderer tete4;
    private final ModelRenderer tete5;
    private final ModelRenderer oeilgauhe;
    private final ModelRenderer oeildroit;
    private final ModelRenderer cornedroite1;
    private final ModelRenderer cornedroite2;
    private final ModelRenderer cornegauche1;
    private final ModelRenderer cornegauche2;
    private final ModelRenderer Museau1;
    private final ModelRenderer Museau2;
    private final ModelRenderer Corps;
    private final ModelRenderer corps1;
    private final ModelRenderer corps2;
    private final ModelRenderer corps3;
    private final ModelRenderer corps4;
    private final ModelRenderer corpsbasduventre;
    private final ModelRenderer corps6;
    private final ModelRenderer corps7;
    private final ModelRenderer corpsbasduventresuite;
    private final ModelRenderer corps8;
    private final ModelRenderer TAIL;
    private final ModelRenderer queue1;
    private final ModelRenderer queue1_r1;
    private final ModelRenderer queue2;
    private final ModelRenderer queue2_r1;
    private final ModelRenderer AileGauche;
    private final ModelRenderer ailegauche1;
    private final ModelRenderer ailegauche2;
    private final ModelRenderer ailegauche3;
    private final ModelRenderer ailedegauche4;
    private final ModelRenderer ailedegauche5;
    private final ModelRenderer peaudelailegauche1;
    private final ModelRenderer peaudelailegauche2;
    private final ModelRenderer AileDroite;
    private final ModelRenderer aileDroite1;
    private final ModelRenderer ailegauche1_r1;
    private final ModelRenderer aileDroite2;
    private final ModelRenderer ailegauche2_r1;
    private final ModelRenderer aileDroite3;
    private final ModelRenderer aileDroite4;
    private final ModelRenderer ailedegauche4_r1;
    private final ModelRenderer aileDroite5;
    private final ModelRenderer peauDroite1;
    private final ModelRenderer peaudelailegauche1_r1;
    private final ModelRenderer peauDroite2;
    private final ModelRenderer peaudelailegauche2_r1;

    public DragonCustom()
    {
        textureWidth = 256;
        textureHeight = 128;

        root = new ModelRenderer(this);
        root.setRotationPoint(0.0F, 24.0F, 0.0F);

        PatteArriereGauche = new ModelRenderer(this);
        PatteArriereGauche.setRotationPoint(-5.0F, -10.0F, 7.0F);
        root.addChild(PatteArriereGauche);

        pattegauche7 = new ModelRenderer(this);
        pattegauche7.setRotationPoint(1.0F, 0.0F, -2.0F);
        PatteArriereGauche.addChild(pattegauche7);
        pattegauche7.cubeList.add(new ModelBox(pattegauche7, 142, 1, -2.0F, 0.0F, 0.0F, 2, 2, 6, 0.0F, false));

        pattegauche6 = new ModelRenderer(this);
        pattegauche6.setRotationPoint(1.0F, 2.0F, -2.0F);
        PatteArriereGauche.addChild(pattegauche6);
        pattegauche6.cubeList.add(new ModelBox(pattegauche6, 160, 1, -4.0F, 0.0F, 0.0F, 4, 2, 2, 0.0F, false));

        pattegauche5 = new ModelRenderer(this);
        pattegauche5.setRotationPoint(3.0F, 4.0F, 0.0F);
        PatteArriereGauche.addChild(pattegauche5);
        pattegauche5.cubeList.add(new ModelBox(pattegauche5, 176, 1, -2.0F, 0.0F, 0.0F, 2, 2, 4, 0.0F, false));

        pattegauche4 = new ModelRenderer(this);
        pattegauche4.setRotationPoint(1.0F, 2.0F, 0.0F);
        PatteArriereGauche.addChild(pattegauche4);
        pattegauche4.cubeList.add(new ModelBox(pattegauche4, 193, 1, -4.0F, 0.0F, 0.0F, 4, 4, 4, 0.0F, false));

        pattegauche3 = new ModelRenderer(this);
        pattegauche3.setRotationPoint(1.0F, 6.0F, 0.0F);
        PatteArriereGauche.addChild(pattegauche3);
        pattegauche3.cubeList.add(new ModelBox(pattegauche3, 212, 1, -4.0F, 0.0F, 0.0F, 4, 2, 2, 0.0F, false));

        pattegauche2 = new ModelRenderer(this);
        pattegauche2.setRotationPoint(1.0F, 8.0F, -2.0F);
        PatteArriereGauche.addChild(pattegauche2);
        pattegauche2.cubeList.add(new ModelBox(pattegauche2, 226, 1, -4.0F, 0.0F, 0.0F, 4, 2, 4, 0.0F, false));

        pattegauche1orange = new ModelRenderer(this);
        pattegauche1orange.setRotationPoint(1.0F, 8.0F, -4.0F);
        PatteArriereGauche.addChild(pattegauche1orange);
        pattegauche1orange.cubeList.add(new ModelBox(pattegauche1orange, 243, 1, -4.0F, 0.0F, 0.0F, 4, 2, 2, 0.0F, false));

        PatteArriereDroite = new ModelRenderer(this);
        PatteArriereDroite.setRotationPoint(5.0F, -10.0F, 7.0F);
        root.addChild(PatteArriereDroite);

        pattedroite7 = new ModelRenderer(this);
        pattedroite7.setRotationPoint(1.0F, 0.0F, -2.0F);
        PatteArriereDroite.addChild(pattedroite7);
        pattedroite7.cubeList.add(new ModelBox(pattedroite7, 142, 10, -2.0F, 0.0F, 0.0F, 2, 2, 6, 0.0F, false));

        pattedroite6 = new ModelRenderer(this);
        pattedroite6.setRotationPoint(3.0F, 2.0F, -2.0F);
        PatteArriereDroite.addChild(pattedroite6);
        pattedroite6.cubeList.add(new ModelBox(pattedroite6, 160, 10, -4.0F, 0.0F, 0.0F, 4, 2, 2, 0.0F, false));

        pattedroite5 = new ModelRenderer(this);
        pattedroite5.setRotationPoint(-1.0F, 4.0F, 0.0F);
        PatteArriereDroite.addChild(pattedroite5);
        pattedroite5.cubeList.add(new ModelBox(pattedroite5, 176, 10, -2.0F, 0.0F, 0.0F, 2, 2, 4, 0.0F, false));

        pattedroite4 = new ModelRenderer(this);
        pattedroite4.setRotationPoint(3.0F, 2.0F, 0.0F);
        PatteArriereDroite.addChild(pattedroite4);
        pattedroite4.cubeList.add(new ModelBox(pattedroite4, 193, 10, -4.0F, 0.0F, 0.0F, 4, 4, 4, 0.0F, false));

        pattedroite3 = new ModelRenderer(this);
        pattedroite3.setRotationPoint(3.0F, 6.0F, 0.0F);
        PatteArriereDroite.addChild(pattedroite3);
        pattedroite3.cubeList.add(new ModelBox(pattedroite3, 212, 10, -4.0F, 0.0F, 0.0F, 4, 2, 2, 0.0F, false));

        pattedoite2 = new ModelRenderer(this);
        pattedoite2.setRotationPoint(3.0F, 8.0F, -2.0F);
        PatteArriereDroite.addChild(pattedoite2);
        pattedoite2.cubeList.add(new ModelBox(pattedoite2, 226, 10, -4.0F, 0.0F, 0.0F, 4, 2, 4, 0.0F, false));

        pattedroite1orange = new ModelRenderer(this);
        pattedroite1orange.setRotationPoint(3.0F, 8.0F, -4.0F);
        PatteArriereDroite.addChild(pattedroite1orange);
        pattedroite1orange.cubeList.add(new ModelBox(pattedroite1orange, 243, 10, -4.0F, 0.0F, 0.0F, 4, 2, 2, 0.0F, false));

        PatteAvantDroite = new ModelRenderer(this);
        PatteAvantDroite.setRotationPoint(5.0F, -10.0F, -6.0F);
        root.addChild(PatteAvantDroite);

        pattedroiteavant1 = new ModelRenderer(this);
        pattedroiteavant1.setRotationPoint(1.0F, 8.0F, -5.0F);
        PatteAvantDroite.addChild(pattedroiteavant1);
        pattedroiteavant1.cubeList.add(new ModelBox(pattedroiteavant1, 243, 30, -4.0F, 0.0F, 0.0F, 4, 2, 2, 0.0F, false));

        pattedroiteavant2 = new ModelRenderer(this);
        pattedroiteavant2.setRotationPoint(1.0F, 8.0F, -3.0F);
        PatteAvantDroite.addChild(pattedroiteavant2);
        pattedroiteavant2.cubeList.add(new ModelBox(pattedroiteavant2, 226, 30, -4.0F, 0.0F, 0.0F, 4, 2, 4, 0.0F, false));

        pattedroiteavant3 = new ModelRenderer(this);
        pattedroiteavant3.setRotationPoint(1.0F, 6.0F, -1.0F);
        PatteAvantDroite.addChild(pattedroiteavant3);
        pattedroiteavant3.cubeList.add(new ModelBox(pattedroiteavant3, 212, 30, -4.0F, 0.0F, 0.0F, 4, 2, 2, 0.0F, false));

        pattedroiteavant4 = new ModelRenderer(this);
        pattedroiteavant4.setRotationPoint(1.0F, 4.0F, -1.0F);
        PatteAvantDroite.addChild(pattedroiteavant4);
        pattedroiteavant4.cubeList.add(new ModelBox(pattedroiteavant4, 193, 30, -4.0F, 0.0F, 0.0F, 4, 2, 4, 0.0F, false));

        pattedroiteavant5 = new ModelRenderer(this);
        pattedroiteavant5.setRotationPoint(1.0F, 2.0F, -1.0F);
        PatteAvantDroite.addChild(pattedroiteavant5);
        pattedroiteavant5.cubeList.add(new ModelBox(pattedroiteavant5, 173, 30, -2.0F, 0.0F, 0.0F, 2, 2, 4, 0.0F, false));

        pattedroiteavant6 = new ModelRenderer(this);
        pattedroiteavant6.setRotationPoint(1.0F, 0.0F, -1.0F);
        PatteAvantDroite.addChild(pattedroiteavant6);
        pattedroiteavant6.cubeList.add(new ModelBox(pattedroiteavant6, 162, 30, -2.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F, false));

        PatteAvantGauche = new ModelRenderer(this);
        PatteAvantGauche.setRotationPoint(-5.0F, -10.0F, -6.0F);
        root.addChild(PatteAvantGauche);

        pattegaucheavant6 = new ModelRenderer(this);
        pattegaucheavant6.setRotationPoint(1.0F, 0.0F, -1.0F);
        PatteAvantGauche.addChild(pattegaucheavant6);
        pattegaucheavant6.cubeList.add(new ModelBox(pattegaucheavant6, 162, 20, -2.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F, false));

        pattegaucheavant5 = new ModelRenderer(this);
        pattegaucheavant5.setRotationPoint(1.0F, 2.0F, -1.0F);
        PatteAvantGauche.addChild(pattegaucheavant5);
        pattegaucheavant5.cubeList.add(new ModelBox(pattegaucheavant5, 173, 20, -2.0F, 0.0F, 0.0F, 2, 2, 4, 0.0F, false));

        pattegaucheavant4 = new ModelRenderer(this);
        pattegaucheavant4.setRotationPoint(3.0F, 4.0F, -1.0F);
        PatteAvantGauche.addChild(pattegaucheavant4);
        pattegaucheavant4.cubeList.add(new ModelBox(pattegaucheavant4, 193, 20, -4.0F, 0.0F, 0.0F, 4, 2, 4, 0.0F, false));

        pattegaucheavant3 = new ModelRenderer(this);
        pattegaucheavant3.setRotationPoint(3.0F, 6.0F, -1.0F);
        PatteAvantGauche.addChild(pattegaucheavant3);
        pattegaucheavant3.cubeList.add(new ModelBox(pattegaucheavant3, 212, 20, -4.0F, 0.0F, 0.0F, 4, 2, 2, 0.0F, false));

        pattegaucheavant2 = new ModelRenderer(this);
        pattegaucheavant2.setRotationPoint(3.0F, 8.0F, -3.0F);
        PatteAvantGauche.addChild(pattegaucheavant2);
        pattegaucheavant2.cubeList.add(new ModelBox(pattegaucheavant2, 226, 20, -4.0F, 0.0F, 0.0F, 4, 2, 4, 0.0F, false));

        pattegaucheavant1 = new ModelRenderer(this);
        pattegaucheavant1.setRotationPoint(3.0F, 8.0F, -5.0F);
        PatteAvantGauche.addChild(pattegaucheavant1);
        pattegaucheavant1.cubeList.add(new ModelBox(pattegaucheavant1, 243, 20, -4.0F, 0.0F, 0.0F, 4, 2, 2, 0.0F, false));

        Tete = new ModelRenderer(this);
        Tete.setRotationPoint(0.0F, -12.0F, -5.0F);
        root.addChild(Tete);

        tete1 = new ModelRenderer(this);
        tete1.setRotationPoint(4.0F, -2.0F, -8.0F);
        Tete.addChild(tete1);
        tete1.cubeList.add(new ModelBox(tete1, 218, 50, -8.0F, 0.0F, 0.0F, 8, 2, 10, 0.0F, false));

        tete2 = new ModelRenderer(this);
        tete2.setRotationPoint(4.0F, -4.0F, -8.0F);
        Tete.addChild(tete2);
        tete2.cubeList.add(new ModelBox(tete2, 183, 50, -8.0F, 0.0F, 0.0F, 8, 2, 8, 0.0F, false));

        tete3 = new ModelRenderer(this);
        tete3.setRotationPoint(2.0F, -4.0F, 0.0F);
        Tete.addChild(tete3);
        tete3.cubeList.add(new ModelBox(tete3, 168, 50, -4.0F, 0.0F, 0.0F, 4, 2, 2, 0.0F, false));

        tete4 = new ModelRenderer(this);
        tete4.setRotationPoint(4.0F, -8.0F, -4.0F);
        Tete.addChild(tete4);
        tete4.cubeList.add(new ModelBox(tete4, 140, 50, -8.0F, 0.0F, 0.0F, 8, 4, 4, 0.0F, false));

        tete5 = new ModelRenderer(this);
        tete5.setRotationPoint(2.0F, -8.0F, -6.0F);
        Tete.addChild(tete5);
        tete5.cubeList.add(new ModelBox(tete5, 124, 50, -4.0F, 0.0F, 0.0F, 4, 4, 2, 0.0F, false));

        oeilgauhe = new ModelRenderer(this);
        oeilgauhe.setRotationPoint(-2.0F, -6.0F, -6.0F);
        Tete.addChild(oeilgauhe);
        oeilgauhe.cubeList.add(new ModelBox(oeilgauhe, 112, 50, -2.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F, false));

        oeildroit = new ModelRenderer(this);
        oeildroit.setRotationPoint(4.0F, -6.0F, -6.0F);
        Tete.addChild(oeildroit);
        oeildroit.cubeList.add(new ModelBox(oeildroit, 100, 50, -2.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F, false));

        cornedroite1 = new ModelRenderer(this);
        cornedroite1.setRotationPoint(4.0F, -8.0F, 0.0F);
        Tete.addChild(cornedroite1);
        cornedroite1.cubeList.add(new ModelBox(cornedroite1, 83, 50, -2.0F, 0.0F, 0.0F, 2, 2, 4, 0.0F, false));

        cornedroite2 = new ModelRenderer(this);
        cornedroite2.setRotationPoint(4.0F, -10.0F, 2.0F);
        Tete.addChild(cornedroite2);
        cornedroite2.cubeList.add(new ModelBox(cornedroite2, 87, 43, -2.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F, false));

        cornegauche1 = new ModelRenderer(this);
        cornegauche1.setRotationPoint(-2.0F, -8.0F, 0.0F);
        Tete.addChild(cornegauche1);
        cornegauche1.cubeList.add(new ModelBox(cornegauche1, 66, 50, -2.0F, 0.0F, 0.0F, 2, 2, 4, 0.0F, false));

        cornegauche2 = new ModelRenderer(this);
        cornegauche2.setRotationPoint(-2.0F, -10.0F, 2.0F);
        Tete.addChild(cornegauche2);
        cornegauche2.cubeList.add(new ModelBox(cornegauche2, 68, 42, -2.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F, false));

        Museau1 = new ModelRenderer(this);
        Museau1.setRotationPoint(2.0F, -4.0F, -10.0F);
        Tete.addChild(Museau1);
        Museau1.cubeList.add(new ModelBox(Museau1, 50, 50, -4.0F, 0.0F, 0.0F, 4, 2, 2, 0.0F, false));

        Museau2 = new ModelRenderer(this);
        Museau2.setRotationPoint(2.0F, -2.0F, -10.0F);
        Tete.addChild(Museau2);
        Museau2.cubeList.add(new ModelBox(Museau2, 50, 43, -4.0F, 0.0F, 0.0F, 4, 2, 2, 0.0F, false));

        Corps = new ModelRenderer(this);
        Corps.setRotationPoint(4.0F, -10.0F, -7.0F);
        root.addChild(Corps);

        corps1 = new ModelRenderer(this);
        corps1.setRotationPoint(0.0F, 0.0F, 0.0F);
        Corps.addChild(corps1);
        corps1.cubeList.add(new ModelBox(corps1, 197, 80, -8.0F, 0.0F, 0.0F, 8, 4, 20, 0.0F, false));

        corps2 = new ModelRenderer(this);
        corps2.setRotationPoint(-2.0F, -2.0F, 4.0F);
        Corps.addChild(corps2);
        corps2.cubeList.add(new ModelBox(corps2, 158, 80, -4.0F, 0.0F, 0.0F, 4, 2, 14, 0.0F, false));

        corps3 = new ModelRenderer(this);
        corps3.setRotationPoint(0.0F, -2.0F, 4.0F);
        Corps.addChild(corps3);
        corps3.cubeList.add(new ModelBox(corps3, 147, 80, -2.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F, false));

        corps4 = new ModelRenderer(this);
        corps4.setRotationPoint(-6.0F, -2.0F, 4.0F);
        Corps.addChild(corps4);
        corps4.cubeList.add(new ModelBox(corps4, 135, 80, -2.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F, false));

        corpsbasduventre = new ModelRenderer(this);
        corpsbasduventre.setRotationPoint(-2.0F, 4.0F, 0.0F);
        Corps.addChild(corpsbasduventre);
        corpsbasduventre.cubeList.add(new ModelBox(corpsbasduventre, 84, 80, -4.0F, 0.0F, 0.0F, 4, 2, 20, 0.0F, false));

        corps6 = new ModelRenderer(this);
        corps6.setRotationPoint(0.0F, -2.0F, 0.0F);
        Corps.addChild(corps6);
        corps6.cubeList.add(new ModelBox(corps6, 58, 80, -8.0F, 0.0F, 0.0F, 8, 2, 4, 0.0F, false));

        corps7 = new ModelRenderer(this);
        corps7.setRotationPoint(-6.0F, -2.0F, -2.0F);
        Corps.addChild(corps7);
        corps7.cubeList.add(new ModelBox(corps7, 73, 90, -2.0F, 0.0F, 0.0F, 2, 4, 2, 0.0F, false));

        corpsbasduventresuite = new ModelRenderer(this);
        corpsbasduventresuite.setRotationPoint(-2.0F, -2.0F, -2.0F);
        Corps.addChild(corpsbasduventresuite);
        corpsbasduventresuite.cubeList.add(new ModelBox(corpsbasduventresuite, 69, 100, -4.0F, 0.0F, 0.0F, 4, 6, 2, 0.0F, false));

        corps8 = new ModelRenderer(this);
        corps8.setRotationPoint(0.0F, -2.0F, -2.0F);
        Corps.addChild(corps8);
        corps8.cubeList.add(new ModelBox(corps8, 60, 90, -2.0F, 0.0F, 0.0F, 2, 4, 2, 0.0F, false));

        TAIL = new ModelRenderer(this);
        TAIL.setRotationPoint(0.0F, 0.0F, 0.0F);
        Corps.addChild(TAIL);

        queue1 = new ModelRenderer(this);
        queue1.setRotationPoint(-2.7F, 0.1F, 19.0F);
        TAIL.addChild(queue1);
        setRotationAngle(queue1, 0.0F, -0.0524F, 0.0F);

        queue1_r1 = new ModelRenderer(this);
        queue1_r1.setRotationPoint(-1.3F, 9.9F, -12.0F);
        queue1.addChild(queue1_r1);
        setRotationAngle(queue1_r1, -0.0873F, 0.0F, 0.0F);
        queue1_r1.cubeList.add(new ModelBox(queue1_r1, 66, 3, -0.7F, -9.9F, 12.0F, 2, 2, 7, 0.0F, false));

        queue2 = new ModelRenderer(this);
        queue2.setRotationPoint(-3.6F, -23.6F, 26.0F);
        TAIL.addChild(queue2);
        setRotationAngle(queue2, 0.0F, -0.1222F, 0.0F);

        queue2_r1 = new ModelRenderer(this);
        queue2_r1.setRotationPoint(-0.4F, 9.6F, -19.0F);
        queue2.addChild(queue2_r1);
        setRotationAngle(queue2_r1, -0.1745F, 0.0F, 0.0F);
        queue2_r1.cubeList.add(new ModelBox(queue2_r1, 51, 3, -0.6853F, 12.8782F, 22.2395F, 1, 1, 4, 0.0F, false));

        AileGauche = new ModelRenderer(this);
        AileGauche.setRotationPoint(-6.0F, -2.6F, 6.5F);
        Corps.addChild(AileGauche);

        ailegauche1 = new ModelRenderer(this);
        ailegauche1.setRotationPoint(0.0F, -0.4F, 0.0F);
        AileGauche.addChild(ailegauche1);
        setRotationAngle(ailegauche1, 0.0F, 0.0F, 0.0873F);
        ailegauche1.cubeList.add(new ModelBox(ailegauche1, 230, 114, -10.0F, 0.0F, 0.0F, 10, 1, 1, 0.0F, false));

        ailegauche2 = new ModelRenderer(this);
        ailegauche2.setRotationPoint(-10.0F, -1.2F, 0.0F);
        AileGauche.addChild(ailegauche2);
        ailegauche2.cubeList.add(new ModelBox(ailegauche2, 203, 114, -10.0F, 0.0F, 0.0F, 10, 1, 1, 0.0F, false));

        ailegauche3 = new ModelRenderer(this);
        ailegauche3.setRotationPoint(-10.5F, -1.2F, 1.0F);
        AileGauche.addChild(ailegauche3);
        setRotationAngle(ailegauche3, 0.0F, 1.5708F, 0.0873F);
        ailegauche3.cubeList.add(new ModelBox(ailegauche3, 176, 114, -10.0F, 0.0F, 0.0F, 10, 1, 1, 0.0F, false));

        ailedegauche4 = new ModelRenderer(this);
        ailedegauche4.setRotationPoint(-11.0F, -1.2F, 0.5F);
        AileGauche.addChild(ailedegauche4);
        setRotationAngle(ailedegauche4, 0.0F, 0.7854F, 0.0F);
        ailedegauche4.cubeList.add(new ModelBox(ailedegauche4, 144, 114, -12.0F, 0.0F, 0.0F, 12, 1, 1, 0.0F, false));

        ailedegauche5 = new ModelRenderer(this);
        ailedegauche5.setRotationPoint(-10.0F, -1.2F, 1.0F);
        AileGauche.addChild(ailedegauche5);
        setRotationAngle(ailedegauche5, 0.0F, 2.3562F, 0.0698F);
        ailedegauche5.cubeList.add(new ModelBox(ailedegauche5, 114, 114, -12.0F, 0.0F, 0.0F, 12, 1, 1, 0.0F, false));

        peaudelailegauche1 = new ModelRenderer(this);
        peaudelailegauche1.setRotationPoint(-10.0F, -0.65F, 1.0F);
        AileGauche.addChild(peaudelailegauche1);
        peaudelailegauche1.cubeList.add(new ModelBox(peaudelailegauche1, 75, 114, -9.0F, 0.0F, 0.0F, 9, 0, 8, 0.0F, false));

        peaudelailegauche2 = new ModelRenderer(this);
        peaudelailegauche2.setRotationPoint(-1.0F, 0.1F, 1.0F);
        AileGauche.addChild(peaudelailegauche2);
        setRotationAngle(peaudelailegauche2, -0.0349F, 0.0F, 0.0873F);
        peaudelailegauche2.cubeList.add(new ModelBox(peaudelailegauche2, 37, 114, -9.0F, 0.0F, 0.0F, 9, 0, 8, 0.0F, false));

        AileDroite = new ModelRenderer(this);
        AileDroite.setRotationPoint(-2.0F, -2.6F, 6.5F);
        Corps.addChild(AileDroite);

        aileDroite1 = new ModelRenderer(this);
        aileDroite1.setRotationPoint(-4.0F, -0.2F, 0.0F);
        AileDroite.addChild(aileDroite1);
        setRotationAngle(aileDroite1, 0.0F, 0.0F, 0.0349F);

        ailegauche1_r1 = new ModelRenderer(this);
        ailegauche1_r1.setRotationPoint(2.0F, 13.0F, 0.5F);
        aileDroite1.addChild(ailegauche1_r1);
        setRotationAngle(ailegauche1_r1, 0.0F, 0.0F, -0.0349F);
        ailegauche1_r1.cubeList.add(new ModelBox(ailegauche1_r1, 230, 114, 12.4958F, -13.9955F, -0.5F, 10, 1, 1, 0.0F, false));

        aileDroite2 = new ModelRenderer(this);
        aileDroite2.setRotationPoint(-14.0F, -1.4F, 0.0F);
        AileDroite.addChild(aileDroite2);
        setRotationAngle(aileDroite2, 0.0F, 0.0F, -0.0175F);

        ailegauche2_r1 = new ModelRenderer(this);
        ailegauche2_r1.setRotationPoint(36.0F, 14.8F, 0.5F);
        aileDroite2.addChild(ailegauche2_r1);
        setRotationAngle(ailegauche2_r1, 0.0F, 0.0F, -0.0524F);
        ailegauche2_r1.cubeList.add(new ModelBox(ailegauche2_r1, 203, 114, -21.1821F, -14.7452F, -0.5F, 10, 1, 1, 0.0F, false));

        aileDroite3 = new ModelRenderer(this);
        aileDroite3.setRotationPoint(-14.5F, -1.4F, 1.0F);
        AileDroite.addChild(aileDroite3);
        setRotationAngle(aileDroite3, 0.0F, 1.5708F, 0.0873F);
        aileDroite3.cubeList.add(new ModelBox(aileDroite3, 176, 114, -10.0F, -1.8427F, 23.9305F, 10, 1, 1, 0.0F, false));

        aileDroite4 = new ModelRenderer(this);
        aileDroite4.setRotationPoint(-15.0F, -1.4F, 0.5F);
        AileDroite.addChild(aileDroite4);
        setRotationAngle(aileDroite4, 0.0F, 0.7854F, 0.0F);

        ailedegauche4_r1 = new ModelRenderer(this);
        ailedegauche4_r1.setRotationPoint(13.0F, 13.8F, 0.0F);
        aileDroite4.addChild(ailedegauche4_r1);
        setRotationAngle(ailedegauche4_r1, 0.0F, 0.0F, -0.0524F);
        ailedegauche4_r1.cubeList.add(new ModelBox(ailedegauche4_r1, 144, 114, -7.3233F, -13.113F, 17.112F, 12, 1, 1, 0.0F, false));

        aileDroite5 = new ModelRenderer(this);
        aileDroite5.setRotationPoint(-15.0F, -1.4F, 1.0F);
        AileDroite.addChild(aileDroite5);
        setRotationAngle(aileDroite5, 0.0F, 2.3562F, 0.0698F);
        aileDroite5.cubeList.add(new ModelBox(aileDroite5, 114, 114, -30.1301F, -1.5643F, 17.5745F, 12, 1, 1, 0.0F, false));

        peauDroite1 = new ModelRenderer(this);
        peauDroite1.setRotationPoint(-14.0F, -0.85F, 1.0F);
        AileDroite.addChild(peauDroite1);
        setRotationAngle(peauDroite1, 0.0F, 0.0F, -0.0175F);

        peaudelailegauche1_r1 = new ModelRenderer(this);
        peaudelailegauche1_r1.setRotationPoint(36.0F, 14.0F, -0.5F);
        peauDroite1.addChild(peaudelailegauche1_r1);
        setRotationAngle(peaudelailegauche1_r1, 0.0F, 0.0F, -0.0436F);
        peaudelailegauche1_r1.cubeList.add(new ModelBox(peaudelailegauche1_r1, 75, 114, -20.9573F, -13.6987F, 0.5F, 9, 0, 8, 0.0F, false));

        peauDroite2 = new ModelRenderer(this);
        peauDroite2.setRotationPoint(-5.0F, -0.1F, 1.0F);
        AileDroite.addChild(peauDroite2);
        setRotationAngle(peauDroite2, -0.0349F, 0.0F, 0.0873F);

        peaudelailegauche2_r1 = new ModelRenderer(this);
        peaudelailegauche2_r1.setRotationPoint(3.0F, 12.5F, -0.5F);
        peauDroite2.addChild(peaudelailegauche2_r1);
        setRotationAngle(peaudelailegauche2_r1, 0.0F, 0.0F, -0.0524F);
        peaudelailegauche2_r1.cubeList.add(new ModelBox(peaudelailegauche2_r1, 37, 114, 12.9569F, -13.8278F, 0.4518F, 9, 0, 8, 0.0F, false));
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        root.render(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime)
    {
        super.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);

        EntityBabyDragonBase entity = (EntityBabyDragonBase)entitylivingbaseIn;

        if(entity.isSitting())
        {
            this.Corps.rotateAngleX = 12.24f;
            this.TAIL.setRotationPoint(0, 10, 1);
            this.TAIL.rotateAngleX = 0.5f;
        }
        else
        {
            this.Corps.rotateAngleX = 0;
            this.TAIL.setRotationPoint(0, 0, 0);
            this.TAIL.rotateAngleX = 0.0f;
        }
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        this.PatteArriereDroite.rotateAngleX = MathHelper.cos(limbSwing * 0.6462f) * 1.2f * limbSwingAmount;
        this.PatteArriereGauche.rotateAngleX = MathHelper.cos(limbSwing * 0.6462f) * 1.2f * limbSwingAmount;
        this.PatteAvantDroite.rotateAngleX = MathHelper.cos(limbSwing * 0.6462f) * -1.1f * limbSwingAmount;
        this.PatteAvantGauche.rotateAngleX = MathHelper.cos(limbSwing * 0.6462f) * -1.1f * limbSwingAmount;

        this.AileDroite.rotateAngleY = MathHelper.cos(limbSwing * 0.6462f) * 0.8f * limbSwingAmount;
        this.AileGauche.rotateAngleY = MathHelper.cos(limbSwing * 0.6462f) * -0.8f * limbSwingAmount;

        this.AileDroite.rotateAngleZ = MathHelper.cos(limbSwing * 0.6462f) * 0.8f * limbSwingAmount;
        this.AileGauche.rotateAngleZ = MathHelper.cos(limbSwing * 0.6462f) * -0.8f * limbSwingAmount;

        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
    }
}