package com.bocbin.testmod;

import com.bocbin.testmod.lists.BlockList;
import com.bocbin.testmod.lists.ItemList;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("testmod")  // pass in modid, which in this case is "testmod"
public class TestMod {
    public static TestMod instance;
    public static final String modid = "testmod";
    // create logger to print things out in consile for debug yay
    private static final Logger logger = LogManager.getLogger(modid);

    // constructor init method
    public TestMod() {
        // declare instance of mod to be able to reference it outside this class
        instance = this;

        // Forge specific functions
        // register setup functions
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);  // listen for everything inside this setup function
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);

        // register mod
        MinecraftForge.EVENT_BUS.register(this);
    }

    // set up mod ~ preinitialise
    private void setup(final FMLCommonSetupEvent event) {
        logger.info("Setup method registered");
    }

    // clientside - models, etc that need only to render client side
    private void clientRegistries(final FMLClientSetupEvent event) {
        logger.info("clientRegistries method registered");
    }

    // registry events
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        // creating a new function to register items. Subscribe event functions run at game launch
        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            // register items - resource location gives the /give thing, i.e. testmod:glorious_fabric
            event.getRegistry().registerAll(
                    // Items
                    ItemList.glorious_fabric = new Item(new Item.Properties().group(ItemGroup.MISC).maxStackSize(64)).setRegistryName(location("glorious_fabric")),

                    // BlockItems
                    ItemList.glorious_fabric_block = new BlockItem(BlockList.glorious_fabric_block, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(BlockList.glorious_fabric_block.getRegistryName())
            );

            logger.info("Items registered");
        }

        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            event.getRegistry().registerAll(
                    BlockList.glorious_fabric_block = new Block(Block.Properties.create(Material.WOOL).hardnessAndResistance(0.8f, 0.8f).sound(SoundType.CLOTH)).setRegistryName(location("glorious_fabric_block"))
            );
        }

        private static ResourceLocation location(String name) {
            return new ResourceLocation(modid, name);
        }
    }
}
