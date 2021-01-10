package com.drastic.redskyll.init;

import com.drastic.redskyll.Main;
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
import com.drastic.redskyll.objects.entity.EntitySeat;
import com.drastic.redskyll.objects.entity.EntityShield;
import com.drastic.redskyll.util.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit
{
    public static void regiterEntities()
    {
        registerEntity("fuse", EntityFuseT1.class, 111, 50, 1515215255, 5184825);
        registerEntity("minion_diamond", EntityMinionDiamond.class, 112, 50, 38143, 38143);
        registerEntity("minion_gold", EntityMinionGold.class, 113, 50, 16762368, 16762368);
        registerEntity("minion_iron", EntityMinionIron.class, 114, 50, 12698049, 12698049);
        registerEntity("redskyll", EntityRedskyll.class, 115, 50, 16777215, 16711705);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":seat"), EntitySeat.class, "Seat", 116, Main.instance, 80, 1, false);
        registerEntity("car", EntityCar.class, 117, 50, 15674874, 1577785);
        registerEntity("firedragon", EntityBabyFireDragon.class, 118, 50, 14359552, 14372352);
        registerEntity("earthdragon", EntityBabyEarthDragon.class, 119, 50, 8336128, 10502144);
        registerEntity("icedragon", EntityBabyIceDragon.class, 120, 50, 38143, 65535);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":magma_ball_entity"), EntityMagmaBall.class, "Magma Ball", 121, Main.instance, 80, 1, false);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":ice_block_entity"), EntityIceBlock.class, "Ice Block", 122, Main.instance, 80, 1, false);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":dirt_block_entity"), EntityDirtBlock.class, "Dirt Block", 123, Main.instance, 80, 1, false);
        registerEntity("scorpion", EntityScorpion.class, 124, 50, 16765255, 1672485);
        registerEntity("fuse_t2", EntityFuseT2.class, 125, 50, 1515215255, 5184825);
        registerEntity("z_shield", EntityShield.class, 126, 50, 1515215255, 5184825);
    }

    private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2)
    {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), entity, name, id, Main.instance, range, 1, true, color1, color2);
    }

}