package com.drastic.redskyll.client.gui;

import java.awt.Color;
import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.util.Reference;
import com.drastic.redskyll.util.handlers.ConfigColor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.GuiContainerEvent.DrawForeground;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiColorSecondary extends GuiScreen
{
    public static final ResourceLocation BACK = new ResourceLocation(Reference.MODID + ":textures/gui/colorprimary.png");

    private static Minecraft mc = Minecraft.getMinecraft();

    GuiButton white = new GuiButton(0, 0, 0, 46, 20, TextFormatting.WHITE + "Blanc"); 
    GuiButton lightGray = new GuiButton(1, 0, 0, 46, 20, "Gris C.");
    GuiButton gray = new GuiButton(2, 0, 0, 46, 20, TextFormatting.GRAY + "Gris");
    GuiButton darkGray = new GuiButton(3, 0, 0, 46, 20, TextFormatting.DARK_GRAY + "Gris F.");
    GuiButton black = new GuiButton(4, 0, 0, 46, 20, TextFormatting.BLACK + "Noir");
    GuiButton red = new GuiButton(5, 0, 0, 46, 20, TextFormatting.RED + "Rouge");
    GuiButton pink = new GuiButton(6, 0, 0, 46, 20, TextFormatting.LIGHT_PURPLE + "Rose");
    GuiButton orange = new GuiButton(7, 0, 0, 46, 20, TextFormatting.GOLD + "Orange");
    GuiButton yellow = new GuiButton(8, 0, 0, 46, 20, TextFormatting.YELLOW + "Jaune");
    GuiButton green = new GuiButton(9, 0, 0, 46, 20, TextFormatting.GREEN + "Vert");
    GuiButton magenta = new GuiButton(10, 0, 0, 46, 20, TextFormatting.DARK_PURPLE + "Magenta");
    GuiButton cyan = new GuiButton(11, 0, 0, 46, 20, TextFormatting.AQUA + "Cyan");
    GuiButton blue = new GuiButton(12, 0, 0, 46, 20, TextFormatting.BLUE + "Bleu");
    GuiButton escape = new GuiButton(13, 0, 0, 46, 20, "Fermer");

    protected int sizeX = 110;
    /** The Y size of the inventory window in pixels. */
    protected int sizeY = 223;

    public GuiColorSecondary()
    {

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        Keyboard.enableRepeatEvents(true);

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(BACK);

        this.drawModalRectWithCustomSizedTexture(width / 2 - this.sizeX / 2, height / 2 - this.sizeY / 2, 0, 0, 110, 223, 110, 223);

        super.drawScreen(mouseX, mouseY, partialTicks);

    }

    @Override
    public void initGui()
    {
        buttonList.clear();

        buttonList.add(this.white);
        buttonList.add(this.lightGray);
        buttonList.add(this.gray);
        buttonList.add(this.darkGray);
        buttonList.add(this.black);
        buttonList.add(this.red);
        buttonList.add(this.pink);
        buttonList.add(this.orange);
        buttonList.add(this.yellow);
        buttonList.add(this.green);
        buttonList.add(this.magenta);
        buttonList.add(this.cyan);
        buttonList.add(this.blue);
        buttonList.add(this.escape);

        this.white.x = width / 2 - this.sizeX / 2 + 4;
        this.white.y = height / 2 - 100;

        this.lightGray.x = width / 2 - this.sizeX / 2 + 4;
        this.lightGray.y = height / 2 - 77;

        this.gray.x = width / 2 - this.sizeX / 2 + 4;
        this.gray.y = height / 2 - 54;

        this.darkGray.x = width / 2 - this.sizeX / 2 + 4;
        this.darkGray.y = height / 2 - 31;

        this.black.x = width / 2 - this.sizeX / 2 + 4;
        this.black.y = height / 2 - 8;

        this.red.x = width / 2 - this.sizeX / 2 + 4;
        this.red.y = height / 2 + 15;

        this.pink.x = width / 2 - this.sizeX / 2 + 60;
        this.pink.y = height / 2 - 100;

        this.orange.x = width / 2 - this.sizeX / 2 + 60;
        this.orange.y = height / 2 - 77;

        this.yellow.x = width / 2 - this.sizeX / 2 + 60;
        this.yellow.y = height / 2 - 54;

        this.green.x = width / 2 - this.sizeX / 2 + 60;
        this.green.y = height / 2 - 31;

        this.magenta.x = width / 2 - this.sizeX / 2 + 60;
        this.magenta.y = height / 2 - 8;

        this.cyan.x = width / 2 - this.sizeX / 2 + 60;
        this.cyan.y = height / 2 + 15;

        this.blue.x = width / 2 - this.sizeX / 2 + 32;
        this.blue.y = height / 2 + 39;
    
        this.escape.x = width / 2 - this.sizeX / 2 + 32;
        this.escape.y = height / 2 + 74;
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException
    {
        if(button.id == 0)
        {
            ConfigColor.secondary = "white";
            ConfigColor.secondaryInt = Color.white.getRGB();
            this.mc.player.sendMessage(new TextComponentString(TextFormatting.GREEN + "Vous venez de changer la couleur secondaire en : " + TextFormatting.WHITE + "BLANC"));
        }
        else if(button.id == 1)
        {
            ConfigColor.secondary = "lightGray";
            ConfigColor.secondaryInt = Color.lightGray.getRGB();
            this.mc.player.sendMessage(new TextComponentString(TextFormatting.GREEN + "Vous venez de changer la couleur secondaire en : " + TextFormatting.GRAY + "GRIS CLAIR"));
        }

        else if(button.id == 2)
        {
            ConfigColor.secondary = "gray";
            ConfigColor.secondaryInt = Color.gray.getRGB();
            this.mc.player.sendMessage(new TextComponentString(TextFormatting.GREEN + "Vous venez de changer la couleur secondaire en : " + TextFormatting.GRAY + "GRIS"));
        }
        else if(button.id == 3)
        {
            ConfigColor.secondary = "darkGray";
            ConfigColor.secondaryInt = Color.darkGray.getRGB();
            this.mc.player.sendMessage(new TextComponentString(TextFormatting.GREEN + "Vous venez de changer la couleur secondaire en : " + TextFormatting.DARK_GRAY + "GRIS FONCE"));
        }
        else if(button.id == 4)
        {
            ConfigColor.secondary = "black";
            ConfigColor.secondaryInt = Color.black.getRGB();
            this.mc.player.sendMessage(new TextComponentString(TextFormatting.GREEN + "Vous venez de changer la couleur secondaire en : " + TextFormatting.BLACK + "NOIR"));
        }
        else if(button.id == 5)
        {
            ConfigColor.secondary = "red";
            ConfigColor.secondaryInt = Color.red.getRGB();
            this.mc.player.sendMessage(new TextComponentString(TextFormatting.GREEN + "Vous venez de changer la couleur secondaire en : " + TextFormatting.RED + "ROUGE"));
        }
        else if(button.id == 6)
        {
            ConfigColor.secondary = "pink";
            ConfigColor.secondaryInt = Color.pink.getRGB();
            this.mc.player.sendMessage(new TextComponentString(TextFormatting.GREEN + "Vous venez de changer la couleur secondaire en : " + TextFormatting.LIGHT_PURPLE + "ROSE"));
        }
        else if(button.id == 7)
        {
            ConfigColor.secondary = "orange";
            ConfigColor.secondaryInt = Color.orange.getRGB();
            this.mc.player.sendMessage(new TextComponentString(TextFormatting.GREEN + "Vous venez de changer la couleur secondaire en : " + TextFormatting.GOLD + "ORANGE"));
        }
        else if(button.id == 8)
        {
            ConfigColor.secondary = "yellow";
            ConfigColor.secondaryInt = Color.yellow.getRGB();
            this.mc.player.sendMessage(new TextComponentString(TextFormatting.GREEN + "Vous venez de changer la couleur secondaire en : " + TextFormatting.YELLOW + "JAUNE"));
        }
        else if(button.id == 9)
        {
            ConfigColor.secondary = "green";
            ConfigColor.secondaryInt = Color.green.getRGB();
            this.mc.player.sendMessage(new TextComponentString(TextFormatting.GREEN + "Vous venez de changer la couleur secondaire en : " + TextFormatting.GREEN + "VERT"));
        }
        else if(button.id == 10)
        {
            ConfigColor.secondary = "magenta";
            ConfigColor.secondaryInt = Color.magenta.getRGB();
            this.mc.player.sendMessage(new TextComponentString(TextFormatting.GREEN + "Vous venez de changer la couleur secondaire en : " + TextFormatting.DARK_PURPLE + "MAGENTA"));
        }
        else if(button.id == 11)
        {
            ConfigColor.secondary = "cyan";
            ConfigColor.secondaryInt = Color.cyan.getRGB();
            this.mc.player.sendMessage(new TextComponentString(TextFormatting.GREEN + "Vous venez de changer la couleur secondaire en : " + TextFormatting.AQUA + "CYAN"));
        }
        else if(button.id == 12)
        {
            ConfigColor.secondary = "blue";
            ConfigColor.secondaryInt = Color.blue.getRGB();
            this.mc.player.sendMessage(new TextComponentString(TextFormatting.GREEN + "Vous venez de changer la couleur secondaire en : " + TextFormatting.BLUE + "BLEU"));
        }
        
        else if(button.id == 13)
        {
            Minecraft.getMinecraft().player.closeScreen();
        }
        super.actionPerformed(button);
    }
}
