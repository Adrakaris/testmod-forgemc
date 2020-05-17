package com.bocbin.testmod.setup;

import com.bocbin.testmod.items.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModSetup {

    // custom creative tab (itemgroup)
    public ItemGroup itemGroup = new ItemGroup("testmod") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.GLORIOUSFABRIC);
        }
    };

    public void init() {

    }
}
