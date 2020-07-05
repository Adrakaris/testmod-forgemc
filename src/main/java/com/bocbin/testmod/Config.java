package com.bocbin.testmod;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import java.nio.file.Path;

@Mod.EventBusSubscriber
public class Config {

    public static final String CATEGORY_GENERAL = "general";
    public static final String CATEGORY_POWER = "power";
    public static final String SUBCATEGORY_POTATO = "potato_generator";

    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();  // common between client and server
    private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();  // clientside only

    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;

    // for value to configure:
    public static ForgeConfigSpec.IntValue POTATO_GENERATOR_CAPACITY;
    public static ForgeConfigSpec.IntValue POTGEN_GENERATION;
    public static ForgeConfigSpec.IntValue POTGEN_BURNTIME;
    public static ForgeConfigSpec.IntValue POTGEN_TRANSFER_RATE;


    // initialiser
    static {
        // structure
        COMMON_BUILDER.comment("General Settings").push(CATEGORY_GENERAL);
        // ...
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Power Settings").push(CATEGORY_POWER);
            setupPotatoGenerator();
        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

    private static void setupPotatoGenerator() {
        COMMON_BUILDER.comment("Potato Generator Settings").push(SUBCATEGORY_POTATO);

        POTATO_GENERATOR_CAPACITY = COMMON_BUILDER.comment("Maximum power capcity of the potato generator (RF, default = 200,000)")
                .defineInRange("capacity", 200000, 0, Integer.MAX_VALUE);  // define(name, default, min, max)
        POTGEN_GENERATION = COMMON_BUILDER.comment("Power generation per tick for a potato (RF/t, default = 20)")
                .defineInRange("generation_per_tick", 20, 0, Integer.MAX_VALUE);
        POTGEN_BURNTIME = COMMON_BUILDER.comment("Burn time for potatoes (ticks, default = 200)")
                .defineInRange("burntime", 200, 0, Integer.MAX_VALUE);
        POTGEN_TRANSFER_RATE = COMMON_BUILDER.comment("Transfer rate per side (RF/t, default = 2000)")
                .defineInRange("transfer_rate", 2000, 0, Integer.MAX_VALUE);

        COMMON_BUILDER.pop();
    }

    /*Loads a config file given specification*/
    public static void loadConfig(ForgeConfigSpec spec, Path path) {
        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync().autosave().writingMode(WritingMode.REPLACE).build();

        configData.load();
        spec.setConfig(configData);
    }

    // called when config has finished loading, if calculations based on config necessary
    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {

    }

    // when something changes in config
    @SubscribeEvent
    public static void onReload(final ModConfig.Loading configEvent) {

    }
}
