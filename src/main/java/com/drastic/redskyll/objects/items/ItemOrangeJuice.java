package com.drastic.redskyll.objects.items;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.util.interfaces.IHasModel;
import com.drastic.redskyll.util.interfaces.IMana;
import com.drastic.redskyll.util.provider.ManaProvider;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemOrangeJuice extends ItemFood implements IHasModel
{
    public ItemOrangeJuice(String name, int amount, boolean isWolfFood)
    {
        super(amount, isWolfFood);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.REDSKYLL_TAB);
        ItemInit.ITEMS.add(this);
        setAlwaysEdible();
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRederer(this, 0);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.DRINK;
    }
    
    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 25 * 20, 1));
        super.onFoodEaten(stack, worldIn, player);
        IMana mana = player.getCapability(ManaProvider.MANA_CAP, null);

        if(mana.getMana() != 20 && mana.getMana() != 18 && mana.getMana() != 19)
        {
            mana.setMana(mana.getMana() + 3);
        }
        else if(mana.getMana() == 18)
        {
            mana.setMana(mana.getMana() + 2);

            // Main.network.sendTo(new SyncManaData(player.getCapability(ManaProvider.MANA_CAP, null)), (EntityPlayerMP) player);
        }
        else if(mana.getMana() == 19)
        {
            mana.setMana(mana.getMana() + 1); 
        }

    }

}
