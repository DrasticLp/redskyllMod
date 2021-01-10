package com.drastic.redskyll.util.interfaces;

public interface IMoney
{
    public void setMoney(int money);
    public void shrinkMoney(int count);
    public void addMoney(int count);   
    public int getMoney();
    public String getMoneyString();
}