package hu.yijun.forgetestmodthree.item;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {

    public static final FoodProperties STRAWBERRY = new FoodProperties.Builder()
            .fast().nutrition(4).saturationMod(0.2f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 30*20), 0.5f)  // it's bloody ticks
            .build();
}
