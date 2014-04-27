package com.gugu42.rcmod.items;

import com.gugu42.rcmod.entity.projectiles.EntityRYNOAmmo;
import com.gugu42.rcmod.handler.ExtendedEntityLivingBaseTarget;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemRYNO extends ItemRcWeap {

	private boolean hasFired = false;
	private int fireCount = 0;
	private int cooldown;

	public ItemRYNO() {
		super();
		this.ammoPrice = 20;
		this.maxAmmo = 50;
		this.weaponName = "ryno";
		this.hasAmmoImage = true;
		this.heldType = 1;
		this.setMaxDamage(maxAmmo);
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F,
				0.4F / (Item.itemRand.nextFloat() * 0.4F + 0.8F));

		if (!par2World.isRemote) {
			if (cooldown <= 0) {
				fireCount = 27;
				hasFired = true;
				cooldown = 120;
				if (!par3EntityPlayer.capabilities.isCreativeMode) {
					if (maxAmmo - par1ItemStack.getItemDamage() > 0) {
						par1ItemStack.damageItem(1, par3EntityPlayer);
					}
				}
			}
		}

		return par1ItemStack;
	}

	public void onUpdate(ItemStack par1ItemStack, World par2World,
			Entity par3Entity, int par4, boolean par5) {
		if (cooldown >= 1) {
			cooldown--;
		}

		if (hasFired) {
			EntityPlayer par3EntityPlayer = (EntityPlayer) par3Entity;
			if (fireCount >= 1) {

				fireCount--;
				if (fireCount == 27) {
					fireRocket(par2World, par3EntityPlayer); // 1
				} else if (fireCount == 24) {
					fireRocket(par2World, par3EntityPlayer); // 2
				} else if (fireCount == 21) {
					fireRocket(par2World, par3EntityPlayer); // 3
				} else if (fireCount == 18) {
					fireRocket(par2World, par3EntityPlayer); // 4
				} else if (fireCount == 15) {
					fireRocket(par2World, par3EntityPlayer); // 5
				} else if (fireCount == 12) {
					fireRocket(par2World, par3EntityPlayer); // 6
				} else if (fireCount == 9) {
					fireRocket(par2World, par3EntityPlayer); // 7
				} else if (fireCount == 6) {
					fireRocket(par2World, par3EntityPlayer); // 8
				} else if (fireCount == 3) {
					fireRocket(par2World, par3EntityPlayer); // 9
				}

				if (fireCount == 0) {
					hasFired = false;
				}
			}

		}
	}

	public void fireRocket(World world, EntityPlayer player) {
		EntityRYNOAmmo rocket = new EntityRYNOAmmo(world, player);
		world.spawnEntityInWorld(rocket);
	}

}
