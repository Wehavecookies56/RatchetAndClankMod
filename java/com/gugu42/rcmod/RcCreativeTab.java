package com.gugu42.rcmod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RcCreativeTab extends CreativeTabs {

	public Item icon;

	public RcCreativeTab(int par1, String par2Str) {
		super(par1, par2Str);
	}

	public RcCreativeTab(String par2Str) {
		super(getNextID(), par2Str);
	}

	@SideOnly(Side.CLIENT)
	public void setTabIconItem(Item icon1) {
		this.icon = icon1;
	}

	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return icon;
	}

}
