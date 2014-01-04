package com.gugu42.rcmod.weapons;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.weapons.ammo.EntityBlasterAmmo;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBlaster extends Item {

	public int ammo;
	public int maxAmmo;
	public int cooldown;

	public ItemBlaster(int par1) {
		super(par1);
		this.ammo = 100;
		this.maxAmmo = 100;
		this.cooldown = 0;
		this.setMaxDamage(maxAmmo);
		this.setCreativeTab(RcMod.rcTab);
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		if (maxAmmo - par1ItemStack.getItemDamage() > 0) {
			if (!par2World.isRemote) {
				EntityBlasterAmmo bullet = new EntityBlasterAmmo(
						par2World, par3EntityPlayer, this, 0.1F, 1.0F, 1F, 0f,
						0f);
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

	public void onUpdate(ItemStack stack, World w, Entity ent, int i,
			boolean flag) {

	}

	public void onPlayerStoppedUsing(ItemStack stack, World w,
			EntityPlayer player, int i) {
	}
}
