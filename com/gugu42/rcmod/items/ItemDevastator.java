package com.gugu42.rcmod.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.gugu42.rcmod.entity.projectiles.EntityVisibombAmmo;
import com.gugu42.rcmod.testing.TargetingSystem;

public class ItemDevastator extends ItemRcWeap{

	public ItemDevastator(int par1) {
		super(par1);
		this.useTargetingSystem = true;
		this.useAmmo = true;
		this.maxAmmo = 15;
		this.setMaxDamage(this.maxAmmo);
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		if (maxAmmo - par1ItemStack.getItemDamage() > 0) {
			if (!par2World.isRemote) {
				TargetingSystem.target(par2World, par3EntityPlayer, 40.0d);
			}
		}
		return par1ItemStack;
	}

}
