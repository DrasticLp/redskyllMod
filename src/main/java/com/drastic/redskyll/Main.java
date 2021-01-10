package com.drastic.redskyll;

import org.apache.logging.log4j.Logger;

import com.drastic.redskyll.commands.CommandBuy;
import com.drastic.redskyll.commands.CommandDelHome;
import com.drastic.redskyll.commands.CommandDelWarp;
import com.drastic.redskyll.commands.CommandEc;
import com.drastic.redskyll.commands.CommandEcAdmin;
import com.drastic.redskyll.commands.CommandHomeTeleport;
import com.drastic.redskyll.commands.CommandInvsee;
import com.drastic.redskyll.commands.CommandManaGive;
import com.drastic.redskyll.commands.CommandMoney;
import com.drastic.redskyll.commands.CommandMoneyGive;
import com.drastic.redskyll.commands.CommandNv;
import com.drastic.redskyll.commands.CommandPay;
import com.drastic.redskyll.commands.CommandRelic;
import com.drastic.redskyll.commands.CommandSell;
import com.drastic.redskyll.commands.CommandSetHome;
import com.drastic.redskyll.commands.CommandSetWarp;
import com.drastic.redskyll.commands.CommandSpawn;
import com.drastic.redskyll.commands.CommandTpDim;
import com.drastic.redskyll.commands.CommandTps;
import com.drastic.redskyll.commands.CommandWarpTeleport;
import com.drastic.redskyll.objects.packets.KickPlayer;
import com.drastic.redskyll.objects.packets.SyncBoss;
import com.drastic.redskyll.objects.packets.SyncMoney;
import com.drastic.redskyll.objects.packets.SyncRelic;
import com.drastic.redskyll.objects.packets.SyncKeybind;
import com.drastic.redskyll.objects.packets.SyncMana;
import com.drastic.redskyll.objects.packets.SyncMinion;
import com.drastic.redskyll.proxy.CommonProxy;
import com.drastic.redskyll.tabs.TabRedskyll;
import com.drastic.redskyll.util.Reference;
import com.drastic.redskyll.util.handlers.RegistryHandler;
import com.drastic.redskyll.util.helpers.ModsHelper;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Main
{
    @Instance
    public static Main instance;

    public static CreativeTabs REDSKYLL_TAB = new TabRedskyll("redskyll");

    @SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
    public static CommonProxy proxy;

    public static Logger logger;

    public static SimpleNetworkWrapper network;

    static
    {
        FluidRegistry.enableUniversalBucket();
    }

    @EventHandler
    public static void preInit(FMLPreInitializationEvent e)
    {
        proxy.load();

        logger = e.getModLog();

        proxy.preInit();

        RegistryHandler.preInitRegistries(e);

        network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);
        network.registerMessage(SyncMoney.Handler.class, SyncMoney.class, 27, Side.CLIENT);
        network.registerMessage(SyncMinion.Handler.class, SyncMinion.class, 28, Side.CLIENT);
        network.registerMessage(SyncBoss.Handler.class, SyncBoss.class, 29, Side.CLIENT);
        network.registerMessage(SyncMana.Handler.class, SyncMana.class, 30, Side.CLIENT);
        network.registerMessage(SyncRelic.Handler.class, SyncRelic.class, 31, Side.CLIENT);
        network.registerMessage(SyncKeybind.Handler.class, SyncKeybind.class, 25, Side.SERVER);
        network.registerMessage(KickPlayer.Handler.class, KickPlayer.class, 26, Side.SERVER);
        ModsHelper.setMods();
    }

    @EventHandler
    public static void init(FMLInitializationEvent e)
    {
        RegistryHandler.initRegistries(e);
        proxy.registerHandler();
    }

    @EventHandler
    public static void postInit(FMLPostInitializationEvent e)
    {
        proxy.postInit();
    }

    @EventHandler
    public static void serverInit(FMLServerStartingEvent e)
    {
        /*
         * String dir = DimensionManager.getCurrentSaveRootDirectory() + "/DIM1/";
         * File file = new File(dir);
         * file.deleteOnExit();
         * String dir2 = DimensionManager.getCurrentSaveRootDirectory() + "/DIM2/";
         * File file2 = new File(dir2);
         * file2.deleteOnExit();
         */

        e.registerServerCommand(new CommandMoneyGive());
        e.registerServerCommand(new CommandPay());
        e.registerServerCommand(new CommandBuy());
        e.registerServerCommand(new CommandSell());
        e.registerServerCommand(new CommandMoney());
        e.registerServerCommand(new CommandSpawn());
        e.registerServerCommand(new CommandInvsee());
        e.registerServerCommand(new CommandSetHome());
        e.registerServerCommand(new CommandDelHome());
        e.registerServerCommand(new CommandHomeTeleport());
        e.registerServerCommand(new CommandSetWarp());
        e.registerServerCommand(new CommandDelWarp());
        e.registerServerCommand(new CommandWarpTeleport());
        e.registerServerCommand(new CommandEcAdmin());
        e.registerServerCommand(new CommandEc());
        e.registerServerCommand(new CommandTpDim());
        e.registerServerCommand(new CommandNv());
        e.registerServerCommand(new CommandManaGive());
        e.registerServerCommand(new CommandTps());
        e.registerServerCommand(new CommandRelic());
    }
}