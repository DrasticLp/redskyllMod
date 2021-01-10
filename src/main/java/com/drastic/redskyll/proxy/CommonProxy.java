package com.drastic.redskyll.proxy;

import com.drastic.redskyll.blockhit.common.handler.ClassicCombatHandler;
import com.drastic.redskyll.blockhit.common.handler.CombatFoodHandler;
import com.drastic.redskyll.blockhit.common.handler.InitiateBlockHandler;
import com.drastic.redskyll.blockhit.common.helper.ItemBlockingHelper;
import com.drastic.redskyll.capabilities.HasBoss;
import com.drastic.redskyll.capabilities.HasMinion;
import com.drastic.redskyll.capabilities.Mana;
import com.drastic.redskyll.capabilities.Money;
import com.drastic.redskyll.capabilities.Relic;
import com.drastic.redskyll.capabilities.stockage.HasBossStorage;
import com.drastic.redskyll.capabilities.stockage.HasMinionStorage;
import com.drastic.redskyll.capabilities.stockage.ManaStorage;
import com.drastic.redskyll.capabilities.stockage.MoneyStorage;
import com.drastic.redskyll.capabilities.stockage.RelicStorage;
import com.drastic.redskyll.util.handlers.CapabilityHandler;
import com.drastic.redskyll.util.interfaces.IHasBoss;
import com.drastic.redskyll.util.interfaces.IHasMinion;
import com.drastic.redskyll.util.interfaces.IMana;
import com.drastic.redskyll.util.interfaces.IMoney;
import com.drastic.redskyll.util.interfaces.IRelic;
import com.google.common.util.concurrent.ListenableFuture;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CommonProxy
{
    public void registerItemRederer(Item item, int meta)
    {}

    public void registerVariantRenderer(Item item, int meta, String filename, String id)
    {}

    public void registerEntityRenderers()
    {}

    public void registerHandler()
    {}

    public EntityPlayer getPlayer(MessageContext context)
    {
        return context.getServerHandler().player;
    }

    public void preInit()
    {
        MinecraftForge.EVENT_BUS.register(new InitiateBlockHandler());
        MinecraftForge.EVENT_BUS.register(new ClassicCombatHandler());
        if(!Loader.isModLoaded("applecore"))
        {

            MinecraftForge.EVENT_BUS.register(new CombatFoodHandler());
        }
    }

    public void postInit()
    {
        ItemBlockingHelper.sync();
    }

    public void load()
    {
        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
        CapabilityManager.INSTANCE.register(IHasMinion.class, new HasMinionStorage(), HasMinion.class);
        CapabilityManager.INSTANCE.register(IHasBoss.class, new HasBossStorage(), HasBoss.class);
        CapabilityManager.INSTANCE.register(IMana.class, new ManaStorage(), Mana.class);
        CapabilityManager.INSTANCE.register(IMoney.class, new MoneyStorage(), Money.class);
        CapabilityManager.INSTANCE.register(IRelic.class, new RelicStorage(), Relic.class);
    }

    public ListenableFuture<Object> addScheduledTaskClient(Runnable runnableToSchedule)
    {
        throw new IllegalStateException("This should only be called from client side");
    }

    public EntityPlayer getClientPlayer()
    {
        throw new IllegalStateException("This should only be called from client side");
    }

    public Minecraft getClient()
    {
        throw new IllegalStateException("This should only be called from client side");
    }
}
