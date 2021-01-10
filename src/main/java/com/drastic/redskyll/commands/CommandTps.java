package com.drastic.redskyll.commands;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.drastic.redskyll.client.gui.GuiMoney;
import com.drastic.redskyll.client.gui.InvSeeInventory;
import com.google.common.collect.Lists;

import net.minecraft.block.BlockEnderChest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.server.command.TextComponentHelper;

public class CommandTps extends CommandBase
{
    private static final DecimalFormat TIME_FORMATTER = new DecimalFormat("########0.000");

    @Override
    public String getName()
    {
        return "tps";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "/tps";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if(sender instanceof EntityPlayer)
        {
            EntityPlayerMP self = getCommandSenderAsPlayer(sender);

            if(args.length == 0)
            {
                double worldTickTime = mean(server.worldTickTimes.get(0)) * 1.0E-6D;
                double worldTPS = Math.min(1000.0 / worldTickTime, 20);
                sender.sendMessage(new TextComponentString(TextFormatting.GOLD + "[Latence]: " + getTextFromattingLatency(worldTickTime) + TIME_FORMATTER.format(worldTickTime).substring(0, 5) + "ms,"  + TextFormatting.GOLD + " [TPS]: " + getTextFromattingTps(worldTPS) + TIME_FORMATTER.format(worldTPS).substring(0, 5)));

            }
            else
            {

                self.sendMessage(new TextComponentString(TextFormatting.RED + "Vous devez utiliser la commande comme ceci : /tps"));

            }
        }
    }

    private static TextFormatting getTextFromattingLatency(double d)
    {
        if(d <= 50)
        {
            return TextFormatting.GREEN;
        }
        else if(d > 50 && d <= 100)
        {
            return TextFormatting.RED;
        }
        else
        {
            return TextFormatting.DARK_RED;
        }
    }
    
    private static TextFormatting getTextFromattingTps(double d)
    {
        if(d >= 15)
        {
            return TextFormatting.GREEN;
        }
        else if(d < 15 && d >= 10)
        {
            return TextFormatting.RED;
        }
        else
        {
            return TextFormatting.DARK_RED;
        }
    }
    
    private static String getDimensionPrefix(int dimId)
    {
        DimensionType providerType = DimensionManager.getProviderType(dimId);
        if(providerType == null)
        {
            return String.format("Dim %2d", dimId);
        }
        else
        {
            return String.format("Dim %2d (%s)", dimId, providerType.getName());
        }
    }

    private static long mean(long[] values)
    {
        long sum = 0L;
        for(long v : values)
        {
            sum += v;
        }
        return sum / values.length;
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
        return Arrays.asList(server.getOnlinePlayerNames());
    }
}
