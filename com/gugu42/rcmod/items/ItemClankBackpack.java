package com.gugu42.rcmod.items;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import com.gugu42.rcmod.render.ClankBackpackRender;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemClankBackpack extends ItemArmor {

	@SideOnly(Side.CLIENT)
	public ClankBackpackRender model;
	
	public ItemClankBackpack(int par1, EnumArmorMaterial par2EnumArmorMaterial,
			int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);
		
		//this.setCreativeTab(RcMod.rcTab);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot,
			String type) {
		return "rcmod:models/ClankBackpack.png";
	}
	
	@SideOnly(Side.CLIENT)
	public void initModel(){
		if(model == null){
			model = new ClankBackpackRender();
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving,
			ItemStack itemStack, int armorSlot) {
		initModel();
		return model;
	}

}
