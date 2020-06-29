package com.bocbin.testmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

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

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new PotatoGeneratorTile();
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);  // technically not needed since parent is empty
        builder.add(BlockStateProperties.FACING);
        // BlockStateProperties contains properties already used by vanilla minecraft
        // FACING is what direction the block is facing
    }
}
