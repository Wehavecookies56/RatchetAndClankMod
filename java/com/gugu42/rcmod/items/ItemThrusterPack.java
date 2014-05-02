package com.gugu42.rcmod.items;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.render.armor.ThrusterPackRender;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemThrusterPack extends ItemArmor {

	@SideOnly(Side.CLIENT)
	public ThrusterPackRender model;

	public ItemThrusterPack(ArmorMaterial p_i45325_1_, int p_i45325_2_,
			int p_i45325_3_) {
		super(p_i45325_1_, p_i45325_2_, p_i45325_3_);
		this.setCreativeTab(RcMod.rcTab);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot,
			String type) {
		return "rcmod:models/ThrusterPack.png";
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		if (model == null) {
			model = new ThrusterPackRender();
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving,
			ItemStack itemStack, int armorSlot) {
		initModel();
		return model;
	}

	public void onArmorTick(World world, EntityPlayer player,
			ItemStack itemstack) {
		if (player != null) {
			if (player.getCurrentArmor(2).getItem() == this) {
				if (player.getEntityData().getBoolean("clankJumped")) {
					double x = player.posX -= 0.15d;

					double z = player.posZ -= 0.15d;
					
					world.spawnParticle("flame", x, player.posY, z,
				0.0D, 0.0D, 0.0D);
				}
			}
		}
	}
}
