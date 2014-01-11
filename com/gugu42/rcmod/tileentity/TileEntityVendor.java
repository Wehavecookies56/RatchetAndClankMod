package com.gugu42.rcmod.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityVendor extends TileEntity implements IInventory {

	private ItemStack[] inv;

	public TileEntityVendor() {
		inv = new ItemStack[9];
	}

	@Override
	public int getSizeInventory() {

		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return inv[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
        ItemStack stack = getStackInSlot(i);
        if (stack != null) {
                if (stack.stackSize <= j) {
                        setInventorySlotContents(i, null);
                } else {
                        stack = stack.splitStack(j);
                        if (stack.stackSize == 0) {
                                setInventorySlotContents(i, null);
                        }
                }
        }
        return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
        ItemStack stack = getStackInSlot(i);
        if (stack != null) {
                setInventorySlotContents(i, null);
        }
        return stack;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
        inv[i] = itemstack;
        if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
                itemstack.stackSize = getInventoryStackLimit();
        }    
	}

	@Override
	public String getInvName() {
		// TODO Auto-generated method stub
		return "rcmod.tileentityvendor";
	}

	@Override
	public boolean isInvNameLocalized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this &&
        entityplayer.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
	}

	@Override
	public void openChest() {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeChest() {
		// TODO Auto-generated method stub

	}
	
	@Override
    public void readFromNBT(NBTTagCompound tagCompound) {
            super.readFromNBT(tagCompound);
            
            NBTTagList tagList = tagCompound.getTagList("Inventory");
            for (int i = 0; i < tagList.tagCount(); i++) {
                    NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
                    byte slot = tag.getByte("Slot");
                    if (slot >= 0 && slot < inv.length) {
                            inv[slot] = ItemStack.loadItemStackFromNBT(tag);
                    }
            }
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
            super.writeToNBT(tagCompound);
                            
            NBTTagList itemList = new NBTTagList();
            for (int i = 0; i < inv.length; i++) {
                    ItemStack stack = inv[i];
                    if (stack != null) {
                            NBTTagCompound tag = new NBTTagCompound();
                            tag.setByte("Slot", (byte) i);
                            stack.writeToNBT(tag);
                            itemList.appendTag(tag);
                    }
            }
            tagCompound.setTag("Inventory", itemList);
    }

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return false;
	}

}
