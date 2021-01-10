package com.drastic.redskyll.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.drastic.redskyll.util.handlers.ConfigMod;
import com.drastic.redskyll.util.helpers.MoneyHelper;
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

public class CommandBaltop extends CommandBase
{
    private final List<String> aliases = Lists.newArrayList("baltop", "balancetop");

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {

        if(sender instanceof EntityPlayer)
        {
            if(args.length == 0)
            {
                HashMap<EntityPlayer, Integer> map = new HashMap<EntityPlayer, Integer>();

                for(int i = 0; i < server.getOnlinePlayerNames().length; i++)
                {
                    EntityPlayer player = server.getPlayerList().getPlayers().get(i);
                    map.put(player, MoneyHelper.getMoney(player));
                    sender.sendMessage(new TextComponentString(map.get(player)+""));
                }
            }

        }
    }

    @Override
    public String getName()
    {
        return "baltop";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "/baltop";
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
        return Collections.EMPTY_LIST;

    }
}
