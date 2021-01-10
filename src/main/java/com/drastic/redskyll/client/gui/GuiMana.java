package com.drastic.redskyll.client.gui;

import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.Display;

import com.drastic.redskyll.util.Reference;
import com.drastic.redskyll.util.interfaces.IHasMinion;
import com.drastic.redskyll.util.interfaces.IMana;
import com.drastic.redskyll.util.provider.ManaProvider;
import com.drastic.redskyll.util.provider.MinionProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiMana extends Gui
{
    private static Minecraft mc;

    FontRenderer fontRender;
    private ScaledResolution res = null;

    final ResourceLocation mana0 = new ResourceLocation(Reference.MODID + ":textures/gui/mana0.png");
    final ResourceLocation mana1 = new ResourceLocation(Reference.MODID + ":textures/gui/mana1.png");
    final ResourceLocation mana2 = new ResourceLocation(Reference.MODID + ":textures/gui/mana2.png");
    final ResourceLocation mana3 = new ResourceLocation(Reference.MODID + ":textures/gui/mana3.png");
    final ResourceLocation mana4 = new ResourceLocation(Reference.MODID + ":textures/gui/mana4.png");
    final ResourceLocation mana5 = new ResourceLocation(Reference.MODID + ":textures/gui/mana5.png");
    final ResourceLocation mana6 = new ResourceLocation(Reference.MODID + ":textures/gui/mana6.png");
    final ResourceLocation mana7 = new ResourceLocation(Reference.MODID + ":textures/gui/mana7.png");
    final ResourceLocation mana8 = new ResourceLocation(Reference.MODID + ":textures/gui/mana8.png");
    final ResourceLocation mana9 = new ResourceLocation(Reference.MODID + ":textures/gui/mana9.png");
    final ResourceLocation mana10 = new ResourceLocation(Reference.MODID + ":textures/gui/mana10.png");
    final ResourceLocation mana11 = new ResourceLocation(Reference.MODID + ":textures/gui/mana11.png");
    final ResourceLocation mana12 = new ResourceLocation(Reference.MODID + ":textures/gui/mana12.png");
    final ResourceLocation mana13 = new ResourceLocation(Reference.MODID + ":textures/gui/mana13.png");
    final ResourceLocation mana14 = new ResourceLocation(Reference.MODID + ":textures/gui/mana14.png");
    final ResourceLocation mana15 = new ResourceLocation(Reference.MODID + ":textures/gui/mana15.png");
    final ResourceLocation mana16 = new ResourceLocation(Reference.MODID + ":textures/gui/mana16.png");
    final ResourceLocation mana17 = new ResourceLocation(Reference.MODID + ":textures/gui/mana17.png");
    final ResourceLocation mana18 = new ResourceLocation(Reference.MODID + ":textures/gui/mana18.png");
    final ResourceLocation mana19 = new ResourceLocation(Reference.MODID + ":textures/gui/mana19.png");
    final ResourceLocation mana20 = new ResourceLocation(Reference.MODID + ":textures/gui/mana20.png");

    public GuiMana()
    {
        this.mc = Minecraft.getMinecraft();
        this.fontRender = this.mc.fontRenderer;
    }
    
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onRenderPre(RenderGameOverlayEvent.Pre event)
    {
        if(event.getType() == RenderGameOverlayEvent.ElementType.HELMET && !this.mc.player.isCreative())
        {
            res = new ScaledResolution(this.mc);

            int width = res.getScaledWidth();
            int height = res.getScaledHeight();

            int left = width / 2 + 91;
            int top = height - 39;

            IMana mana = this.mc.player.getCapability(ManaProvider.MANA_CAP, null);

            int x = left + 1;
            int y = top;

            if(mana.getMana() == 20)
            {
                mc.getTextureManager().bindTexture(this.mana20);

                drawModalRectWithCustomSizedTexture(x - 82, y - 10, 0, 0, 81, 9, 81, 9);
            }
            else if(mana.getMana() == 19)
            {
                mc.getTextureManager().bindTexture(this.mana19);

                drawModalRectWithCustomSizedTexture(x - 82, y - 10, 0, 0, 81, 9, 81, 9);
            }
            else if(mana.getMana() == 18)
            {
                mc.getTextureManager().bindTexture(this.mana18);

                drawModalRectWithCustomSizedTexture(x - 82, y - 10, 0, 0, 81, 9, 81, 9);
            }
            else if(mana.getMana() == 17)
            {
                mc.getTextureManager().bindTexture(this.mana17);

                drawModalRectWithCustomSizedTexture(x - 82, y - 10, 0, 0, 81, 9, 81, 9);
            }
            else if(mana.getMana() == 16)
            {
                mc.getTextureManager().bindTexture(this.mana16);

                drawModalRectWithCustomSizedTexture(x - 82, y - 10, 0, 0, 81, 9, 81, 9);
            }
            else if(mana.getMana() == 15)
            {
                mc.getTextureManager().bindTexture(this.mana15);

                drawModalRectWithCustomSizedTexture(x - 82, y - 10, 0, 0, 81, 9, 81, 9);
            }
            else if(mana.getMana() == 14)
            {
                mc.getTextureManager().bindTexture(this.mana14);

                drawModalRectWithCustomSizedTexture(x - 82, y - 10, 0, 0, 81, 9, 81, 9);
            }
            else if(mana.getMana() == 13)
            {
                mc.getTextureManager().bindTexture(this.mana13);

                drawModalRectWithCustomSizedTexture(x - 82, y - 10, 0, 0, 81, 9, 81, 9);
            }
            else if(mana.getMana() == 12)
            {
                mc.getTextureManager().bindTexture(this.mana12);

                drawModalRectWithCustomSizedTexture(x - 82, y - 10, 0, 0, 81, 9, 81, 9);
            }
            else if(mana.getMana() == 11)
            {
                mc.getTextureManager().bindTexture(this.mana11);

                drawModalRectWithCustomSizedTexture(x - 82, y - 10, 0, 0, 81, 9, 81, 9);
            }
            else if(mana.getMana() == 10)
            {
                mc.getTextureManager().bindTexture(this.mana10);

                drawModalRectWithCustomSizedTexture(x - 82, y - 10, 0, 0, 81, 9, 81, 9);
            }
            else if(mana.getMana() == 9)
            {
                mc.getTextureManager().bindTexture(this.mana9);

                drawModalRectWithCustomSizedTexture(x - 82, y - 10, 0, 0, 81, 9, 81, 9);
            }
            else if(mana.getMana() == 8)
            {
                mc.getTextureManager().bindTexture(this.mana8);

                drawModalRectWithCustomSizedTexture(x - 82, y - 10, 0, 0, 81, 9, 81, 9);
            }
            else if(mana.getMana() == 7)
            {
                mc.getTextureManager().bindTexture(this.mana7);

                drawModalRectWithCustomSizedTexture(x - 82, y - 10, 0, 0, 81, 9, 81, 9);
            }
            else if(mana.getMana() == 6)
            {
                mc.getTextureManager().bindTexture(this.mana6);

                drawModalRectWithCustomSizedTexture(x - 82, y - 10, 0, 0, 81, 9, 81, 9);
            }
            else if(mana.getMana() == 5)
            {
                mc.getTextureManager().bindTexture(this.mana5);

                drawModalRectWithCustomSizedTexture(x - 82, y - 10, 0, 0, 81, 9, 81, 9);
            }
            else if(mana.getMana() == 4)
            {
                mc.getTextureManager().bindTexture(this.mana4);

                drawModalRectWithCustomSizedTexture(x - 82, y - 10, 0, 0, 81, 9, 81, 9);
            }
            else if(mana.getMana() == 3)
            {
                mc.getTextureManager().bindTexture(this.mana3);

                drawModalRectWithCustomSizedTexture(x - 82, y - 10, 0, 0, 81, 9, 81, 9);
            }
            else if(mana.getMana() == 2)
            {
                mc.getTextureManager().bindTexture(this.mana2);

                drawModalRectWithCustomSizedTexture(x - 82, y - 10, 0, 0, 81, 9, 81, 9);
            }
            else if(mana.getMana() == 1)
            {
                mc.getTextureManager().bindTexture(this.mana1);

                drawModalRectWithCustomSizedTexture(x - 82, y - 10, 0, 0, 81, 9, 81, 9);
            }
            else if(mana.getMana() == 0)
            {
                mc.getTextureManager().bindTexture(this.mana0);

                drawModalRectWithCustomSizedTexture(x - 82, y - 10, 0, 0, 81, 9, 81, 9);
            }

        }
    }
}
