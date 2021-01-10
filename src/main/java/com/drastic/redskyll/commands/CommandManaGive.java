package com.drastic.redskyll.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.drastic.redskyll.util.interfaces.IMana;
import com.drastic.redskyll.util.interfaces.IMoney;
import com.drastic.redskyll.util.provider.ManaProvider;
import com.drastic.redskyll.util.provider.MoneyProvider;
import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class CommandManaGive extends CommandBase
{
    private final List<String> aliases = Lists.newArrayList("mana");

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

        if(args.length < 1)
        {
            sender.sendMessage(new TextComponentString(TextFormatting.RED + "Arguments Incorrects"));
        }
        else if(args.length == 1 && isPlayerOnline(args[0], server.getOnlinePlayerNames()))
        {
            IMana mana = (getPlayer(server, sender, args[0])).getCapability(ManaProvider.MANA_CAP, null);

            sender.sendMessage(new TextComponentString(TextFormatting.GREEN + args[0] + " a " + (mana.getMana() + " de mana")));
        }
        else if(args.length == 2)
        {
            sender.sendMessage(new TextComponentString(TextFormatting.RED + "Format Incorrect, utilisez la commande comme ça :" + " /mana <joueur> remove/give <nombre> "));
        }
        else if(args.length == 3 && isPlayerOnline(args[0], server.getOnlinePlayerNames()))
        {
            IMana mana = (getPlayer(server, sender, args[0])).getCapability(ManaProvider.MANA_CAP, null);

            if("remove".equalsIgnoreCase(args[1]))
            {
                try
                {
                    if(mana.getMana() >= Integer.parseInt(args[2]))
                    {
                        mana.shrinkMana(Integer.parseInt(args[2]));
                        sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "Vous venez de prendre " + args[2] + " de mana" + " à " + args[0]));

                        getPlayer(server, sender, args[0]).sendMessage(new TextComponentString(TextFormatting.RED + "Un Administrateur vient de vous prendre " + args[2] + " de mana"));
                    }
                    else
                    {
                        sender.sendMessage(new TextComponentString(TextFormatting.RED + "Le Joueur n'a pas assez de Mana. Sa mana a donc ete remis a 0"));
                        mana.setMana(0);
                        getPlayer(server, sender, args[0]).sendMessage(new TextComponentString(TextFormatting.RED + "Un Administrateur vient de vous retirer votre mana"));
                    }
                }
                catch(NumberFormatException e)
                {
                    sender.sendMessage(new TextComponentString(TextFormatting.RED + "Nombre Incorrect"));
                }
            }
            else if("give".equalsIgnoreCase(args[1]))
            {
                try
                {
                    if((mana.getMana() + parseInt(args[2])) <= 20)
                    {
                        mana.addMana(Integer.parseInt(args[2]));
                        sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "Vous venez de donner " + args[2] + " de mana" + " à " + args[0]));

                        getPlayer(server, sender, args[0]).sendMessage(new TextComponentString(TextFormatting.GREEN + "Un Administrateur vient de vous donner " + args[2] + " de mana"));

                    }
                    else
                    {
                        sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "Vous ne pouvez pas donner autant de mana a " + args[1] + " donc sa mana a ete remplie"));
                        mana.setMana(20);
                    }
                }
                catch(NumberFormatException e)
                {
                    sender.sendMessage(new TextComponentString(TextFormatting.RED + "Nombre Incorrect"));
                }
            }
        }

    }

    @Override
    public String getName()
    {
        return "mana";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "/mana [action] [count] [reciever]";
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos)
    {
        List<String> list = new ArrayList<String>();
        list.add("remove");
        list.add("give");

        List<String> list2 = new ArrayList<String>();

        if(args.length == 1)
        {
            for(int i = 0; i < server.getOnlinePlayerNames().length; i++)
            {
                list2.add(server.getOnlinePlayerNames()[i]);
            }
            return list2;
        }
        else if(args.length == 2)
        {
            return list;
        }
        else
        {
            return Collections.EMPTY_LIST;
        }
    }

}
