package com.drastic.redskyll.client.gui;

import java.util.List;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.util.handlers.ConfigColor;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiOverlayDebug;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiF3 extends Gui
{
    private static Minecraft mc;

    private ScaledResolution res;

    public GuiF3()
    {
        this.mc = Minecraft.getMinecraft();
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onRenderPre(RenderGameOverlayEvent.Pre event)
    {
        if(event.getType() == RenderGameOverlayEvent.ElementType.DEBUG)
        {
            event.setCanceled(true);

            res = new ScaledResolution(this.mc);

            drawEntityOnScreen(18, 134, 30, -30, 10, this.mc.player, this.mc.player);

            this.renderDebugInfo(res);

        }
    }

    public static void drawEntityOnScreen(int posX, int posY, int scale, float mouseX, float mouseY, EntityLivingBase ent, EntityPlayer player)
    {
        ent.setCustomNameTag(player.getName());
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)posX, (float)posY, 50.0F);
        GlStateManager.scale((float)(-scale), (float)scale, (float)scale);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        float f = ent.renderYawOffset;
        float f1 = ent.rotationYaw;
        float f2 = ent.rotationPitch;
        float f3 = ent.prevRotationYawHead;
        float f4 = ent.rotationYawHead;
        GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-((float)Math.atan((double)(mouseY / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        ent.renderYawOffset = (float)Math.atan((double)(mouseX / 40.0F)) * 20.0F;
        ent.rotationYaw = (float)Math.atan((double)(mouseX / 40.0F)) * 40.0F;
        ent.rotationPitch = -((float)Math.atan((double)(mouseY / 40.0F))) * 20.0F;
        ent.rotationYawHead = ent.rotationYaw;
        ent.prevRotationYawHead = ent.rotationYaw;
        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0F);
        rendermanager.setRenderShadow(false);
        rendermanager.renderEntity(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
        rendermanager.setRenderShadow(true);
        ent.renderYawOffset = f;
        ent.rotationYaw = f1;
        ent.rotationPitch = f2;
        ent.prevRotationYawHead = f3;
        ent.rotationYawHead = f4;
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }

    public void renderDebugInfo(ScaledResolution scaledResolutionIn)
    {
        this.mc.mcProfiler.startSection("debug");
        GlStateManager.pushMatrix();
        this.renderDebugInfoLeft();
        // this.renderDebugInfoRight(scaledResolutionIn);
        GlStateManager.popMatrix();

        if(this.mc.gameSettings.showLagometer)
        {
            // this.renderLagometer();
        }

        this.mc.mcProfiler.endSection();
    }

    protected void renderDebugInfoLeft()
    {
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;

        List<String> list = this.call();
        list.add("" + (int)this.mc.player.posX);
        list.add("" + (int)this.mc.player.posY);
        list.add("" + (int)this.mc.player.posZ);
        list.add(this.mc.world.getBiome(this.mc.player.getPosition()).getBiomeName());
        list.add(this.mc.player.getName());
        list.add(I18n.translateToLocal(this.mc.player.world.getBlockState(this.mc.player.rayTrace(5, this.mc.getRenderPartialTicks()).getBlockPos()).getBlock().getUnlocalizedName() + ".name"));

        String s1 = this.mc.player.world.getBlockState(this.mc.player.rayTrace(5, this.mc.getRenderPartialTicks()).getBlockPos()).getBlock().getUnlocalizedName().substring(5);
        int j1 = this.mc.player.world.getBlockState(this.mc.player.rayTrace(5, this.mc.getRenderPartialTicks()).getBlockPos()).getBlock().getRegistryName().toString().length() - 1;
        int j2 = s1.length();
        String s2 = this.mc.player.world.getBlockState(this.mc.player.rayTrace(5, this.mc.getRenderPartialTicks()).getBlockPos()).getBlock().getRegistryName().toString().substring(0, j1 - j2);
        String s3 = s2.substring(0, 1).toUpperCase() + s2.substring(1, s2.length());

        for(int i = 0; i < list.size(); ++i)
        {
            String s = list.get(i);

            if(!Strings.isNullOrEmpty(s))
            {
                int j = fontRenderer.FONT_HEIGHT;
                int k = fontRenderer.getStringWidth(s);
                int l = 2;
                int i1 = 2 + j * i;

                if(s.equalsIgnoreCase(this.mc.getDebugFPS() + ""))
                {
                    if(this.mc.getDebugFPS() < 30)
                    {
                        // drawRect(1, i1 - 1, 2 + k + 2 + fontRenderer.getStringWidth("» FPS: "), i1 + j - 1, -1873784752);
                        fontRenderer.drawString(TextFormatting.BOLD + "»" + TextFormatting.RESET + " FPS: ", 2, i1, ConfigColor.pimaryInt);
                        fontRenderer.drawString(s, 1 + fontRenderer.getStringWidth(TextFormatting.BOLD + "»" + TextFormatting.RESET + " FPS: "), i1, ConfigColor.secondaryInt);

                    }
                    else if(this.mc.getDebugFPS() >= 30 && this.mc.getDebugFPS() < 60)
                    {
                        // drawRect(1, i1 - 1, 2 + k + 2 + fontRenderer.getStringWidth("» FPS: "), i1 + j - 1, -1873784752);
                        fontRenderer.drawString(TextFormatting.BOLD + "»" + TextFormatting.RESET + " FPS: ", 2, i1, ConfigColor.pimaryInt);
                        fontRenderer.drawString(s, 1 + fontRenderer.getStringWidth(TextFormatting.BOLD + "»" + TextFormatting.RESET + " FPS: "), i1, ConfigColor.secondaryInt);
                    }
                    else if(this.mc.getDebugFPS() >= 60)
                    {
                        // drawRect(1, i1 - 1, 2 + k + 2 + fontRenderer.getStringWidth("» FPS: "), i1 + j - 1, -1873784752);
                        fontRenderer.drawString(TextFormatting.BOLD + "»" + TextFormatting.RESET + " FPS: ", 2, i1, ConfigColor.pimaryInt);
                        fontRenderer.drawString(s, 1 + fontRenderer.getStringWidth(TextFormatting.BOLD + "»" + TextFormatting.RESET + " FPS: "), i1, ConfigColor.secondaryInt);
                    }
                }
                else
                {
                    // drawRect(1, i1 - 1, 2 + k + 1, i1 + 3 + j - 1, -1873784752);
                    if(s.equalsIgnoreCase("" + (int)this.mc.player.posX))
                    {
                        fontRenderer.drawString(TextFormatting.BOLD + "X: ", 2, i1 + 3, ConfigColor.pimaryInt);
                        fontRenderer.drawString(s, 1 + fontRenderer.getStringWidth(TextFormatting.BOLD + "X: "), i1 + 3, ConfigColor.secondaryInt);
                    }
                    else if(s.equalsIgnoreCase("" + (int)this.mc.player.posY))
                    {
                        fontRenderer.drawString(TextFormatting.BOLD + "Y: ", 2, i1 + 3, ConfigColor.pimaryInt);
                        fontRenderer.drawString(s, 1 + fontRenderer.getStringWidth(TextFormatting.BOLD + "Y: "), i1 + 3, ConfigColor.secondaryInt);
                    }
                    else if(s.equalsIgnoreCase("" + (int)this.mc.player.posZ))
                    {
                        fontRenderer.drawString(TextFormatting.BOLD + "Z: ", 2, i1 + 3, ConfigColor.pimaryInt);
                        fontRenderer.drawString(s, 1 + fontRenderer.getStringWidth(TextFormatting.BOLD + "Z: "), i1 + 3, ConfigColor.secondaryInt);
                    }
                    else if(s.equalsIgnoreCase(this.mc.world.getBiome(this.mc.player.getPosition()).getBiomeName()))
                    {
                        fontRenderer.drawString(TextFormatting.BOLD + "Biome: ", 2, i1 + 6, ConfigColor.pimaryInt);
                        fontRenderer.drawString(s, fontRenderer.getStringWidth(TextFormatting.BOLD + "Biome: "), i1 + 6, ConfigColor.secondaryInt);
                    }
                    else if(s.equalsIgnoreCase(I18n.translateToLocal(this.mc.player.world.getBlockState(this.mc.player.rayTrace(5, this.mc.getRenderPartialTicks()).getBlockPos()).getBlock().getUnlocalizedName() + ".name")))
                    {
                        fontRenderer.drawString(TextFormatting.BOLD + "Block: ", 2, i1, ConfigColor.pimaryInt);
                        fontRenderer.drawString(s, fontRenderer.getStringWidth(TextFormatting.BOLD + "Block: "), i1, ConfigColor.secondaryInt);
                        fontRenderer.drawString(TextFormatting.BOLD + "Mod: ", fontRenderer.getStringWidth(TextFormatting.BOLD + "Block: " + TextFormatting.RESET + s + " "), i1, ConfigColor.pimaryInt);
                        fontRenderer.drawString(s3, fontRenderer.getStringWidth(TextFormatting.BOLD + "Block: " + TextFormatting.RESET + s + TextFormatting.BOLD + "Mod: "), i1, ConfigColor.secondaryInt);
                    }
                    else if(s.equalsIgnoreCase(this.mc.player.getName()))
                    {
                        fontRenderer.drawString(TextFormatting.BOLD + "Pseudo: ", 2, i1 + 20, ConfigColor.pimaryInt);
                        fontRenderer.drawString(s, fontRenderer.getStringWidth(TextFormatting.BOLD + "Pseudo: "), i1 + 20, ConfigColor.secondaryInt);
                    }
                }
            }
        }
    }

    protected List<String> call()
    {
        BlockPos blockpos = new BlockPos(this.mc.getRenderViewEntity().posX, this.mc.getRenderViewEntity().getEntityBoundingBox().minY, this.mc.getRenderViewEntity().posZ);

        if(this.mc.isReducedDebug())
        {
            return Lists.newArrayList(this.mc.debug);
        }
        else
        {
            return Lists.newArrayList(Minecraft.getDebugFPS() + "");
        }
    }

    private int getFrameColor(int p_181552_1_, int p_181552_2_, int p_181552_3_, int p_181552_4_)
    {
        return p_181552_1_ < p_181552_3_ ? this.blendColors(-16711936, -256, (float)p_181552_1_ / (float)p_181552_3_) : this.blendColors(-256, -65536, (float)(p_181552_1_ - p_181552_3_) / (float)(p_181552_4_ - p_181552_3_));
    }

    private int blendColors(int p_181553_1_, int p_181553_2_, float p_181553_3_)
    {
        int i = p_181553_1_ >> 24 & 255;
        int j = p_181553_1_ >> 16 & 255;
        int k = p_181553_1_ >> 8 & 255;
        int l = p_181553_1_ & 255;
        int i1 = p_181553_2_ >> 24 & 255;
        int j1 = p_181553_2_ >> 16 & 255;
        int k1 = p_181553_2_ >> 8 & 255;
        int l1 = p_181553_2_ & 255;
        int i2 = MathHelper.clamp((int)((float)i + (float)(i1 - i) * p_181553_3_), 0, 255);
        int j2 = MathHelper.clamp((int)((float)j + (float)(j1 - j) * p_181553_3_), 0, 255);
        int k2 = MathHelper.clamp((int)((float)k + (float)(k1 - k) * p_181553_3_), 0, 255);
        int l2 = MathHelper.clamp((int)((float)l + (float)(l1 - l) * p_181553_3_), 0, 255);
        return i2 << 24 | j2 << 16 | k2 << 8 | l2;
    }

    private static long bytesToMb(long bytes)
    {
        return bytes / 1024L / 1024L;
    }
}
