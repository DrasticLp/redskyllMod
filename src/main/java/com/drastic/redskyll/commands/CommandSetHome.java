package com.drastic.redskyll.commands;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.drastic.redskyll.util.interfaces.IMoney;
import com.drastic.redskyll.util.provider.MoneyProvider;
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

public class CommandSetHome extends CommandBase
{

    private final List<String> aliases = Lists.newArrayList("shome", "sh");

    @Override
    public String getName()
    {
        return "sethome";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "/sethome [name]";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if(sender instanceof EntityPlayer)
        {
            // Joueur
            EntityPlayer player = (EntityPlayer)sender;

            if(args.length == 0)
            {
                player.sendMessage(new TextComponentString(TextFormatting.GOLD + "[HOMES]: " + TextFormatting.RED + "Vous devez mettre le nom de votre home"));
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
                            player.sendMessage(new TextComponentString(TextFormatting.GOLD + "[HOMES]: " + TextFormatting.RED + "Vous avez deja un home appele " + TextFormatting.AQUA + args[0].toUpperCase()));
                        }
                        else
                        {
                            if(((JSONObject)arrayOld.get(0)).size() < 3 || server.getPlayerList().getOppedPlayers().getEntry(player.getGameProfile()) != null)
                            {
                                if(((JSONObject)arrayOld.get(0)).size() == 0)
                                {
                                    JSONObject homesList = ((JSONObject)arrayOld.get(0));
                                    homesList.put(args[0], homeProperty);

                                    JSONArray arrayNew = new JSONArray();
                                    arrayNew.add(homesList);

                                    FileWriter writer = new FileWriter(file);

                                    player.sendMessage(new TextComponentString(TextFormatting.GOLD + "[HOMES]: " + TextFormatting.GREEN + "Vous venez de creer un home appele " + TextFormatting.AQUA + args[0].toUpperCase()));
                                    player.sendMessage(new TextComponentString(TextFormatting.GOLD + "[HOMES]: " + TextFormatting.RED + "Attention ! Votre premier home est gratuit mais les prochains seront payants !"));

                                    IMoney money = (player).getCapability(MoneyProvider.MONEY_CAP, null);

                                    file.delete();
                                    writer.write(arrayNew.toJSONString());
                                    writer.flush();
                                    writer.close();
                                    reader.close();

                                }
                                else
                                {
                                    JSONObject homesList = ((JSONObject)arrayOld.get(0));
                                    homesList.put(args[0], homeProperty);

                                    JSONArray arrayNew = new JSONArray();
                                    arrayNew.add(homesList);

                                    FileWriter writer = new FileWriter(file);

                                    IMoney money = (player).getCapability(MoneyProvider.MONEY_CAP, null);

                                    if(money.getMoney() >= 100000)
                                    {
                                        player.sendMessage(new TextComponentString(TextFormatting.GOLD + "[HOMES]: " + TextFormatting.GREEN + "Vous venez de creer un home appele " + TextFormatting.AQUA + args[0].toUpperCase() + TextFormatting.GREEN + "pour 100k $"));
                                        file.delete();
                                        money.shrinkMoney(100000);
                                        writer.write(arrayNew.toJSONString());
                                        writer.flush();
                                        writer.close();
                                        reader.close();
                                    }
                                    else
                                    {
                                        player.sendMessage(new TextComponentString(TextFormatting.GOLD + "[HOMES]: " + TextFormatting.RED + "Vous n'avez pas assez d'argent"));

                                    }
                                }
                            }
                            else
                            {
                                player.sendMessage(new TextComponentString(TextFormatting.GOLD + "[HOMES]: " + TextFormatting.RED + "Vous avez deja plus de " + TextFormatting.AQUA + "3" + TextFormatting.RED + " homes"));
                            }
                        }
                    }
                    else
                    {

                        JSONObject homesList = new JSONObject();
                        homesList.put(args[0] + "", homeProperty);

                        JSONArray array = new JSONArray();
                        array.add(homesList);
                        IMoney money = (player).getCapability(MoneyProvider.MONEY_CAP, null);

                        FileWriter writer = new FileWriter(file);

                        player.sendMessage(new TextComponentString(TextFormatting.GOLD + "[HOMES]: " + TextFormatting.GREEN + "Vous venez de creer un home appele " + TextFormatting.AQUA + args[0].toUpperCase()));
                        player.sendMessage(new TextComponentString(TextFormatting.GOLD + "[HOMES]: " + TextFormatting.RED + "Attention ! Votre premier home est gratuit mais les prochains seront payants !"));
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
