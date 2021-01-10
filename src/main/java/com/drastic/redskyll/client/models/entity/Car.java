package com.drastic.redskyll.client.models.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.12
// Paste this class into your mod and generate all required imports


public class Car extends ModelBase {
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone14;
	private final ModelRenderer bone13;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer bone7;
	private final ModelRenderer bone10;
	private final ModelRenderer bone8;
	private final ModelRenderer bone11;
	private final ModelRenderer bone52;
	private final ModelRenderer bone53;
	private final ModelRenderer bone12;
	private final ModelRenderer bone16;
	private final ModelRenderer bone15;
	private final ModelRenderer bone17;
	private final ModelRenderer bone18;
	private final ModelRenderer bone19;
	private final ModelRenderer bone55;
	private final ModelRenderer bone54;
	private final ModelRenderer bone23;
	private final ModelRenderer Wheels;
	private final ModelRenderer Wheels6;
	private final ModelRenderer bone25;
	private final ModelRenderer bone28;
	private final ModelRenderer bone29;
	private final ModelRenderer bone30;
	private final ModelRenderer bone27;
	private final ModelRenderer bone26;
	private final ModelRenderer bone24;
	private final ModelRenderer Wheels4;
	private final ModelRenderer bone45;
	private final ModelRenderer bone46;
	private final ModelRenderer bone47;
	private final ModelRenderer bone48;
	private final ModelRenderer bone49;
	private final ModelRenderer bone50;
	private final ModelRenderer bone51;
	private final ModelRenderer Wheels2;
	private final ModelRenderer bone31;
	private final ModelRenderer bone32;
	private final ModelRenderer bone33;
	private final ModelRenderer bone34;
	private final ModelRenderer bone35;
	private final ModelRenderer bone36;
	private final ModelRenderer bone37;
	private final ModelRenderer Wheels3;
	private final ModelRenderer bone38;
	private final ModelRenderer bone39;
	private final ModelRenderer bone40;
	private final ModelRenderer bone41;
	private final ModelRenderer bone42;
	private final ModelRenderer bone43;
	private final ModelRenderer bone44;
	private final ModelRenderer bone20;
	private final ModelRenderer bone21;
	private final ModelRenderer bone22;
	private final ModelRenderer bone9;

