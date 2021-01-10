package com.drastic.redskyll.util.interfaces;

public interface IMana
{
    public void setMana(int mana);
    public void shrinkMana(int count);
    public int getMana();
    public void addMana(int count);
}