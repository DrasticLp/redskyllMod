package com.drastic.redskyll.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.drastic.redskyll.util.handlers.ConfigMod;
import com.drastic.redskyll.util.interfaces.IMoney;
import com.drastic.redskyll.util.provider.MoneyProvider;
import com.google.common.collect.Lists;

import ibxm.Player;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentKeybind;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class CommandBuy extends CommandBase
{
    private final List<String> aliases = Lists.newArrayList("buy");

    boolean isItemCorrect(String arg, String[][] array)
    {
        for(int i = 0; i < array.length; i++)
        {
            if(arg.equalsIgnoreCase(array[i][0]))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {

        if(sender instanceof EntityPlayer)
        {
            EntityPlayerMP player = (EntityPlayerMP)sender;

            IMoney money = (player).getCapability(MoneyProvider.MONEY_CAP, null);

            if(args.length == 0)
            {
                player.sendMessage(new TextComponentString(TextFormatting.RED + "Format Incorrect, utilisez la commande comme ça :" + " /buy <item> <nombre>"));
            }
            if(args.length == 1)
            {
                for(int i = 0; i < ConfigMod.prices.length; i++)
                {
                    if(args[0].equalsIgnoreCase(ConfigMod.prices[i][0]))
                    {
                        player.sendMessage(new TextComponentString(TextFormatting.GREEN + "Prix de 1 * " + args[0].toUpperCase() + " : " + Integer.parseInt(ConfigMod.prices[i][1]) + "$"));

                    }
                }
            }
            else
            {
                for(int i = 0; i < ConfigMod.prices.length; i++)
                {
                    if(args[0].equalsIgnoreCase(ConfigMod.prices[i][0]))
                    {
                        try
                        {
                            if(money.getMoney() >= (Integer.parseInt(ConfigMod.prices[i][1]) * Integer.parseInt(args[1])))
                            {
                                player.addItemStackToInventory(new ItemStack(Item.getByNameOrId(ConfigMod.prices[i][0]), Integer.parseInt(args[1])));
                                money.shrinkMoney(Integer.parseInt(ConfigMod.prices[i][1]) * Integer.parseInt(args[1]));
                                player.sendMessage(new TextComponentString(TextFormatting.GREEN + "Vous venez d'acheter " + args[1] + " * " + args[0].toUpperCase() + " pour " + Integer.parseInt(ConfigMod.prices[i][1]) * Integer.parseInt(args[1]) + "$"));
                            }

                            else
                            {
                                player.sendMessage(new TextComponentString(TextFormatting.RED + "Vous n'avez pas assez d'argent"));
                            }
                        }
                        catch(NumberFormatException e)
                        {
                            player.sendMessage(new TextComponentString(TextFormatting.RED + "Nombre Incorrect"));
                        }

                    }

                }
            }
        }
    }

    @Override
    public String getName()
    {
        return "buy";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "/buy [item] [count]";
    }

    @Override
    public List<String> getAliases()
    {
        return aliases;
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        return true;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos)
    {
        List<String> list = new ArrayList<String>();
        if(args.length == 1)
        {
            for(int i = 0; i < ConfigMod.prices.length; i++)
            {
                list.add(ConfigMod.prices[i][0]);
            }
        }
        else if(args.length == 1)
        {
            return Collections.EMPTY_LIST;
        }
        return list;
    }
}
