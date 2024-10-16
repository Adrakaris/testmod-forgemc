package hu.yijun.forgetestmodthree.item.items;

import hu.yijun.forgetestmodthree.ForgeTestModThree;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MetalDetectorItem extends Item {

    private static final Block VALUABLE_BLOCK = Blocks.IRON_ORE;

    public MetalDetectorItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        if (pContext.getLevel().isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        BlockPos positionClicked = pContext.getClickedPos();
        Player player = pContext.getPlayer();
        if (player == null) {
            return InteractionResult.FAIL;
        }

        boolean foundBlock = false;

        for (int y_offset = 0; y_offset <= positionClicked.getY() + 64; y_offset++) {
            BlockState block = pContext.getLevel().getBlockState(positionClicked.below(y_offset));

            if (isValuableBlock(block)) {
                outputCoordinatesToChat(positionClicked.below(y_offset), player, block.getBlock());
                foundBlock = true;
                break;
            }
        }

        if (!foundBlock) {
            player.sendSystemMessage(Component.literal("Nothing found"));
        }

        damageItemDurability(pContext, player);

        return InteractionResult.SUCCESS;  // makes the right click animation work
    }

    private boolean isValuableBlock(BlockState block) {
        return block.is(VALUABLE_BLOCK);
    }

    private void outputCoordinatesToChat(BlockPos position, Player player, Block block) {
        player.sendSystemMessage(Component.literal(
                "Found %s at (%d, %d, %d)".formatted(I18n.get(block.getDescriptionId()), position.getX(), position.getY(), position.getZ())
        ));  // I18n: Internationalisation (18 letters between I and n)
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip." + ForgeTestModThree.MOD_ID + ".metal_detector"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    private static void damageItemDurability(UseOnContext pContext, Player player) {
        pContext.getItemInHand().hurtAndBreak(1, player, _player -> _player.broadcastBreakEvent(_player.getUsedItemHand()));
    }
}
