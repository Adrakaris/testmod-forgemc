package com.bocbin.testmod.blocks;

import com.bocbin.testmod.tools.CustomEnergyStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.bocbin.testmod.blocks.ModBlocks.POTATOGENERATOR_TILE;

// implement INamedContainerProvider to se serverside
public class PotatoGeneratorTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    // caps use lazy optionals, which cache and postpone operations until theyre needed for the first time
    private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);
    // to produce power we need another cap for energy storage
    private LazyOptional<IEnergyStorage> energy = LazyOptional.of(this::createEnergy);

    private int counter;
    private boolean baked = false;

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
        if (counter > 0) {
            energy.ifPresent(e -> {
                // operation to chekc type of fuel
                handler.ifPresent(h -> {
                    ItemStack stack = h.getStackInSlot(0);
                    if (stack.getItem() == Items.BAKED_POTATO) {
                        baked = true;
                    } else { baked = false; }
                });
                // decision to generate more or less power
                if (baked) {
                    ((CustomEnergyStorage) e).addEnergy(40);
                } else {
                    ((CustomEnergyStorage) e).addEnergy(20);
                }
            });  // 20 RF/t, I suppose, maybe 40 for baked
            counter--;
        } else {
            handler.ifPresent(h -> {
                ItemStack stack = h.getStackInSlot(0);
                if (stack.getItem() == Items.POTATO || stack.getItem() == Items.POISONOUS_POTATO || stack.getItem() == Items.BAKED_POTATO) {
                    h.extractItem(0, 1, false);
                    counter = 200;
                }
            });
        }
    }

    // read and write to save inventory
    @Override
    public void read(CompoundNBT compound) {
        CompoundNBT invTag = compound.getCompound("inv");
        // h -> f(h) == lambda h: f(h)
        // () -> result == lambda: result
        // for items
        handler.ifPresent(h -> ((INBTSerializable<CompoundNBT>)h).deserializeNBT(invTag));
        createHandler().deserializeNBT(invTag);

        // for energy
        CompoundNBT energyTag = compound.getCompound("energy");  // because the energy tag is created in CustomEnergyStorage
        energy.ifPresent(h -> ((INBTSerializable<CompoundNBT>)h).deserializeNBT(energyTag));

        super.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        // for item
        handler.ifPresent(h -> {
            CompoundNBT comp = ((INBTSerializable<CompoundNBT>)h).serializeNBT();
            compound.put("inv", comp);
        });

        // for energy
        energy.ifPresent(h -> {
            CompoundNBT comp = ((INBTSerializable<CompoundNBT>)h).serializeNBT();
            compound.put("energy", comp);
        });

        return super.write(compound);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(1) {
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

    private IEnergyStorage createEnergy() {
        return new CustomEnergyStorage(200000, 0);
    }

    // we need capabilities to be able to us this as a generator
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        // a way to add stuff to items in minecraft, used for the inventory as well as power system
        // can also define own capabilites
        // side will be all sides, so null, and the capability will be ItemStackHandler from forge
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        // energy
        if (cap == CapabilityEnergy.ENERGY) {
            return energy.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("block.testmod.potato_generator.gui_name");
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new PotatoGeneratorContainer(i, world, pos, playerInventory, playerEntity);
    }
}
