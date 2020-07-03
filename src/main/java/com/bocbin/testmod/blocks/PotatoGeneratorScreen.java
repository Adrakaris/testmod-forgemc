package com.bocbin.testmod.blocks;

import com.bocbin.testmod.TestMod;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class PotatoGeneratorScreen extends ContainerScreen<PotatoGeneratorContainer> {

    private ResourceLocation GUI_TEXTURE = new ResourceLocation(TestMod.MODID, "textures/gui/potato_generator_gui.png");

    // ALT + INSERT -> Generate...
    public PotatoGeneratorScreen(PotatoGeneratorContainer screenContainer, PlayerInventory inv, ITextComponent name) {
        super(screenContainer, inv, name);
    }

    // **copied from minecraft HopperScreen class and modified**

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();  // this darkens the background, without this the background wouldn't darken
        super.render(mouseX, mouseY, partialTicks);
        // partialticks are in which part of the tick we are rendering at
        // since ticks are often much lower than framerate
        this.renderHoveredToolTip(mouseX, mouseY);  // this renders the tooltips (also the place to add custom tooltips)
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        // Potato Generator
        this.font.drawString(this.title.getFormattedText(), 8.0F, 6.0F, 4210752); // #404040 = 4210752
        // Inventory
        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float)(this.ySize - 96 + 2), 4210752);
        // Energy Value
        this.font.drawString("Energy:", 8.0F, 16.0F, 0x404040);
        this.font.drawString(container.getEnergy() + "RF", 8.0F, 26.0F, 0x404040);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        // this is what handles the actual GUI image itself
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(GUI_TEXTURE);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.blit(i, j, 0, 0, this.xSize, this.ySize);
    }
}
