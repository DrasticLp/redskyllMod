package com.drastic.redskyll.util.helpers;

import com.drastic.redskyll.util.interfaces.IMoney;
import com.drastic.redskyll.util.provider.MoneyProvider;

import net.minecraft.entity.player.EntityPlayer;

public class MoneyHelper
{
    public static void resetMoney(EntityPlayer player)
    {
        IMoney money = (player).getCapability(MoneyProvider.MONEY_CAP, null);

        money.setMoney(0);
    }
    
    public static void addMoney(EntityPlayer player, int count)
    {
        IMoney money = (player).getCapability(MoneyProvider.MONEY_CAP, null);

        money.addMoney(count);
    }
    
    public static void shrinkMoney(EntityPlayer player, int count)
    {
        IMoney money = (player).getCapability(MoneyProvider.MONEY_CAP, null);

        money.shrinkMoney(count);
    }
    
    public static void setMoney(EntityPlayer player, int count)
    {
        IMoney money = (player).getCapability(MoneyProvider.MONEY_CAP, null);

        money.setMoney(count);
    }
    
    public static int getMoney(EntityPlayer player)
    {
        IMoney money = (player).getCapability(MoneyProvider.MONEY_CAP, null);

        return money.getMoney();
    }
}
