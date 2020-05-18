package com.bocbin.testmod;

import com.bocbin.testmod.blocks.GloriousFabricBlock;
import com.bocbin.testmod.blocks.ModBlocks;
import com.bocbin.testmod.constructors.GloriousArmour;
import com.bocbin.testmod.items.GloriousFabric;
import com.bocbin.testmod.items.HammerSickle;
import com.bocbin.testmod.items.IngloriousFabric;
import com.bocbin.testmod.setup.ClientProxy;
import com.bocbin.testmod.setup.IProxy;
import com.bocbin.testmod.setup.ModSetup;
import com.bocbin.testmod.setup.ServerProxy;
import net.minecraft.block.Block;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("testmod")
public class TestMod {

    // get a proxy
    // weird scary lambdas that intellij complains about
    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());

    public static ModSetup setup = new ModSetup();

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public TestMod() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {  // run after everything is registered
        setup.init();
        proxy.init();
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            blockRegistryEvent.getRegistry().register(new GloriousFabricBlock());

            LOGGER.info("Block Registering Yes");
        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
            // register a new item here
            itemRegistryEvent.getRegistry().register(new GloriousFabric());
            itemRegistryEvent.getRegistry().register(new IngloriousFabric());

            // register new tools and stuff
            itemRegistryEvent.getRegistry().register(new HammerSickle());
            // these are built off an armour constructor in constructors, and so are not held in ModItems
            itemRegistryEvent.getRegistry().register(new GloriousArmour(EquipmentSlotType.HEAD).setRegistryName("glorious_hat"));
            itemRegistryEvent.getRegistry().register(new GloriousArmour(EquipmentSlotType.CHEST).setRegistryName("glorious_chestplate"));
            itemRegistryEvent.getRegistry().register(new GloriousArmour(EquipmentSlotType.LEGS).setRegistryName("glorious_pants"));
            itemRegistryEvent.getRegistry().register(new GloriousArmour(EquipmentSlotType.FEET).setRegistryName("glorious_boots"));

            // register new blockitems
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.GLORIOUSFABRICBLOCK, new Item.Properties().group(setup.itemGroup)).setRegistryName("glorious_fabric_block"));

            LOGGER.info("Item Registering Yes");
        }
    }
}
