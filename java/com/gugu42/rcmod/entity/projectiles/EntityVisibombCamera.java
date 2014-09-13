package com.gugu42.rcmod.entity.projectiles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.util.JsonException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import com.gugu42.rcmod.ClientProxy;
import com.gugu42.rcmod.testing.MathHelper;

import cpw.mods.fml.common.ObfuscationReflectionHelper;

public class EntityVisibombCamera extends EntityLiving {
	private static EntityVisibombCamera instance;
	private Entity target;
	private boolean enabled, invert, isReturning;
	private int maxLife, despawnDelay;

	private boolean hideGUI;
	private float fovSetting;
	private int thirdPersonView;
    private long startedReturningTime;
    private double oldPosX;
    private double oldPosY;
    private double oldPosZ;
    
    private float oldRotYaw;
    private float oldRotYawHead;
    private float oldRotPitch;
    
    private static final long maxReturnTime = 500;
	private static final int positionSmoother = 5;
	private static final int rotationSmoother = 5;
    private static final ResourceLocation shaderLocation = new ResourceLocation("rcmod", "shaders/post/visibomb.json");
    
    
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
		startCam(target, invert, 150);
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
		this.oldRotYaw = mc.thePlayer.rotationYaw;
		this.oldRotYawHead = mc.thePlayer.rotationYawHead;
		this.oldRotPitch = mc.thePlayer.rotationPitch;
		
		
		setPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
		setRotation(mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch);
		activateGreenScreenShader();
		doCameraMove();
	}

	private void activateGreenScreenShader()
    {
	    if(OpenGlHelper.shadersSupported)
	    {
    	    ShaderGroup theShaderGroup;
            try
            {
                theShaderGroup = new ShaderGroup(null, ClientProxy.rcResourceManager, Minecraft.getMinecraft().getFramebuffer(), shaderLocation);
                theShaderGroup.createBindFramebuffers(Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
                ObfuscationReflectionHelper.setPrivateValue(EntityRenderer.class, Minecraft.getMinecraft().entityRenderer, theShaderGroup, 42);
            }
            catch (JsonException e)
            {
                e.printStackTrace();
            }
	    }
    }
	
	private void desactivateGreenScreenShader()
    {
        Minecraft.getMinecraft().entityRenderer.deactivateShader();
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
		mc.thePlayer.rotationPitch = oldRotPitch;
		mc.thePlayer.rotationYaw = oldRotYaw;
		mc.thePlayer.rotationYawHead = oldRotYawHead;
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

		if(this.isReturning)
		{
		    double t = ((double)System.currentTimeMillis()-(double)startedReturningTime)/(double)maxReturnTime;
            x = (t)*target.posX + (1.0-t)*oldPosX;
            y = (t)*target.posY + (1.0-t)*oldPosY;
            z = (t)*target.posZ + (1.0-t)*oldPosZ;
		}
		setPosition(x, y+target.getEyeHeight(), z);
		setRotation(yaw, pitch);
	}
	
	public void setThrowableHeading(double par1, double par3, double par5, float par7, float par8)
    {
        float f2 = net.minecraft.util.MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
        par1 /= (double)f2;
        par3 /= (double)f2;
        par5 /= (double)f2;
        par1 += this.rand.nextGaussian() * 0.007499999832361937D * (double)par8;
        par3 += this.rand.nextGaussian() * 0.007499999832361937D * (double)par8;
        par5 += this.rand.nextGaussian() * 0.007499999832361937D * (double)par8;
        par1 *= (double)par7;
        par3 *= (double)par7;
        par5 *= (double)par7;
        this.motionX = par1;
        this.motionY = par3;
        this.motionZ = par5;
        float f3 = net.minecraft.util.MathHelper.sqrt_double(par1 * par1 + par5 * par5);
        this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(par1, par5) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(par3, (double)f3) * 180.0D / Math.PI);
    }

	private void setIsReturning() {
		oldPosX = posX;
		oldPosY = posY;
		oldPosZ = posZ;
		float oldYaw = rotationYaw;
		float oldPitch = rotationPitch;
		startCam(Minecraft.getMinecraft().thePlayer, false, 20);
		setPosition(oldPosX, oldPosY, oldPosZ);
		setRotation(oldYaw, oldPitch);
		startedReturningTime = System.currentTimeMillis();
		isReturning = true;
		EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
        target = player;
        desactivateGreenScreenShader();
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();

		if (!enabled)
			return;

		if (target.isDead){
			this.desactivateGreenScreenShader();
			this.stopCam();
		}
		
		if (target.isDead && target instanceof EntityVisibombAmmo)
		{

		}

		/*
		 * if (!isReturning) { // if (target !=
		 * Minecraft.getMinecraft().thePlayer) { // setIsReturning(); // } else
		 * { // stopCam(); // return; // } // }
		 */
		if (maxLife < 0 || despawnDelay < 0) {
			if (target != Minecraft.getMinecraft().thePlayer) {
				setIsReturning();
			} else {
				if(Minecraft.getMinecraft().thePlayer.getDistanceSqToEntity(this) <= 2)
				    stopCam();
			}
		} else if (target.isDead) {
		    setIsReturning();
//			stopCam();
			return;
		} else {
			--maxLife;
		}

		if (isReturning) 
		{
			EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
//			if (Math.abs(posX - player.posX) < 1
//					&& Math.abs(posY - player.posY) < 1
//					&& Math.abs(posZ - player.posZ) < 1) {
//				isReturning = false;
//				stopCam();
//			}
			if(System.currentTimeMillis()-startedReturningTime > maxReturnTime)
			{
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