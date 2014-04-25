package com.gugu42.rcmod.items;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.entity.projectiles.EntityBombGloveAmmo;

public class ItemBombGlove extends ItemRcWeap {

	public int ammo;
	public int maxAmmo;
	public int cooldown;
	
	public ItemBombGlove(int par1) {
		super(par1);
		this.ammo = 40;
		this.maxAmmo = 40;
		this.weaponName = "bombGlove";
		this.hasAmmoImage = true;
		this.ammoPrice = 5;
		this.cooldown = 10;
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
					EntityBombGloveAmmo bomb = new EntityBombGloveAmmo(
							par2World, par3EntityPlayer, this, 0.1F, 1.0F, 1F,
							0f, 0f, par3EntityPlayer);
					par2World.spawnEntityInWorld(bomb);
					par1ItemStack.damageItem(1, par3EntityPlayer);
					System.out.println(cooldown);
					cooldown = 60;
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

	public void onUpdate(ItemStack stack, World w, Entity ent, int i,
			boolean flag) {
		if (cooldown > 0) {
			cooldown--;
		}

	}

	public void onPlayerStoppedUsing(ItemStack stack, World w,
			EntityPlayer player, int i) {
	}
}
