package com.gugu42.rcmod.items;

import net.minecraft.item.Item;

public class ItemRcWeap extends Item{

	protected int ammoPrice;
	protected int maxAmmo;
	protected int heldType;
	
	public ItemRcWeap(int par1) {
		super(par1);
		this.heldType = 0;
	}

	public int getPrice(){
		return ammoPrice;
	}
	
	/**
	 * This method returns an int
	 * If it returns 0, the item is held normally
	 * If it returns 1, the item is held like a bow
	 * @return the held type
	 */
	public int getHeldType(){
		return heldType;
	}
	
}
