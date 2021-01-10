package com.drastic.redskyll.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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

public class CommandDelWarp extends CommandBase
{

    private final List<String> aliases = Lists.newArrayList("dwarp", "dw");

    @Override
    public String getName()
    {
        return "delwarp";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "/dewarp [name]";
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
                            ((JSONObject)arrayOld.get(0)).remove(args[0]);

                            FileWriter writer = new FileWriter(file);

                            file.delete();
                            writer.write(arrayOld.toJSONString());
                            writer.flush();
                            writer.close();
                            reader.close();
                            player.sendMessage(new TextComponentString(TextFormatting.GOLD + "[WARPS]: " + TextFormatting.GREEN + "Vous venez de supprimer le warp " + TextFormatting.AQUA + args[0].toUpperCase()));
                        }
                        else
                        {
                            player.sendMessage(new TextComponentString(TextFormatting.GOLD + "[WARPS]: " + TextFormatting.RED + "Vous n'avez pas de warp appele " + TextFormatting.AQUA + args[0].toUpperCase()));
                        }
                    }
                    else
                    {
                        player.sendMessage(new TextComponentString(TextFormatting.GOLD + "[WARPS]: " + TextFormatting.RED + "Vous n'avez pas de warp appele " + TextFormatting.AQUA + args[0].toUpperCase()));
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
        EntityPlayer player = (EntityPlayer)sender;

        File file = new File(DimensionManager.getCurrentSaveRootDirectory() + player.getName() + "/warps.json");

        try
        {
            if(file.exists())
            {
                FileReader reader = new FileReader(file);

                JSONParser parser = new JSONParser();

                JSONArray homesList = (JSONArray)parser.parse(reader);
                JSONObject homesListObject = (JSONObject)homesList.get(0);

                // System.out.println(homesListObject.keySet());
                reader.close();
                return new ArrayList<String>(homesListObject.keySet());
            }
            else
            {
                return Collections.EMPTY_LIST;
            }
        }
        catch(ParseException e)
        {
            e.printStackTrace();

            return Collections.EMPTY_LIST;

        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();

            return Collections.EMPTY_LIST;
        }
        catch(IOException e)
        {
            e.printStackTrace();

            return Collections.EMPTY_LIST;
        }
    }
}
