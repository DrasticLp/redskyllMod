package com.drastic.redskyll.capabilities;

import com.drastic.redskyll.util.interfaces.IHasMinion;

public class HasMinion implements IHasMinion
{
    private int hasMinion = 0;
    
    @Override
    public void set(int hasMinion)
    {
        this.hasMinion = hasMinion;
    }

    @Override
    public int get()
    {
        return hasMinion;
    }

}
