package hu.yijun.forgetestmodthree.block;

import hu.yijun.forgetestmodthree.ForgeTestModThree;
import hu.yijun.forgetestmodthree.block.blocks.SoundBlock;
import hu.yijun.forgetestmodthree.item.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ForgeTestModThree.MOD_ID);

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    // sapphire and all variants
    public static final RegistryObject<Block> SAPPHIRE_BLOCK = registerBlock("sapphire_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).sound(SoundType.AMETHYST)));
    public static final RegistryObject<Block> SAPPHIRE_STAIRS = registerBlock("sapphire_stairs", () -> new StairBlock(() -> SAPPHIRE_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.copy(SAPPHIRE_BLOCK.get())));
    public static final RegistryObject<Block> SAPPHIRE_SLAB = registerBlock("sapphire_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).sound(SoundType.AMETHYST)));
    public static final RegistryObject<Block> SAPPHIRE_BUTTON = registerBlock("sapphire_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON), BlockSetType.IRON, 10, false));
    public static final RegistryObject<Block> SAPPHIRE_PRESSURE_PLATE = registerBlock("sapphire_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).sound(SoundType.AMETHYST), BlockSetType.IRON));
    public static final RegistryObject<Block> SAPPHIRE_FENCE = registerBlock("sapphire_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).sound(SoundType.AMETHYST)));
    public static final RegistryObject<Block> SAPPHIRE_FENCE_GATE = registerBlock("sapphire_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).sound(SoundType.AMETHYST), SoundEvents.CHAIN_BREAK, SoundEvents.ANVIL_BREAK));
    public static final RegistryObject<Block> SAPPHIRE_WALL = registerBlock("sapphire_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).sound(SoundType.AMETHYST)));
    // iron: not openable by hand like iron doors
    // noOcclusion: prevents xray
    public static final RegistryObject<Block> SAPPHIRE_DOOR = registerBlock("sapphire_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).sound(SoundType.AMETHYST).noOcclusion(), BlockSetType.STONE));
    public static final RegistryObject<Block> SAPPHIRE_TRAPDOOR = registerBlock("sapphire_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).sound(SoundType.AMETHYST).noOcclusion(), BlockSetType.STONE));

    // others
    public static final RegistryObject<Block> RAW_SAPPHIRE_BLOCK = registerBlock("raw_sapphire_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)));

    public static final RegistryObject<Block> SAPPHIRE_ORE = registerBlock("sapphire_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE), UniformInt.of(3, 6)));
    public static final RegistryObject<Block> DEEPSLATE_SAPPHIRE_ORE = registerBlock("deepslate_sapphire_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE), UniformInt.of(3, 6)));
    public static final RegistryObject<Block> NETHER_SAPPHIRE_ORE = registerBlock("nether_sapphire_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.NETHERRACK).strength(3f), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> END_SAPPHIRE_ORE = registerBlock("end_sapphire_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE).strength(6f), UniformInt.of(3, 7)));

    public static final RegistryObject<Block> SOUND_BLOCK = registerBlock("sound_block", () -> new SoundBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(6f)));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> blockSupplier) {
        RegistryObject<T> blockObject = BLOCKS.register(name, blockSupplier);
        registerBlockItem(name, blockObject);
        return blockObject;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
