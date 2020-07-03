package com.bocbin.testmod.items;

import com.bocbin.testmod.TestMod;
import com.bocbin.testmod.blocks.ModBlocks;
import com.bocbin.testmod.constructors.ToolMaterialHammerSickle;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;

import java.util.Set;

public class HammerSickle extends PickaxeItem {
    public HammerSickle() {
        super(
                new ToolMaterialHammerSickle(),
                -1,
                -2.8f,
                new Item.Properties()
                        .group(TestMod.setup.itemGroup)
                        .maxStackSize(1)
        );

        setRegistryName("hammer_sickle");
    }

    @Override
    public boolean canHarvestBlock(BlockState blockIn) {
        return effectiveAgainst.contains(blockIn.getBlock()) || super.canHarvestBlock(blockIn);
    }

    @Override  // multitooling
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if (effectiveAgainst.contains(state.getBlock())) {
            return this.efficiency;
        } else {
            return super.getDestroySpeed(stack, state);
        }
    }

    private static Set<Block> effectiveAgainst = Sets.newHashSet(Blocks.WHITE_WOOL, Blocks.ORANGE_WOOL, Blocks.MAGENTA_WOOL, Blocks.LIGHT_BLUE_WOOL, Blocks.YELLOW_WOOL,
            Blocks.LIME_WOOL, Blocks.PINK_WOOL, Blocks.GRAY_WOOL, Blocks.LIGHT_GRAY_WOOL, Blocks.CYAN_WOOL,
            Blocks.PURPLE_WOOL, Blocks.BLUE_WOOL, Blocks.BROWN_WOOL, Blocks.GREEN_WOOL, Blocks.RED_WOOL,
            Blocks.BLACK_WOOL, Blocks.ACACIA_LEAVES, Blocks.BIRCH_LEAVES, Blocks.DARK_OAK_LEAVES, Blocks.JUNGLE_LEAVES,
            Blocks.OAK_LEAVES, Blocks.SPRUCE_LEAVES, ModBlocks.GLORIOUSFABRICBLOCK, ModBlocks.POTATOGENERATOR);
}
