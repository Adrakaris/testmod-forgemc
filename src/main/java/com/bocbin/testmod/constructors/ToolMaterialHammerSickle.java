package com.bocbin.testmod.constructors;

import com.bocbin.testmod.items.ModItems;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public class ToolMaterialHammerSickle implements IItemTier {
    @Override
    public int getMaxUses() {
        return 1917;
    }

    @Override
    public float getEfficiency() {
        return 19.17f;
    }

    @Override
    public float getAttackDamage() {
        return 10.24f;
    }

    @Override
    public int getHarvestLevel() {
        return 4;
    }

    @Override
    public int getEnchantability() {
        return 19;  // 1917
    }

    @Override
    public Ingredient getRepairMaterial() {
        return Ingredient.fromItems(ModItems.GLORIOUSFABRIC);
    }
}
