package com.drastic.redskyll.util.handlers;

import java.awt.Color;
import java.util.Random;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.blockhit.common.helper.ItemBlockingHelper;
import com.drastic.redskyll.client.gui.GuiEscape;
import com.drastic.redskyll.client.gui.GuiMoney;
import com.drastic.redskyll.client.renderer.entity.RenderCustomPlayer;
import com.drastic.redskyll.init.BlockInit;
import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.objects.dimension.CustomTeleporter;
import com.drastic.redskyll.objects.entity.EntityFuseT1;
import com.drastic.redskyll.objects.entity.EntityFuseT2;
import com.drastic.redskyll.objects.entity.EntityRedskyll;
import com.drastic.redskyll.objects.entity.base.EntityFuseBase;
import com.drastic.redskyll.objects.items.armor.ArmourMod;
import com.drastic.redskyll.objects.packets.SyncBoss;
import com.drastic.redskyll.objects.packets.SyncMana;
import com.drastic.redskyll.objects.packets.SyncMinion;
import com.drastic.redskyll.objects.packets.SyncMoney;
import com.drastic.redskyll.objects.packets.SyncRelic;
import com.drastic.redskyll.util.Reference;
import com.drastic.redskyll.util.helpers.ModsHelper;
import com.drastic.redskyll.util.helpers.PlayerHelper;
import com.drastic.redskyll.util.interfaces.IHasMinion;
import com.drastic.redskyll.util.interfaces.IMana;
import com.drastic.redskyll.util.interfaces.IMoney;
import com.drastic.redskyll.util.interfaces.IRelic;
import com.drastic.redskyll.util.provider.BossProvider;
import com.drastic.redskyll.util.provider.ManaProvider;
import com.drastic.redskyll.util.provider.MinionProvider;
import com.drastic.redskyll.util.provider.MoneyProvider;
import com.drastic.redskyll.util.provider.RelicProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentKeybind;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class EventHandler
{
    @SubscribeEvent
    public static void entityFalling(LivingFallEvent e)
    {

        if(e.getEntityLiving() instanceof EntityPlayer)
        {
            IMana mana = ((EntityPlayer)e.getEntityLiving()).getCapability(ManaProvider.MANA_CAP, null);

            if(e.getEntityLiving().dimension == 2 || e.getEntityLiving().dimension == 58 || mana.getMana() != 0 && ArmourMod.isFullSet((EntityPlayer)e.getEntityLiving()) || ((EntityPlayer)e.getEntityLiving()).getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == ItemInit.KRAMAZIA_BOOTS || e.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == ItemInit.PARACHUTE || e.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == ItemInit.JETPACK)
            {
                e.setDamageMultiplier(0);
            }
        }

    }

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent evt)
    {

        if(evt.getModID().equals(Reference.MODID))
        {

            ConfigManager.sync(Reference.MODID, Config.Type.INSTANCE);
            ItemBlockingHelper.sync();
        }

    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void preRenderPlayer(RenderPlayerEvent.Pre event)
    {
        RenderCustomPlayer playerRenderer;

        if(event.getRenderer() instanceof RenderPlayer && event.getEntity() instanceof EntityPlayer && event.getEntityPlayer() != null)
        {
            if(event.getEntityPlayer().getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == ItemInit.ANT_HELMET)
            {
                event.setCanceled(true);

                playerRenderer = new RenderCustomPlayer(event.getRenderer().getRenderManager());
                playerRenderer.doRender((AbstractClientPlayer)event.getEntityPlayer(), event.getX(), event.getY(), event.getZ(), 0, event.getPartialRenderTick());

            }
        }
    }
    
    @SubscribeEvent
    public static void livingDeathEvent(LivingDeathEvent e)
    {
        if(e.getEntityLiving() instanceof EntityRedskyll)
        {
            if(e.getSource().getTrueSource() instanceof EntityPlayer)
            {
                EntityPlayer source = (EntityPlayer)e.getSource().getTrueSource();

                IRelic relic = source.getCapability(RelicProvider.RELIC_CAP, null);

                if(relic.get() == 155)
                {
                    Random rand = new Random();
                    
                    int i = rand.nextInt(2);
                    if(i == 0)
                    {
                        source.addItemStackToInventory(new ItemStack(ItemInit.THOR_HAMMER));
                        source.sendMessage(new TextComponentTranslation("text.kill.redskyll"));
                        source.sendMessage(new TextComponentTranslation("text.thor"));
                    }
                    else if(i == 1)
                    {
                        source.addItemStackToInventory(new ItemStack(ItemInit.ZEUS_SHIELD));
                        source.sendMessage(new TextComponentTranslation("text.kill.redskyll"));
                        source.sendMessage(new TextComponentTranslation("text.zeus"));
                    }
                    else if(i == 2)
                    {
                        source.addItemStackToInventory(new ItemStack(ItemInit.ATHENA_SPEAR));
                        source.sendMessage(new TextComponentTranslation("text.kill.redskyll"));
                        source.sendMessage(new TextComponentTranslation("text.athena"));
                    }
                    relic.set(i);
                }

            }
        }
    }

    @SubscribeEvent
    public static void playerTickEvent(PlayerTickEvent e)
    {
        if(e.player.dimension == 2 || e.player.dimension == 58)
        {
            if(!e.player.isCreative())
            {
                e.player.addVelocity(0, 0.02754375, 0);

            }

            if(e.player.getPosition().getY() >= 299)
            {

                if(!e.player.world.isRemote && e.player.getRidingEntity() instanceof EntityFuseT1)
                {
                    e.player.dismountRidingEntity();
                    e.player.sendMessage(new TextComponentString("Copyright: DrasticLp :D"));
                    e.player.inventory.addItemStackToInventory(new ItemStack(BlockInit.BLOCK_FUSE, 1));

                    int x = e.player.getBedLocation().getX();
                    int y = e.player.getBedLocation().getY();
                    int z = e.player.getBedLocation().getZ();
                   CustomTeleporter.teleportToDimension(e.player, 0, x, y, z);
                }
                else if(!e.player.world.isRemote && e.player.getRidingEntity() instanceof EntityFuseT2)
                {
                    e.player.dismountRidingEntity();
                    e.player.sendMessage(new TextComponentString("Copyright: DrasticLp :D"));
                    e.player.inventory.addItemStackToInventory(new ItemStack(BlockInit.BLOCK_FUSE_T2, 1));

                    int x = e.player.getBedLocation().getX();
                    int y = e.player.getBedLocation().getY();
                    int z = e.player.getBedLocation().getZ();
                    CustomTeleporter.teleportToDimension(e.player, 0, x, y, z);
                }
            }
        }
        if(e.player.dimension == 0)
        {
            if(e.player.getPosition().getY() >= 299)
            {
                if(!e.player.world.isRemote && e.player.getRidingEntity() instanceof EntityFuseT1)
                {
                    e.player.dismountRidingEntity();
                    e.player.sendMessage(new TextComponentString("Copyright: DrasticLp :D"));
                    e.player.inventory.addItemStackToInventory(new ItemStack(BlockInit.BLOCK_FUSE, 1));

                    CustomTeleporter.teleportToDimension(e.player, Reference.MOON, 0, 150, 0);
                }
                else if(!e.player.world.isRemote && e.player.getRidingEntity() instanceof EntityFuseT2)
                {
                    e.player.dismountRidingEntity();
                    e.player.sendMessage(new TextComponentString("Copyright: DrasticLp :D"));
                    e.player.inventory.addItemStackToInventory(new ItemStack(BlockInit.BLOCK_FUSE_T2, 1));

                    CustomTeleporter.teleportToDimension(e.player, Reference.MARS, e.player.posX, 150, e.player.posZ);
                }

            }
        }
        if(e.player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == ItemInit.PARACHUTE)
        {
            e.player.addVelocity(0, 0.02754375, 0);
        }

        if(e.player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == ItemInit.ANT_HELMET)
        {
            PlayerHelper.setPlayerSize(e.player, 0.52F, 0.8F, 0.65f);
        }
        else
        {
            PlayerHelper.resetPlayerSize(e.player);
        }

        EntityPlayer player = e.player;

        replaceInt();

        if(!player.world.isRemote)
        {
            Main.network.sendTo(new SyncMoney(player.getCapability(MoneyProvider.MONEY_CAP, null)), (EntityPlayerMP)player);
            Main.network.sendTo(new SyncMana(player.getCapability(ManaProvider.MANA_CAP, null)), (EntityPlayerMP)player);
            Main.network.sendTo(new SyncBoss(player.getCapability(BossProvider.BOSS_CAP, null)), (EntityPlayerMP)player);
            Main.network.sendTo(new SyncMinion(player.getCapability(MinionProvider.MINION_CAP, null)), (EntityPlayerMP)player);
            Main.network.sendTo(new SyncRelic(player.getCapability(RelicProvider.RELIC_CAP, null)), (EntityPlayerMP)player);

            if(ArmourMod.isFullSet(player))
            {
                IMana mana = player.getCapability(ManaProvider.MANA_CAP, null);
                // System.out.println("je suis un elfe libre");
                if((player.ticksExisted % 100 * 40) == 198 * 10 && !player.onGround && !player.isCreative() && !player.isSpectator() && mana.getMana() > 0)
                {
                    // System.out.println("Spoiler : dobby est mort");
                    mana.shrinkMana(1);
                }
            }
        }
    }

    @SubscribeEvent
    public static void livingUpdate(LivingUpdateEvent e)
    {
        if(e.getEntityLiving().getPosition().getY() >= 298D)
        {
            // System.out.println("Pos");
            if(!e.getEntityLiving().world.isRemote)
            {
                // System.out.println("world");
                if(e.getEntityLiving() instanceof EntityFuseBase)
                {
                    // System.out.println("fuse");
                    e.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.INSTANT_DAMAGE, 20, 20));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone e)
    {
        EntityPlayer player = e.getEntityPlayer();

        IMoney money = player.getCapability(MoneyProvider.MONEY_CAP, null);
        IMoney oldMoney = e.getOriginal().getCapability(MoneyProvider.MONEY_CAP, null);

        money.setMoney(oldMoney.getMoney());

        IMana mana = player.getCapability(ManaProvider.MANA_CAP, null);
        IMana oldMana = e.getOriginal().getCapability(ManaProvider.MANA_CAP, null);

        mana.setMana(oldMana.getMana());
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent e)
    {
        EntityPlayer player = e.getPlayer();

        IHasMinion hasMinion = player.getCapability(MinionProvider.MINION_CAP, null);

        if(e.getState().getBlock().equals(Blocks.DIAMOND_ORE) && hasMinion.get() == 1)
        {
            if(!e.getWorld().isRemote)
                ;
            {
                e.setCanceled(false);
                e.getWorld().setBlockToAir(e.getPos());
                e.getWorld().spawnEntity(new EntityItem(e.getWorld(), e.getPos().getX(), e.getPos().getY(), e.getPos().getZ(), new ItemStack(Items.DIAMOND, 3)));
            }
        }
        else if(e.getState().getBlock().equals(Blocks.GOLD_ORE) && hasMinion.get() == 2)
        {
            if(!e.getWorld().isRemote)
                ;
            {
                e.setCanceled(false);
                e.getWorld().setBlockToAir(e.getPos());
                e.getWorld().spawnEntity(new EntityItem(e.getWorld(), e.getPos().getX(), e.getPos().getY(), e.getPos().getZ(), new ItemStack(Items.GOLD_INGOT, 3)));
            }
        }
        else if(e.getState().getBlock().equals(Blocks.IRON_ORE) && hasMinion.get() == 3)
        {
            if(!e.getWorld().isRemote)
                ;
            {
                e.setCanceled(false);
                e.getWorld().setBlockToAir(e.getPos());
                e.getWorld().spawnEntity(new EntityItem(e.getWorld(), e.getPos().getX(), e.getPos().getY(), e.getPos().getZ(), new ItemStack(Items.IRON_INGOT, 3)));
            }
        }
        /*
         * else if(e.getState().equals(Blocks.LOG.getStateFromMeta(3)))
         * {
         * Random random = new Random();
         * if(random.nextInt(16) == 7 && !e.getWorld().isRemote)
         * {
         * e.getWorld().spawnEntity(new EntityItem(e.getWorld(), e.getPos().getX(), e.getPos().getY(), e.getPos().getZ(), new ItemStack(ItemInit.KIWI)));
         * }
         * }
         */
    }

    public static void replaceInt()
    {
        if(ConfigColor.primary.equalsIgnoreCase("white"))
        {
            ConfigColor.pimaryInt = Color.white.getRGB();
        }
        if(ConfigColor.primary.equalsIgnoreCase("lightGray"))
        {
            ConfigColor.pimaryInt = Color.lightGray.getRGB();
        }
        if(ConfigColor.primary.equalsIgnoreCase("gray"))
        {
            ConfigColor.pimaryInt = Color.gray.getRGB();
        }
        if(ConfigColor.primary.equalsIgnoreCase("darkGray"))
        {
            ConfigColor.pimaryInt = Color.darkGray.getRGB();
        }
        if(ConfigColor.primary.equalsIgnoreCase("black"))
        {
            ConfigColor.pimaryInt = Color.black.getRGB();
        }
        if(ConfigColor.primary.equalsIgnoreCase("red"))
        {
            ConfigColor.pimaryInt = Color.red.getRGB();
        }
        if(ConfigColor.primary.equalsIgnoreCase("pink"))
        {
            ConfigColor.pimaryInt = Color.pink.getRGB();
        }
        if(ConfigColor.primary.equalsIgnoreCase("orange"))
        {
            ConfigColor.pimaryInt = Color.orange.getRGB();
        }
        if(ConfigColor.primary.equalsIgnoreCase("yellow"))
        {
            ConfigColor.pimaryInt = Color.yellow.getRGB();
        }
        if(ConfigColor.primary.equalsIgnoreCase("green"))
        {
            ConfigColor.pimaryInt = Color.green.getRGB();
        }
        if(ConfigColor.primary.equalsIgnoreCase("magenta"))
        {
            ConfigColor.pimaryInt = Color.magenta.getRGB();
        }
        if(ConfigColor.primary.equalsIgnoreCase("cyan"))
        {
            ConfigColor.pimaryInt = Color.cyan.getRGB();
        }
        if(ConfigColor.primary.equalsIgnoreCase("blue"))
        {
            ConfigColor.pimaryInt = Color.blue.getRGB();
        }

        if(ConfigColor.secondary.equalsIgnoreCase("white"))
        {
            ConfigColor.secondaryInt = Color.white.getRGB();
        }
        if(ConfigColor.secondary.equalsIgnoreCase("lightGray"))
        {
            ConfigColor.secondaryInt = Color.lightGray.getRGB();
        }
        if(ConfigColor.secondary.equalsIgnoreCase("gray"))
        {
            ConfigColor.secondaryInt = Color.gray.getRGB();
        }
        if(ConfigColor.secondary.equalsIgnoreCase("darkGray"))
        {
            ConfigColor.secondaryInt = Color.darkGray.getRGB();
        }
        if(ConfigColor.secondary.equalsIgnoreCase("black"))
        {
            ConfigColor.secondaryInt = Color.black.getRGB();
        }
        if(ConfigColor.secondary.equalsIgnoreCase("red"))
        {
            ConfigColor.secondaryInt = Color.red.getRGB();
        }
        if(ConfigColor.secondary.equalsIgnoreCase("pink"))
        {
            ConfigColor.secondaryInt = Color.pink.getRGB();
        }
        if(ConfigColor.secondary.equalsIgnoreCase("orange"))
        {
            ConfigColor.secondaryInt = Color.orange.getRGB();
        }
        if(ConfigColor.secondary.equalsIgnoreCase("yellow"))
        {
            ConfigColor.secondaryInt = Color.yellow.getRGB();
        }
        if(ConfigColor.secondary.equalsIgnoreCase("green"))
        {
            ConfigColor.secondaryInt = Color.green.getRGB();
        }
        if(ConfigColor.secondary.equalsIgnoreCase("magenta"))
        {
            ConfigColor.secondaryInt = Color.magenta.getRGB();
        }
        if(ConfigColor.secondary.equalsIgnoreCase("cyan"))
        {
            ConfigColor.secondaryInt = Color.cyan.getRGB();
        }
        if(ConfigColor.secondary.equalsIgnoreCase("blue"))
        {
            ConfigColor.secondaryInt = Color.blue.getRGB();
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void changeGui(GuiOpenEvent e)
    {
        if(e.getGui() instanceof GuiInventory)
        {
            e.setGui(new GuiMoney(Minecraft.getMinecraft().player));
        }
        if(e.getGui() instanceof GuiIngameMenu)
        {
            e.setGui(new GuiEscape());
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onConnect(ClientConnectedToServerEvent e)
    {
        if(!e.isLocal())
            ModsHelper.checkForMods();
    }
}
