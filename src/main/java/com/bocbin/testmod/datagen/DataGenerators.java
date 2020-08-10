package com.bocbin.testmod.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.RecipeProvider;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {  // only get fired when mod is started with run data options
        DataGenerator generator = event.getGenerator();
        generator.addProvider(new RecipeProvider(generator));  // add providers for recipes and loot tables

        // instead of calling like recipes do, loot table providers call an act() with a directory cache
        generator.addProvider(new LootTableProvider(generator));
        // these are things from IDataProvider
    }
}
