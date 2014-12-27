package com.gugu42.rcmod.entity.projectiles;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

import com.gugu42.rcmod.TNTCrateExplosion;

public class EntityBombGloveAmmo extends EntityThrowable implements IProjectile {

	public EntityBombGloveAmmo(World par1World) {
		super(par1World);
	}

	public EntityBombGloveAmmo(World par1World, EntityLiving par2EntityLiving) {
		super(par1World, par2EntityLiving);
	}

	public EntityBombGloveAmmo(World par1World, EntityPlayer par2EntityLiving) {
		super(par1World, par2EntityLiving);
	}

	public EntityBombGloveAmmo(World par1World, double par2, double par4,
			double par6) {
		super(par1World, par2, par4, par6);
	}

	@Override
	protected void onImpact(MovingObjectPosition movingobjectposition) {
		if (movingobjectposition != null) {
			if(movingobjectposition.typeOfHit == MovingObjectType.ENTITY){
				this.explode();
			}
			if(movingobjectposition.typeOfHit == MovingObjectType.BLOCK){
				this.explode();
			}
			
		}
	}

	@Override
	protected float getGravityVelocity() {
		return 0.20F;
	}

	private void explode() {
		TNTCrateExplosion explosion = new TNTCrateExplosion(this.worldObj,
				this, this.posX, this.posY, this.posZ, 3.0f);
		explosion.doExplosionA(false);
		explosion.doExplosionB(true, false);
		this.worldObj.playSoundAtEntity(this, "rcmod:BombGloveExplosion",
				10.0f, 1.0f);
		this.setDead();
	}

}