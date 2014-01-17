package com.gugu42.rcmod.entity;

import java.util.List;

import com.gugu42.rcmod.handler.ExtendedEntityLivingBaseTarget;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityRYNOAmmo extends EntityThrowable {
	public boolean homing = true;
	private int ticksAlive = 0;
	protected EntityLiving target;

	public EntityRYNOAmmo(World par1World) {
		super(par1World);
	}

	public EntityRYNOAmmo(World par1World, EntityPlayer par3EntityPlayer) {
		super(par1World, par3EntityPlayer);
	}

	protected void entityInit() {
	}

	protected float getGravityVelocity() {
		return 0.0F;
	}

	protected float func_70182_d() {
		return 1.2F;
	}

	protected float func_70183_g() {
		return -0.0F;
	}

	public void onUpdate() {
		super.onUpdate();
		this.ticksAlive += 1;
		if (this.ticksAlive >= 300) {
			setDead();
			this.ticksAlive = 0;
		}
		this.worldObj.spawnParticle("smoke", this.posX, this.posY, this.posZ,
				0.0D, 0.0D, 0.0D);

		if (this.homing) {
			if ((this.target == null) || (this.target.velocityChanged)
					|| (!this.target.canEntityBeSeen(this))) {
				this.target = getNearestEntity();
			}

			if (this.target != null) {

				double d = this.target.boundingBox.minX
						+ (this.target.boundingBox.maxX - this.target.boundingBox.minX)
						/ 2.0D - this.posX;
				double d1 = this.target.boundingBox.minY
						+ (this.target.boundingBox.maxY - this.target.boundingBox.minY)
						/ 2.0D - this.posY;
				double d2 = this.target.boundingBox.minZ
						+ (this.target.boundingBox.maxZ - this.target.boundingBox.minZ)
						/ 2.0D - this.posZ;
				setThrowableHeading(d, d1, d2, 0.9F, 0.0F);
			}

		}

		float f4 = 0.99F;
		float f6 = 0.05F;

		if (!this.homing) {
			this.motionX *= f4;
			this.motionY *= f4;
			this.motionZ *= f4;
			this.motionY -= f6;
		}
	}

	public EntityLiving getActualTarget() {
		return this.target;
	}

	private EntityLiving getTarget(double d, double d1, double d2, double d3) {
		double d4 = -1.0D;
		EntityLiving entityliving = null;
		List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(
				getThrower(), this.boundingBox.expand(16.0D, 16.0D, 16.0D));

		for (int i = 0; i < list.size(); i++) {
			EntityLiving entityliving1 = (EntityLiving) list.get(i);

			if (entityliving1 != getThrower()) {
				double d5 = entityliving1.getDistance(d, d1, d2);

				if (((d3 < 0.0D) || (d5 < d3 * d3))
						&& ((d4 == -1.0D) || (d5 < d4))
						&& (entityliving1.canEntityBeSeen(this))) {
					d4 = d5;
					entityliving = entityliving1;
				}
			}
		}

		return entityliving;
	}

	public boolean validTarget(EntityLiving entityliving) {
		if (entityliving.equals(getThrower())) {
			return false;
		}

		return true;
	}

	protected void onImpact(MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit != null) {
			byte b0 = 6;
			movingobjectposition.entityHit.attackEntityFrom(
					DamageSource.causeThrownDamage(this, getThrower()), b0);

			if (!this.worldObj.isRemote) {
				setDead();
			}
		}

		for (int i = 0; i < 8; i++) {
			this.worldObj.spawnParticle("snowballpoof", this.posX, this.posY,
					this.posZ, 0.0D, 0.0D, 0.0D);
		}

		if (!this.worldObj.isRemote) {
			setDead();
		}

		if ((!this.isDead)
				&& (movingobjectposition.typeOfHit == EnumMovingObjectType.TILE)
				&& (!this.worldObj.isRemote))
			setDead();
	}

	private EntityLiving getNearestEntity() {
		EntityLiving target = null;
		float explosionSize = 5.0F;
		explosionSize *= 2.0F;
		int i = MathHelper.floor_double(this.posX - explosionSize - 1.0D);
		int j = MathHelper.floor_double(this.posX + explosionSize + 1.0D);
		int k = MathHelper.floor_double(this.posY - explosionSize - 1.0D);
		int l1 = MathHelper.floor_double(this.posY + explosionSize + 1.0D);
		int i2 = MathHelper.floor_double(this.posZ - explosionSize - 1.0D);
		int j2 = MathHelper.floor_double(this.posZ + explosionSize + 1.0D);

		if (!this.worldObj.isRemote) {
			List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(
					getThrower(),
					AxisAlignedBB.getAABBPool().getAABB(i, k, i2, j, l1, j2));

			for (int k2 = 0; k2 < list.size(); k2++) {
				Entity entity = (Entity) list.get(k2);
				if (((entity instanceof EntityLiving))
						&& (((EntityLiving) entity).canEntityBeSeen(this))) {
					target = (EntityLiving) entity;
					return target;
				}
			}
		}
		return target;
	}
}