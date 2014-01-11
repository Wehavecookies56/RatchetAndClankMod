package com.gugu42.rcmod;

import com.gugu42.rcmod.gui.SlotVendShow;
import com.gugu42.rcmod.gui.SlotVendor;
import com.gugu42.rcmod.items.EnumRcWeapons;
import com.gugu42.rcmod.tileentity.TileEntityVendor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerVendor extends Container {

	protected TileEntityVendor tileEntity;
	private EnumRcWeapons weaps;

	public ContainerVendor(InventoryPlayer inventoryPlayer, TileEntityVendor te) {
		tileEntity = te;

		addSlotToContainer(new Slot(tileEntity, 1, 8, 18));
		addSlotToContainer(new SlotVendor(tileEntity, 2, 80, 100));
		addSlotToContainer(new SlotVendShow(tileEntity, 3, 80, 10));
		addSlotToContainer(new SlotVendor(tileEntity, 4, 98, 100));
		addSlotToContainer(new SlotVendor(tileEntity, 5, 116, 100));
		addSlotToContainer(new SlotVendor(tileEntity, 6, 134, 100));
		
		bindPlayerInventory(inventoryPlayer);
	}

	
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tileEntity.isUseableByPlayer(player);
	}

	protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
						8 + j * 18, (84 + 56) + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 198));
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		ItemStack stack = null;
		Slot slotObject = (Slot) inventorySlots.get(slot);

		// null checks and checks if the item can be stacked (maxStackSize > 1)
		if (slotObject != null && slotObject.getHasStack()) {
			ItemStack stackInSlot = slotObject.getStack();
			stack = stackInSlot.copy();

			// merges the item into player inventory since its in the tileEntity
			if (slot < 9) {
				if (!this.mergeItemStack(stackInSlot, 0, 35, true)) {
					return null;
				}
			}
			// places it into the tileEntity is possible since its in the player
			// inventory
			else if (!this.mergeItemStack(stackInSlot, 0, 9, false)) {
				return null;
			}

			if (stackInSlot.stackSize == 0) {
				slotObject.putStack(null);
			} else {
				slotObject.onSlotChanged();
			}

			if (stackInSlot.stackSize == stack.stackSize) {
				return null;
			}
			slotObject.onPickupFromSlot(player, stackInSlot);
		}
		return stack;
	}

	
    public TileEntityVendor getVendor()
    {
            return this.tileEntity;
    }

}
