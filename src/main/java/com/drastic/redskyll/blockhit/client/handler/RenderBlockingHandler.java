package com.drastic.redskyll.blockhit.client.handler;

import com.drastic.redskyll.blockhit.common.helper.ItemBlockingHelper;
import com.drastic.redskyll.blockhit.util.ReflectionHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.Timer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBlockingHandler
{

    private final Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onRenderLiving(final RenderLivingEvent.Pre<AbstractClientPlayer> evt)
    {

        if(evt.getEntity() instanceof AbstractClientPlayer)
        {

            AbstractClientPlayer player = (AbstractClientPlayer)evt.getEntity();
            if(player.isHandActive() && ItemBlockingHelper.getCanStackBlock(player.getActiveItemStack()))
            {

                ModelPlayer model = (ModelPlayer)evt.getRenderer().getMainModel();
                boolean left1 = player.getActiveHand() == EnumHand.OFF_HAND && player.getPrimaryHand() == EnumHandSide.RIGHT;
                boolean left2 = player.getActiveHand() == EnumHand.MAIN_HAND && player.getPrimaryHand() == EnumHandSide.LEFT;
                if(left1 || left2)
                {

                    if(model.leftArmPose == ModelBiped.ArmPose.ITEM)
                    {
                        model.leftArmPose = ModelBiped.ArmPose.BLOCK;
                    }
                }
                else
                {

                    if(model.rightArmPose == ModelBiped.ArmPose.ITEM)
                    {
                        model.rightArmPose = ModelBiped.ArmPose.BLOCK;
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onRenderHand(final RenderSpecificHandEvent evt)
    {

        EntityPlayerSP player = this.mc.player;
        if(player != null && player.isHandActive() && player.getActiveHand() == evt.getHand())
        {

            ItemStack stack = evt.getItemStack();
            if(ItemBlockingHelper.getCanStackBlock(stack))
            {

                GlStateManager.pushMatrix();
                boolean rightHanded = (evt.getHand() == EnumHand.MAIN_HAND ? player.getPrimaryHand() : player.getPrimaryHand().opposite()) == EnumHandSide.RIGHT;
                this.transformSideFirstPerson(rightHanded ? 1.0F : -1.0F, evt.getEquipProgress());
                this.mc.getItemRenderer().renderItemSide(player, stack, rightHanded ? ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND : ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND, !rightHanded);
                GlStateManager.popMatrix();
                evt.setCanceled(true);
            }
        }
    }

    private void transformSideFirstPerson(float side, float equippedProg)
    {
        AbstractClientPlayer abstractClientPlayer = mc.getMinecraft().player;

        float swingProgress = abstractClientPlayer.getSwingProgress(Minecraft.getMinecraft().getRenderPartialTicks());

        GlStateManager.translate(0.40F, -0.56F, -0.71999997F);
        GlStateManager.translate(0.0F, equippedProg * -0.6F, 0.0F);
        GlStateManager.rotate(99.0F, -200.0F, 200F, 90.0F);
        float f = MathHelper.sin(swingProgress * swingProgress * (float)Math.PI);
        float f1 = MathHelper.sin(MathHelper.sqrt(swingProgress) * (float)Math.PI);
        GlStateManager.rotate(f * -20.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(f1 * -20.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(f1 * -80.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.scale(1.1F, 1.1F, 1.1F);
    }

}
