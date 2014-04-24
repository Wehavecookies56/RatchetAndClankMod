package com.gugu42.rcmod.items;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.entity.projectiles.EntityWrenchThrown;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ItemOmniWrench3000 extends Item {

	public ItemOmniWrench3000(int par1) {
		super(par1);
		this.maxStackSize = 1;
		this.setCreativeTab(RcMod.rcTab);
	}

	public boolean hitEntity(ItemStack stack, EntityLivingBase entityTarget,
			EntityLivingBase entitySource) {
		entityTarget
				.attackEntityFrom(DamageSource
						.causePlayerDamage((EntityPlayer) entitySource), 6f);

		return true;
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		if (par3EntityPlayer.isSneaking()) {
			par2World.spawnEntityInWorld(new EntityWrenchThrown(par2World,
					par3EntityPlayer, par1ItemStack));
			--par1ItemStack.stackSize;
		}

		return par1ItemStack;
	}

	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}

	public void removeItem(EntityPlayer ep, ItemStack removeitem) {
		IInventory inv = ep.inventory;
		for (int i = 0; i < inv.getSizeInventory(); i++) {
			if (inv.getStackInSlot(i) != null) {
				ItemStack j = inv.getStackInSlot(i);
				if (j.getItem() != null && j.getItem() == removeitem.getItem()) {
					inv.setInventorySlotContents(i, null);
				}
			}
		}
	}
}
