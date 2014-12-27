package com.gugu42.rcmod.entity.projectiles;

import java.util.List;

import com.gugu42.rcmod.TNTCrateExplosion;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

public class EntityDecoyGloveAmmo extends EntityThrowable implements
		IProjectile {

	//isActive = hasTouched the ground. When it touches the ground, it starts to inflate.
	public boolean isActive;
	public int tickSinceActive;
	public float inflateSize;
	public float hitboxSizeX;
	public float hitboxSizeY;
	public float orientationYaw;
	public int delay = 0;

	public EntityDecoyGloveAmmo(World par1World) {
		super(par1World);
		this.isActive = false;
		this.inflateSize = 0.001f;
		this.hitboxSizeX = 0.25f;
		this.hitboxSizeY = 0.25f;
	}

	public EntityDecoyGloveAmmo(World par1World, EntityLiving par2EntityLiving) {
		super(par1World, par2EntityLiving);
		this.isActive = false;
		this.inflateSize = 0.001f;
		this.hitboxSizeX = 0.25f;
		this.hitboxSizeY = 0.25f;
		this.orientationYaw = par2EntityLiving.rotationYawHead;
	}

	public EntityDecoyGloveAmmo(World par1World, EntityPlayer par2EntityPlayer,
			float orientationYaw) {
		super(par1World, par2EntityPlayer);
		this.isActive = false;
		this.inflateSize = 0.001f;
		this.hitboxSizeX = 0.25f;
		this.hitboxSizeY = 0.25f;
		this.orientationYaw = orientationYaw;
		System.out.println(this.orientationYaw);
	}

	public EntityDecoyGloveAmmo(World par1World, double par2, double par4,
			double par6) {
		super(par1World, par2, par4, par6);
		this.isActive = false;
		this.inflateSize = 0.001f;
		this.hitboxSizeX = 0.25f;
		this.hitboxSizeY = 0.25f;
	}

	@Override
	protected void onImpact(MovingObjectPosition movingobjectposition) {
		if (movingobjectposition != null) {
			if (movingobjectposition.typeOfHit == MovingObjectType.ENTITY) {

			}
			if (movingobjectposition.typeOfHit == MovingObjectType.BLOCK) {
				this.motionX = 0;
				this.motionY = 0;
				this.motionZ = 0;
				this.isActive = true;
			}

		}
	}

	@Override
	protected float getGravityVelocity() {
		return 0.30F;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (isActive) {
			tickSinceActive++;
			this.setSize(hitboxSizeX, hitboxSizeY);
			//Attract entities
		}

		if (tickSinceActive >= 90 * 20) {
			this.explode();
			this.setDead();
		}

		if (tickSinceActive <= 20) {
			inflateSize += 0.00195f;
			hitboxSizeX += 0.0525f;
			hitboxSizeY += 0.0875;
		}

		if (isActive && delay == 0) {
			if (!this.worldObj.isRemote) {
				float size = 32f;
				List<Entity> entities = this.worldObj
						.getEntitiesWithinAABBExcludingEntity(this,
								this.boundingBox.expand(size, size, size));
				for (Entity e : entities) {
					if (e instanceof EntityCreature) {
						EntityCreature mob = (EntityCreature) e;
						mob.setPathToEntity(mob.worldObj.getPathEntityToEntity(
								mob, this, size, true, true, true, true));
					}
					if (e instanceof EntityLiving) {
						EntityLiving living = (EntityLiving) e;
						living.getNavigator().clearPathEntity();
						living.getNavigator().setPath(
								living.getNavigator().getPathToEntityLiving(
										this), 1.0);
					}
				}

				this.delay = 20;
			}
		}

		if (this.delay > 0) {
			delay--;
		}

	}

	private void explode() {
		TNTCrateExplosion explosion = new TNTCrateExplosion(this.worldObj,
				this, this.posX, this.posY, this.posZ, 1.5f);
		explosion.doExplosionA(false);
		explosion.doExplosionB(true, false);
		this.worldObj.playSoundAtEntity(this, "rcmod:BombGloveExplosion",
				10.0f, 1.0f);
		this.setDead();
	}

	public float getOrientationYaw() {
		return this.orientationYaw;
	}
}
