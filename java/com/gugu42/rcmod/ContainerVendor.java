package com.gugu42.rcmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.gugu42.rcmod.gui.SlotVendor;
import com.gugu42.rcmod.items.InventoryGadgetronPDA;
import com.gugu42.rcmod.tileentity.TileEntityVendor;

public class ContainerVendor extends Container {

	protected TileEntityVendor tileEntity;
	protected InventoryGadgetronPDA inv;
	public ContainerVendor(InventoryPlayer inventoryPlayer, TileEntityVendor te, InventoryGadgetronPDA inv) {
		tileEntity = te;
		this.inv = inv;
		if(inv != null){
			addSlotToContainer(new SlotVendor(inv, 1, 48, 173)); 
			addSlotToContainer(new SlotVendor(inv, 2, 66, 173)); 
			addSlotToContainer(new SlotVendor(inv, 3, 84, 173)); 
			addSlotToContainer(new SlotVendor(inv, 4, 102, 173)); 
			addSlotToContainer(new SlotVendor(inv, 5, 120, 173)); 
			addSlotToContainer(new SlotVendor(inv, 6, 138, 173)); 
			addSlotToContainer(new SlotVendor(inv, 7, 156, 173)); 
			addSlotToContainer(new SlotVendor(inv, 8, 174, 173)); 
			addSlotToContainer(new SlotVendor(inv, 9, 192, 173)); 
		} else {
			addSlotToContainer(new SlotVendor(tileEntity, 1, 48, 173)); 
			addSlotToContainer(new SlotVendor(tileEntity, 2, 66, 173)); 
			addSlotToContainer(new SlotVendor(tileEntity, 3, 84, 173)); 
			addSlotToContainer(new SlotVendor(tileEntity, 4, 102, 173)); 
			addSlotToContainer(new SlotVendor(tileEntity, 5, 120, 173)); 
			addSlotToContainer(new SlotVendor(tileEntity, 6, 138, 173)); 
			addSlotToContainer(new SlotVendor(tileEntity, 7, 156, 173)); 
			addSlotToContainer(new SlotVendor(tileEntity, 8, 174, 173)); 
			addSlotToContainer(new SlotVendor(tileEntity, 9, 192, 173)); 
		}
	}

	
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		if(tileEntity != null){
		return tileEntity.isUseableByPlayer(player);
		} else {
			return true;
		}
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
