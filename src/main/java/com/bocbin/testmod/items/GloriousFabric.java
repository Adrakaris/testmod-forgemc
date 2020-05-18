package com.bocbin.testmod.items;

import com.bocbin.testmod.TestMod;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

@SuppressWarnings("ALL")
public class GloriousFabric extends Item {
    public GloriousFabric() {
        super(new Item.Properties()
                .group(TestMod.setup.itemGroup)
                .maxStackSize(64)
        );

        setRegistryName("glorious_fabric");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        playerIn.playSound(SoundEvents.BLOCK_WOOL_BREAK, 1.0f, 1.0f);
        return new ActionResult<ItemStack>(ActionResultType.SUCCESS, playerIn.getHeldItem(handIn));
    }
}
