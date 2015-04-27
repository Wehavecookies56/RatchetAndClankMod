package com.gugu42.rcmod.items;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

public class ItemRcGun extends ItemRcWeap {
	public int id;

	public void setID(int ID) {
		this.id = ID;
	}

	public int getID() {
		return this.id;
	}

	public boolean refill(ItemStack stack, ItemRcGun thisthing,
			EntityItemPickupEvent event, int ammotogive) {
		if (stack.getItemDamage() != 0) {
			for (int i = 0; i < 10; i++) {
				if (stack.getItemDamage() != stack.getMaxDamage())
					stack.setItemDamage(stack.getItemDamage() - 1);
			}
			return true;
		} else {
			return false;
		}

	}
}
