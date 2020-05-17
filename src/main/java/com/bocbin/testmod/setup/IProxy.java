package com.bocbin.testmod.setup;

import net.minecraft.world.World;

public interface IProxy {
    // proxy to allow cross communication
    // can be used to set clientside or serverside only
    void init();
    World getClientWorld();
}
