package hu.yijun.forgetestmodthree.datagen;

import hu.yijun.forgetestmodthree.ForgeTestModThree;
import hu.yijun.forgetestmodthree.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static hu.yijun.forgetestmodthree.block.ModBlocks.*;

public class ModBlockTagProvider extends BlockTagsProvider {

    private static final Block[] SAPPHIRE_ORES = new Block[]{SAPPHIRE_ORE.get(), DEEPSLATE_SAPPHIRE_ORE.get(), NETHER_SAPPHIRE_ORE.get(), END_SAPPHIRE_ORE.get()};
    private static final Block[] SAPPHIRE_BLOCK_VARIANTS = new Block[]{SAPPHIRE_SLAB.get(), SAPPHIRE_STAIRS.get(), SAPPHIRE_DOOR.get(), SAPPHIRE_TRAPDOOR.get(), SAPPHIRE_BUTTON.get(), SAPPHIRE_PRESSURE_PLATE.get(), SAPPHIRE_FENCE.get(), SAPPHIRE_FENCE_GATE.get(), SAPPHIRE_WALL.get()};

    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ForgeTestModThree.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(SAPPHIRE_ORE.get(), DEEPSLATE_SAPPHIRE_ORE.get());
        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(NETHER_SAPPHIRE_ORE.get());
        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(END_SAPPHIRE_ORE.get());
        this.tag(ModTags.Blocks.NEEDS_SAPPHIRE_TOOL)
                .add(SOUND_BLOCK.get());  // bhig

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(SAPPHIRE_ORES)
                .add(SAPPHIRE_BLOCK.get(), RAW_SAPPHIRE_BLOCK.get(), SOUND_BLOCK.get())
                .add(SAPPHIRE_BLOCK_VARIANTS);

        this.tag(Tags.Blocks.ORES)
                .add(SAPPHIRE_ORES);

        this.tag(ModTags.Blocks.METAL_DETECTOR_VALUABLES)
                .addTag(Tags.Blocks.ORES);

        // essential else the fences etc won't connect
        this.tag(BlockTags.FENCES)
                .add(SAPPHIRE_FENCE.get());
        this.tag(BlockTags.FENCE_GATES)
                .add(SAPPHIRE_FENCE_GATE.get());
        this.tag(BlockTags.WALLS)
                .add(SAPPHIRE_WALL.get());
    }
}
