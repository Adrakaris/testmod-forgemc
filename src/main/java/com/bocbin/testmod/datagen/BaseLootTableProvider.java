package com.bocbin.testmod.datagen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.data.LootTableProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.functions.CopyName;
import net.minecraft.world.storage.loot.functions.CopyNbt;
import net.minecraft.world.storage.loot.functions.SetContents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseLootTableProvider extends LootTableProvider {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();  // using GSON json builder with pretty printing

    protected final Map<Block, LootTable.Builder> lootTables = new HashMap<>();
    private final DataGenerator generator;

    public BaseLootTableProvider(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
        this.generator = dataGeneratorIn;
    }

    protected abstract void addTables();

    protected LootTable.Builder createStandardTable(String name, Block block) {
        // Essentially this builds a loot table and puts everything necessary in it for a simple block loot table
        // (that has an inventory) so that we dont have to do this multiple times.
        LootPool.Builder builder = LootPool.builder()
                .name(name)
                .rolls(ConstantRange.of(1))
                .addEntry(ItemLootEntry.builder(block)
                        .acceptFunction(CopyName.builder(CopyName.Source.BLOCK_ENTITY))
                        .acceptFunction(CopyNbt.func_215881_a(CopyNbt.Source.BLOCK_ENTITY)  // func is CopyNbt.Builder()
                                .func_216055_a("inv", "BlockEntityTag.inv", CopyNbt.Action.REPLACE)  // .addOperation()
                                .func_216055_a("energy", "BlockEntityTag.energy", CopyNbt.Action.REPLACE)
                        )
                        .acceptFunction(SetContents.func_215920_b()  // .builder()
                                .func_216075_a(DynamicLootEntry.func_216162_a(new ResourceLocation("minecraft", "contents")))  // .addLootEntry(),
                        )
                );
        return LootTable.builder().addLootPool(builder);
    }

    @Override
    public void act(DirectoryCache cache) {
        addTables();  // call add tables which is implemented in subclass, which will fill this.lootTables

        Map<ResourceLocation, LootTable> tables = new HashMap<>();  // make a new Map
        for (Map.Entry<Block, LootTable.Builder> entry : lootTables.entrySet()) {  // iterate over each in this.lootTables
            tables.put(entry.getKey().getLootTable(), entry.getValue().setParameterSet(LootParameterSets.BLOCK).build());  // associate the table of the block with the loot table of the block
        }
        writeTables(cache, tables);  // write tables
    }

    private void writeTables(DirectoryCache cache, Map<ResourceLocation, LootTable> tables) {
        Path outputFolder = this.generator.getOutputFolder();  // gets the output folder from the generator
        tables.forEach((key, lootTable) -> {  // for each key:
            Path path = outputFolder.resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json");  // make a path
            try {
                IDataProvider.save(GSON, cache, LootTableManager.toJson(lootTable), path);  // try save loot table as json
            } catch (IOException e) {
                LOGGER.error("Couldn't write loot table {}", path, e);
            }
        });
    }

    @Override
    public String getName() {
        return "TestMod LootTables";
    }
}
