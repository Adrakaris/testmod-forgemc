package com.bocbin.testmod;

import com.bocbin.testmod.blocks.*;
import com.bocbin.testmod.constructors.GloriousArmour;
import com.bocbin.testmod.items.Borscht;
import com.bocbin.testmod.items.GloriousFabric;
import com.bocbin.testmod.items.HammerSickle;
import com.bocbin.testmod.items.IngloriousFabric;
import com.bocbin.testmod.setup.ClientProxy;
import com.bocbin.testmod.setup.IProxy;
import com.bocbin.testmod.setup.ModSetup;
import com.bocbin.testmod.setup.ServerProxy;
import net.minecraft.block.Block;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;
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

    public static final String MODID = "testmod";

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
            blockRegistryEvent.getRegistry().register(new PotatoGenerator());

            LOGGER.info("Block Registering Yes");
        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
            // register a new item here
            itemRegistryEvent.getRegistry().register(new GloriousFabric());
            itemRegistryEvent.getRegistry().register(new IngloriousFabric());
            itemRegistryEvent.getRegistry().register(new Borscht());

            // register new tools and stuff
            itemRegistryEvent.getRegistry().register(new HammerSickle());
            // these are built off an armour constructor in constructors, and so are not held in ModItems
            itemRegistryEvent.getRegistry().register(new GloriousArmour(EquipmentSlotType.HEAD).setRegistryName("glorious_hat"));
            itemRegistryEvent.getRegistry().register(new GloriousArmour(EquipmentSlotType.CHEST).setRegistryName("glorious_chestplate"));
            itemRegistryEvent.getRegistry().register(new GloriousArmour(EquipmentSlotType.LEGS).setRegistryName("glorious_pants"));
            itemRegistryEvent.getRegistry().register(new GloriousArmour(EquipmentSlotType.FEET).setRegistryName("glorious_boots"));

            // register new blockitems
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.GLORIOUSFABRICBLOCK, new Item.Properties().group(setup.itemGroup)).setRegistryName("glorious_fabric_block"));
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.POTATOGENERATOR, new Item.Properties().group(setup.itemGroup)).setRegistryName("potato_generator"));

            LOGGER.info("Item Registering Yes");
        }

        @SubscribeEvent
        public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> tileRegistryEvent) {
            tileRegistryEvent.getRegistry().register(TileEntityType.Builder.create(PotatoGeneratorTile::new, ModBlocks.POTATOGENERATOR).build(null).setRegistryName("potato_generator"));

            LOGGER.info("Tile Entity Registering Yes");
        }

        @SubscribeEvent
        public static void onContainerRegistry(final RegistryEvent.Register<ContainerType<?>> containerRegistryEvent) {
            containerRegistryEvent.getRegistry().register(IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                return new PotatoGeneratorContainer(windowId, TestMod.proxy.getClientWorld(), pos, inv, TestMod.proxy.getClientPlayer());  // clientside
            }).setRegistryName("potato_generator"));
        }
    }
}
