package com.bocbin.testmod.setup;

import com.bocbin.testmod.blocks.ModBlocks;
import com.bocbin.testmod.blocks.PotatoGeneratorScreen;
import com.bocbin.testmod.entities.PotatoBlockEntity;
import com.bocbin.testmod.entities.PotatoBlockEntityRenderer;
import net.minecraft.client.Minecraft;  // CLIENTSIDE
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy implements IProxy{
    @Override
    public void init() {
        /* ScreenManager.registerFactory(ModBlocks.POTATOGENERATOR_CONTAINER, (container, playerInventory, title) -> {
            return new PotatoGeneratorScreen(container, playerInventory, title);
        }); */  // IS EQUAL TO
        ScreenManager.registerFactory(ModBlocks.POTATOGENERATOR_CONTAINER, PotatoGeneratorScreen::new);  // links container and gui on clientside
        // fuck me.
        RenderingRegistry.registerEntityRenderingHandler(PotatoBlockEntity.class, PotatoBlockEntityRenderer::new);
    }

    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().world;  // get client side
    }

    @Override
    public PlayerEntity getClientPlayer() {
        return Minecraft.getInstance().player;
    }
}
