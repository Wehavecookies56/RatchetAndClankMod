package com.gugu42.rcmod.items;

import com.gugu42.rcmod.entity.projectiles.EntityBombGloveAmmo;
import com.gugu42.rcmod.entity.projectiles.EntityRYNOAmmo;
import com.gugu42.rcmod.entity.projectiles.EntityVisibombAmmo;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemVisibombGun extends ItemRcWeap
{
	private int cooldown;

	public ItemVisibombGun() {
		super();
		this.ammoPrice = 100;
		this.maxAmmo = 20;
		this.weaponName = "visibomb";
		this.hasAmmoImage = true;
		this.hideCrosshair = true;
		this.heldType = 1;
		this.setMaxDamage(maxAmmo);
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		if (maxAmmo - par1ItemStack.getItemDamage() > 0) {
			if (!par2World.isRemote) {
				if (cooldown <= 0) {
					par2World.spawnEntityInWorld(new EntityVisibombAmmo(par2World, par3EntityPlayer));
					par1ItemStack.damageItem(1, par3EntityPlayer);
					cooldown = 200;
					par3EntityPlayer.swingItem();
				}
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
	
	public void onUpdate(ItemStack par1ItemStack, World par2World,Entity par3Entity, int par4, boolean par5) 
	{
		if (cooldown >= 1) {
			cooldown--;
		}
	}
}
