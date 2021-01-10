package com.drastic.redskyll.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentKeybind;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class CommandPay extends CommandBase
{
    private final List<String> aliases = Lists.newArrayList("pay");

    boolean isPlayerOnline(String arg, String[] array)
    {
       for(int i = 0; i < array.length; i++) 
       {
           if(arg.equalsIgnoreCase(array[i]))
           {
               return true;
           }
       }
       return false;
    }
    
    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {    
        EntityPlayerMP player = (EntityPlayerMP)sender;

        if(player instanceof EntityPlayer && player.isServerWorld())
        {
            if(args.length == 0)
            {
                player.sendMessage(new TextComponentString(TextFormatting.RED + "Format Incorrect, utilisez la commande comme ça :"+" /pay joueur <nombre>"));
            }
            else
            {
                if(isPlayerOnline(args[0], server.getOnlinePlayerNames()))
                {
                    if(args.length < 2)
                    {
                        player.sendMessage(new TextComponentString(TextFormatting.RED + "Format Incorrect, utilisez la commande comme ça :"+" /pay joueur <nombre>"));
                    }
                    else if(args.length == 2)
                    {
                        try
                        {        
                            IMoney moneySender = (player).getCapability(MoneyProvider.MONEY_CAP, null);
                            IMoney moneyReciever = (getPlayer(server, sender, args[0])).getCapability(MoneyProvider.MONEY_CAP, null);
                            if(moneySender.getMoney() >= Integer.parseInt(args[1]))
                            {
                                moneyReciever.addMoney(Integer.parseInt(args[1]));
                                moneySender.shrinkMoney(Integer.parseInt(args[1]));
                                sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "Vous venez de donner " + args[1] + "$ à " + args[0]));
                                getPlayer(server, sender, args[0]).sendMessage(new TextComponentString(TextFormatting.GREEN + "Vous venez de recevoir " + args[1] + "$ de " + sender.getName()));
                            }
                            else
                            {
                                sender.sendMessage(new TextComponentString(TextFormatting.RED + "Vous n'avez pas assez d'argent"));
                            }
                            
                        }
                        catch(NumberFormatException e)
                        {
                            sender.sendMessage(new TextComponentString(TextFormatting.RED + "Nombre Incorrect"));
                        }
                    }
                }
                else
                {
                    player.sendMessage(new TextComponentString(TextFormatting.RED + "Joueur Hors-Ligne"));
                }
            }
        }
    }

    @Override
    public String getName()
    {
        return "pay";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "/pay [reciever] [count]";
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
            for(int i = 0; i < server.getOnlinePlayerNames().length; i++)
            {
                list.add(server.getOnlinePlayerNames()[i]);
            }
        }
        else if(args.length == 1)
        {
            return Collections.EMPTY_LIST;
        }
        return list;
    }
}
