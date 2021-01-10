package com.drastic.redskyll.objects.items;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.util.interfaces.IHasModel;
import com.drastic.redskyll.util.interfaces.IMana;
import com.drastic.redskyll.util.interfaces.IMoney;
import com.drastic.redskyll.util.provider.ManaProvider;
import com.drastic.redskyll.util.provider.MoneyProvider;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemMoney extends Item implements IHasModel
{
    public ItemMoney(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.REDSKYLL_TAB);
        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRederer(this, 0);
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        IMoney money = ((EntityPlayer)playerIn).getCapability(MoneyProvider.MONEY_CAP, null);

        if(playerIn.isSneaking())
        {
            money.addMoney(playerIn.getHeldItem(handIn).getCount() * 1000000);
            playerIn.getHeldItem(handIn).shrink(playerIn.getHeldItem(handIn).getCount());            
        }
        else
        {
            playerIn.getHeldItem(handIn).shrink(1);
            money.addMoney(100); 
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
