package com.drastic.redskyll.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.drastic.redskyll.util.handlers.ConfigMod;
import com.drastic.redskyll.util.interfaces.IMoney;
import com.drastic.redskyll.util.interfaces.IRelic;
import com.drastic.redskyll.util.provider.MoneyProvider;
import com.drastic.redskyll.util.provider.RelicProvider;
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

public class CommandRelic extends CommandBase
{
    private final List<String> aliases = Lists.newArrayList("relic", "miracle");

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if(args.length == 2)
        {
            EntityPlayerMP player = getPlayer(server, sender, args[0]);

            IRelic money = (player).getCapability(RelicProvider.RELIC_CAP, null);

            money.set(parseInt(args[1]));
        }
    }

    @Override
    public String getName()
    {
        return "money";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "/money";
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
        return Arrays.asList(server.getOnlinePlayerNames());

    }
}
