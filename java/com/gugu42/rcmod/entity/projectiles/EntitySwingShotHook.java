package com.gugu42.rcmod.entity.projectiles;

import java.util.List;

import com.gugu42.rcmod.RcMod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntitySwingShotHook extends EntityThrowable {

	public boolean shouldPullPlayer = false;
	public EntityPlayer thrower;

	public int returnTime = 30;
	public int timeLived;

	public EntitySwingShotHook(World par1World) {
		super(par1World);

	}

	public EntitySwingShotHook(World par1World,
			EntityLivingBase par2EntityLivingBase) {
		super(par1World, par2EntityLivingBase);
		this.timeLived = 0;
		this.shouldPullPlayer = false;

		if (par2EntityLivingBase instanceof EntityPlayerMP) {
			this.thrower = (EntityPlayerMP) par2EntityLivingBase;
		} else {
			this.thrower = (EntityPlayer) par2EntityLivingBase;
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!this.worldObj.isRemote) {
			timeLived++;
		}
		if (shouldPullPlayer) {

			pullPlayer(thrower);

			List entityTagetList = this.worldObj.getEntitiesWithinAABB(
					Entity.class, this.boundingBox.expand(0.3D, 0.3D, 0.3D));
			for (int i = 0; i < entityTagetList.size(); i++) {
				Entity entityTarget = (Entity) entityTagetList.get(i);
				if (entityTarget != null && entityTarget == thrower) {
					this.setDead();
					thrower.getEntityData()
							.setBoolean("canFireSwingshot", true);
					System.out
							.println("SWGSHT : Died because I pulled the player");
				}
			}
		}

		if (timeLived >= returnTime && !shouldPullPlayer) {
			returnToThrower();
			List entityTagetList = this.worldObj.getEntitiesWithinAABB(
					Entity.class, this.boundingBox.expand(0.3D, 0.3D, 0.3D));
			for (int i = 0; i < entityTagetList.size(); i++) {
				Entity entityTarget = (Entity) entityTagetList.get(i);
				if (entityTarget != null && entityTarget == thrower) {
					this.setDead();
					thrower.getEntityData()
							.setBoolean("canFireSwingshot", true);
					System.out
							.println("SWGSHT : Died because I returned the player");
				}
			}

		}

		if (timeLived >= 70 && !shouldPullPlayer) {
			this.setDead();
			thrower.getEntityData().setBoolean("canFireSwingshot", true);
			System.out.println("SWGSHT : Died because I was old");
		}

		if (timeLived >= 120) {
			this.setDead();
			thrower.getEntityData().setBoolean("canFireSwingshot", true);
			System.out.println("SWGSHT : Died because I was very old");
		}

	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		if (mop != null) {
			if (mop.typeOfHit != null
					&& mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
				if (worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ) != null
						&& worldObj
								.getBlock(mop.blockX, mop.blockY, mop.blockZ) == RcMod.versaTargetGreen) {
					System.out.println("I should pull the player !");
					shouldPullPlayer = true;
					motionX = 0;
					motionY = 0;
					motionZ = 0;

					this.posX = mop.hitVec.xCoord;
					this.posY = mop.hitVec.yCoord;
					this.posZ = mop.hitVec.zCoord;
				} else {
					returnToThrower();
				}
			}
		}

	}

	public void pullPlayer(EntityPlayer player) {
		// Vec3 playerHookVec = Vec3.createVectorHelper(posX - player.posX, posY
		// - player.posY, posZ - player.posZ);
		//
		// playerHookVec.normalize();
		//
		// player.addVelocity(-player.motionX * 0.5, -player.motionY * 0.5,
		// -player.motionZ * 0.5);
		// player.addVelocity(playerHookVec.xCoord * 0.5,
		// playerHookVec.yCoord * 0.5, playerHookVec.zCoord * 0.5);

		if (player instanceof EntityPlayerMP) {
			EntityPlayerMP playerMP = (EntityPlayerMP)player;
			
			double par1 = this.posX - thrower.posX;
			double par3 = this.posY - thrower.posY;
			double par5 = this.posZ - thrower.posZ;
			float par7 = 0.5f;
			float par8 = 0.0f;

			float f2 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5
					* par5);
			par1 /= (double) f2;
			par3 /= (double) f2;
			par5 /= (double) f2;
			par1 += this.rand.nextGaussian() * 0.007499999832361937D
					* (double) par8;
			par3 += this.rand.nextGaussian() * 0.007499999832361937D
					* (double) par8;
			par5 += this.rand.nextGaussian() * 0.007499999832361937D
					* (double) par8;
			par1 *= (double) par7;
			par3 *= (double) par7;
			par5 *= (double) par7;
			playerMP.motionX = par1;
			playerMP.motionY = par3;
			playerMP.motionZ = par5;
			float f3 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
			playerMP.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(
					par1, par5) * 180.0D / Math.PI);
			playerMP.prevRotationPitch = this.rotationPitch = (float) (Math
					.atan2(par3, (double) f3) * 180.0D / Math.PI);
		}
		// this.ticksInGround = 0;
	}

	protected void returnToThrower() {

		if (thrower == null) {
			this.setDead();
		} else {
			double newX = thrower.posX - this.posX;
			double newY = thrower.posY - this.posY;
			double newZ = thrower.posZ - this.posZ;
			setThrowableHeading(newX, newY, newZ, this.func_70182_d(), 0.0F);
		}

	}

	protected float getGravityVelocity() {
		return 0.00F;
	}

}
