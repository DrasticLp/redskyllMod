package com.drastic.redskyll.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.drastic.redskyll.objects.dimension.CustomTeleporter;
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

public class CommandHomeTeleport extends CommandBase
{

    private final List<String> aliases = Lists.newArrayList("tphome", "h");

    @Override
    public String getName()
    {
        return "home";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "/home";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if(sender instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)sender;

            if(args.length == 0)
            {
                player.sendMessage(new TextComponentString(TextFormatting.GOLD + "[HOMES]: " + TextFormatting.RED + "Vous devez mettre le nom de votre home"));
            }
            else
            {
                World world = server.getEntityWorld();

                try
                {
                    File fileDir = new File(DimensionManager.getCurrentSaveRootDirectory() + "/homes/");
                    fileDir.mkdirs();

                    File file = new File(DimensionManager.getCurrentSaveRootDirectory() + "/homes/" + player.getName() + ".json");

                    if(file.exists())
                    {
                        FileReader reader = new FileReader(file);

                        JSONParser parser = new JSONParser();

                        JSONArray arrayOld = (JSONArray)parser.parse(reader);

                        if(this.isHomeExists(args[0], ((JSONObject)arrayOld.get(0))))
                        {
                            JSONObject listOfHomes = ((JSONObject)arrayOld.get(0));
                            JSONObject home = (JSONObject)listOfHomes.get(args[0]);

                            Long longX = (Long)home.get("x");
                            Long longY = (Long)home.get("y");
                            Long longZ = (Long)home.get("z");
                            Long longDim = (Long)home.get("dim");
                            double x = longX.doubleValue();
                            double y = longY.doubleValue();
                            double z = longZ.doubleValue();
                            int dim = longDim.intValue();

                            System.out.println(x);

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
                                        player.sendMessage(new TextComponentString(TextFormatting.GREEN + "[TELEPORTATION]: " + TextFormatting.GOLD + "Vous avez ete teleporte à votre home " + TextFormatting.RED + args[0]));

                                        if(player.dimension == dim)
                                        {
                                            player.attemptTeleport(x, y, z);
                                        }
                                        else
                                        {
                                            try
                                            {
                                                CustomTeleporter.teleportToDimension(player, dim, x, y, z);
                                            }
                                            catch(Exception e)
                                            {
                                                e.printStackTrace();
                                            }
                                        }
                                        this.cancel();
                                    }
                                }
                            }, 0, 1000);

                            reader.close();
                        }
                        else
                        {
                            player.sendMessage(new TextComponentString(TextFormatting.GOLD + "[HOMES]: " + TextFormatting.RED + "Vous n'avez pas de home appele " + TextFormatting.AQUA + args[0].toUpperCase()));
                        }
                    }
                    else
                    {
                        player.sendMessage(new TextComponentString(TextFormatting.GOLD + "[HOMES]: " + TextFormatting.RED + "Vous n'avez pas de home appele " + TextFormatting.AQUA + args[0].toUpperCase()));
                    }
                }
                catch(IOException e)
                {

                }
                catch(ParseException e)
                {
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

        File fileDir = new File(DimensionManager.getCurrentSaveRootDirectory() + "/homes/");
        fileDir.mkdirs();

        File file = new File(DimensionManager.getCurrentSaveRootDirectory() + "/homes/" + player.getName() + ".json");

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
