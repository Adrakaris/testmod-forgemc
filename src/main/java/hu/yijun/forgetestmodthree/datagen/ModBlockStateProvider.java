package hu.yijun.forgetestmodthree.datagen;

import hu.yijun.forgetestmodthree.ForgeTestModThree;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
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

        new BlockStairsEtcModelGenerator(SAPPHIRE_BLOCK)
                .stair(SAPPHIRE_STAIRS)
                .slab(SAPPHIRE_SLAB)
                .button(SAPPHIRE_BUTTON)
                .pressurePlate(SAPPHIRE_PRESSURE_PLATE)
                .fence(SAPPHIRE_FENCE)
                .fenceGate(SAPPHIRE_FENCE_GATE)
                .wall(SAPPHIRE_WALL)
                .door(SAPPHIRE_DOOR, "block/sapphire_door_bottom", "block/sapphire_door_top")
                .trapdoor(SAPPHIRE_TRAPDOOR, "block/sapphire_trapdoor").end();

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


    private class BlockStairsEtcModelGenerator {
        private final Block masterBlock;

        public BlockStairsEtcModelGenerator(RegistryObject<Block> mainBlockTexture) {
            this.masterBlock = mainBlockTexture.get();
        }

        public BlockStairsEtcModelGenerator stair(RegistryObject<Block> stair) {
            stairsBlock((StairBlock) stair.get(), blockTexture(masterBlock));
            return this;
        }

        public BlockStairsEtcModelGenerator slab(RegistryObject<Block> slab) {
            slabBlock((SlabBlock) slab.get(), blockTexture(masterBlock), blockTexture(masterBlock));
            return this;
        }

        public BlockStairsEtcModelGenerator slab(RegistryObject<Block> slab, String optionalDoubleSlabTexture) {
            slabBlock((SlabBlock) slab.get(), modLoc(optionalDoubleSlabTexture), blockTexture(masterBlock));
            return this;
        }

        public BlockStairsEtcModelGenerator button(RegistryObject<Block> button) {
            buttonBlock((ButtonBlock) button.get(), blockTexture(masterBlock));
            return this;
        }

        public BlockStairsEtcModelGenerator pressurePlate(RegistryObject<Block> pressurePlate) {
            pressurePlateBlock((PressurePlateBlock) pressurePlate.get(), blockTexture(masterBlock));
            return this;
        }

        public BlockStairsEtcModelGenerator fence(RegistryObject<Block> fence) {
            fenceBlock((FenceBlock) fence.get(), blockTexture(masterBlock));
            return this;
        }

        public BlockStairsEtcModelGenerator fenceGate(RegistryObject<Block> fenceGate) {
            fenceGateBlock((FenceGateBlock) fenceGate.get(), blockTexture(masterBlock));
            return this;
        }

        public BlockStairsEtcModelGenerator wall(RegistryObject<Block> wall) {
            wallBlock((WallBlock) wall.get(), blockTexture(masterBlock));
            return this;
        }

        public BlockStairsEtcModelGenerator door(RegistryObject<Block> door, String doorBottomTexture, String doorTopTexture) {
            doorBlockWithRenderType((DoorBlock) door.get(), modLoc(doorBottomTexture), modLoc(doorTopTexture), "cutout");  // cutout: makes transparency work
            return this;
        }

        public BlockStairsEtcModelGenerator trapdoor(RegistryObject<Block> trapdoor, String trapdoorTexture) {
            trapdoorBlockWithRenderType((TrapDoorBlock) trapdoor.get(), modLoc(trapdoorTexture), true, "cutout");
            return this;
        }

        public void end() {}
    }
}
