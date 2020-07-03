package com.bocbin.testmod.blocks;

import com.bocbin.testmod.tools.CustomEnergyStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import static com.bocbin.testmod.blocks.ModBlocks.POTATOGENERATOR_CONTAINER;

public class PotatoGeneratorContainer extends Container {

    private TileEntity tileEntity;
    private PlayerEntity playerEntity;
    private IItemHandler playerInventory;

    public PotatoGeneratorContainer(int id, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        super(POTATOGENERATOR_CONTAINER, id);
        this.tileEntity = world.getTileEntity(pos);
        this.playerEntity = playerEntity;
        this.playerInventory = new InvWrapper(playerInventory);

        // need to add slots

        this.tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(
                h -> {
                    addSlot(new SlotItemHandler(h, 0, 80, 34));
                });

        layoutPlayerInventorySlots(8, 84);

        // to keep track of energy as a number
        trackInt(new IntReferenceHolder() {
            @Override
            public int get() {
                return getEnergy();
            }

            @Override
            public void set(int value) {
                tileEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(h -> ((CustomEnergyStorage)h).setEnergy(value));
            }
        });
    }

    public int getEnergy() {
        return tileEntity.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), playerEntity, ModBlocks.POTATOGENERATOR);
    }

    /* THIS IS ESSENTIAL SUCH THAT SHIFT-CLICKING DOES NOT CRASH THE GAME */
    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            itemStack = stack.copy();
            if (index == 0) {
                if (!this.mergeItemStack(stack, 1, 37, true)) {  // 1 and 37 are player inventory slots
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(stack, itemStack);
            } else {
                if (stack.getItem() == Items.POTATO || stack.getItem() == Items.BAKED_POTATO || stack.getItem() == Items.POISONOUS_POTATO) {
                    if (!this.mergeItemStack(stack, 0, 1, true)) {  // [0, 1) are the item slot we want
                        return ItemStack.EMPTY;
                    }
                } else if (index < 28) {
                    if (!this.mergeItemStack(stack, 28, 37, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 37 && !this.mergeItemStack(stack, 1, 28, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (stack.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, stack);
        }

        return itemStack;
    }

    // adding slots and stuff
    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBlox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        // player inventory
        addSlotBlox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // hotbar
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }
}
