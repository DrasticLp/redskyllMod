package com.drastic.redskyll.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.drastic.redskyll.util.handlers.ConfigMod;
import com.drastic.redskyll.util.interfaces.IMoney;
import com.drastic.redskyll.util.provider.MoneyProvider;
import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class CommandSell extends CommandBase
{
    private final List<String> aliases = Lists.newArrayList("sell");

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
                player.sendMessage(new TextComponentString(TextFormatting.RED + "Format Incorrect, utilisez la commande comme ça :" + " /sell <item> <nombre>"));
            }
            else
            {
                boolean hasDone = false;
                int count = 0;
                for(int a = 0; a < ConfigMod.prices.length; a++)
                {
                    if(args.length == 1)
                    {

                        if(args[0].equalsIgnoreCase(ConfigMod.prices[a][0]))
                        {
                            player.sendMessage(new TextComponentString(TextFormatting.GREEN + "Prix de 1 * " + args[0].toUpperCase() + " : " + Integer.parseInt(ConfigMod.prices[a][2]) + "$"));

                        }
                        else if(args[0].replaceAll(" ", "").equalsIgnoreCase("hand"))
                        {
                            if(player.getHeldItem(EnumHand.MAIN_HAND).isEmpty() && !hasDone)
                            {
                                player.sendMessage(new TextComponentString(TextFormatting.RED + "Vous n'avez pas d'Item dans votre main"));
                                hasDone = true;
                            }
                            else if(player.getHeldItem(EnumHand.MAIN_HAND).getItem() == Item.getByNameOrId(ConfigMod.prices[a][0]) && !hasDone)
                            {
                                hasDone = true;

                                if(Integer.parseInt(ConfigMod.prices[a][2]) == 0)
                                {
                                    player.sendMessage(new TextComponentString(TextFormatting.RED + "Vous ne pouvez pas vendre cet item"));
                                }
                                else
                                {
                                    player.sendMessage(new TextComponentString(TextFormatting.GREEN + "Vous venez de vendre " + player.getHeldItem(EnumHand.MAIN_HAND).getCount() + " * " + ConfigMod.prices[a][0].toUpperCase() + " pour " + (parseInt(ConfigMod.prices[a][2]) * player.getHeldItem(EnumHand.MAIN_HAND).getCount()) + "$"));
                                    money.addMoney(parseInt(ConfigMod.prices[a][2]) * player.getHeldItem(EnumHand.MAIN_HAND).getCount());
                                    player.getHeldItem(EnumHand.MAIN_HAND).shrink(player.getHeldItem(EnumHand.MAIN_HAND).getCount());
                                    hasDone = true;
                                }
                            }
                        }
                    }
                    else if(args.length == 2)
                    {
                        if(args[0].equalsIgnoreCase(ConfigMod.prices[a][0]))
                        {
                            System.out.println("inventory");
                            for(int i = 0; i < 36; i++)
                            {
                                count++;
                                if(player.inventory.getStackInSlot(i).getItem() == Item.getByNameOrId(args[0]) && !hasDone)
                                {
                                    try
                                    {
                                        if(Integer.parseInt(args[1]) > 64)
                                        {
                                            hasDone = true;
                                            player.sendMessage(new TextComponentString(TextFormatting.RED + "Vous ne pouvez vendre que 64 items a la fois"));
                                        }
                                        else if(player.inventory.getStackInSlot(i).getCount() >= Integer.parseInt(args[1]))
                                        {
                                            if(parseInt(ConfigMod.prices[a][2]) == 0)
                                            {
                                                player.sendMessage(new TextComponentString(TextFormatting.RED + "Vous ne pouvez pas vendre cet item"));
                                            }
                                            else
                                            {
                                                player.inventory.getStackInSlot(i).shrink(parseInt(args[1]));
                                                money.addMoney(parseInt(ConfigMod.prices[a][2]) * parseInt(args[1]));
                                                hasDone = true;
                                                player.sendMessage(new TextComponentString(TextFormatting.GREEN + "Vous venez de vendre " + args[1] + " * " + args[0].toUpperCase() + " pour " + (parseInt(ConfigMod.prices[a][2]) * parseInt(args[1])) + "$"));
                                            }
                                        }
                                        else
                                        {
                                            hasDone = true;
                                            player.sendMessage(new TextComponentString(TextFormatting.RED + "Vous n'avez pas assez de " + args[0].toUpperCase()));
                                        }
                                    }

                                    catch(NumberFormatException e)
                                    {
                                        hasDone = true;
                                        player.sendMessage(new TextComponentString(TextFormatting.RED + "Nombre Incorrect"));
                                    }
                                }
                                else
                                {
                                    if(count == 35 && !hasDone)
                                    {
                                        player.sendMessage(new TextComponentString(TextFormatting.RED + "Vous n'avez pas de " + args[0].toUpperCase()));
                                    }
                                }
                            }
                        }
                    }
                    else
                    {
                        player.sendMessage(new TextComponentString(TextFormatting.RED + "Format Incorrect, utilisez la commande comme ça :" + " /sell <item> <nombre>"));
                    }
                }

            }
        }

    }

    @Override
    public String getName()
    {
        return "sell";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "/sell [item/hand] [count]";
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
