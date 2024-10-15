package hu.yijun.forgetestmodthree.block.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;

@SuppressWarnings("deprecation")
public class SoundBlock extends Block {

    private final Random random = new Random();

    public SoundBlock(Properties pProperties) {
        super(pProperties);
    }


    @Override  // NOTE: Block and BlockBehaviour use @deprecated to indicate they are not called, but can be overridden safely for some reason
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        pLevel.playSound(pPlayer, pPos, SoundEvents.NOTE_BLOCK_DIDGERIDOO.get(), SoundSource.BLOCKS, 1f, random.nextFloat(0.5f, 1.5f));

        return InteractionResult.SUCCESS;
    }
}
