package com.gugu42.rcmod.items;

import com.gugu42.rcmod.entity.projectiles.EntityBombGloveAmmo;
import com.gugu42.rcmod.entity.projectiles.EntitySwingShotHook;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSwingShot extends ItemRcWeap {
	
	public int cooldown = 90;
	
	public ItemSwingShot(){
		super();
		this.useAmmo = false;
		this.hasCrosshair = true;
		this.weaponName = "swingShot";
		this.setMaxStackSize(1);
		this.hasEquipSound = true;
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
			if (!par2World.isRemote) {
				if (cooldown <= 0) {
					EntitySwingShotHook hook = new EntitySwingShotHook(par2World, par3EntityPlayer);
					par2World.spawnEntityInWorld(hook);
					cooldown = 90;
					par3EntityPlayer.swingItem();
					par3EntityPlayer.worldObj.playSoundAtEntity(par3EntityPlayer, "rcmod:SwingShotShoot", 1.0f, 1.0f);
				}
			}
		return par1ItemStack;
	}
	
	public void onUpdate(ItemStack stack, World w, Entity ent, int i,
			boolean flag) {
		super.onUpdate(stack, w, ent, i, flag);
		if (cooldown > 0) {
			cooldown--;
		}
	}

	public boolean canHarvestBlock(Block par1Block) {
		return false;
	}

	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
		return 0.0f;
	}
	
}
