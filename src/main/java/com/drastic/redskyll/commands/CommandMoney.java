package com.drastic.redskyll.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.drastic.redskyll.util.handlers.ConfigMod;
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

public class CommandMoney extends CommandBase
{
    private final List<String> aliases = Lists.newArrayList("money", "purse", "eco", "bal", "balance", "balanciaga");

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {

        if(sender instanceof EntityPlayer)
        {
            EntityPlayerMP player = (EntityPlayerMP)sender;

            IMoney money = (player).getCapability(MoneyProvider.MONEY_CAP, null);

            if(args.length == 0)
            {
                player.sendMessage(new TextComponentString(TextFormatting.GREEN + "Vous avez : " + money.getMoneyString() + " (" + TextFormatting.GOLD + money.getMoney() + TextFormatting.GREEN + ")"));
            }

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
        return Collections.EMPTY_LIST;

    }
}
