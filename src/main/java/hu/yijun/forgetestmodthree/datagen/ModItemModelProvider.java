package hu.yijun.forgetestmodthree.datagen;

import hu.yijun.forgetestmodthree.ForgeTestModThree;
import hu.yijun.forgetestmodthree.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import static hu.yijun.forgetestmodthree.item.ModItems.*;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ForgeTestModThree.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // seems to return a builder but does nothing, mysterious...
        configureSimpleItem(SAPPHIRE);
        configureSimpleItem(RAW_SAPPHIRE);
        configureSimpleItem(METAL_DETECTOR);
        configureSimpleItem(STRAWBERRY);
    }

    private ItemModelBuilder configureSimpleItem(RegistryObject<Item> itemObject) {
        return withExistingParent(
                    itemObject.getId().getPath(),
                    new ResourceLocation("item/generated")
        ).texture(
                "layer0",
                new ResourceLocation(ForgeTestModThree.MOD_ID, "item/%s".formatted(itemObject.getId().getPath()))
        );
    }
}
