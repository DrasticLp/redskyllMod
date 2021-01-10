package com.drastic.redskyll.proxy;

import org.lwjgl.input.Keyboard;

import com.drastic.redskyll.blockhit.client.handler.NoCooldownHandler;
import com.drastic.redskyll.blockhit.client.handler.RenderBlockingHandler;
import com.drastic.redskyll.client.gui.GuiF3;
import com.drastic.redskyll.client.gui.GuiMana;
import com.drastic.redskyll.util.handlers.KeybindHandler;
import com.drastic.redskyll.util.handlers.RenderHandler;
import com.google.common.util.concurrent.ListenableFuture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.Util;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy
{
    public static KeyBinding[] keyBindings;

    @Override
    public void registerItemRederer(Item item, int meta)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    @Override
    public void registerEntityRenderers()
    {
        RenderHandler.registerEntityRenders();
    }

    @Override
    public void postInit()
    {
        super.postInit();
    }
    
    @Override
    public void registerHandler()
    {
        MinecraftForge.EVENT_BUS.register(new KeybindHandler());
    }
    
    @Override
    public void preInit()
    {

        super.preInit();

        MinecraftForge.EVENT_BUS.register(new GuiMana());
        MinecraftForge.EVENT_BUS.register(new GuiF3());

        org.lwjgl.opengl.Display.setTitle("Redskyll's Adventure");

        Util.EnumOS util$enumos = Util.getOSType();

        if(util$enumos != Util.EnumOS.OSX)
        {

            libs.club.minnced.discord.rpc.DiscordRichPresence presence = new libs.club.minnced.discord.rpc.DiscordRichPresence();
            libs.club.minnced.discord.rpc.DiscordRPC lib = libs.club.minnced.discord.rpc.DiscordRPC.INSTANCE;
            String applicationId = "739129492248985666";
            String steamId = "";
            libs.club.minnced.discord.rpc.DiscordEventHandlers handlers = new libs.club.minnced.discord.rpc.DiscordEventHandlers();
            handlers.ready = () -> System.out.println("Ready!");
            lib.Discord_Initialize(applicationId, handlers, true, steamId);
            presence.startTimestamp = System.currentTimeMillis() / 1000; // epoch second
            presence.details = "Joue";
            presence.largeImageKey = "icon";
            presence.state = "Sur le Serveur";
            lib.Discord_UpdatePresence(presence);
        }
        MinecraftForge.EVENT_BUS.register(new RenderBlockingHandler());
        MinecraftForge.EVENT_BUS.register(new NoCooldownHandler());

        keyBindings = new KeyBinding[1];
        
        keyBindings[0] = new KeyBinding("key.hammer.jump", Keyboard.KEY_B, "key.redskyll.category");

        for(int i = 0; i < keyBindings.length; ++i)
        {
            ClientRegistry.registerKeyBinding(keyBindings[i]);
        }
    }

    @Override
    public ListenableFuture<Object> addScheduledTaskClient(Runnable runnableToSchedule)
    {
        return Minecraft.getMinecraft().addScheduledTask(runnableToSchedule);
    }

    @Override
    public EntityPlayer getClientPlayer()
    { 
        return Minecraft.getMinecraft().player;
    }
    
    @Override
    public Minecraft getClient()
    {
        return Minecraft.getMinecraft();
    }
}
