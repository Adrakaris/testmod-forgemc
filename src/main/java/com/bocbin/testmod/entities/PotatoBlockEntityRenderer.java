package com.bocbin.testmod.entities;

import com.bocbin.testmod.TestMod;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class PotatoBlockEntityRenderer extends MobRenderer<PotatoBlockEntity, PotatoBlockEntityModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(TestMod.MODID, "textures/entity/potato_block_entity.png");

    public PotatoBlockEntityRenderer(EntityRendererManager manager) {
        super(manager, new PotatoBlockEntityModel(), 0.5f);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(PotatoBlockEntity entity) {
        return TEXTURE;
    }
}
