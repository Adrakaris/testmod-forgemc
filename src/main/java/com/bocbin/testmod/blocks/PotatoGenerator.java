package com.bocbin.testmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class PotatoGenerator extends Block {
    public PotatoGenerator() {
        super(Properties.create(Material.IRON)
                .hardnessAndResistance(2.0f)
                .sound(SoundType.METAL)
        );
        setRegistryName("potato_generator");
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        // tries to place the block, and try use the getFacingFromPlacer to determine what direction its facing
        if (placer != null) {
            worldIn.setBlockState(pos, state.with(BlockStateProperties.FACING, getFacingFromPlacer(pos, placer)), 2);
        }
    }

    public static Direction getFacingFromPlacer(BlockPos clickedBlock, LivingEntity placer) {
        return Direction.getFacingFromVector(  // using a provided method from direction
                (float) (placer.posX - clickedBlock.getX()), // (float) (...) is casting in java
                (float) (placer.posY - clickedBlock.getY()),
                (float) (placer.posZ - clickedBlock.getZ())
        );
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public int getLightValue(BlockState state) {
        return state.get(BlockStateProperties.POWERED) ? 15 : super.getLightValue(state);  // only give off light of 15 if powered
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new PotatoGeneratorTile();
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);  // technically not needed since parent is empty
        builder.add(BlockStateProperties.FACING, BlockStateProperties.POWERED);
        // BlockStateProperties contains properties already used by vanilla minecraft
        // FACING is what direction the block is facing
    }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {  // check if on server side
            // container should activate on server side
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof INamedContainerProvider) {
                NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity, tileEntity.getPos());
            }
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }
}
