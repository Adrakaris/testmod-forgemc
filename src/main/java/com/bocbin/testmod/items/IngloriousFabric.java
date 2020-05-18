package com.bocbin.testmod.items;

import com.bocbin.testmod.TestMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class IngloriousFabric extends Item {
    public IngloriousFabric() {
        super(new Item.Properties()
                .group(TestMod.setup.itemGroup)
                .maxStackSize(64)
        );

        setRegistryName("inglorious_fabric");
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {  // to act as fuel
        return 340;
    }
}
