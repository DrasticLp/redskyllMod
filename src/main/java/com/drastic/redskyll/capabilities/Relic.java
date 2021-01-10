package com.drastic.redskyll.capabilities;

import com.drastic.redskyll.util.interfaces.IHasBoss;
import com.drastic.redskyll.util.interfaces.IRelic;

public class Relic implements IRelic
{
    private int relic = 155;
    
    @Override
    public void set(int relic)
    {
        this.relic = relic;
    }

    @Override
    public int get()
    {
        return relic;
    }
}
