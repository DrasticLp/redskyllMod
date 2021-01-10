package com.drastic.redskyll.objects.packets;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.objects.items.armor.ArmourJetpack;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class KickPlayer implements IMessage
{
    public KickPlayer()
    {
        
    }
    
    public static class Handler implements IMessageHandler<KickPlayer, IMessage>
    {

        @Override
        public IMessage onMessage(KickPlayer message, MessageContext ctx)
        {
            EntityPlayerMP serverPlayer = ctx.getServerHandler().player;

            WorldServer worldServer = serverPlayer.getServerWorld();
            worldServer.addScheduledTask(() ->
            {
                serverPlayer.connection.disconnect(new TextComponentString("Vous avez des Mods en trop sur votre client."));
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
