package com.bocbin.testmod.blocks;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class ModBlocks {

    @ObjectHolder("testmod:glorious_fabric_block")
    public static GloriousFabricBlock GLORIOUSFABRICBLOCK;  // to associate a key with an instance, helpful for cross-mod stuff

    @ObjectHolder("testmod:potato_generator")
    public static PotatoGenerator POTATOGENERATOR;
    @ObjectHolder("testmod:potato_generator")
    public static TileEntityType<PotatoGeneratorTile> POTATOGENERATOR_TILE;  // defines tile entity type
}
