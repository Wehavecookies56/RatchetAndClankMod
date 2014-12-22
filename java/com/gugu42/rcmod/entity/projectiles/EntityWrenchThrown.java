package com.gugu42.rcmod.entity.projectiles;

import java.util.List;

import com.gugu42.rcmod.RcMod;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.IThrowableEntity;

public class EntityWrenchThrown extends EntityThrowable implements
		IThrowableEntity {

	private boolean isReturningToOwner = false;

	private ItemStack itemThrown;

	public EntityWrenchThrown(World world) {
		super(world);
	}

	public EntityWrenchThrown(World p_i1777_1_, EntityLivingBase p_i1777_2_) {
		super(p_i1777_1_, p_i1777_2_);
	}

	public EntityWrenchThrown(World p_i1777_1_, EntityLivingBase p_i1777_2_,
			ItemStack itemstack) {
		super(p_i1777_1_, p_i1777_2_);
		this.itemThrown = itemstack;
	}

	@Override
	public void setThrower(Entity entity) {

	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		if (!this.worldObj.isRemote) {
			if (this.ticksExisted > 1) {
				if (mop.entityHit != null
						&& mop.entityHit instanceof EntityLiving) {
					if (mop.entityHit != this.getThrower()) {
						mop.entityHit.attackEntityFrom(DamageSource
								.causePlayerDamage((EntityPlayer) this
										.getThrower()), 5f);
						this.setReturningToOwner(true);
					}
				} else if (worldObj
						.getBlock(mop.blockX, mop.blockY, mop.blockZ) != null) {
					if (worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ) == RcMod.crate
							|| worldObj.getBlock(mop.blockX, mop.blockY,
									mop.blockZ) == RcMod.tntCrate) {
						//Continue
					} else {
						this.setReturningToOwner(true);
					}
				}
			}
		}

	}

	public void setReturningToOwner(boolean bool) {
		this.isReturningToOwner = bool;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (this.ticksExisted >= 25) {
			this.setReturningToOwner(true);
		}

		if (!this.worldObj.isRemote) {
			if (this.ticksExisted >= 60) {
				if (this.getThrower() instanceof EntityPlayer) {
					EntityPlayer owner = (EntityPlayer) this.getThrower();
					if (itemThrown != null) {
						worldObj.spawnEntityInWorld(new EntityItem(worldObj,
								this.posX, this.posY, this.posZ, itemThrown));
					}

				}
				this.setDead();
			}
		}

		if (!this.worldObj.isRemote) {
			if (this.getThrower() == null || this.getThrower().isDead) {
				this.setDead();
			}
		}

		if (this.isReturningToOwner) {
			returnToOwner();
		}

		if (this.isReturningToOwner) {
			List entityTagetList = this.worldObj.getEntitiesWithinAABB(
					Entity.class, this.boundingBox.expand(1.0D, 1.0D, 1.0D));
			for (int i = 0; i < entityTagetList.size(); i++) {
				Entity entityTarget = (Entity) entityTagetList.get(i);
				if (entityTarget != null
						&& entityTarget instanceof EntityPlayer) {
					EntityPlayer owner = (EntityPlayer) entityTarget;
					if (itemThrown != null) {
						int slot = owner.inventory.getFirstEmptyStack();
						if(slot >= 0){
							owner.inventory.mainInventory[slot] = itemThrown.copy();
						} else {
							worldObj.spawnEntityInWorld(new EntityItem(
							worldObj, this.posX, this.posY, this.posZ,
							itemThrown));
						}
					}
					this.setDead();
				}
			}
		}
		
		if(!worldObj.isRemote)
		{
    		destroyBoltCrates();
    		destroyTNTCrates();
		}

	}

	@Override
	protected float getGravityVelocity() {
		return 0.00F;
	}
	
	private void destroyBoltCrates() {
		int x = MathHelper.floor_double(posX);
		int y = MathHelper.floor_double(posY);
		int z = MathHelper.floor_double(posZ);
		Block block = worldObj.getBlock(x, y, z);
		if (block == RcMod.crate) {
			block.dropBlockAsItem(worldObj, x, y, z,
					this.worldObj.getBlockMetadata(x, y, z), 0);
			this.worldObj.setBlock(x, y, z, Blocks.air);
		}
	}

	private void destroyTNTCrates() {
		int x = MathHelper.floor_double(posX);
		int y = MathHelper.floor_double(posY);
		int z = MathHelper.floor_double(posZ);
		Block block = worldObj.getBlock(x, y, z);
		if (block == RcMod.tntCrate) {
			block.dropBlockAsItem(worldObj, x, y, z,
					this.worldObj.getBlockMetadata(x, y, z), 0);
			this.worldObj.setBlock(x, y, z, Blocks.air);
		}
	}

	public void returnToOwner() {
		if (!this.worldObj.isRemote) {
			if (this.getThrower() == null) {
				this.setDead();
			} else {
				double newX = this.getThrower().posX - this.posX;
				double newY = (this.getThrower().posY + 1) - this.posY;
				double newZ = this.getThrower().posZ - this.posZ;
				setThrowableHeading(newX, newY, newZ, 1.5F, 0.0F);
			}
		}
	}

}