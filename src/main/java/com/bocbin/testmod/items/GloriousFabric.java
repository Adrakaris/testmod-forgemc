package com.bocbin.testmod.items;

import com.bocbin.testmod.TestMod;
import net.minecraft.item.Item;

public class GloriousFabric extends Item {
    public GloriousFabric() {
        super(new Item.Properties()
                .group(TestMod.setup.itemGroup)
                .maxStackSize(64)
        );

        setRegistryName("glorious_fabric");
    }
}
