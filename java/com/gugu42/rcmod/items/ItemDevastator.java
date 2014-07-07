package com.gugu42.rcmod.items;

import java.util.List;

import com.gugu42.rcmod.entity.projectiles.EntityRYNOAmmo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemDevastator extends ItemRcWeap {

	public ItemDevastator() {
		super();
//		this.useTargetingSystem = true;
		this.useAmmo = true;
		this.maxAmmo = 20;
		this.ammoPrice = 50;
		this.setMaxDamage(this.maxAmmo);
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		if (maxAmmo - par1ItemStack.getItemDamage() > 0) {
			if (!par2World.isRemote) {
				if (par3EntityPlayer.getEntityData().getInteger(
						"devastatorCooldown") <= 0) {
					par3EntityPlayer.getEntityData().setInteger(
							"devastatorCooldown", 20);
					par3EntityPlayer.getEntityData().setBoolean(
							"devastatorFired", true);
					if (!par3EntityPlayer.capabilities.isCreativeMode) {
						if (maxAmmo - par1ItemStack.getItemDamage() > 0) {
							par1ItemStack.damageItem(1, par3EntityPlayer);
						}
					}
				}
			}
		}
		return par1ItemStack;
	}

	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World,
			Entity par3Entity, int par4, boolean par5) {
		if (par5 && par3Entity instanceof EntityPlayer && !par2World.isRemote) {
			if (par3Entity.getEntityData().getBoolean("devastatorFired")) {
				EntityPlayer player = (EntityPlayer) par3Entity;
				Entity target = null;
				List entityTagetList = par2World.getEntitiesWithinAABB(
						Entity.class,
						player.boundingBox.expand(48.0D, 48.0D, 48.0D));
				for (int i = 0; i < entityTagetList.size(); i++) {
					Entity entityTarget = (Entity) entityTagetList.get(i);
					if (entityTarget != player
							&& entityTarget instanceof EntityLivingBase) {
						EntityLivingBase entityLivingTarget = (EntityLivingBase) entityTarget;
						Vec3 vec3 = player.getLook(1.0F).normalize();
						Vec3 vec31 = entityLivingTarget.worldObj
								.getWorldVec3Pool()
								.getVecFromPool(
										entityLivingTarget.posX - player.posX,
										entityLivingTarget.boundingBox.minY
												+ (double) (entityLivingTarget.height / 2.0F)
												- (player.posY + (double) player
														.getEyeHeight()),
										entityLivingTarget.posZ - player.posZ);
						double d0 = vec31.lengthVector();
						vec31 = vec31.normalize();
						double d1 = vec3.dotProduct(vec31);
						if (d1 > 1.0D - 0.025D / d0
								&& player.canEntityBeSeen(entityLivingTarget)) {
							target = entityLivingTarget;
						} else {
							target = null;
						}

					}
				}

				if (par3Entity.getEntityData().getBoolean("devastatorFired")) {
					if (target != null) {
						fireRocket(par2World, player, target);
						par3Entity.getEntityData().setBoolean(
								"devastatorFired", false);
					} else {
						fireRocket(par2World, player);
						par3Entity.getEntityData().setBoolean(
								"devastatorFired", false);
					}
				}
			}

		}

		if (par3Entity.getEntityData().getInteger("devastatorCooldown") > 0) {
			par3Entity.getEntityData()
					.setInteger(
							"devastatorCooldown",
							par3Entity.getEntityData().getInteger(
									"devastatorCooldown") - 1);
		}

	}

	public void fireRocket(World world, EntityPlayer player) {
		EntityRYNOAmmo rocket = new EntityRYNOAmmo(world, player);
		world.spawnEntityInWorld(rocket);
	}

	public void fireRocket(World world, EntityPlayer player, Entity target) {
		EntityRYNOAmmo rocket = new EntityRYNOAmmo(world, player, target);
		world.spawnEntityInWorld(rocket);
	}

}
