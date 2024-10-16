package hu.yijun.forgetestmodthree.datagen.loot;

import hu.yijun.forgetestmodthree.block.ModBlocks;
import hu.yijun.forgetestmodthree.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

import static hu.yijun.forgetestmodthree.block.ModBlocks.*;
import static hu.yijun.forgetestmodthree.item.ModItems.SAPPHIRE;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(SAPPHIRE_BLOCK.get());  // drops itself when broken
        this.dropSelf(SAPPHIRE_STAIRS.get());
        this.dropSelf(SAPPHIRE_BUTTON.get());
        this.dropSelf(SAPPHIRE_PRESSURE_PLATE.get());
        this.dropSelf(SAPPHIRE_TRAPDOOR.get());
        this.dropSelf(SAPPHIRE_FENCE.get());
        this.dropSelf(SAPPHIRE_FENCE_GATE.get());
        this.dropSelf(SAPPHIRE_WALL.get());
        this.add(SAPPHIRE_SLAB.get(), block -> createSlabItemTable(SAPPHIRE_SLAB.get()));  // slabs and doors have different drop than one per block
        this.add(SAPPHIRE_DOOR.get(), block -> createDoorTable(SAPPHIRE_DOOR.get()));

        this.dropSelf(RAW_SAPPHIRE_BLOCK.get());
        this.dropSelf(SOUND_BLOCK.get());

        this.add(SAPPHIRE_ORE.get(), block -> creatOreDropsWithMultipleOres(SAPPHIRE_ORE.get(), SAPPHIRE.get()));
        this.add(DEEPSLATE_SAPPHIRE_ORE.get(), block -> creatOreDropsWithMultipleOres(DEEPSLATE_SAPPHIRE_ORE.get(), SAPPHIRE.get()));
        this.add(NETHER_SAPPHIRE_ORE.get(), block -> creatOreDropsWithMultipleOres(NETHER_SAPPHIRE_ORE.get(), SAPPHIRE.get()));
        this.add(END_SAPPHIRE_ORE.get(), block -> creatOreDropsWithMultipleOres(END_SAPPHIRE_ORE.get(), SAPPHIRE.get(), 2, 6));
    }

    protected LootTable.Builder creatOreDropsWithMultipleOres(Block block, Item item) {
        return creatOreDropsWithMultipleOres(block, item, 2f, 5f);
    }

    protected LootTable.Builder creatOreDropsWithMultipleOres(Block block, Item item, float minDropCount, float maxDropCount) {
        return createSilkTouchDispatchTable(
                block,
                this.applyExplosionDecay(
                        block,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDropCount, maxDropCount)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))
                )
        );
    }


    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;  // iterable is () -> Iterator
    }


}
