package com.gugu42.rcmod.items;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemRcWeap extends Item{

	protected int ammoPrice;
	protected int maxAmmo;
	protected int heldType;
	public boolean useAmmo;
	public boolean useTargetingSystem;
	
	public String weaponName;
	
	/**
	 * Used to say that the item uses its own crosshair. No need to use hideCrossair if using this.
	 */
	public boolean hasCrosshair;
	public String crosshairPath;
	
	/**
	 * Used to hide the vanilla crosshair, without using a crosshair ( Pyrocitor )
	 */
	public boolean hideCrosshair;
	
	
	public boolean hasAmmoImage;
	public String ammoTexturePath;
	


	public ItemRcWeap(int par1) {
		super(par1);
		this.heldType = 0;
		this.setMaxDamage(maxAmmo);
		this.maxStackSize = 1;
		this.useAmmo = true;
		this.useTargetingSystem = false;
		this.hasCrosshair = false;
		this.hideCrosshair = false;
		this.hasAmmoImage = false;
	}

	public int getPrice(){
		return ammoPrice;
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
	
	public String getAmmoImageTexturePath(){
		if(this.hasAmmoImage){
			return "textures/gui/ammoImage/ammoImage_" + this.weaponName + ".png";
		} else {
			return null;
		}
	}
	
	public String getCrosshairImagePath(){
		if(this.hasCrosshair){
			 return "textures/gui/crosshair/crosshair_" + this.weaponName + ".png";
		} else {
			return null;
		}
	}
	
	/**
	 * This method returns an int
	 * If it returns 0, the item is held normally
	 * If it returns 1, the item is held like a bow
	 * @return the held type
	 */
	public int getHeldType(){
		return heldType;
	}
	
	public boolean isUsingAmmo() {
		return useAmmo;
	}
}