	public Car() {
		textureWidth = 256;
		textureHeight = 256;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, -4.0F);
		setRotationAngle(bone, 0.0F, -1.5708F, 0.0F);
		

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.0F, -0.1745F);
		bone2.cubeList.add(new ModelBox(bone2, 128, 25, -15.924F, -10.8682F, -9.0F, 11, 1, 18, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 133, 25, -15.7495F, -10.2456F, -10.0F, 10, 1, 1, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 139, 9, -15.0977F, -9.8834F, 9.0F, 10, 1, 1, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 149, 21, -7.3299F, -9.7318F, -10.0F, 1, 1, 1, 0.0F, false));

		bone14 = new ModelRenderer(this);
		bone14.setRotationPoint(1.25F, -8.75F, -9.0F);
		bone.addChild(bone14);
		bone14.cubeList.add(new ModelBox(bone14, 134, 32, -8.0F, -1.0F, 0.0F, 8, 1, 18, 0.0F, false));

		bone13 = new ModelRenderer(this);
		bone13.setRotationPoint(2.25F, -10.75F, -9.0F);
		bone.addChild(bone13);
		setRotationAngle(bone13, 0.0F, 0.0F, -1.0472F);
		bone13.cubeList.add(new ModelBox(bone13, 10, 89, -2.0474F, -0.9757F, 0.0F, 6, 1, 18, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(-17.0F, -6.0F, -9.0F);
		bone.addChild(bone3);
		setRotationAngle(bone3, 0.0F, 0.0F, 0.8727F);
		bone3.cubeList.add(new ModelBox(bone3, 144, 18, -1.6428F, -1.234F, 0.0F, 1, 2, 18, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 162, 28, -0.7469F, -1.1349F, 17.75F, 1, 2, 1, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(-17.0F, -6.0F, -9.0F);
		bone.addChild(bone4);
		setRotationAngle(bone4, 0.0F, 0.0F, 0.1745F);
		bone4.cubeList.add(new ModelBox(bone4, 141, 15, -2.005F, -0.4085F, 0.0F, 1, 2, 18, 0.0F, true));
		bone4.cubeList.add(new ModelBox(bone4, 150, 22, -0.9767F, -0.336F, 17.75F, 1, 2, 1, 0.0F, false));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(-4.0F, 6.0F, 0.0F);
		bone.addChild(bone5);
		setRotationAngle(bone5, 0.0F, 0.0F, 0.1745F);
		bone5.cubeList.add(new ModelBox(bone5, 132, 10, -16.445F, -7.9138F, -9.0F, 11, 1, 18, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 138, 33, -15.329F, -9.6958F, 9.0F, 10, 2, 1, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 137, 21, -9.0388F, -12.8087F, -10.0F, 4, 1, 1, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 152, 18, -11.9086F, -12.0701F, -10.0F, 7, 1, 1, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 159, 21, -13.7783F, -11.3315F, -10.0F, 9, 1, 1, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 147, 32, -15.6481F, -10.5929F, -10.0F, 11, 1, 1, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 143, 25, -16.0828F, -9.7392F, -10.0F, 11, 2, 1, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 147, 27, -14.8943F, -10.5495F, 9.0F, 10, 1, 1, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 143, 30, -13.0245F, -11.2881F, 9.0F, 8, 1, 1, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 146, 30, -11.1548F, -12.0267F, 9.0F, 6, 1, 1, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 153, 28, -8.285F, -12.7653F, 9.0F, 3, 1, 1, 0.0F, false));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(-2.25F, 2.5F, 0.0F);
		bone.addChild(bone6);
		setRotationAngle(bone6, 0.0F, 0.0F, 0.4363F);
		bone6.cubeList.add(new ModelBox(bone6, 138, 20, -8.0F, -6.0F, -10.0F, 1, 2, 20, 0.0F, false));
		bone6.cubeList.add(new ModelBox(bone6, 137, 19, 9.673F, -14.2411F, -10.0F, 1, 2, 20, 0.0F, false));

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(-2.25F, 2.5F, 0.0F);
		bone.addChild(bone7);
		setRotationAngle(bone7, 0.0F, 0.0F, 1.1345F);
		bone7.cubeList.add(new ModelBox(bone7, 138, 16, -9.9474F, -1.3661F, -10.0F, 1, 2, 20, 0.0F, false));
		bone7.cubeList.add(new ModelBox(bone7, 136, 7, -1.7063F, -19.0391F, -10.0F, 1, 2, 20, 0.0F, false));

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(-5.75F, 2.5F, 0.0F);
		bone.addChild(bone10);
		setRotationAngle(bone10, 0.0F, 0.0F, -1.1345F);
		bone10.cubeList.add(new ModelBox(bone10, 132, 15, 9.053F, -1.1395F, -10.0F, 1, 2, 20, 0.0F, true));
		bone10.cubeList.add(new ModelBox(bone10, 137, 20, 17.2941F, 16.5335F, -10.0F, 1, 2, 20, 0.0F, true));

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(-7.5F, 2.0F, 0.0F);
		bone.addChild(bone8);
		setRotationAngle(bone8, 0.0F, 0.0F, 1.5708F);
		bone8.cubeList.add(new ModelBox(bone8, 141, 18, -9.1021F, -5.1787F, -10.0F, 1, 3, 20, 0.0F, false));
		bone8.cubeList.add(new ModelBox(bone8, 138, 20, -9.1021F, -24.6787F, -10.0F, 1, 3, 20, 0.0F, false));

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(-6.5F, 2.5F, 0.0F);
		bone.addChild(bone11);
		setRotationAngle(bone11, 0.0F, 0.0F, -0.4363F);
		bone11.cubeList.add(new ModelBox(bone11, 140, 6, 7.9063F, -5.5774F, -10.0F, 1, 2, 20, 0.0F, true));
		bone11.cubeList.add(new ModelBox(bone11, 142, 27, 25.5793F, 2.6637F, -10.0F, 1, 2, 20, 0.0F, true));

		bone52 = new ModelRenderer(this);
		bone52.setRotationPoint(-18.75F, -4.0F, -7.0F);
		bone.addChild(bone52);
		bone52.cubeList.add(new ModelBox(bone52, 75, 18, -1.0F, -2.0F, 0.0F, 1, 2, 14, 0.0F, false));

		bone53 = new ModelRenderer(this);
		bone53.setRotationPoint(0.0F, -3.0F, 0.0F);
		bone52.addChild(bone53);
		setRotationAngle(bone53, 0.0F, 0.0F, 0.6109F);
		bone53.cubeList.add(new ModelBox(bone53, 90, 8, -0.4264F, -1.1808F, 0.0F, 1, 2, 2, 0.0F, false));
		bone53.cubeList.add(new ModelBox(bone53, 90, 8, -0.4264F, -1.1808F, 12.0F, 1, 2, 2, 0.0F, false));

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(-4.75F, 6.0F, 0.0F);
		bone.addChild(bone12);
		setRotationAngle(bone12, 0.0F, 0.0F, -0.1745F);
		bone12.cubeList.add(new ModelBox(bone12, 141, 5, 5.4878F, -9.6481F, -10.0F, 1, 3, 20, 0.0F, true));
		bone12.cubeList.add(new ModelBox(bone12, 140, 5, 24.6915F, -6.2619F, -10.0F, 1, 3, 20, 0.0F, true));

		bone16 = new ModelRenderer(this);
		bone16.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(bone16);
		bone16.cubeList.add(new ModelBox(bone16, 140, 26, 0.0F, -9.0F, 9.0F, 12, 7, 1, 0.0F, false));
		bone16.cubeList.add(new ModelBox(bone16, 144, 34, 0.0F, -9.0F, -10.0F, 12, 7, 1, 0.0F, false));

		bone15 = new ModelRenderer(this);
		bone15.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(bone15);
		bone15.cubeList.add(new ModelBox(bone15, 130, 19, 0.0F, -2.5F, -9.0F, 12, 1, 18, 0.0F, false));

		bone17 = new ModelRenderer(this);
		bone17.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(bone17);
		bone17.cubeList.add(new ModelBox(bone17, 141, 13, -8.0F, -9.0F, 9.0F, 8, 2, 1, 0.0F, false));
		bone17.cubeList.add(new ModelBox(bone17, 161, 34, -8.0F, -9.0F, -10.0F, 8, 2, 1, 0.0F, false));
		bone17.cubeList.add(new ModelBox(bone17, 161, 18, -5.0F, -7.5F, 9.0F, 5, 1, 1, 0.0F, false));
		bone17.cubeList.add(new ModelBox(bone17, 156, 29, 14.75F, -7.5F, 9.0F, 5, 1, 1, 0.0F, false));
		bone17.cubeList.add(new ModelBox(bone17, 148, 20, 14.75F, -7.5F, -10.0F, 5, 1, 1, 0.0F, false));
		bone17.cubeList.add(new ModelBox(bone17, 155, 24, -5.0F, -7.5F, -10.0F, 5, 1, 1, 0.0F, false));
		bone17.cubeList.add(new ModelBox(bone17, 139, 21, -7.25F, -7.5F, 9.0F, 3, 1, 1, 0.0F, false));
		bone17.cubeList.add(new ModelBox(bone17, 164, 26, 12.5F, -7.5F, 9.0F, 3, 1, 1, 0.0F, false));
		bone17.cubeList.add(new ModelBox(bone17, 170, 32, 12.5F, -7.5F, -10.0F, 3, 1, 1, 0.0F, false));
		bone17.cubeList.add(new ModelBox(bone17, 150, 30, -7.25F, -7.5F, -10.0F, 3, 1, 1, 0.0F, false));
		bone17.cubeList.add(new ModelBox(bone17, 154, 44, -7.5F, -6.75F, 9.0F, 1, 1, 1, 0.0F, false));
		bone17.cubeList.add(new ModelBox(bone17, 165, 21, 12.25F, -6.75F, 9.0F, 1, 1, 1, 0.0F, false));
		bone17.cubeList.add(new ModelBox(bone17, 163, 32, 12.25F, -6.75F, -10.0F, 1, 1, 1, 0.0F, false));
		bone17.cubeList.add(new ModelBox(bone17, 157, 30, -7.5F, -6.75F, -10.0F, 1, 1, 1, 0.0F, false));
		bone17.cubeList.add(new ModelBox(bone17, 156, 26, -1.75F, -6.75F, 9.0F, 2, 1, 1, 0.0F, false));
		bone17.cubeList.add(new ModelBox(bone17, 153, 39, 18.0F, -6.75F, 9.0F, 2, 1, 1, 0.0F, false));
		bone17.cubeList.add(new ModelBox(bone17, 137, 20, 18.0F, -6.75F, -10.0F, 2, 1, 1, 0.0F, false));
		bone17.cubeList.add(new ModelBox(bone17, 149, 39, -1.75F, -6.75F, -10.0F, 2, 1, 1, 0.0F, false));
		bone17.cubeList.add(new ModelBox(bone17, 166, 19, -0.75F, -5.75F, 9.0F, 1, 2, 1, 0.0F, false));
		bone17.cubeList.add(new ModelBox(bone17, 145, 26, 19.0F, -5.75F, 9.0F, 1, 2, 1, 0.0F, false));
		bone17.cubeList.add(new ModelBox(bone17, 148, 16, 19.0F, -5.75F, -10.0F, 1, 2, 1, 0.0F, false));
		bone17.cubeList.add(new ModelBox(bone17, 152, 15, -0.75F, -5.75F, -10.0F, 1, 2, 1, 0.0F, false));

		bone18 = new ModelRenderer(this);
		bone18.setRotationPoint(0.5F, 1.25F, -1.0F);
		bone.addChild(bone18);
		setRotationAngle(bone18, 0.0F, 0.0F, -0.0873F);
		bone18.cubeList.add(new ModelBox(bone18, 136, 30, 19.0F, -2.0F, -8.0F, 4, 1, 18, 0.0F, false));

		bone19 = new ModelRenderer(this);
		bone19.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(bone19);
		setRotationAngle(bone19, 0.0F, 0.0F, -0.0873F);
		bone19.cubeList.add(new ModelBox(bone19, 137, 17, 22.4797F, -6.0532F, -9.0F, 1, 6, 18, 0.0F, false));

		bone55 = new ModelRenderer(this);
		bone55.setRotationPoint(-1.0F, -7.0F, 0.0F);
		bone.addChild(bone55);
		bone55.cubeList.add(new ModelBox(bone55, 0, 0, 3.5F, 3.5F, -4.0F, 6, 1, 8, 0.0F, false));

		bone54 = new ModelRenderer(this);
		bone54.setRotationPoint(-1.0F, -7.0F, 0.0F);
		bone.addChild(bone54);
		setRotationAngle(bone54, 0.0F, 0.0F, 0.0873F);
		bone54.cubeList.add(new ModelBox(bone54, 0, 0, 9.0F, -4.5F, -4.0F, 1, 8, 8, 0.0F, false));

		bone23 = new ModelRenderer(this);
		bone23.setRotationPoint(-1.0F, -6.0F, 0.0F);
		bone.addChild(bone23);
		setRotationAngle(bone23, 0.0F, 0.0F, -0.0873F);
		bone23.cubeList.add(new ModelBox(bone23, 81, 26, 22.2307F, -2.0749F, -9.0F, 1, 2, 18, 0.0F, false));
		bone23.cubeList.add(new ModelBox(bone23, 81, 26, 23.7873F, 3.0803F, -9.0F, 1, 2, 18, 0.0F, false));
		bone23.cubeList.add(new ModelBox(bone23, 90, 98, 24.0487F, 0.0918F, -8.0F, 1, 2, 2, 0.0F, false));
		bone23.cubeList.add(new ModelBox(bone23, 90, 98, 24.0487F, 0.0918F, 6.0F, 1, 2, 2, 0.0F, false));
		bone23.cubeList.add(new ModelBox(bone23, 246, 46, -14.78F, -4.0383F, -1.0F, 3, 1, 2, 0.0F, false));
		bone23.cubeList.add(new ModelBox(bone23, 33, 36, -14.0253F, -4.1472F, -0.5F, 1, 1, 1, 0.0F, false));

		Wheels = new ModelRenderer(this);
		Wheels.setRotationPoint(-5.75F, 2.5F, 0.0F);
		bone.addChild(Wheels);
		

		Wheels6 = new ModelRenderer(this);
		Wheels6.setRotationPoint(1.75F, -5.5F, -11.0F);
		Wheels.addChild(Wheels6);
		

		bone25 = new ModelRenderer(this);
		bone25.setRotationPoint(-1.75F, 0.75F, 0.0F);
		Wheels6.addChild(bone25);
		setRotationAngle(bone25, 0.0F, 0.0F, 0.7854F);
		bone25.cubeList.add(new ModelBox(bone25, 0, 0, -1.7165F, -2.741F, 0.0F, 1, 2, 2, 0.0F, false));

		bone28 = new ModelRenderer(this);
		bone28.setRotationPoint(-0.75F, 1.75F, 0.0F);
		Wheels6.addChild(bone28);
		setRotationAngle(bone28, 0.0F, 0.0F, 0.7854F);
		bone28.cubeList.add(new ModelBox(bone28, 0, 0, 0.7165F, -2.741F, 0.0F, 1, 2, 2, 0.0F, true));

		bone29 = new ModelRenderer(this);
		bone29.setRotationPoint(1.75F, 0.75F, 0.0F);
		Wheels6.addChild(bone29);
		setRotationAngle(bone29, 0.0F, 0.0F, -0.7854F);
		bone29.cubeList.add(new ModelBox(bone29, 0, 0, 0.7165F, -2.741F, 0.0F, 1, 2, 2, 0.0F, true));

		bone30 = new ModelRenderer(this);
		bone30.setRotationPoint(2.25F, -0.5F, 0.0F);
		Wheels6.addChild(bone30);
		setRotationAngle(bone30, 0.0F, 0.0F, -1.7453F);
		

		bone27 = new ModelRenderer(this);
		bone27.setRotationPoint(2.25F, -0.5F, 0.0F);
		Wheels6.addChild(bone27);
		setRotationAngle(bone27, 0.0F, 0.0F, 1.7453F);
		

		bone26 = new ModelRenderer(this);
		bone26.setRotationPoint(0.75F, 1.75F, 0.0F);
		Wheels6.addChild(bone26);
		setRotationAngle(bone26, 0.0F, 0.0F, -0.7854F);
		bone26.cubeList.add(new ModelBox(bone26, 0, 0, -1.7165F, -2.741F, 0.0F, 1, 2, 2, 0.0F, false));

		bone24 = new ModelRenderer(this);
		bone24.setRotationPoint(-1.5F, 17.0F, 0.0F);
		Wheels6.addChild(bone24);
		bone24.cubeList.add(new ModelBox(bone24, 0, 0, -1.0F, -18.0F, 0.0F, 1, 2, 2, 0.0F, false));
		bone24.cubeList.add(new ModelBox(bone24, 0, 0, 3.0F, -18.0F, 0.0F, 1, 2, 2, 0.0F, false));
		bone24.cubeList.add(new ModelBox(bone24, 0, 0, 0.0F, -18.5F, 1.0F, 3, 3, 1, 0.0F, false));
		bone24.cubeList.add(new ModelBox(bone24, 89, 16, 1.0F, -18.5F, 0.0F, 1, 3, 1, 0.0F, false));
		bone24.cubeList.add(new ModelBox(bone24, 105, 22, 0.0F, -17.5F, 0.0F, 3, 1, 1, 0.0F, false));
		bone24.cubeList.add(new ModelBox(bone24, 0, 0, 0.5F, -19.5F, 0.0F, 2, 1, 2, 0.0F, false));
		bone24.cubeList.add(new ModelBox(bone24, 0, 0, 0.5F, -15.5F, 0.0F, 2, 1, 2, 0.0F, false));

		Wheels4 = new ModelRenderer(this);
		Wheels4.setRotationPoint(21.25F, -5.5F, 10.0F);
		Wheels.addChild(Wheels4);
		

		bone45 = new ModelRenderer(this);
		bone45.setRotationPoint(-21.25F, 0.75F, -21.0F);
		Wheels4.addChild(bone45);
		setRotationAngle(bone45, 0.0F, 0.0F, 0.7854F);
		bone45.cubeList.add(new ModelBox(bone45, 0, 0, 12.0721F, -16.5296F, 20.0F, 1, 2, 2, 0.0F, false));

		bone46 = new ModelRenderer(this);
		bone46.setRotationPoint(-20.25F, 1.75F, -21.0F);
		Wheels4.addChild(bone46);
		setRotationAngle(bone46, 0.0F, 0.0F, 0.7854F);
		bone46.cubeList.add(new ModelBox(bone46, 0, 0, 14.5051F, -16.5296F, 20.0F, 1, 2, 2, 0.0F, true));

		bone47 = new ModelRenderer(this);
		bone47.setRotationPoint(-17.75F, 0.75F, -21.0F);
		Wheels4.addChild(bone47);
		setRotationAngle(bone47, 0.0F, 0.0F, -0.7854F);
		bone47.cubeList.add(new ModelBox(bone47, 0, 0, 14.5051F, 11.0476F, 20.0F, 1, 2, 2, 0.0F, true));

		bone48 = new ModelRenderer(this);
		bone48.setRotationPoint(-17.25F, -0.5F, -21.0F);
		Wheels4.addChild(bone48);
		setRotationAngle(bone48, 0.0F, 0.0F, -1.7453F);
		

		bone49 = new ModelRenderer(this);
		bone49.setRotationPoint(-17.25F, -0.5F, -21.0F);
		Wheels4.addChild(bone49);
		setRotationAngle(bone49, 0.0F, 0.0F, 1.7453F);
		

		bone50 = new ModelRenderer(this);
		bone50.setRotationPoint(-18.75F, 1.75F, -21.0F);
		Wheels4.addChild(bone50);
		setRotationAngle(bone50, 0.0F, 0.0F, -0.7854F);
		bone50.cubeList.add(new ModelBox(bone50, 0, 0, 12.0721F, 11.0476F, 20.0F, 1, 2, 2, 0.0F, false));

		bone51 = new ModelRenderer(this);
		bone51.setRotationPoint(-21.0F, 17.0F, -21.0F);
		Wheels4.addChild(bone51);
		bone51.cubeList.add(new ModelBox(bone51, 0, 0, 18.5F, -18.0F, 20.0F, 1, 2, 2, 0.0F, false));
		bone51.cubeList.add(new ModelBox(bone51, 0, 0, 22.5F, -18.0F, 20.0F, 1, 2, 2, 0.0F, false));
		bone51.cubeList.add(new ModelBox(bone51, 0, 0, 19.5F, -18.5F, 20.0F, 3, 3, 1, 0.0F, false));
		bone51.cubeList.add(new ModelBox(bone51, 88, 25, 20.5F, -18.5F, 21.0F, 1, 3, 1, 0.0F, false));
		bone51.cubeList.add(new ModelBox(bone51, 86, 12, 19.5F, -17.5F, 21.0F, 3, 1, 1, 0.0F, false));
		bone51.cubeList.add(new ModelBox(bone51, 0, 0, 20.0F, -19.5F, 20.0F, 2, 1, 2, 0.0F, false));
		bone51.cubeList.add(new ModelBox(bone51, 0, 0, 20.0F, -15.5F, 20.0F, 2, 1, 2, 0.0F, false));

		Wheels2 = new ModelRenderer(this);
		Wheels2.setRotationPoint(21.25F, -5.5F, -11.0F);
		Wheels.addChild(Wheels2);
		

		bone31 = new ModelRenderer(this);
		bone31.setRotationPoint(-21.25F, 0.75F, 0.0F);
		Wheels2.addChild(bone31);
		setRotationAngle(bone31, 0.0F, 0.0F, 0.7854F);
		bone31.cubeList.add(new ModelBox(bone31, 0, 0, 12.0721F, -16.5296F, 0.0F, 1, 2, 2, 0.0F, false));

		bone32 = new ModelRenderer(this);
		bone32.setRotationPoint(-20.25F, 1.75F, 0.0F);
		Wheels2.addChild(bone32);
		setRotationAngle(bone32, 0.0F, 0.0F, 0.7854F);
		bone32.cubeList.add(new ModelBox(bone32, 0, 0, 14.5051F, -16.5296F, 0.0F, 1, 2, 2, 0.0F, true));

		bone33 = new ModelRenderer(this);
		bone33.setRotationPoint(-17.75F, 0.75F, 0.0F);
		Wheels2.addChild(bone33);
		setRotationAngle(bone33, 0.0F, 0.0F, -0.7854F);
		bone33.cubeList.add(new ModelBox(bone33, 0, 0, 14.5051F, 11.0476F, 0.0F, 1, 2, 2, 0.0F, true));

		bone34 = new ModelRenderer(this);
		bone34.setRotationPoint(-17.25F, -0.5F, 0.0F);
		Wheels2.addChild(bone34);
		setRotationAngle(bone34, 0.0F, 0.0F, -1.7453F);
		

		bone35 = new ModelRenderer(this);
		bone35.setRotationPoint(-17.25F, -0.5F, 0.0F);
		Wheels2.addChild(bone35);
		setRotationAngle(bone35, 0.0F, 0.0F, 1.7453F);
		

		bone36 = new ModelRenderer(this);
		bone36.setRotationPoint(-18.75F, 1.75F, 0.0F);
		Wheels2.addChild(bone36);
		setRotationAngle(bone36, 0.0F, 0.0F, -0.7854F);
		bone36.cubeList.add(new ModelBox(bone36, 0, 0, 12.0721F, 11.0476F, 0.0F, 1, 2, 2, 0.0F, false));

		bone37 = new ModelRenderer(this);
		bone37.setRotationPoint(-21.0F, 17.0F, 0.0F);
		Wheels2.addChild(bone37);
		bone37.cubeList.add(new ModelBox(bone37, 0, 0, 18.5F, -18.0F, 0.0F, 1, 2, 2, 0.0F, false));
		bone37.cubeList.add(new ModelBox(bone37, 0, 0, 22.5F, -18.0F, 0.0F, 1, 2, 2, 0.0F, false));
		bone37.cubeList.add(new ModelBox(bone37, 0, 0, 19.5F, -18.5F, 1.0F, 3, 3, 1, 0.0F, false));
		bone37.cubeList.add(new ModelBox(bone37, 85, 19, 20.5F, -18.5F, 0.0F, 1, 3, 1, 0.0F, false));
		bone37.cubeList.add(new ModelBox(bone37, 90, 19, 19.5F, -17.5F, 0.0F, 3, 1, 1, 0.0F, false));
		bone37.cubeList.add(new ModelBox(bone37, 0, 0, 20.0F, -19.5F, 0.0F, 2, 1, 2, 0.0F, false));
		bone37.cubeList.add(new ModelBox(bone37, 0, 0, 20.0F, -15.5F, 0.0F, 2, 1, 2, 0.0F, false));

		Wheels3 = new ModelRenderer(this);
		Wheels3.setRotationPoint(1.75F, -5.5F, 10.0F);
		Wheels.addChild(Wheels3);
		

		bone38 = new ModelRenderer(this);
		bone38.setRotationPoint(-1.75F, 0.75F, -21.0F);
		Wheels3.addChild(bone38);
		setRotationAngle(bone38, 0.0F, 0.0F, 0.7854F);
		bone38.cubeList.add(new ModelBox(bone38, 0, 0, -1.7165F, -2.741F, 20.0F, 1, 2, 2, 0.0F, false));

		bone39 = new ModelRenderer(this);
		bone39.setRotationPoint(-0.75F, 1.75F, -21.0F);
		Wheels3.addChild(bone39);
		setRotationAngle(bone39, 0.0F, 0.0F, 0.7854F);
		bone39.cubeList.add(new ModelBox(bone39, 0, 0, 0.7165F, -2.741F, 20.0F, 1, 2, 2, 0.0F, true));

		bone40 = new ModelRenderer(this);
		bone40.setRotationPoint(1.75F, 0.75F, -21.0F);
		Wheels3.addChild(bone40);
		setRotationAngle(bone40, 0.0F, 0.0F, -0.7854F);
		bone40.cubeList.add(new ModelBox(bone40, 0, 0, 0.7165F, -2.741F, 20.0F, 1, 2, 2, 0.0F, true));

		bone41 = new ModelRenderer(this);
		bone41.setRotationPoint(2.25F, -0.5F, -21.0F);
		Wheels3.addChild(bone41);
		setRotationAngle(bone41, 0.0F, 0.0F, -1.7453F);
		

		bone42 = new ModelRenderer(this);
		bone42.setRotationPoint(2.25F, -0.5F, -21.0F);
		Wheels3.addChild(bone42);
		setRotationAngle(bone42, 0.0F, 0.0F, 1.7453F);
		

		bone43 = new ModelRenderer(this);
		bone43.setRotationPoint(0.75F, 1.75F, -21.0F);
		Wheels3.addChild(bone43);
		setRotationAngle(bone43, 0.0F, 0.0F, -0.7854F);
		bone43.cubeList.add(new ModelBox(bone43, 0, 0, -1.7165F, -2.741F, 20.0F, 1, 2, 2, 0.0F, false));

		bone44 = new ModelRenderer(this);
		bone44.setRotationPoint(-1.5F, 17.0F, -21.0F);
		Wheels3.addChild(bone44);
		bone44.cubeList.add(new ModelBox(bone44, 0, 0, -1.0F, -18.0F, 20.0F, 1, 2, 2, 0.0F, false));
		bone44.cubeList.add(new ModelBox(bone44, 0, 0, 3.0F, -18.0F, 20.0F, 1, 2, 2, 0.0F, false));
		bone44.cubeList.add(new ModelBox(bone44, 0, 0, 0.0F, -18.5F, 20.0F, 3, 3, 1, 0.0F, false));
		bone44.cubeList.add(new ModelBox(bone44, 90, 33, 1.0F, -18.5F, 21.0F, 1, 3, 1, 0.0F, false));
		bone44.cubeList.add(new ModelBox(bone44, 88, 27, 0.0F, -17.5F, 21.0F, 3, 1, 1, 0.0F, false));
		bone44.cubeList.add(new ModelBox(bone44, 0, 0, 0.5F, -19.5F, 20.0F, 2, 1, 2, 0.0F, false));
		bone44.cubeList.add(new ModelBox(bone44, 0, 0, 0.5F, -15.5F, 20.0F, 2, 1, 2, 0.0F, false));

		bone20 = new ModelRenderer(this);
		bone20.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(bone20);
		bone20.cubeList.add(new ModelBox(bone20, 137, 16, 13.0F, -11.0F, -9.0F, 8, 1, 18, 0.0F, false));

		bone21 = new ModelRenderer(this);
		bone21.setRotationPoint(-2.0F, -2.5F, 0.0F);
		bone.addChild(bone21);
		setRotationAngle(bone21, 0.0F, 0.0F, 0.0873F);
		bone21.cubeList.add(new ModelBox(bone21, 138, 11, 13.2649F, -9.8158F, -9.0F, 1, 9, 18, 0.0F, false));

		bone22 = new ModelRenderer(this);
		bone22.setRotationPoint(22.0F, -3.0F, 9.0F);
		bone.addChild(bone22);
		bone22.cubeList.add(new ModelBox(bone22, 168, 30, -10.0F, -7.0F, 0.0F, 10, 3, 1, 0.0F, false));
		bone22.cubeList.add(new ModelBox(bone22, 138, 31, -10.0F, -7.0F, -19.0F, 10, 3, 1, 0.0F, false));
		bone22.cubeList.add(new ModelBox(bone22, 149, 27, -10.0F, -4.0F, 0.0F, 1, 2, 1, 0.0F, false));
		bone22.cubeList.add(new ModelBox(bone22, 151, 45, -10.0F, -4.0F, -19.0F, 1, 2, 1, 0.0F, false));
		bone22.cubeList.add(new ModelBox(bone22, 149, 26, -2.5F, -4.0F, 0.0F, 3, 5, 1, 0.0F, false));
		bone22.cubeList.add(new ModelBox(bone22, 160, 30, -2.5F, -4.0F, -19.0F, 3, 5, 1, 0.0F, false));

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(-4.0F, 6.0F, 0.0F);
		bone.addChild(bone9);
		setRotationAngle(bone9, 0.0F, 0.0F, 0.1745F);
		bone9.cubeList.add(new ModelBox(bone9, 140, 10, -5.503F, -9.8217F, -10.0F, 1, 3, 20, 0.0F, false));
		bone9.cubeList.add(new ModelBox(bone9, 137, 21, 13.7008F, -13.2078F, -10.0F, 1, 3, 20, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
	{
        Wheels2.rotateAngleZ = -ageInTicks;
        Wheels3.rotateAngleZ = -ageInTicks;
        Wheels4.rotateAngleZ = -ageInTicks;
        Wheels6.rotateAngleZ = -ageInTicks;
	}
}