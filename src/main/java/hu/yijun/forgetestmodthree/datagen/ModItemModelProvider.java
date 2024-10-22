package hu.yijun.forgetestmodthree.datagen;

import hu.yijun.forgetestmodthree.ForgeTestModThree;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;

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

        trimmedArmorItem(SAPPHIRE_HELMET);
        trimmedArmorItem(SAPPHIRE_CHESTPLATE);
        trimmedArmorItem(SAPPHIRE_LEGGINGS);
        trimmedArmorItem(SAPPHIRE_BOOTS);
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

    // this is to get armour trims working, because ItemGenerators.generateArmorTrims() is private and hence not very useful
    private static final LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    // source El_Redstoniano
    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = ForgeTestModThree.MOD_ID;

        if(itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.forEach((trimMaterial, value) -> {

                float trimValue = value;

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = new ResourceLocation(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = new ResourceLocation(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = new ResourceLocation(MOD_ID, currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                new ResourceLocation(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }

}
