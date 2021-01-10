package com.drastic.redskyll.commands;

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

public class CommandSpawn extends CommandBase
{

    private final List<String> aliases = Lists.newArrayList("worldspawn", "sp");

    @Override
    public String getName()
    {
        return "spawn";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "/spawn";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if(sender instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)sender;

            if(args.length == 0)
            {

                Timer timer = new Timer();
                timer.schedule(new TimerTask()
                {
                    int howManyTimes = 5;
                    World world = server.getEntityWorld();

                    @Override
                    public void run()
                    {
                        if(howManyTimes >= 0)
                        {
                            player.sendMessage(new TextComponentString(TextFormatting.GREEN + "[TELEPORTATION]: " + TextFormatting.GOLD + "Teleportation dans " + TextFormatting.RED + this.howManyTimes + TextFormatting.GOLD + " secondes"));
                            howManyTimes--;
                        }
                        if(howManyTimes == -1)
                        {
                            player.sendMessage(new TextComponentString(TextFormatting.GREEN + "[TELEPORTATION]: " + TextFormatting.GOLD + "Vous avez ete teleporte au " + TextFormatting.RED + "SPAWN"));
                        
                            if(player.dimension == 0)
                            {
                                player.attemptTeleport(world.getSpawnPoint().getX(), world.getSpawnPoint().getY(), world.getSpawnPoint().getZ());
                            }
                            else
                            {
                                CustomTeleporter.teleportToDimension(player, 0, world.getSpawnPoint().getX(), world.getSpawnPoint().getY(), world.getSpawnPoint().getZ());
                            }
                            this.cancel();
                        }
                    }
                }, 0, 1000);

            }
            else
            {
                player.sendMessage(new TextComponentString(TextFormatting.RED + "Vous devez utiliser la commande comme ceci : /spawn"));
            }
        }
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
