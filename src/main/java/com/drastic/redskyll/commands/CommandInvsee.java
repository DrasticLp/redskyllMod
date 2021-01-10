package com.drastic.redskyll.commands;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.drastic.redskyll.client.gui.GuiMoney;
import com.drastic.redskyll.client.gui.InvSeeInventory;
import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class CommandInvsee extends CommandBase
{
    @Override
    public String getName()
    {
        return "invsee";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "/invsee";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if(sender instanceof EntityPlayer)
        {
            EntityPlayerMP self = getCommandSenderAsPlayer(sender);
            EntityPlayerMP other = getPlayer(server, sender, args[0]);

            if(args.length == 0)
            {
                self.sendMessage(new TextComponentString(TextFormatting.RED + "Vous devez utiliser la commande comme ceci : /invsee [joueur]"));
            }
            else
            {
                if(args.length == 1)
                {
                    if(Arrays.asList(server.getOnlinePlayerNames()).contains(args[0]))
                    {
                        if(getPlayer(server, sender, args[0]) != null)
                        {
                            self.displayGUIChest(new InvSeeInventory(other.inventory, other));
                        }
                    }
                }
            }
        }
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
