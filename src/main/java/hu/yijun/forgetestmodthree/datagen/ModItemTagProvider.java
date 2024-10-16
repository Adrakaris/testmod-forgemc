package hu.yijun.forgetestmodthree.datagen;

import hu.yijun.forgetestmodthree.ForgeTestModThree;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput packOutput,
                              CompletableFuture<HolderLookup.Provider> holderLookupProviderCompletableFuture,
                              CompletableFuture<TagLookup<Block>> blockTagLookupCompletableFuture,
                              @Nullable ExistingFileHelper existingFileHelper
    ) {
        super(packOutput, holderLookupProviderCompletableFuture, blockTagLookupCompletableFuture, ForgeTestModThree.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

    }
}
