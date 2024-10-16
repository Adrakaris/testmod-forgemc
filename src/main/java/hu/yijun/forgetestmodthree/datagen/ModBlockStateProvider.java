package hu.yijun.forgetestmodthree.datagen;

import hu.yijun.forgetestmodthree.ForgeTestModThree;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import static hu.yijun.forgetestmodthree.block.ModBlocks.*;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ForgeTestModThree.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        makeSimpleCubeBlock(SAPPHIRE_BLOCK);
        makeSimpleCubeBlock(RAW_SAPPHIRE_BLOCK);

        makeSimpleCubeBlock(SAPPHIRE_ORE);
        makeSimpleCubeBlock(DEEPSLATE_SAPPHIRE_ORE);
        makeSimpleCubeBlock(NETHER_SAPPHIRE_ORE);
        makeSimpleCubeBlock(END_SAPPHIRE_ORE);

        makeSimpleCubeBlock(SOUND_BLOCK);
    }

    private void makeSimpleCubeBlock(RegistryObject<Block> blockObject) {
        simpleBlockWithItem(blockObject.get(), cubeAll(blockObject.get()));
    }
}
