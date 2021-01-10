package com.drastic.redskyll.commands;

import java.util.Arrays;
import java.util.List;

import com.drastic.redskyll.Main;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.StringUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;

public class CommandNick extends CommandBase
{
    @Override
    public String getName()
    {
        return "nick";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "/nick";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        EntityPlayerMP player = (EntityPlayerMP)sender;

        player.setCustomNameTag(args[0]);
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos)
    {
        return Arrays.asList(server.getOnlinePlayerNames());
    }
}
