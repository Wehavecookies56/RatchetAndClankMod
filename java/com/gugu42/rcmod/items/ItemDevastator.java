package com.gugu42.rcmod.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDevastator extends ItemRcWeap {

	public ItemDevastator() {
		super();
		this.useTargetingSystem = true;
		this.useAmmo = true;
		this.maxAmmo = 15;
		this.setMaxDamage(this.maxAmmo);
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		if (maxAmmo - par1ItemStack.getItemDamage() > 0) {
			if (!par2World.isRemote) {
				
			}
		}
		return par1ItemStack;
	}

}
