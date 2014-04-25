package com.gugu42.rcmod.entity.projectiles;

import com.gugu42.rcmod.ClientProxy;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.IThrowableEntity;

public class EntityPyrocitorAmmo extends EntityThrowable {

	public int lifeTime;
	private double startX;
	private double startY;
	private double startZ;

	public EntityPyrocitorAmmo(World par1World) {
		super(par1World);
		this.lifeTime = 0;
	}

	public EntityPyrocitorAmmo(World par1World, EntityLiving par2EntityLiving) {
		super(par1World, par2EntityLiving);
		this.startX = posX;
		this.startY = posY;
		this.startZ = posZ;
	}

	public EntityPyrocitorAmmo(World par1World, EntityPlayer par2EntityLiving) {
		super(par1World, par2EntityLiving);
		this.startX = posX;
		this.startY = posY;
		this.startZ = posZ;
	}

	public EntityPyrocitorAmmo(World par1World, EntityPlayer par2EntityLiving,
			float random) {
		super(par1World, par2EntityLiving);
		this.setSize(0.25F, 0.25F);
		this.setLocationAndAngles(par2EntityLiving.posX, par2EntityLiving.posY
				+ (double) par2EntityLiving.getEyeHeight(),
				par2EntityLiving.posZ, par2EntityLiving.rotationYaw,
				par2EntityLiving.rotationPitch);
		this.posX -= (double) (MathHelper.cos(this.rotationYaw / 180.0F
				* (float) Math.PI) * 0.16F);
		this.posY -= 0.10000000149011612D;
		this.posZ -= (double) (MathHelper.sin(this.rotationYaw / 180.0F
				* (float) Math.PI) * 0.16F);
		this.setPosition(this.posX, this.posY, this.posZ);
		float f = 0.4F;
		this.motionX = (double) (-MathHelper.sin(this.rotationYaw / 180.0F
				* (float) Math.PI)
				* MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * f);
		this.motionZ = (double) (MathHelper.cos(this.rotationYaw / 180.0F
				* (float) Math.PI)
				* MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * f);
		this.motionY = (double) (-MathHelper.sin((this.rotationPitch + this
				.func_70183_g()) / 180.0F * (float) Math.PI) * f);
		this.setThrowableHeading(this.motionX, this.motionY, this.motionZ,
				this.func_70182_d() + (rand.nextFloat() * 5f), random);
		this.startX = posX;
		this.startY = posY;
		this.startZ = posZ;
	}

	public EntityPyrocitorAmmo(World par1World, double par2, double par4,
			double par6) {
		super(par1World, par2, par4, par6);
		this.startX = posX;
		this.startY = posY;
		this.startZ = posZ;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		lifeTime++;
		// this.worldObj.spawnParticle("flame", this.posX, this.posY, this.posZ,
		// 0.0D, 0.0D, 0.0D);
		double MAX_DISTANCE = 3;
		if (Math.sqrt(Math.pow(posX - startX, 2) + Math.pow(posY - startY, 2)
				+ Math.pow(posZ - startZ, 2)) > MAX_DISTANCE * 2
				&& !worldObj.isRemote) {
			setDead();
		}
	}

	@Override
	protected void onImpact(MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit != null
				&& movingobjectposition.entityHit != this
				&& !(movingobjectposition.entityHit instanceof EntityPyrocitorAmmo)) {
			byte b0 = 6;
			if (movingobjectposition.entityHit == this.getThrower())
				return;
			movingobjectposition.entityHit.attackEntityFrom(
					DamageSource.inFire, b0);
			movingobjectposition.entityHit.setFire(8);
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

	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		startX = nbt.getDouble("First X");
		startY = nbt.getDouble("First Y");
		startZ = nbt.getDouble("First Z");
	}

	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setDouble("First X", startX);
		nbt.setDouble("First Y", startY);
		nbt.setDouble("First Z", startZ);
	}

	@Override
	protected float getGravityVelocity() {
		return 0.00F;
	}
}