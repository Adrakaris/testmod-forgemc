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

        new BlockStairsEtcModelBuilder(SAPPHIRE_BLOCK)
                .stair(SAPPHIRE_STAIRS)
                .slab(SAPPHIRE_SLAB)
                .button(SAPPHIRE_BUTTON)
                .pressurePlate(SAPPHIRE_PRESSURE_PLATE)
                .fence(SAPPHIRE_FENCE)
                .fenceGate(SAPPHIRE_FENCE_GATE)
                .wall(SAPPHIRE_WALL)
                .door(SAPPHIRE_DOOR, "block/sapphire_door_bottom", "block/sapphire_door_top")
                .trapdoor(SAPPHIRE_TRAPDOOR, "block/sapphire_trapdoor").build();

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


    private class BlockStairsEtcModelBuilder {
        private final Block masterBlock;
        private Block stair;
        private Block slab;
        private Block button;
        private Block pressurePlate;
        private Block fence;
        private Block fenceGate;
        private Block wall;
        private Block door;
        private Block trapdoor;

        private String optionalDoubleSlabTexture;
        private String doorBottomTexture;
        private String doorTopTexture;
        private String trapdoorTexture;

        public BlockStairsEtcModelBuilder(RegistryObject<Block> mainBlockTexture) {
            this.masterBlock = mainBlockTexture.get();
        }

        public BlockStairsEtcModelBuilder stair(RegistryObject<Block> stair) {
            this.stair = stair.get();
            return this;
        }

        public BlockStairsEtcModelBuilder slab(RegistryObject<Block> slab) {
            this.slab = slab.get();
            return this;
        }

        public BlockStairsEtcModelBuilder slab(RegistryObject<Block> slab, String optionalDoubleSlabTexture) {
            this.slab = slab.get();
            this.optionalDoubleSlabTexture = optionalDoubleSlabTexture;
            return this;
        }

        public BlockStairsEtcModelBuilder button(RegistryObject<Block> button) {
            this.button = button.get();
            return this;
        }

        public BlockStairsEtcModelBuilder pressurePlate(RegistryObject<Block> pressurePlate) {
            this.pressurePlate = pressurePlate.get();
            return this;
        }

        public BlockStairsEtcModelBuilder fence(RegistryObject<Block> fence) {
            this.fence = fence.get();
            return this;
        }

        public BlockStairsEtcModelBuilder fenceGate(RegistryObject<Block> fenceGate) {
            this.fenceGate = fenceGate.get();
            return this;
        }

        public BlockStairsEtcModelBuilder wall(RegistryObject<Block> wall) {
            this.wall = wall.get();
            return this;
        }

        public BlockStairsEtcModelBuilder door(RegistryObject<Block> door, String doorBottomTexture, String doorTopTexture) {
            this.door = door.get();
            this.doorBottomTexture = doorBottomTexture;
            this.doorTopTexture = doorTopTexture;
            return this;
        }

        public BlockStairsEtcModelBuilder trapdoor(RegistryObject<Block> trapdoor, String trapdoorTexture) {
            this.trapdoor = trapdoor.get();
            this.trapdoorTexture = trapdoorTexture;
            return this;
        }

        public void build() {
            if (stair != null) {
                stairsBlock((StairBlock) stair, blockTexture(masterBlock));
            }
            if (slab != null) {
                if (optionalDoubleSlabTexture != null) {
                    slabBlock((SlabBlock) slab, modLoc(optionalDoubleSlabTexture), blockTexture(masterBlock));
                } else {
                    slabBlock((SlabBlock) slab, blockTexture(masterBlock), blockTexture(masterBlock));
                }
            }
            if (button != null) {
                buttonBlock((ButtonBlock) button, blockTexture(masterBlock));
            }
            if (pressurePlate != null) {
                pressurePlateBlock((PressurePlateBlock) pressurePlate, blockTexture(masterBlock));
            }
            if (fence != null) {
                fenceBlock((FenceBlock) fence, blockTexture(masterBlock));
            }
            if (fenceGate != null) {
                fenceGateBlock((FenceGateBlock) fenceGate, blockTexture(masterBlock));
            }
            if (wall != null) {
                wallBlock((WallBlock) wall, blockTexture(masterBlock));
            }
            if (door != null) {
                doorBlockWithRenderType((DoorBlock) door, modLoc(doorBottomTexture), modLoc(doorTopTexture), "cutout");  // cutout: makes transparency work
            }
            if (trapdoor != null) {
                trapdoorBlockWithRenderType((TrapDoorBlock) trapdoor, modLoc(trapdoorTexture), true, "cutout");
            }
        }
    }
}
