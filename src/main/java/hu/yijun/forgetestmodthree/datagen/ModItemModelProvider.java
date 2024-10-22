package hu.yijun.forgetestmodthree.datagen;

import hu.yijun.forgetestmodthree.ForgeTestModThree;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static hu.yijun.forgetestmodthree.block.ModBlocks.*;
import static hu.yijun.forgetestmodthree.item.ModItems.*;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ForgeTestModThree.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // seems to return a builder but does nothing, mysterious...
        simpleItem(SAPPHIRE);
        simpleItem(RAW_SAPPHIRE);
        simpleItem(METAL_DETECTOR);
        simpleItem(STRAWBERRY);
        simpleItem(PINE_CONE);

        handheldToolItem(SAPPHIRE_SWORD);
        handheldToolItem(SAPPHIRE_PICKAXE);
        handheldToolItem(SAPPHIRE_AXE);
        handheldToolItem(SAPPHIRE_SHOVEL);
        handheldToolItem(SAPPHIRE_HOE);

        blockWithSeparateItem(SAPPHIRE_DOOR);
        fenceItem(SAPPHIRE_FENCE, SAPPHIRE_BLOCK);
        buttonItem(SAPPHIRE_BUTTON, SAPPHIRE_BLOCK);
        wallItem(SAPPHIRE_WALL, SAPPHIRE_BLOCK);
        simpleBlockItem(SAPPHIRE_STAIRS);
        simpleBlockItem(SAPPHIRE_SLAB);
        simpleBlockItem(SAPPHIRE_PRESSURE_PLATE);
        simpleBlockItem(SAPPHIRE_FENCE_GATE);
        trapdoorItem(SAPPHIRE_TRAPDOOR);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> itemObject) {
        return withExistingParent(
                    itemObject.getId().getPath(), new ResourceLocation("item/generated")
        ).texture(
                "layer0", new ResourceLocation(ForgeTestModThree.MOD_ID, "item/%s".formatted(itemObject.getId().getPath()))
        );
    }

    private ItemModelBuilder handheldToolItem(RegistryObject<Item> itemObject) {
        return withExistingParent(
                itemObject.getId().getPath(), new ResourceLocation("item/handheld")
        ).texture(
                "layer0", new ResourceLocation(ForgeTestModThree.MOD_ID, "item/" + itemObject.getId().getPath())
        );
    }

    private ItemModelBuilder blockWithSeparateItem(RegistryObject<Block> blockToItem) {
        return withExistingParent(
                blockToItem.getId().getPath(), new ResourceLocation("item/generated")
        ).texture(
                "layer0", new ResourceLocation(ForgeTestModThree.MOD_ID, "item/" + blockToItem.getId().getPath())
        );
    }

    // for specially generated blocks which do not have blockitems automatically generated
    private void simpleBlockItem(RegistryObject<Block> block) {
        withExistingParent(
                ForgeTestModThree.MOD_ID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath())
        );
    }

    // created by nj since apparently forge doesn't do fences walls etc
    private void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture", new ResourceLocation(ForgeTestModThree.MOD_ID, "block/" + baseBlock.getId().getPath()));
    }
    private void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture", new ResourceLocation(ForgeTestModThree.MOD_ID, "block/" + baseBlock.getId().getPath()));
    }

    private void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall", new ResourceLocation(ForgeTestModThree.MOD_ID, "block/" + baseBlock.getId().getPath()));
    }

    private void trapdoorItem(RegistryObject<Block> block) {
        withExistingParent(
                ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + block.getId().getPath() + "_bottom")
        );
    }

}
