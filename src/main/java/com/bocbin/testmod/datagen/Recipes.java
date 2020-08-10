package com.bocbin.testmod.datagen;

import com.bocbin.testmod.blocks.ModBlocks;
import com.bocbin.testmod.items.ModItems;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class Recipes extends RecipeProvider {
    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        // example for the glorious fabric block
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.GLORIOUSFABRICBLOCK)
                .patternLine("##")
                .patternLine("##")
                .key('#', ModItems.GLORIOUSFABRIC)
                .setGroup("testmod")
                .addCriterion("glorious_fabric", InventoryChangeTrigger.Instance.forItems(ModItems.GLORIOUSFABRIC))  // criteria to unlock recipe
                .build(consumer);

        // example of hammer and sickle
        ShapedRecipeBuilder.shapedRecipe(ModItems.HAMMERSICKLE)
                .patternLine("#g ")
                .patternLine("O/g")
                .patternLine("/g/")
                .key('#', Blocks.GOLD_BLOCK)
                .key('g', Tags.Items.INGOTS_GOLD)  // uses tags
                .key('/', Items.STICK)
                .key('O', ModItems.GLORIOUSFABRIC)
                .setGroup("testmod")
                .addCriterion("glorious_fabric", InventoryChangeTrigger.Instance.forItems(ModItems.GLORIOUSFABRIC))  // there are a lot of triggers
                .build(consumer);
    }
}
