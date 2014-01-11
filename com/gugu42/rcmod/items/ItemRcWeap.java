package com.gugu42.rcmod.items;

import net.minecraft.item.Item;

public class ItemRcWeap extends Item{

	protected int ammoPrice;
	
	public ItemRcWeap(int par1) {
		super(par1);
	}

	public int getPrice(){
		return ammoPrice;
	}
	
}
