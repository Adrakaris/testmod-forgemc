package com.bocbin.testmod;

import net.minecraftforge.common.MinecraftForge;
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
}
