package com.gugu42.rcmod.items;

import com.gugu42.rcmod.RcMod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGadgetronHelper extends Item {

	public ItemGadgetronHelper() {
		super();
		this.setCreativeTab(RcMod.rcGadgTab);
		this.setMaxStackSize(1);
		this.setFull3D();
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world,
			EntityPlayer player) {
		player.openGui(RcMod.instance, 3, player.worldObj, 0, 0, 0);

		return stack;
	}

}
