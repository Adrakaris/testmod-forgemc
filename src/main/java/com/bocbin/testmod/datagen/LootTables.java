package com.bocbin.testmod.datagen;

import com.bocbin.testmod.blocks.ModBlocks;
import net.minecraft.data.DataGenerator;

public class LootTables extends BaseLootTableProvider {

    public LootTables(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void addTables() {
        lootTables.put(ModBlocks.GLORIOUSFABRICBLOCK, createStandardTable("glorious_fabric_block", ModBlocks.GLORIOUSFABRICBLOCK));
        lootTables.put(ModBlocks.POTATOGENERATOR, createStandardTable("potato_generator", ModBlocks.POTATOGENERATOR));
    }
}
