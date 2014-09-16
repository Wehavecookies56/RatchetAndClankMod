package com.gugu42.rcmod.items;

import java.util.Random;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.render.armor.ThrusterPackRender;
import com.gugu42.rcmod.utils.Vector3;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemThrusterPack extends ItemArmor {

	@SideOnly(Side.CLIENT)
	public ThrusterPackRender model;

	public Random rand = new Random();

	public ItemThrusterPack(ArmorMaterial p_i45325_1_, int p_i45325_2_,
			int p_i45325_3_) {
		super(p_i45325_1_, p_i45325_2_, p_i45325_3_);
		this.setCreativeTab(RcMod.rcGadgTab);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot,
			String type) {
		return "rcmod:models/ThrusterPack0.png";
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

					Vector3 playerPos = new Vector3(player);

					Vector3 vCenter = new Vector3();
					vCenter.z -= 0.35;
					vCenter.x = 0.25F;
					vCenter.rotate(player.renderYawOffset);
					vCenter.y -= 0.94;

					vCenter = Vector3.translate(vCenter.clone(), new Vector3(
							-player.motionX, -player.motionY, -player.motionZ));

					Vector3 v = new Vector3(playerPos).translate(vCenter);
					world.spawnParticle(
							"flame",
							v.x,
							v.y,
							v.z,
							0.05D * (Math.cos(player.renderYawOffset * Math.PI
									/ 180)),
							-0.1D,
							0.05D * (Math.sin(player.renderYawOffset * Math.PI
									/ 180)));

					Vector3 vCenter2 = new Vector3();
					vCenter2.z -= 0.35;
					vCenter2.x = -0.25F;
					vCenter2.rotate(player.renderYawOffset);
					vCenter2.y -= 0.94;

					vCenter2 = Vector3.translate(vCenter2.clone(), new Vector3(
							-player.motionX, -player.motionY, -player.motionZ));

					Vector3 v2 = new Vector3(playerPos).translate(vCenter2);
					world.spawnParticle(
							"flame",
							v2.x,
							v2.y,
							v2.z,
							-0.05D
									* (Math.cos(player.renderYawOffset
											* Math.PI / 180)),
							-0.1D,
							-0.05D
									* (Math.sin(player.renderYawOffset
											* Math.PI / 180)));
				}
			}
		}
	}
}
