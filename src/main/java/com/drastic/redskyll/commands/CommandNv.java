package com.drastic.redskyll.commands;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class CommandNv extends CommandBase
{
    private final List<String> aliases = Lists.newArrayList("vision");

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {

        if(sender instanceof EntityPlayer)
        {
            EntityPlayerMP player = (EntityPlayerMP)sender;

            if(args.length == 0)
            {
                player.sendMessage(new TextComponentString(TextFormatting.RED + "Utilisez la commande comme ca : /nv <minutes>"));
            }
            else if(args.length == 1)
            {
                player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, parseInt(args[0]) * 20 * 60, 0));
            }

        }
    }

    @Override
    public String getName()
    {
        return "nv";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "/nv";
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
