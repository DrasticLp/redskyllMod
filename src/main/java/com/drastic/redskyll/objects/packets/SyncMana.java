package com.drastic.redskyll.objects.packets;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.util.interfaces.IMana;
import com.drastic.redskyll.util.interfaces.IMoney;
import com.drastic.redskyll.util.provider.ManaProvider;
import com.drastic.redskyll.util.provider.MoneyProvider;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SyncMana implements IMessage
{
    private int mana;

    public SyncMana(IMana mana)
    {
        this.mana = mana.getMana();
    }

    public SyncMana()
    {

    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.mana = buf.readInt();
    }
    

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(this.mana);
    }

    public static class Handler implements IMessageHandler<SyncMana, IMessage>
    {
        @Override
        public IMessage onMessage(SyncMana message, MessageContext ctx)
        {
            Main.proxy.addScheduledTaskClient(() -> handle(message, ctx));
            return null;
        }

        private void handle(SyncMana message, MessageContext ctx)
        {
            EntityPlayer player = Main.proxy.getClientPlayer();
            if(player instanceof EntityPlayer)
            {
                ((IMana)player.getCapability(ManaProvider.MANA_CAP, null)).setMana(message.mana);
            }
        }
    }

}
