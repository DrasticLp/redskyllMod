package com.drastic.redskyll.client.gui;

import java.awt.Color;
import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.client.gui.buttons.CustomButton;
import com.drastic.redskyll.init.BlockInit;
import com.drastic.redskyll.objects.items.ItemWrench;
import com.drastic.redskyll.util.Reference;
import com.drastic.redskyll.util.handlers.ConfigColor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.GuiContainerEvent.DrawForeground;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiWrench extends GuiScreen
{
    public static final ResourceLocation BACK = new ResourceLocation(Reference.MODID + ":textures/gui/wrench.png");

    private static Minecraft mc = Minecraft.getMinecraft();

    private static ItemWrench item;

    GuiButton base = new GuiButton(0, 0, 0, 46, 20, "Basic");
    GuiButton planks = new GuiButton(1, 0, 0, 46, 20, "Planks");
    GuiButton cobble = new GuiButton(2, 0, 0, 46, 20, "Cobble");
    GuiButton stone = new GuiButton(3, 0, 0, 46, 20, "Stone");
    GuiButton bricks = new GuiButton(4, 0, 0, 46, 20, "Bricks");
    GuiButton dirt = new GuiButton(5, 0, 0, 46, 20, "Dirt");
    GuiButton grass = new GuiButton(6, 0, 0, 46, 20, "Grass");

    GuiButton escape = new GuiButton(7, 0, 0, 46, 20, "Fermer");

    protected int sizeX = 110;
    /** The Y size of the inventory window in pixels. */
    protected int sizeY = 150;

    public GuiWrench()
    {
        if(this.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemWrench)
        {
            this.item = (ItemWrench)this.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem();
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        Keyboard.enableRepeatEvents(true);

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(BACK);

        this.drawModalRectWithCustomSizedTexture(width / 2 - this.sizeX / 2, height / 2 - this.sizeY / 2, 0, 0, 110, 150, 110, 150);

        super.drawScreen(mouseX, mouseY, partialTicks);

    }

    @Override
    public void initGui()
    {
        buttonList.clear();

        buttonList.add(this.base);
        buttonList.add(this.planks);
        buttonList.add(this.cobble);
        buttonList.add(this.stone);
        buttonList.add(this.bricks);
        buttonList.add(this.dirt);
        buttonList.add(this.grass);
        buttonList.add(this.escape);

        this.base.x = width / 2 - this.sizeX / 2 + 7;
        this.base.y = height / 2 - 68;

        this.planks.x = width / 2 - this.sizeX / 2 + 57;
        this.planks.y = height / 2 - 68;

        this.cobble.x = width / 2 - this.sizeX / 2 + 7;
        this.cobble.y = height / 2 - 68 + 32;

        this.stone.x = width / 2 - this.sizeX / 2 + 57;
        this.stone.y = height / 2 - 68 + 32;

        this.bricks.x = width / 2 - this.sizeX / 2 + 7;
        this.bricks.y = height / 2 - 68 + 32 + 32;

        this.dirt.x = width / 2 - this.sizeX / 2 + 57;
        this.dirt.y = height / 2 - 4;

        this.grass.x = width / 2 - this.sizeX / 2 + 33;
        this.grass.y = height / 2 + 25;

        this.escape.x = width / 2 - this.sizeX / 2 + 33;
        this.escape.y = height / 2 + 50;
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
            this.item.setType(0);
            // ConfigColor.primary = "white";
            // ConfigColor.pimaryInt = Color.white.getRGB();
            // this.mc.player.sendMessage(new TextComponentString(TextFormatting.GREEN + "Vous venez de changer la couleur primaire en : " + TextFormatting.WHITE + "BLANC"));
        }
        else if(button.id == 1)
        {
            this.item.setType(1);
            // ConfigColor.primary = "lightGray";
            // ConfigColor.pimaryInt = Color.lightGray.getRGB();
            // this.mc.player.sendMessage(new TextComponentString(TextFormatting.GREEN + "Vous venez de changer la couleur primaire en : " + TextFormatting.GRAY + "GRIS CLAIR"));
        }

        else if(button.id == 2)
        {
            this.item.setType(2);
            // ConfigColor.primary = "gray";
            // ConfigColor.pimaryInt = Color.gray.getRGB();
            // this.mc.player.sendMessage(new TextComponentString(TextFormatting.GREEN + "Vous venez de changer la couleur primaire en : " + TextFormatting.GRAY + "GRIS"));
        }
        else if(button.id == 3)
        {
            this.item.setType(3);
            // ConfigColor.primary = "darkGray";
            // ConfigColor.pimaryInt = Color.darkGray.getRGB();
            // this.mc.player.sendMessage(new TextComponentString(TextFormatting.GREEN + "Vous venez de changer la couleur primaire en : " + TextFormatting.DARK_GRAY + "GRIS FONCE"));
        }
        else if(button.id == 4)
        {
            this.item.setType(4);
            // ConfigColor.primary = "black";
            // ConfigColor.pimaryInt = Color.black.getRGB();
            // this.mc.player.sendMessage(new TextComponentString(TextFormatting.GREEN + "Vous venez de changer la couleur primaire en : " + TextFormatting.BLACK + "NOIR"));
        }
        else if(button.id == 5)
        {
            this.item.setType(5);
            // ConfigColor.primary = "red";
            // ConfigColor.pimaryInt = Color.red.getRGB();
            // his.mc.player.sendMessage(new TextComponentString(TextFormatting.GREEN + "Vous venez de changer la couleur primaire en : " + TextFormatting.RED + "ROUGE"));
        }
        else if(button.id == 6)
        {
            this.item.setType(6);
            // ConfigColor.primary = "pink";
            // ConfigColor.pimaryInt = Color.pink.getRGB();
            // this.mc.player.sendMessage(new TextComponentString(TextFormatting.GREEN + "Vous venez de changer la couleur primaire en : " + TextFormatting.LIGHT_PURPLE + "ROSE"));
        }
        else if(button.id == 7)
        {
            Minecraft.getMinecraft().player.closeScreen();
        }
        this.mc.player.sendMessage(new TextComponentString(TextFormatting.GREEN + "Vous venez de changer le block en " + TextFormatting.AQUA + button.displayString.toUpperCase()));
        System.out.println(this.item.getType());
        super.actionPerformed(button);
    }
}
