package com.bocbin.testmod.setup;

import net.minecraft.client.Minecraft;  // CLIENTSIDE
import net.minecraft.world.World;

public class ClientProxy implements IProxy{
    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().world;  // get client side
    }
}
