package com.drastic.redskyll.objects.packets;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.client.gui.GuiColor;
import com.drastic.redskyll.util.interfaces.IMana;
import com.drastic.redskyll.util.provider.ManaProvider;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class ColorPacket implements IMessage
{
    public ColorPacket()
    {

    }

    @Override
    public void fromBytes(ByteBuf buf)
    {

    }

    @Override
    public void toBytes(ByteBuf buf)
    {

    }

    public static class Handler implements IMessageHandler<ColorPacket, IMessage>
    {

        @Override
        public IMessage onMessage(ColorPacket message, MessageContext ctx)
        {
            Main.proxy.addScheduledTaskClient(() -> handle(message, ctx));
            return null;
        }

        private void handle(ColorPacket message, MessageContext ctx)
        {
            Main.proxy.getClient().displayGuiScreen(new GuiColor());
        }
    }
}
