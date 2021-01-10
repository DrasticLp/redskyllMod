package com.drastic.redskyll.client.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiContainerEvent.DrawForeground;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiColor extends GuiScreen
{
    public static final ResourceLocation BACK = new ResourceLocation(Reference.MODID + ":textures/gui/color.png");

    private static Minecraft mc = Minecraft.getMinecraft();

    GuiButton primary = new GuiButton(0, 0, 0, 87, 20, "Primary Color");
    GuiButton secondary = new GuiButton(1, 0, 0, 87, 20, "Secondary Color");
    
    protected int sizeX = 110;
    /** The Y size of the inventory window in pixels. */
    protected int sizeY = 60;

    public GuiColor()
    {

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        Keyboard.enableRepeatEvents(true);

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(BACK);

        this.drawModalRectWithCustomSizedTexture(width / 2 - this.sizeX / 2, height / 2 - this.sizeY / 2, 0, 0, 110, 60, 110, 60);

        super.drawScreen(mouseX, mouseY, partialTicks);
        
    }

    @Override
    public void initGui()
    {
        buttonList.clear();

        buttonList.add(this.primary);
        buttonList.add(this.secondary);
        
        primary.x = width / 2 - primary.width / 2;
        secondary.x = width / 2 - secondary.width / 2;
        primary.y = height / 2 - 20;
        secondary.y = height / 2;
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
            Minecraft.getMinecraft().player.closeScreen();
            Minecraft.getMinecraft().displayGuiScreen(new GuiColorPrimary());
        }
        else if(button.id == 1)
        {
            Minecraft.getMinecraft().player.closeScreen();
            Minecraft.getMinecraft().displayGuiScreen(new GuiColorSecondary());
        }
        super.actionPerformed(button);
    }
}
