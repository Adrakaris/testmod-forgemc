package com.bocbin.testmod.constructors;

import com.bocbin.testmod.TestMod;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;

public class GloriousArmour extends ArmorItem {
    public GloriousArmour(EquipmentSlotType slot) {
        super(new ArmourMaterialGlorious(), slot, new Item.Properties().group(TestMod.setup.itemGroup).maxStackSize(1));
    }
}
