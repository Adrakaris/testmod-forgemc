package com.bocbin.testmod.entities;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class PotatoBlockEntity extends AnimalEntity {
    public PotatoBlockEntity(EntityType<? extends AnimalEntity> type, World world) {
        super(type, world);
    }

    @Nullable
    @Override
    public AgeableEntity createChild(AgeableEntity ageable) {
        return null;
    }
}
