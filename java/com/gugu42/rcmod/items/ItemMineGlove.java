package com.gugu42.rcmod.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.entity.projectiles.EntityMineGloveAmmo;

public class ItemMineGlove extends ItemRcGun {

	public int ammo;
	public int maxAmmo;
	public int cooldown;

	public ItemMineGlove() {
		super();
		this.weaponName = "mineGlove";
		this.hasAmmoImage = true;
		this.ammo = 50;
		this.maxAmmo = 50;
		this.ammoPrice = 5;
		this.cooldown = 20;
		this.setMaxDamage(maxAmmo);
		this.maxStackSize = 1;
		this.setCreativeTab(RcMod.rcTab);

	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
		if (maxAmmo - par1ItemStack.getItemDamage() > 0) {
			if (!par2World.isRemote) {
				if (cooldown <= 0) {
					EntityMineGloveAmmo mine = new EntityMineGloveAmmo(
							par2World, par3EntityPlayer);
					par2World.spawnEntityInWorld(mine);
					par1ItemStack.damageItem(1, par3EntityPlayer);
					cooldown = 60;
					par3EntityPlayer.swingItem();
				}
			}
		}
		return par1ItemStack;
	}

	public void onUpdate(ItemStack stack, World w, Entity ent, int i,
			boolean flag) {
		if (cooldown > 0) {
			cooldown--;
		}

	}

}
