package com.gugu42.rcmod.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityBlasterAmmo extends EntityThrowable {
	public EntityBlasterAmmo(World par1World) {
		super(par1World);
	}

	public EntityBlasterAmmo(World par1World, EntityLiving par2EntityLiving) {
		super(par1World, par2EntityLiving);
	}

	public EntityBlasterAmmo(World par1World, EntityPlayer par2EntityLiving) {
		super(par1World, par2EntityLiving);
	}
	
	public EntityBlasterAmmo(World par1World, double par2, double par4,
			double par6) {
		super(par1World, par2, par4, par6);
	}

	@Override
	protected void onImpact(MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit != null) {
			byte b0 = 4;
			
			
			movingobjectposition.entityHit.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)this.getThrower()), b0);
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
	
	@Override
    protected float getGravityVelocity()
    {
        return 0.00F;
    }
}