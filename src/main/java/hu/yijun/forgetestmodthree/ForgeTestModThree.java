package hu.yijun.forgetestmodthree;

import com.mojang.logging.LogUtils;
import hu.yijun.forgetestmodthree.block.ModBlocks;
import hu.yijun.forgetestmodthree.item.ModCreativeTabs;
import hu.yijun.forgetestmodthree.item.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// cd kaupenjoe

@Mod(ForgeTestModThree.MOD_ID)
public class ForgeTestModThree {
    public static final String MOD_ID = "forgetestmodthree";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ForgeTestModThree(FMLJavaModLoadingContext context) {
        IEventBus eventBus = context.getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModCreativeTabs.register(eventBus);

        eventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        eventBus.addListener(this::addToCreativeModeTab);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    private void addToCreativeModeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.SAPPHIRE);
            event.accept(ModItems.RAW_SAPPHIRE);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
