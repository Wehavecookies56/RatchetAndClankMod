package com.gugu42.rcmod.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.gugu42.rcmod.entity.projectiles.EntityBombGloveAmmo;

public class ItemTaunter extends ItemRcWeap{

	public ItemTaunter() {
		super();
		this.useAmmo = false;
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
		par2World.playSoundAtEntity(par3EntityPlayer, "rcmod:TaunterSound", 1.0f, 1.0f);
		return par1ItemStack;
	}
}
