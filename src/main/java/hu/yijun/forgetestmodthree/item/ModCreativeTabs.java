package hu.yijun.forgetestmodthree.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static hu.yijun.forgetestmodthree.ForgeTestModThree.MOD_ID;
import static hu.yijun.forgetestmodthree.block.ModBlocks.*;
import static hu.yijun.forgetestmodthree.item.ModItems.*;

public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

    public static final RegistryObject<CreativeModeTab> TEST_MOD_TAB = CREATIVE_MODE_TABS.register(
            "test_mod_tab", () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(SAPPHIRE.get()))
                    .title(Component.translatable(MOD_ID + ".creativetab.test_tab"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(SAPPHIRE.get());  // this sets the order of items in our creative tab
                        output.accept(RAW_SAPPHIRE.get());

                        output.accept(METAL_DETECTOR.get());

                        output.accept(SAPPHIRE_BLOCK.get());
                        output.accept(RAW_SAPPHIRE_BLOCK.get());
                        output.accept(SAPPHIRE_ORE.get());
                        output.accept(DEEPSLATE_SAPPHIRE_ORE.get());
                        output.accept(NETHER_SAPPHIRE_ORE.get());
                        output.accept(END_SAPPHIRE_ORE.get());
                    })
                    .build()
    );

}
