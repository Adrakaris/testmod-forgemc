package com.bocbin.testmod.blocks;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.bocbin.testmod.blocks.ModBlocks.POTATOGENERATOR_TILE;

public class PotatoGeneratorTile extends TileEntity implements ITickableTileEntity {

    private ItemStackHandler handler;

    public PotatoGeneratorTile() {
        // require a type
        super(POTATOGENERATOR_TILE);
    }

    // TickableTileEntity function which will activate twice every tick, once on client and once on server
    @Override
    public void tick() {
        // section to check if the tile entity exists or not
        /* assert world != null;
        if (world.isRemote) {
            System.out.println("PotatoGenerator.tick");
        } */
    }

    // read and write to save inventory
    @Override
    public void read(CompoundNBT compound) {
        CompoundNBT invTag = compound.getCompound("inv");
        getHandler().deserializeNBT(invTag);
        super.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        CompoundNBT comp = getHandler().serializeNBT();
        compound.put("inv", comp);
        return super.write(compound);
    }

    private ItemStackHandler getHandler() {
        if (handler == null) {
            handler = new ItemStackHandler(1) {
                @Override
                public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                    return stack.getItem() == Items.POTATO || stack.getItem() == Items.BAKED_POTATO || stack.getItem() == Items.POISONOUS_POTATO;
                }

                @Nonnull
                @Override
                public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                    if (stack.getItem() != Items.POTATO && stack.getItem() != Items.BAKED_POTATO && stack.getItem() != Items.POISONOUS_POTATO) {
                        return stack;  // do not insert stack or modify it
                    }
                    return super.insertItem(slot, stack, simulate);
                }
            };
        }
        return handler;
    }

    // we need capabilities to be able to us this as a generator
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        // a way to add stuff to items in minecraft, used for the inventory as well as power system
        // can also define own capabilites
        // side will be all sides, so null, and the capability will be ItemStackHandler from forge
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            // caps use lazy optionals, which cache and postpone operations until theyre needed for the first time
            return LazyOptional.of(() -> (T) getHandler());
        }
        return super.getCapability(cap, side);
    }
}
