package com.drastic.redskyll.objects.packets;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.util.interfaces.IMoney;
import com.drastic.redskyll.util.provider.MoneyProvider;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SyncMoney implements IMessage
{
    private int money;

    public SyncMoney(IMoney money)
    {
        this.money = money.getMoney();
    }

    public SyncMoney()
    {

    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.money = buf.readInt();
    }
    

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(this.money);
    }

    public static class Handler implements IMessageHandler<SyncMoney, IMessage>
    {
        @Override
        public IMessage onMessage(SyncMoney message, MessageContext ctx)
        {
            Main.proxy.addScheduledTaskClient(() -> handle(message, ctx));
            return null;
        }

        private void handle(SyncMoney message, MessageContext ctx)
        {
            EntityPlayer player = Main.proxy.getClientPlayer();
            if(player instanceof EntityPlayer)
            {
                ((IMoney)player.getCapability(MoneyProvider.MONEY_CAP, null)).setMoney(message.money);
            }
        }
    }

}
