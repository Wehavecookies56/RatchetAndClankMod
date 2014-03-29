package com.gugu42.rcmod.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.gugu42.rcmod.entity.EntityBlasterAmmo;

public class ItemBlaster extends ItemRcWeap {

	public int ammo;
	public int maxAmmo;
	public int cooldown;
	
	public boolean isInGold;

	public ItemBlaster(int par1) {
		super(par1);
		this.ammo = 250;
		this.maxAmmo = 250;
		this.ammoPrice = 1;
		this.cooldown = 0;
		this.heldType = 1;
		this.setMaxDamage(maxAmmo);
		this.maxStackSize = 1;
		this.isInGold = false;
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
		if (maxAmmo - par1ItemStack.getItemDamage() > 0) {
			if (!par2World.isRemote) {
				EntityBlasterAmmo bullet = new EntityBlasterAmmo(
						par2World, par3EntityPlayer);
				par2World.spawnEntityInWorld(bullet);
				par1ItemStack.damageItem(1, par3EntityPlayer);
			}
		}
		return par1ItemStack;
	}

	public boolean canHarvestBlock(Block par1Block) {
		return false;
	}

	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
		return 0.0f;
	}

	public void onPlayerStoppedUsing(ItemStack stack, World w,
			EntityPlayer player, int i) {
	}
}
