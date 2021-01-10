package com.drastic.redskyll.objects.packets;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.util.interfaces.IHasBoss;
import com.drastic.redskyll.util.provider.BossProvider;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SyncBoss implements IMessage
{
    private int boss;

    public SyncBoss(IHasBoss boss)
    {
        this.boss = boss.get();
    }

    public SyncBoss()
    {

    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.boss = buf.readInt();
    }
    

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(this.boss);
    }

    public static class Handler implements IMessageHandler<SyncBoss, IMessage>
    {
        @Override
        public IMessage onMessage(SyncBoss message, MessageContext ctx)
        {
            Main.proxy.addScheduledTaskClient(() -> handle(message, ctx));
            return null;
        }

        private void handle(SyncBoss message, MessageContext ctx)
        {
            EntityPlayer player = Main.proxy.getClientPlayer();
            if(player instanceof EntityPlayer)
            {
                ((IHasBoss)player.getCapability(BossProvider.BOSS_CAP, null)).set(message.boss);
            }
        }
    }

}
