package com.gugu42.rcmod.testing;

import com.gugu42.rcmod.entity.projectiles.EntityVisibombAmmo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;

public class EntityVisibombCamera extends EntityLiving {
	private static EntityVisibombCamera instance;
	private Entity target;
	private boolean enabled, invert, isReturning;
	private int maxLife, despawnDelay;

	private boolean hideGUI;
	private float fovSetting;
	private int thirdPersonView;

	private static final int positionSmoother = 5;
	private static final int rotationSmoother = 5;

	private EntityVisibombCamera() {
		super(null);

		setSize(0.0F, 0.0F);
		yOffset = 0.0F;
	}

	public static EntityVisibombCamera getInstance() {
		if (instance == null) {
			instance = new EntityVisibombCamera();
		}
		return instance;
	}

	public void startCam(Entity target) {
		startCam(target, false);
	}

	public void startCam(Entity target, boolean invert) {
		startCam(target, invert, 120);
	}

	public void startCam(Entity target, boolean invert, int maxLife) {
		startCam(target, invert, maxLife, 20);
	}

	public void startCam(Entity target, boolean invert, int maxLife,
			int despawnDelay) {
		Minecraft mc = Minecraft.getMinecraft();
		stopCam();

		hideGUI = mc.gameSettings.hideGUI;
		fovSetting = mc.gameSettings.fovSetting;
		thirdPersonView = mc.gameSettings.thirdPersonView;

		mc.gameSettings.hideGUI = true;
	//	mc.gameSettings.thirdPersonView = 1;
		mc.renderViewEntity = this;

		enabled = true;
		isReturning = false;
		this.target = target;
		this.invert = invert;
		this.maxLife = maxLife;
		this.despawnDelay = despawnDelay;
		this.isDead = false;
		worldObj = target.worldObj;
		worldObj.spawnEntityInWorld(this);

		setPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
		setRotation(mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch);

		doCameraMove();
	}

	public void stopCam() {
		Minecraft mc = Minecraft.getMinecraft();
		if (worldObj != null)
			worldObj.removeEntity(this);

		if (!enabled)
			return;
		enabled = false;
		isReturning = false;

		mc.gameSettings.hideGUI = hideGUI;
		mc.gameSettings.fovSetting = fovSetting;
		mc.gameSettings.thirdPersonView = thirdPersonView;
		mc.renderViewEntity = mc.thePlayer;
	}

	private void doCameraMove() {
		double x = posX + (target.posX - posX);
		double y = (posY + (target.posY - posY) / positionSmoother) - 0.3f;
		double z = posZ + (target.posZ - posZ);

		float yaw = MathHelper.getShortAngle(rotationYaw, target.rotationYaw);
		float pitch = MathHelper.getShortAngle(rotationPitch,
				target.rotationPitch);
		if (invert) {
			yaw = -(rotationYaw + yaw);
			pitch = -(rotationPitch + pitch);
		} else {
	
			yaw /= rotationSmoother;
			pitch /= rotationSmoother;
			yaw += rotationYaw;
			pitch += rotationPitch;
		}
		yaw = MathHelper.normalize(yaw);
		pitch = MathHelper.normalize(pitch);

		setPosition(x, y, z);
		setRotation(yaw, pitch);
	}

	private void setIsReturning() {
		double oldPosX = posX;
		double oldPosY = posY;
		double oldPosZ = posZ;
		float oldYaw = rotationYaw;
		float oldPitch = rotationPitch;
		startCam(Minecraft.getMinecraft().thePlayer, false, 20);
		setPosition(oldPosX, oldPosY, oldPosZ);
		setRotation(oldYaw, oldPitch);
		isReturning = true;
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();

		if (!enabled)
			return;

		if (target.isDead && target instanceof EntityVisibombAmmo)
			target.isDead = false;

		/*
		 * if (!isReturning) { // if (target !=
		 * Minecraft.getMinecraft().thePlayer) { // setIsReturning(); // } else
		 * { // stopCam(); // return; // } // }
		 */
		if (maxLife < 0 || despawnDelay < 0) {
			if (target != Minecraft.getMinecraft().thePlayer) {
				setIsReturning();
			} else {
				stopCam();
				return;
			}
		} else if (target.isDead) {
			stopCam();
			return;
		} else {
			--maxLife;
		}

		if (isReturning) {
			EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
			if (Math.abs(posX - player.posX) < 1
					&& Math.abs(posY - player.posY) < 1
					&& Math.abs(posZ - player.posZ) < 1) {
				isReturning = false;
				stopCam();
			}
		}

		doCameraMove();

		motionX = motionY = motionZ = 0;
	}

	@Override
	public boolean isEntityInvulnerable() {
		return true;
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	public boolean canBeCollidedWith() {
		return false;
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	/* ===== Don't save the entity ===== */
	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound) {
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompound) {
	}
}