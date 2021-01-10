package com.drastic.redskyll.objects.packets;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.util.interfaces.IHasMinion;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import com.drastic.redskyll.util.provider.MinionProvider;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SyncMinion implements IMessage
{
    private int minion;

    public SyncMinion(IHasMinion minion)
    {
        this.minion = minion.get();
    }

    public SyncMinion()
    {

    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.minion = buf.readInt();
    }
    

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(this.minion);
    }

    public static class Handler implements IMessageHandler<SyncMinion, IMessage>
    {
        @Override
        public IMessage onMessage(SyncMinion message, MessageContext ctx)
        {
            Main.proxy.addScheduledTaskClient(() -> handle(message, ctx));
            return null;
        }

        private void handle(SyncMinion message, MessageContext ctx)
        {
            EntityPlayer player = Main.proxy.getClientPlayer();
            if(player instanceof EntityPlayer)
            {
                ((IHasMinion)player.getCapability(MinionProvider.MINION_CAP, null)).set(message.minion);
            }
        }
    }

}
