package com.bocbin.testmod.setup;

import net.minecraft.world.World;

public interface IProxy {
    // proxy to allow cross communication

    World getClientWorld();
}
