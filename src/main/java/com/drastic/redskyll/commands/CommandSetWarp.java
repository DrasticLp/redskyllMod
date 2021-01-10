package com.drastic.redskyll.commands;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

import libs.org.json.simple.JSONArray;
import libs.org.json.simple.JSONObject;
import libs.org.json.simple.parser.JSONParser;
import libs.org.json.simple.parser.ParseException;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class CommandSetWarp extends CommandBase
{

    private final List<String> aliases = Lists.newArrayList("swarp", "sw");

    @Override
    public String getName()
    {
        return "setwarp";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "/setwarp [name]";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if(sender instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)sender;

            if(args.length == 0)
            {
                player.sendMessage(new TextComponentString(TextFormatting.GOLD + "[WARPS]: " + TextFormatting.RED + "Vous devez mettre le nom de votre warp"));
            }
            else
            {
                World world = server.getEntityWorld();

                JSONObject homeProperty = new JSONObject();
                homeProperty.put("x", (int)player.posX);
                homeProperty.put("y", (int)player.posY);
                homeProperty.put("z", (int)player.posZ);
                homeProperty.put("dim", (int)player.dimension);

                try
                {
                    File file = new File(DimensionManager.getCurrentSaveRootDirectory() + "/warps.json");

                    if(file.exists())
                    {
                        FileReader reader = new FileReader(file);

                        JSONParser parser = new JSONParser();

                        JSONArray arrayOld = (JSONArray)parser.parse(reader);

                        if(this.isHomeExists(args[0], ((JSONObject)arrayOld.get(0))))
                        {
                            player.sendMessage(new TextComponentString(TextFormatting.GOLD + "[WARPS]: " + TextFormatting.RED + "Il y a deja un warp appele " + TextFormatting.AQUA + args[0].toUpperCase()));
                        }
                        else
                        {
                            JSONObject homesList = ((JSONObject)arrayOld.get(0));
                            homesList.put(args[0], homeProperty);

                            JSONArray arrayNew = new JSONArray();
                            arrayNew.add(homesList);

                            FileWriter writer = new FileWriter(file);

                            player.sendMessage(new TextComponentString(TextFormatting.GOLD + "[WARPS]: " + TextFormatting.GREEN + "Vous venez de creer un warp appele " + TextFormatting.AQUA + args[0].toUpperCase()));

                            file.delete();
                            writer.write(arrayNew.toJSONString());
                            writer.flush();
                            writer.close();
                            reader.close();

                        }
                    }
                    else
                    {

                        JSONObject homesList = new JSONObject();
                        homesList.put(args[0] + "", homeProperty);

                        JSONArray array = new JSONArray();
                        array.add(homesList);

                        FileWriter writer = new FileWriter(file);

                        player.sendMessage(new TextComponentString(TextFormatting.GOLD + "[WARPS]: " + TextFormatting.GREEN + "Vous venez de creer un warp appele " + TextFormatting.AQUA + args[0].toUpperCase()));
                        writer.write(array.toJSONString());
                        writer.flush();
                        writer.close();

                    }
                }
                catch(IOException e)
                {

                }
                catch(ParseException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    private static boolean isHomeExists(String string, JSONObject obj)
    {
        return obj.containsKey(string);
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        return true;
    }

    @Override
    public List<String> getAliases()
    {
        return this.aliases;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos)
    {
        return Collections.EMPTY_LIST;
    }
}
