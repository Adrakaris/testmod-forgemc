package com.bocbin.testmod.setup;

import com.bocbin.testmod.TestMod;
import com.bocbin.testmod.items.ModItems;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=TestMod.MODID, value=Dist.CLIENT, bus=Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistration {
    // purely just to register custom and handle custom spawn eggs, because we cannae use vanilla ones because
    // items register before entities and the mob is FINAL on vanilla SpawnEggItem
    @SubscribeEvent
    public static void onItemColor(ColorHandlerEvent.Item event) {
        event.getItemColors().register((stack, i) -> 0xffd450, ModItems.POTATOBLOCKENTITYEGG);
    }
}
