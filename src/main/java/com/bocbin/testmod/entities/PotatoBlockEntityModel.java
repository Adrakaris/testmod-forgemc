package com.bocbin.testmod.entities;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;

public class PotatoBlockEntityModel extends EntityModel<PotatoBlockEntity> {
    public RendererModel body;

    public PotatoBlockEntityModel() {
        body = new RendererModel(this, 0, 0);
        body.addBox(-3, -3, -3, 6, 6, 6);  // simple box model for mob
    }

    @Override
    public void render(PotatoBlockEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        body.render(scale);
    }

    @Override
    public void setRotationAngles(PotatoBlockEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        // empty
    }
}
