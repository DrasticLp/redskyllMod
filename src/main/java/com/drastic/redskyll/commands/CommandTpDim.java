package com.drastic.redskyll.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.drastic.redskyll.objects.dimension.CustomTeleporter;
import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class CommandTpDim extends CommandBase
{
    @Override
    public String getName()
    {
        return "tpdim";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "/tpdim <dim>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if(sender instanceof EntityPlayer)
        {
            if(args.length == 0)
            {
                sender.sendMessage(new TextComponentString(TextFormatting.RED + this.getUsage(sender)));
            }
            else
            {
                if(args.length == 1)
                {
                    if(args[0].equalsIgnoreCase("moon"))
                    {
                        CustomTeleporter.teleportToDimension((EntityPlayer)sender, 2, ((EntityPlayer)sender).posX, ((EntityPlayer)sender).posY, ((EntityPlayer)sender).posZ);
                    }
                    else if(args[0].equalsIgnoreCase("mars"))
                    {
                        CustomTeleporter.teleportToDimension((EntityPlayer)sender, 58, ((EntityPlayer)sender).posX, ((EntityPlayer)sender).posY, ((EntityPlayer)sender).posZ);
                    }
                    else if(args[0].equalsIgnoreCase("earth"))
                    {
                        CustomTeleporter.teleportToDimension((EntityPlayer)sender, 0, ((EntityPlayer)sender).posX, ((EntityPlayer)sender).posY, ((EntityPlayer)sender).posZ);
                    }
                    else if(args[0].equalsIgnoreCase("end"))
                    {
                        CustomTeleporter.teleportToDimension((EntityPlayer)sender, 1, ((EntityPlayer)sender).posX, ((EntityPlayer)sender).posY, ((EntityPlayer)sender).posZ);
                    }
                    else if(args[0].equalsIgnoreCase("nether"))
                    {
                        CustomTeleporter.teleportToDimension((EntityPlayer)sender, -1, ((EntityPlayer)sender).posX, ((EntityPlayer)sender).posY, ((EntityPlayer)sender).posZ);
                    }
                    else
                    {
                        try
                        {
                            CustomTeleporter.teleportToDimension((EntityPlayer)sender, parseInt(args[0]), ((EntityPlayer)sender).posX, ((EntityPlayer)sender).posY, ((EntityPlayer)sender).posZ);
                        }
                        catch(NumberFormatException e)
                        {
                            sender.sendMessage(new TextComponentString(TextFormatting.RED + this.getUsage(sender)));
                        }
                    }
                }
                else
                {
                    sender.sendMessage(new TextComponentString(TextFormatting.RED + this.getUsage(sender)));
                }
            }
        }
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        return true;
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
        list.add("earth");
        list.add("end");
        list.add("mars");
        list.add("moon");
        list.add("nether");

        return list;
    }
}
