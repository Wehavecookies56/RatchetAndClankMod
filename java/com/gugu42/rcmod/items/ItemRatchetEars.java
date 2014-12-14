package com.gugu42.rcmod.items;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.render.armor.RenderRatchetEars_Armor;

public class ItemRatchetEars extends ItemArmor {

	@SideOnly(Side.CLIENT)
	public RenderRatchetEars_Armor model;
	
	public ItemRatchetEars(ArmorMaterial par2EnumArmorMaterial,
			int par3, int par4) {
		super(par2EnumArmorMaterial, par3, par4);
		
		this.setCreativeTab(RcMod.rcTab);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot,
			String type) {
		return "rcmod:models/Ratchet_ears.png";
	}
	
	@SideOnly(Side.CLIENT)
	public void initModel(){
		if(model == null){
			model = new RenderRatchetEars_Armor();
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