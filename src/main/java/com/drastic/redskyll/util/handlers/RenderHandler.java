package com.drastic.redskyll.util.handlers;

import com.drastic.redskyll.client.renderer.entity.RenderCar1;
import com.drastic.redskyll.client.renderer.entity.RenderDirtBlock;
import com.drastic.redskyll.client.renderer.entity.RenderEarthDragon;
import com.drastic.redskyll.client.renderer.entity.RenderEntityMagmaBall;
import com.drastic.redskyll.client.renderer.entity.RenderFireDragon;
import com.drastic.redskyll.client.renderer.entity.RenderFuse;
import com.drastic.redskyll.client.renderer.entity.RenderFuseT2;
import com.drastic.redskyll.client.renderer.entity.RenderIceBlock;
import com.drastic.redskyll.client.renderer.entity.RenderIceDragon;
import com.drastic.redskyll.client.renderer.entity.RenderMinion;
import com.drastic.redskyll.client.renderer.entity.RenderMinion2;
import com.drastic.redskyll.client.renderer.entity.RenderMinion3;
import com.drastic.redskyll.client.renderer.entity.RenderRedskyll;
import com.drastic.redskyll.client.renderer.entity.RenderScorpion;
import com.drastic.redskyll.client.renderer.entity.RenderShield;
import com.drastic.redskyll.init.BlockInit;
import com.drastic.redskyll.objects.entity.EntityBabyEarthDragon;
import com.drastic.redskyll.objects.entity.EntityBabyFireDragon;
import com.drastic.redskyll.objects.entity.EntityBabyIceDragon;
import com.drastic.redskyll.objects.entity.EntityCar;
import com.drastic.redskyll.objects.entity.EntityDirtBlock;
import com.drastic.redskyll.objects.entity.EntityFuseT1;
import com.drastic.redskyll.objects.entity.EntityFuseT2;
import com.drastic.redskyll.objects.entity.EntityIceBlock;
import com.drastic.redskyll.objects.entity.EntityMagmaBall;
import com.drastic.redskyll.objects.entity.EntityMinionDiamond;
import com.drastic.redskyll.objects.entity.EntityMinionGold;
import com.drastic.redskyll.objects.entity.EntityMinionIron;
import com.drastic.redskyll.objects.entity.EntityRedskyll;
import com.drastic.redskyll.objects.entity.EntityScorpion;
import com.drastic.redskyll.objects.entity.EntityShield;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSheep;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler
{
    public static void registerEntityRenders()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityFuseT1.class, new IRenderFactory<EntityFuseT1>()
        {

            @Override
            public Render<? super EntityFuseT1> createRenderFor(RenderManager manager)
            {
                return new RenderFuse(manager);
            }

        });
        
        RenderingRegistry.registerEntityRenderingHandler(EntityFuseT2.class, new IRenderFactory<EntityFuseT2>()
        {

            @Override
            public Render<? super EntityFuseT2> createRenderFor(RenderManager manager)
            {
                return new RenderFuseT2(manager);
            }

        });
        
        RenderingRegistry.registerEntityRenderingHandler(EntityMinionDiamond.class, new IRenderFactory<EntityMinionDiamond>()
        {

            @Override
            public Render<? super EntityMinionDiamond> createRenderFor(RenderManager manager)
            {
                return new RenderMinion(manager);
            }

        });
        
        RenderingRegistry.registerEntityRenderingHandler(EntityMinionGold.class, new IRenderFactory<EntityMinionGold>()
        {

            @Override
            public Render<? super EntityMinionGold> createRenderFor(RenderManager manager)
            {
                return new RenderMinion2(manager);
            }

        });
        
        RenderingRegistry.registerEntityRenderingHandler(EntityMinionIron.class, new IRenderFactory<EntityMinionIron>()
        {

            @Override
            public Render<? super EntityMinionIron> createRenderFor(RenderManager manager)
            {
                return new RenderMinion3(manager);
            }

        });
        
        RenderingRegistry.registerEntityRenderingHandler(EntityRedskyll.class, new IRenderFactory<EntityRedskyll>()
        {

            @Override
            public Render<? super EntityRedskyll> createRenderFor(RenderManager manager)
            {
                return new RenderRedskyll(manager);
            }

        });
        
        RenderingRegistry.registerEntityRenderingHandler(EntityCar.class, new IRenderFactory<EntityCar>()
        {

            @Override
            public Render<? super EntityCar> createRenderFor(RenderManager manager)
            {
                return new RenderCar1(manager);
            }

        });
        
        RenderingRegistry.registerEntityRenderingHandler(EntityBabyFireDragon.class, new IRenderFactory<EntityBabyFireDragon>()
        {

            @Override
            public Render<? super EntityBabyFireDragon> createRenderFor(RenderManager manager)
            {
                return new RenderFireDragon(manager);
            }

        });
        
        RenderingRegistry.registerEntityRenderingHandler(EntityBabyIceDragon.class, new IRenderFactory<EntityBabyIceDragon>()
        {

            @Override
            public Render<? super EntityBabyIceDragon> createRenderFor(RenderManager manager)
            {
                return new RenderIceDragon(manager);
            }

        });
        
        RenderingRegistry.registerEntityRenderingHandler(EntityBabyEarthDragon.class, new IRenderFactory<EntityBabyEarthDragon>()
        {

            @Override
            public Render<? super EntityBabyEarthDragon> createRenderFor(RenderManager manager)
            {
                return new RenderEarthDragon(manager);
            }

        });
        
        RenderingRegistry.registerEntityRenderingHandler(EntityMagmaBall.class, new IRenderFactory<EntityMagmaBall>()
        {

            @Override
            public Render<? super EntityMagmaBall> createRenderFor(RenderManager manager)
            {
                return new RenderEntityMagmaBall(manager, 0.7f);
            }

        });
        
        RenderingRegistry.registerEntityRenderingHandler(EntityIceBlock.class, new IRenderFactory<EntityIceBlock>()
        {

            @Override
            public Render<? super EntityIceBlock> createRenderFor(RenderManager manager)
            { 
                return new RenderIceBlock(manager, 0.85f);
            }

        });
        
        RenderingRegistry.registerEntityRenderingHandler(EntityDirtBlock.class, new IRenderFactory<EntityDirtBlock>()
        {

            @Override
            public Render<? super EntityDirtBlock> createRenderFor(RenderManager manager)
            {
                return new RenderDirtBlock(manager, 0.7f);
            }

        });
        
        RenderingRegistry.registerEntityRenderingHandler(EntityScorpion.class, new IRenderFactory<EntityScorpion>()
        {

            @Override
            public Render<? super EntityScorpion> createRenderFor(RenderManager manager)
            {
                return new RenderScorpion(manager);
            }

        });
        
        RenderingRegistry.registerEntityRenderingHandler(EntityShield.class, new IRenderFactory<EntityThrowable>()
        {

            @Override
            public Render<? super EntityThrowable> createRenderFor(RenderManager manager)
            {
                return new RenderShield(manager, 1.0f);
            }

        });
    }
    
    public static void registerCustomMeshesAndStates()
    {
        ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(BlockInit.OIL), new net.minecraft.client.renderer.ItemMeshDefinition()
        {
            
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack)
            {
                return new ModelResourceLocation("redskyll:oil", "fluid");
            }
        });
        
        ModelLoader.setCustomStateMapper(BlockInit.OIL, new StateMapperBase()
        {
            
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state)
            {
                return new ModelResourceLocation("redskyll:oil", "fluid");
            }
        });
    }
}
