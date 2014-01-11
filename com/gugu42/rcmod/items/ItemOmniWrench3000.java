package com.gugu42.rcmod.items;

import com.gugu42.rcmod.RcMod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.DamageSource;

public class ItemOmniWrench3000 extends Item {

	public ItemOmniWrench3000(int par1) {
		super(par1);
		this.setCreativeTab(RcMod.rcTab);
	}

	public boolean hitEntity(ItemStack stack, EntityLivingBase entityTarget,
			EntityLivingBase entitySource) {
		entityTarget
				.attackEntityFrom(DamageSource
						.causePlayerDamage((EntityPlayer) entitySource), 6f);
		return true;
	}

	
    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }
}
