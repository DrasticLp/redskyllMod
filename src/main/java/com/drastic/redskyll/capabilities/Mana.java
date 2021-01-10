package com.drastic.redskyll.capabilities;

import com.drastic.redskyll.util.interfaces.IHasMinion;
import com.drastic.redskyll.util.interfaces.IMana;

public class Mana implements IMana
{
    private int mana = 20;
    
    @Override
    public void setMana(int mana)
    {
        this.mana = mana;
    }

    @Override
    public void shrinkMana(int count)
    {
        this.mana -= count;
        if(this.mana < 0) this.mana = 0;
    }
    
    @Override
    public int getMana()
    {
        return mana;
    }

    @Override
    public void addMana(int count)
    {
        this.mana += count;
    }

}
