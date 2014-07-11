package com.gugu42.rcmod.entity.projectiles;

import java.util.List;

import com.gugu42.rcmod.RcMod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntitySwingShotHook extends EntityThrowable {

	public boolean shouldPullPlayer = false;
	public EntityPlayer thrower;

	public int returnTime = 30;
	public int timeLived;

	public double startX;
	public double startY;
	public double startZ;

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
	protected void entityInit() {
		dataWatcher.addObject(10, "1"); // powValue
		dataWatcher.addObject(11, "0"); // startX
		dataWatcher.addObject(12, "0"); // startY
		dataWatcher.addObject(13, "0"); // startZ

	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!this.worldObj.isRemote) {
			timeLived++;
		}

		if (getPowValue() < 2D) {
			setPowValue(getPowValue() + 0.05);
		}

		if (thrower != null && !thrower.isDead) {
			setStartPosition();
		}

		if (shouldPullPlayer) {

			if (thrower instanceof EntityPlayerMP) {

				EntityPlayerMP throwerMP = (EntityPlayerMP) thrower;

				Vec3 playerPos = throwerMP.getPosition(1.0f);
				Vec3 entPos = this.getPosition(1.0f);

				Vec3 a = playerPos.subtract(entPos);

				Vec3 b = Vec3.createVectorHelper(a.xCoord, a.yCoord, a.zCoord);
				double suckingPower = 5;
				customKnockBack(throwerMP, 0, b.xCoord * suckingPower, b.yCoord
						* suckingPower, b.zCoord * suckingPower);

				throwerMP.playerNetServerHandler
						.sendPacket(new S12PacketEntityVelocity(throwerMP));

			}

			List entityTagetList = this.worldObj.getEntitiesWithinAABB(
					Entity.class, this.boundingBox.expand(0.3D, 0.3D, 0.3D));
			for (int i = 0; i < entityTagetList.size(); i++) {
				Entity entityTarget = (Entity) entityTagetList.get(i);
				if (entityTarget != null && entityTarget == thrower) {
					this.setDead();
					if (thrower instanceof EntityPlayerMP) {

						EntityPlayerMP throwerMP = (EntityPlayerMP) thrower;

						throwerMP.motionX = 0;
						throwerMP.motionZ = 0;
						throwerMP.motionY = 0;

						throwerMP.playerNetServerHandler
								.sendPacket(new S12PacketEntityVelocity(
										throwerMP));
					}

					thrower.getEntityData()
							.setBoolean("canFireSwingshot", true);
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
				}
			}

		}

		if (timeLived >= 70 && !shouldPullPlayer) {
			this.setDead();
			thrower.getEntityData().setBoolean("canFireSwingshot", true);
		}

		if (timeLived >= 120) {
			this.setDead();
			thrower.getEntityData().setBoolean("canFireSwingshot", true);
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

	protected void setStartPosition() {
		setStartCoordinates(thrower.posX, thrower.posY + 1, thrower.posZ);
	}

	public void setStartCoordinates(double x, double y, double z) {
		setStartX(x);
		setStartY(y);
		setStartZ(z);
		updateEntPos();
	}

	private void updateEntPos() {
		posX = getStartX() + (getEndX() - getStartX());
		posY = getStartY() + (getEndY() - getStartY());
		posZ = getStartZ() + (getEndZ() - getStartZ());
	}

	protected void returnToThrower() {

		if (thrower == null) {
			this.setDead();
		} else {
			double newX = thrower.posX - this.posX;
			double newY = thrower.posY - this.posY;
			double newZ = thrower.posZ - this.posZ;
			setThrowableHeading(newX, newY + 0.5d, newZ, this.func_70182_d(),
					0.0F);
		}

	}

	public void customKnockBack(EntityLivingBase par1Entity, float par2,
			double par3, double par4, double par5) {
		// if (par1Entity.worldObj.rand.nextDouble() >=
		// par1Entity.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue())
		// {
		par1Entity.isAirBorne = true;
		float f1 = MathHelper.sqrt_double(par3 * par3 + par5 * par5);
		float f2 = 0.4F;
		par1Entity.motionX /= 2.0D;
		par1Entity.motionY /= 2.0D;
		par1Entity.motionZ /= 2.0D;
		par1Entity.motionX += par3 / (double) f1 * (double) f2;
		par1Entity.motionY += par4 / (double) f1 * (double) f2;
		par1Entity.motionZ += par5 / (double) f1 * (double) f2;

		if (par1Entity.motionY > 0.4000000059604645D) {
			par1Entity.motionY = 0.4000000059604645D;
		}
		// }
	}

	protected float getGravityVelocity() {
		return 0.00F;
	}

	@SideOnly(Side.CLIENT)
	public Vec3 getPosition(float par1) {
		if (par1 == 1.0F) {
			return this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX,
					this.posY, this.posZ);
		} else {
			double d0 = this.prevPosX + (this.posX - this.prevPosX)
					* (double) par1;
			double d1 = this.prevPosY + (this.posY - this.prevPosY)
					* (double) par1;
			double d2 = this.prevPosZ + (this.posZ - this.prevPosZ)
					* (double) par1;
			return this.worldObj.getWorldVec3Pool().getVecFromPool(d0, d1, d2);
		}
	}

	public double getRopeAbsLength() {
		return Math.sqrt((getEndX() - getStartX()) * (getEndX() - getStartX())
				+ (getEndY() - getStartY()) * (getEndY() - getStartY())
				+ (getEndZ() - getStartZ()) * (getEndZ() - getStartZ()));
	}

	public int getSegmentCount() {
		return (int) Math.rint(getRopeAbsLength() / 0.5D);
	}

	public void setStartX(double input) {
		if (!worldObj.isRemote)
			dataWatcher.updateObject(11, "" + input);
	}

	public void setStartY(double input) {
		if (!worldObj.isRemote)
			dataWatcher.updateObject(12, "" + input);
	}

	public void setStartZ(double input) {
		if (!worldObj.isRemote)
			dataWatcher.updateObject(13, "" + input);
	}

	public double getStartX() {
		return Double.valueOf(dataWatcher.getWatchableObjectString(11));
	}

	public double getStartY() {
		return Double.valueOf(dataWatcher.getWatchableObjectString(12));
	}

	public double getStartZ() {
		return Double.valueOf(dataWatcher.getWatchableObjectString(13));
	}

	private double getEndZ() {

		return this.posZ;
	}

	private double getEndY() {

		return this.posY;
	}

	private double getEndX() {

		return this.posX;
	}

	public double getPowValue() {
		return Double.valueOf(dataWatcher.getWatchableObjectString(10));
	}

	public void setPowValue(double input) {
		if (!worldObj.isRemote)
			dataWatcher.updateObject(10, "" + input);
	}

	public double[] getCoordsAtRelativeLength(float relativeDistance) {
		double[] result = new double[3];
		result[0] = getStartX()
				+ ((getEndX() - getStartX()) * relativeDistance);
		result[2] = getStartZ()
				+ ((getEndZ() - getStartZ()) * relativeDistance);

		if ((relativeDistance *= 2) < 1) {
			result[1] = getStartY()
					+ ((getEndY() - getStartY()) * (0.5 * Math.pow(
							relativeDistance, getPowValue())));
		} else {
			result[1] = getStartY()
					+ ((getEndY() - getStartY()) * (1 - 0.5 * Math.abs(Math
							.pow(2 - relativeDistance, getPowValue()))));
		}

		return result;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		setStartX(compound.getDouble("startX"));
		setStartY(compound.getDouble("startY"));
		setStartZ(compound.getDouble("startZ"));
		setPowValue(compound.getDouble("ropePOWvalue"));

	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		compound.setDouble("startX", getStartX());
		compound.setDouble("startY", getStartY());
		compound.setDouble("startZ", getStartZ());
		compound.setDouble("ropePOWvalue", getPowValue());
	}

}
