package com.drastic.redskyll.capabilities;

import com.drastic.redskyll.util.interfaces.IMoney;

public class Money implements IMoney
{
    private int money = 100;

    @Override
    public void setMoney(int money)
    {
        this.money = money;
    }

    @Override
    public void shrinkMoney(int count)
    {
        if(this.money >= count)
        {
            this.money -= count;
        }
    }

    @Override
    public int getMoney()
    {
        return money;
    }

    @Override
    public void addMoney(int count)
    {
        this.money += count;
    }

    @Override
    public String getMoneyString()
    {
        if(this.money > 999999 && this.money < 1000000000)
        {
            int m = this.money;
            int m2 = m / 1000000;
            int m3 = m2 * 1000000;
            int m4 = m - m3;
            int m5 = m4 / 1000;
            if(m4 > 999)
            {
                return m2 + "." + m5 + "M";
            }
            else
            {
                return m2 + "M";
            }
        }
        if(this.money > 999999999)
        {
            int m = this.money;
            int m2 = m / 1000000000;
            int m3 = m2 * 1000000000;
            int m4 = m - m3;
            int m5 = m4 / 1000000;
            if(m4 > 999999)
            {
                return m2 + "." + m5 + "Md";
            }
            else
            {
                return m2 + "Md";
            }
        }
        if(this.money < 1000000 && this.money > 999)
        {
            int m = this.money;
            int m2 = m / 1000;
            int m3 = m2 * 1000;
            int m4 = m - m3;
            int m5 = m4;
            if(m4 > 0)
            {
                return m2 + "." + m5 + "K";
            }
            else
            {
                return m2 + "K";
            }
        }

        else
        {
            return this.money + "$";
        }
    }

}
