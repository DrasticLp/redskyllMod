package com.drastic.redskyll.util.helpers;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.objects.packets.KickPlayer;
import com.drastic.redskyll.util.Reference;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModsHelper
{
    private static List<String> fileContent = new ArrayList<String>();
    private static float partialTicks;

    @SideOnly(Side.CLIENT)
    public static void checkForMods()
    {
        List<String> fileNames = new ArrayList<String>();

        File[] filesIn = new File("./mods").listFiles();

        try
        {
            for(File file : filesIn)
            {
                if(file.isFile() && file.getName().endsWith(".jar"))
                    fileNames.add(file.getName());
            }

            // System.out.println(fileNames);

            HashMap<String, Boolean> files = new HashMap<String, Boolean>();

            for(String name : fileNames)
            {
                if(fileContent.contains(name))
                {
                    files.put(name, true);
                }
                else
                {
                    files.put(name, false);
                }
            }

            if(files.containsValue(false))
            {
                int count = 0;

                for(Boolean b : files.values())
                {
                    if(!b)
                        count++;
                }

                System.out.println("You tried to connect with " + count + " unauthorized mod(s)");

                Timer t = new Timer();
                t.schedule(new TimerTask()
                {

                    @Override
                    public void run()
                    {
                        Main.network.sendToServer(new KickPlayer());
                        t.cancel();
                    }
                }, 1000, 1000);
            }
            else
            {
                System.out.println("You've been connected without any unauthorized mod(s)");
            }
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();

            Timer t = new Timer();
            t.schedule(new TimerTask()
            {

                @Override
                public void run()
                {
                    Main.network.sendToServer(new KickPlayer());
                    t.cancel();
                }
            }, 1000, 1000);
        }
    }

    private static String readAll(Reader rd) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        int cp;
        while((cp = rd.read()) != -1)
        {
            sb.append((char)cp);
        }
        return sb.toString();
    }

    public static void setMods()
    {
        fileContent.add("Redskyll-Adventure" +  "-" + Reference.VERSION + ".jar");
        /*
         * fileContent.put("BetterFps.jar", true);
         * fileContent.put("Blockcraftery-Mod.jar", true);
         * fileContent.put("Chameleon.jar", true);
         * fileContent.put("Chisel-Mod.jar", true);
         * fileContent.put("CodeChickenLib.jar", true);
         * fileContent.put("CTM-MC.jar", true);
         * fileContent.put("Custom-NPCs.jar", true);
         * fileContent.put("jei.jar", true);
         * fileContent.put("luckyblock.jar", true);
         * fileContent.put("LunatriusCore.jar", true);
         * fileContent.put("mysticallib.jar", true);
         * fileContent.put("NotEnoughItems.jar", true);
         * fileContent.put("OptiFine.jar", true);
         * fileContent.put("Schematica.jar", true);
         * fileContent.put("StorageDrawers.jar", true);
         * fileContent.put("worldedit.jar", true);
         * fileContent.put("WorldProtector.jar", true);
         */

        String content;
        try
        {
            content = readStringFromURL("http://avonia.fr/mods.txt");

            String[] args = content.split(",");

            for(String s : args)
            {
                fileContent.add(s);
            }

            System.out.println("\\u001B[36m" + fileContent);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public static String readStringFromURL(String requestURL) throws IOException
    {
        try(Scanner scanner = new Scanner(new URL(requestURL).openStream(), StandardCharsets.UTF_8.toString()))
        {
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        }
    }
}
