package com.drastic.redskyll.capabilities;

import com.drastic.redskyll.util.interfaces.IHasBoss;

public class HasBoss implements IHasBoss
{
    private int hasBoss = 0;
    
    @Override
    public void set(int hasBoss)
    {
        this.hasBoss = hasBoss;
    }

    @Override
    public int get()
    {
        return hasBoss;
    }

}
