package com.bocbin.testmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class GloriousFabricBlock extends Block {
    public GloriousFabricBlock() {
        super(Properties.create(Material.WOOL)
                .hardnessAndResistance(0.8f)  // can either be one number for both or two separate numbers
                .sound(SoundType.CLOTH)
        );

        setRegistryName("glorious_fabric_block");
    }
}
