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

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static hu.yijun.forgetestmodthree.block.ModBlocks.*;

public class ModBlockTagProvider extends BlockTagsProvider {

    private static final Block[] SAPPHIRE_ORES = new Block[]{SAPPHIRE_ORE.get(), DEEPSLATE_SAPPHIRE_ORE.get(), NETHER_SAPPHIRE_ORE.get(), END_SAPPHIRE_ORE.get()};

    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ForgeTestModThree.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(SAPPHIRE_ORES);
        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(NETHER_SAPPHIRE_ORE.get());
        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(END_SAPPHIRE_ORE.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(SAPPHIRE_ORES)
                .add(SAPPHIRE_BLOCK.get(), RAW_SAPPHIRE_BLOCK.get(), SOUND_BLOCK.get());

        this.tag(Tags.Blocks.ORES)
                .add(SAPPHIRE_ORES);

        this.tag(ModTags.Blocks.METAL_DETECTOR_VALUABLES)
                .addTag(Tags.Blocks.ORES);
    }
}
