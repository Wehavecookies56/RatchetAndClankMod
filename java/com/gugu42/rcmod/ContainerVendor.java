package com.gugu42.rcmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.gugu42.rcmod.gui.SlotVendShow;
import com.gugu42.rcmod.gui.SlotVendor;
import com.gugu42.rcmod.tileentity.TileEntityVendor;

public class ContainerVendor extends Container {

	protected TileEntityVendor tileEntity;
	public ContainerVendor(InventoryPlayer inventoryPlayer, TileEntityVendor te) {
		tileEntity = te;

//		addSlotToContainer(new Slot(tileEntity, 1, 8, 18));
		
//		addSlotToContainer(new SlotVendShow(tileEntity, 3, 80, 10));
		addSlotToContainer(new SlotVendor(tileEntity, 1, 48, 173)); 
		addSlotToContainer(new SlotVendor(tileEntity, 2, 66, 173)); 
		addSlotToContainer(new SlotVendor(tileEntity, 3, 84, 173)); 
		addSlotToContainer(new SlotVendor(tileEntity, 4, 102, 173)); 
		addSlotToContainer(new SlotVendor(tileEntity, 5, 120, 173)); 
		addSlotToContainer(new SlotVendor(tileEntity, 6, 138, 173)); 
		addSlotToContainer(new SlotVendor(tileEntity, 7, 156, 173)); 
		addSlotToContainer(new SlotVendor(tileEntity, 8, 174, 173)); 
		addSlotToContainer(new SlotVendor(tileEntity, 9, 192, 173)); 
		
//		bindPlayerInventory(inventoryPlayer);
	}

	
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tileEntity.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
        ItemStack stack = null;
        Slot slotObject = (Slot) inventorySlots.get(slot);

        if (slotObject != null && slotObject.getHasStack()) {
                ItemStack stackInSlot = slotObject.getStack();
                stack = stackInSlot.copy();
                
                if (slot < 1) {
                    if (!this.mergeItemStack(stackInSlot, 5, 42, true)) {
                            return null;
                    }
                }
                
                else if (!this.mergeItemStack(stackInSlot, 0, 1, false)) {
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
