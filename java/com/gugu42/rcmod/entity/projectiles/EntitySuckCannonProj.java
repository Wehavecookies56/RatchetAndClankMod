package com.gugu42.rcmod.entity.projectiles;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.entity.OwnableEntity;
import com.gugu42.rcmod.network.packets.PacketUpdateOwnerID;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

public class EntitySuckCannonProj extends EntityThrowable implements OwnableEntity
{

	private int ownerID;

	public EntitySuckCannonProj(World par1World)
	{
		super(par1World);
		this.setSize(2, 2);
	}

	public EntitySuckCannonProj(World par1World, EntityLiving par2EntityLiving)
	{
		super(par1World, par2EntityLiving);
	}

	public EntitySuckCannonProj(World par1World, EntityPlayer par2EntityLiving)
	{
		super(par1World, par2EntityLiving);
	}

	public EntitySuckCannonProj(World par1World, double par2, double par4, double par6)
	{
		super(par1World, par2, par4, par6);
	}

	public void setOwnerID(int ownerID)
	{
		this.ownerID = ownerID;
		if(!worldObj.isRemote)
			RcMod.rcModPacketHandler.sendToAll(new PacketUpdateOwnerID(this, ownerID));
	}
	
	@Override
	protected void onImpact(MovingObjectPosition movingobjectposition)
	{
		if(movingobjectposition.typeOfHit == MovingObjectType.ENTITY)
		{
    		if(movingobjectposition.entityHit != null && movingobjectposition.entityHit != this.riddenByEntity && movingobjectposition.entityHit.getEntityId() != ownerID)
    		{
    			System.out.println(movingobjectposition.entityHit.getEntityId()+ ":" + ownerID);
    			if(!this.worldObj.isRemote)
    			{
    				setDead();
    				if(worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"))
    					worldObj.newExplosion(this, posX, posY, posZ, 1f, false, true);
    				else
    					movingobjectposition.entityHit.attackEntityFrom(DamageSource.generic, 5);
    			}
    		}
    		return;
		}

		for(int i = 0; i < 8; i++ )
		{
			this.worldObj.spawnParticle("snowballpoof", this.posX, this.posY,
					this.posZ, 0.0D, 0.0D, 0.0D);
		}

		if(!this.worldObj.isRemote)
		{
			setDead();
			if(worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"))
				worldObj.newExplosion(this, posX, posY, posZ, 1f, false, true);
		}

		if((!this.isDead)
				&& (movingobjectposition.typeOfHit == MovingObjectType.BLOCK)
				&& (!this.worldObj.isRemote))
			setDead();
	}

	@Override
	protected float getGravityVelocity()
	{
		return 0.025F;
	}
	
	public void updateRiderPosition()
	{
		super.updateRiderPosition();
		this.riddenByEntity.posX = this.posX;
		this.riddenByEntity.posY = this.posY;
		this.riddenByEntity.posZ = this.posZ;
	}

	@Override
	public int getOwnerID()
	{
		return ownerID;
	}
}
