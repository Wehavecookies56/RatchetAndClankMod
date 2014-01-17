package com.gugu42.rcmod.items;

import com.gugu42.rcmod.entity.EntityRYNOAmmo;
import com.gugu42.rcmod.handler.ExtendedEntityLivingBaseTarget;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemRYNO extends ItemRcWeap {

	public ItemRYNO(int par1) {
		super(par1);
		this.ammoPrice = 20;
		this.maxAmmo = 50;
		this.heldType = 1;
		this.setMaxDamage(maxAmmo);
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F,
				0.4F / (Item.itemRand.nextFloat() * 0.4F + 0.8F));

		if (!par2World.isRemote) {
			
			EntityRYNOAmmo ent1 = new EntityRYNOAmmo(par2World,
					par3EntityPlayer);
			par2World.spawnEntityInWorld(ent1);
			
		}

		if (!par3EntityPlayer.capabilities.isCreativeMode) {
			if (maxAmmo - par1ItemStack.getItemDamage() > 0) {
				par1ItemStack.damageItem(1, par3EntityPlayer);
			}
		}

		return par1ItemStack;
	}

}
