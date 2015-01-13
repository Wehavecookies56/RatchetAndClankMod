package com.gugu42.rcmod.items;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTeslaClaw extends ItemRcGun{

	public ItemTeslaClaw() {
		super();
		this.weaponName = "teslaClaw";
		this.hasAmmoImage = true;
		this.heldType = 1;
		this.hasEquipSound = true;
	}
	
	@Override
	public void onUpdate(ItemStack stack, World w, Entity ent, int i,
			boolean flag) {
		super.onUpdate(stack, w, ent, i, flag);
	}

}
