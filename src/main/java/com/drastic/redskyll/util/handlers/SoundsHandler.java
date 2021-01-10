package com.drastic.redskyll.util.handlers;

import com.drastic.redskyll.util.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundsHandler
{
    public static SoundEvent IDLE_FUSE;
    public static SoundEvent FUSE_LAUNCH;
    public static SoundEvent AMOR_EQUIP_KRAMAZIA;
    public static SoundEvent RADIO;
    public static SoundEvent RAGE1;
    public static SoundEvent RAGE2;
    public static SoundEvent AMBIENT;
  
    public static void registerSounds()
    {
        IDLE_FUSE = registerSound("entity.fuse.idle");
        FUSE_LAUNCH = registerSound("entity.fuse.launch");
        AMOR_EQUIP_KRAMAZIA = registerSound("armor.kramazia");
        RADIO = registerSound("block.radio.play");
        RAGE1 = registerSound("entity.redskyll.rage1");
        RAGE2 = registerSound("entity.redskyll.rage2");
        AMBIENT = registerSound("entity.redskyll.ambient");
  }
    
    private static SoundEvent registerSound(String name)
    {
        ResourceLocation location = new ResourceLocation(Reference.MODID, name);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(name);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }
    
}
