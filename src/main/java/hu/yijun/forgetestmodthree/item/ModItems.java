package hu.yijun.forgetestmodthree.item;

import hu.yijun.forgetestmodthree.ForgeTestModThree;
import hu.yijun.forgetestmodthree.item.items.FuelItem;
import hu.yijun.forgetestmodthree.item.items.MetalDetectorItem;
import hu.yijun.forgetestmodthree.item.items.SapphireStaffItem;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ForgeTestModThree.MOD_ID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_SAPPHIRE = ITEMS.register("raw_sapphire", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector", MetalDetectorItem::new);
    public static final RegistryObject<Item> STRAWBERRY = ITEMS.register("strawberry", () -> new Item(new Item.Properties().food(ModFoods.STRAWBERRY)));
    public static final RegistryObject<Item> PINE_CONE = ITEMS.register("pine_cone", () -> new FuelItem(new Item.Properties(), 400));
    public static final RegistryObject<Item> SAPPHIRE_STAFF = ITEMS.register("sapphire_staff", SapphireStaffItem::new);

    // default attack speed is 4
    public static final RegistryObject<Item> SAPPHIRE_SWORD = ITEMS.register("sapphire_sword", () -> new SwordItem(ModToolTiers.SAPPHIRE, 8, 1.6f-4, new Item.Properties()));
    public static final RegistryObject<Item> SAPPHIRE_PICKAXE = ITEMS.register("sapphire_pickaxe", () -> new PickaxeItem(ModToolTiers.SAPPHIRE, 4, 1.2f-4, new Item.Properties()));
    public static final RegistryObject<Item> SAPPHIRE_AXE = ITEMS.register("sapphire_axe", () -> new AxeItem(ModToolTiers.SAPPHIRE, 13, 1.2f-4, new Item.Properties()));
    public static final RegistryObject<Item> SAPPHIRE_SHOVEL = ITEMS.register("sapphire_shovel", () -> new ShovelItem(ModToolTiers.SAPPHIRE, 3, 1.1f-4, new Item.Properties()));
    public static final RegistryObject<Item> SAPPHIRE_HOE = ITEMS.register("sapphire_hoe", () -> new HoeItem(ModToolTiers.SAPPHIRE, 5, 3f-4, new Item.Properties()));

    public static final RegistryObject<Item> SAPPHIRE_HELMET = ITEMS.register("sapphire_helmet", () -> new ArmorItem(ModArmourMaterials.SAPPHIRE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> SAPPHIRE_CHESTPLATE = ITEMS.register("sapphire_chestplate", () -> new ArmorItem(ModArmourMaterials.SAPPHIRE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> SAPPHIRE_LEGGINGS = ITEMS.register("sapphire_leggings", () -> new ArmorItem(ModArmourMaterials.SAPPHIRE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> SAPPHIRE_BOOTS = ITEMS.register("sapphire_boots", () -> new ArmorItem(ModArmourMaterials.SAPPHIRE, ArmorItem.Type.BOOTS, new Item.Properties()));

}
