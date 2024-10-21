package hu.yijun.forgetestmodthree.item.items;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeHooks;

import static hu.yijun.forgetestmodthree.util.SetBlockFlags.BLOCK_UPDATE;
import static hu.yijun.forgetestmodthree.util.SetBlockFlags.SEND_TO_CLIENT;

public class SapphireStaffItem extends Item {
    private static final double MAX_RANGE = 32;

    public SapphireStaffItem() {
        super(new Item.Properties().defaultDurability(250));  // note: unable to have durability and use .stacksTo()
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack thisItem = pPlayer.getItemInHand(pUsedHand);

        HitResult lookingAt = ProjectileUtil.getHitResultOnViewVector(pPlayer, entity -> !entity.isSpectator() && entity.isPickable(), MAX_RANGE);
        if (lookingAt.getType() != HitResult.Type.BLOCK) {
            return InteractionResultHolder.fail(thisItem);
        }

        Vec3 raycastHit = lookingAt.getLocation().add(pPlayer.getLookAngle().scale(0.02));
        BlockPos blockPosition = BlockPos.containing(raycastHit);
        BlockState blockLookedAt = pLevel.getBlockState(blockPosition);
        Block block = blockLookedAt.getBlock();

        if (!ForgeHooks.canEntityDestroy(pLevel, blockPosition, pPlayer) || block.defaultDestroyTime() < 0) {
            return InteractionResultHolder.fail(thisItem);
        }

        if (pLevel.isClientSide) {
            block.playerWillDestroy(pLevel, blockPosition, blockLookedAt, pPlayer);  // playerWillDestroy plays the particles so should be clientside
        } else {
            block.playerDestroy(pLevel, pPlayer, blockPosition, blockLookedAt, null, thisItem);
            damageItemDurability(thisItem, pLevel, pPlayer, pUsedHand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
            pLevel.setBlock(blockPosition, Blocks.AIR.defaultBlockState(), BLOCK_UPDATE | SEND_TO_CLIENT);
        }

        return InteractionResultHolder.success(thisItem);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        return InteractionResult.PASS;
    }

    private static void damageItemDurability(ItemStack itemStack, Level level, LivingEntity livingEntity, EquipmentSlot hand) {
        if (!level.isClientSide) {
            itemStack.hurtAndBreak(1, livingEntity, (entity) -> entity.broadcastBreakEvent(hand));
        }
    }
}
