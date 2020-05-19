package com.bocbin.testmod.items;

import com.bocbin.testmod.TestMod;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class Borscht extends Item {

    public Borscht() {
        super(
                new Item.Properties()
                .group(TestMod.setup.itemGroup)
                .maxStackSize(1)
                .food(
                        new Food.Builder()
                                .hunger(14).saturation(0.625f)
                                .meat()
                                .effect(new EffectInstance(Effects.HEALTH_BOOST, 20*60, 0), 0.8f)
                                .build()
                )
        );
        setRegistryName("borscht");
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        ItemStack itemStack = super.onItemUseFinish(stack, worldIn, entityLiving);
        if (entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode) {
            return stack;
        } else {
            return new ItemStack(Items.BOWL, 1);
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(
                new TranslationTextComponent("item.testmod.borscht.tooltip")
                .setStyle(new Style().setItalic(true).setColor(TextFormatting.GRAY))
        );
    }
}