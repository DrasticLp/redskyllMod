package com.drastic.redskyll.util.handlers;

import com.drastic.redskyll.Main;
import com.drastic.redskyll.init.BiomeInit;
import com.drastic.redskyll.init.BlockInit;
import com.drastic.redskyll.init.DimensionInit;
import com.drastic.redskyll.init.EnchantmentInit;
import com.drastic.redskyll.init.EntityInit;
import com.drastic.redskyll.init.FluidInit;
import com.drastic.redskyll.init.ItemInit;
import com.drastic.redskyll.util.interfaces.IHasModel;
import com.drastic.redskyll.world.generation.WorldGenCustomOres;
import com.drastic.redskyll.world.generation.WorldGenCustomStructures;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber
public class RegistryHandler
{

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
        TileEntityHandler.registerTileEntities();
    }
    
    @SubscribeEvent
    public static void onEnchantmentRegister(RegistryEvent.Register<Enchantment> e)
    {
        e.getRegistry().registerAll(EnchantmentInit.ENCHANTEMENTS.toArray(new Enchantment[0]));
    }
    
    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event)
    {
        for(Item item : ItemInit.ITEMS)
        {
            if(item instanceof IHasModel)
            {
                ((IHasModel)item).registerModels();
            }
        }

        for(Block block : BlockInit.BLOCKS)
        {
            if(block instanceof IHasModel)
            {
                ((IHasModel)block).registerModels();
            }
        }

    }

    public static void preInitRegistries(FMLPreInitializationEvent e)
    {
        FluidInit.registerFluids();

        GameRegistry.registerWorldGenerator(new WorldGenCustomOres(), 0);
        GameRegistry.registerWorldGenerator(new WorldGenCustomStructures(), 0);

        DimensionInit.registerDimensions();
        EntityInit.regiterEntities();
        if(e.getSide().isClient())
        {
            RenderHandler.registerEntityRenders();
            RenderHandler.registerCustomMeshesAndStates();
        }
    }

    public static void initRegistries(FMLInitializationEvent e)
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());

        RecipesHandler.init();
        SoundsHandler.registerSounds();
    }
}
