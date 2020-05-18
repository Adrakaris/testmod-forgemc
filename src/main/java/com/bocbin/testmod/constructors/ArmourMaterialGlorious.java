package com.bocbin.testmod.constructors;

import com.bocbin.testmod.items.ModItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class ArmourMaterialGlorious implements IArmorMaterial {

    public static final int[] BASE_DURABILITY = new int[] {13, 15, 16, 11};  // boots legs chest helm
    public static final int[] BASE_PROTECTION = new int[] {2, 4, 5, 2};

    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return BASE_DURABILITY[slotIn.getSlotIndex()-1] * 17;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return BASE_PROTECTION[slotIn.getSlotIndex()-1];
    }

    @Override
    public int getEnchantability() {
        return 19;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return SoundEvents.ITEM_ARMOR_EQUIP_CHAIN;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return Ingredient.fromItems(ModItems.GLORIOUSFABRIC);
    }

    @Override
    public String getName() {
        return "testmod:glorious";
    }

    @Override
    public float getToughness() {
        return 0;
    }
}
