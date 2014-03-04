package com.gugu42.rcmod.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.gugu42.rcmod.entity.EntityPyrocitorAmmo;

public class ItemPyrocitor extends ItemRcWeap {

	public ItemPyrocitor(int par1) {
		super(par1);
		this.setFull3D();
		this.ammoPrice = 1;
		this.maxAmmo = 240;
		this.heldType = 1;
		this.setMaxDamage(maxAmmo);
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		if (maxAmmo - par1ItemStack.getItemDamage() > 0) {
			if (!par2World.isRemote) {
				EntityPyrocitorAmmo flame = new EntityPyrocitorAmmo(par2World,
						par3EntityPlayer);
				par2World.spawnEntityInWorld(flame);
				EntityPyrocitorAmmo flame2 = new EntityPyrocitorAmmo(par2World,
						par3EntityPlayer);
				par2World.spawnEntityInWorld(flame2);
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
