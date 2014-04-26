package com.gugu42.rcmod.testing;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class TargetingSystem {

	private static Entity target;
	private static Entity attackingEntity;

	public static EntityLiving target(Entity attacker) {
		if (Minecraft.getMinecraft().objectMouseOver != null) {
			if (Minecraft.getMinecraft().objectMouseOver.entityHit != null) {
				if (Minecraft.getMinecraft().objectMouseOver.entityHit instanceof EntityLiving) {
					EntityLiving target = (EntityLiving) Minecraft
							.getMinecraft().objectMouseOver.entityHit;
					System.out.println(target.toString());
					return target;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public static Entity target(World world, Entity player, double reach) {
		MovingObjectPosition mop = getMovingObjectPositionFromEntity(world,
				player, false, reach);
		if (mop != null) {
			if (mop.typeOfHit == EnumMovingObjectType.ENTITY) {
				Entity entityHit = mop.entityHit;
				Vec3 hitVec = mop.hitVec;
				System.out.println(mop.entityHit.toString());
				return mop.entityHit;
			} else if (mop.typeOfHit == EnumMovingObjectType.TILE) {
				int blockX = mop.blockX;
				int blockY = mop.blockY;
				int blockZ = mop.blockZ;
				int sideHit = mop.sideHit;
				Vec3 hitVec = mop.hitVec;
				return null;
			} else {
				return null;
			}
		} else {
			return null;
		}

	}

	private static MovingObjectPosition getMovingObjectPositionFromEntity(
			World par1World, Entity par2Entity, boolean par3, double reach) {
		float f = 1.0F;
		float f1 = par2Entity.prevRotationPitch
				+ (par2Entity.rotationPitch - par2Entity.prevRotationPitch) * f;
		float f2 = par2Entity.prevRotationYaw
				+ (par2Entity.rotationYaw - par2Entity.prevRotationYaw) * f;
		double d0 = par2Entity.prevPosX
				+ (par2Entity.posX - par2Entity.prevPosX) * (double) f;
		double d1 = par2Entity.prevPosY
				+ (par2Entity.posY - par2Entity.prevPosY) * (double) f + 1.62D
				- (double) par2Entity.yOffset;
		double d2 = par2Entity.prevPosZ
				+ (par2Entity.posZ - par2Entity.prevPosZ) * (double) f;
		Vec3 vec3 = par1World.getWorldVec3Pool().getVecFromPool(d0, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		Vec3 vec31 = vec3.addVector((double) f7 * reach, (double) f6 * reach,
				(double) f8 * reach);
		return par1World.rayTraceBlocks_do_do(vec3, vec31, par3, !par3);
	}
}
