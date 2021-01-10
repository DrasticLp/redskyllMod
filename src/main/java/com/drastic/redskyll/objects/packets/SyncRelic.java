package com.drastic.redskyll.objects.packets;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.util.interfaces.IHasMinion;
import com.drastic.redskyll.util.interfaces.IRelic;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import com.drastic.redskyll.util.provider.MinionProvider;
import com.drastic.redskyll.util.provider.RelicProvider;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SyncRelic implements IMessage
{
    private int relic;

    public SyncRelic(IRelic relic)
    {
        this.relic = relic.get();
    }

    public SyncRelic()
    {

    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.relic = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(this.relic);
    }

    public static class Handler implements IMessageHandler<SyncRelic, IMessage>
    {
        @Override
        public IMessage onMessage(SyncRelic message, MessageContext ctx)
        {
            Main.proxy.addScheduledTaskClient(() -> handle(message, ctx));
            return null;
        }

        private void handle(SyncRelic message, MessageContext ctx)
        {
            EntityPlayer player = Main.proxy.getClientPlayer();
            if(player instanceof EntityPlayer)
            {
                ((IRelic)player.getCapability(RelicProvider.RELIC_CAP, null)).set(message.relic);
            }
        }
    }

}
