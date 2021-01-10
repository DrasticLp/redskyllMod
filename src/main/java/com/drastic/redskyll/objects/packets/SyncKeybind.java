package com.drastic.redskyll.objects.packets;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.objects.items.armor.ArmourJetpack;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SyncKeybind implements IMessage
{

    public SyncKeybind()
    {

    }

    public static class Handler implements IMessageHandler<SyncKeybind, IMessage>
    {

        @Override
        public IMessage onMessage(SyncKeybind message, MessageContext ctx)
        {
            EntityPlayerMP serverPlayer = ctx.getServerHandler().player;

            WorldServer worldServer = serverPlayer.getServerWorld();
            worldServer.addScheduledTask(() ->
            {
                // System.out.println("poof");
                ItemStack jetpackStack = serverPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
                if((jetpackStack.getMaxDamage() - jetpackStack.getItemDamage()) >= 1)
                {
                    jetpackStack.damageItem(1, serverPlayer);
                }
                // serverPlayer.addVelocity(0, 1, 0);
            });
            return null;
        }

        public static EntityPlayer getPlayer(MessageContext context)
        {
            return Main.proxy.getPlayer(context);
        }
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {

    }

    @Override
    public void toBytes(ByteBuf buf)
    {

    }

}
