package com.drastic.redskyll.util.handlers;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.objects.items.ItemHammer;
import com.drastic.redskyll.objects.items.ItemZeusShield;
import com.drastic.redskyll.objects.items.armor.ArmourJetpack;
import com.drastic.redskyll.objects.packets.SyncKeybind;
import com.drastic.redskyll.proxy.ClientProxy;
import com.drastic.redskyll.util.helpers.PlayerHelper;
import com.drastic.redskyll.util.interfaces.IRelic;
import com.drastic.redskyll.util.provider.RelicProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class KeybindHandler
{
    KeyBinding[] keyBindings = ClientProxy.keyBindings;

    @SubscribeEvent
    public void onKeyInput(KeyInputEvent e)
    {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = Minecraft.getMinecraft().player;

        if(keyBindings[0].isPressed() && player.getHeldItem(EnumHand.MAIN_HAND).getItem() == ItemInit.THOR_HAMMER)
        {
            ItemHammer hammer = ((ItemHammer)player.getHeldItem(EnumHand.MAIN_HAND).getItem());
            if(hammer.isJumpLoaded)
            {
                IRelic relic = player.getCapability(RelicProvider.RELIC_CAP, null);

                if(relic.get() != 0)
                {
                    player.sendMessage(new TextComponentString(TextFormatting.RED + "Le dieu de ce miracle ne vous a pas choisi"));
                }
                else
                {
                    player.addVelocity(0, 5, 0);
                    hammer.isJumpLoaded = false;
                }
            }
            else
            {
                player.sendMessage(new TextComponentString(TextFormatting.RED + "Le pouvoir de cette relique se recharge..."));
                player.sendMessage(new TextComponentString(TextFormatting.RED + "Il devrait être prêt dans " + ((hammer.timer / 20) - 7) + " secondes"));
            }
        }
    }
    
    @SubscribeEvent
    public void clientTick(ClientTickEvent e)
    {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = Minecraft.getMinecraft().player;
        
        if(mc.gameSettings.keyBindJump.isKeyDown() && player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ArmourJetpack)
        {
            ItemStack jetpack = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);

            if((jetpack.getMaxDamage() - jetpack.getItemDamage()) != 0)
            {
                player.motionY = player.motionY + 0.07f;
                player.addVelocity(0, 0.1d, 0);
                mc.world.spawnParticle(EnumParticleTypes.FLAME, player.posX, player.posY, player.posZ, 0, 0, 0, 0);
                mc.world.spawnParticle(EnumParticleTypes.FLAME, player.posX, player.posY - 0.5f, player.posZ, 0, 0, 0, 0);
                Main.network.sendToServer(new SyncKeybind());
            }
        }
    }
}
