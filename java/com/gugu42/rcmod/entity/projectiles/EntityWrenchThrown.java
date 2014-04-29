package com.gugu42.rcmod.entity.projectiles;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.items.RcItems;

import cpw.mods.fml.common.registry.IThrowableEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityWrenchThrown extends EntityThrowable implements
		IThrowableEntity {

	public EntityPlayer owner;
	private boolean isReturningToOwner;
	private int maxTicksBeforeReturn = 10;
	private int ticksLived;

	private ItemStack originalItemThrown;

	public EntityWrenchThrown(World par1World) {
		super(par1World);
		this.isReturningToOwner = false;
		this.ticksLived = 0;
		
	}

	public EntityWrenchThrown(World par1World,
			EntityLivingBase par2EntityLivingBase) {
		super(par1World, par2EntityLivingBase);
		this.isReturningToOwner = false;
		this.ticksLived = 0;
		this.owner = (EntityPlayer) par2EntityLivingBase;
	}

	public EntityWrenchThrown(World par1World, EntityPlayer par2EntityPlayer,
			ItemStack par3ItemStack) {
		super(par1World, (EntityLivingBase) par2EntityPlayer);
		this.isReturningToOwner = false;
		this.ticksLived = 0;
		this.owner = (EntityPlayer) par2EntityPlayer;
		this.originalItemThrown = par3ItemStack;
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		if (ticksLived <= 1) {
		} else {
			if (mop.entityHit != null && mop.entityHit == owner) {
				this.setDead();
				if (!owner.capabilities.isCreativeMode) {
					if (originalItemThrown != null) {
						originalItemThrown.stackSize += 1;
						System.out.println(originalItemThrown.toString());
						if (!owner.inventory
								.addItemStackToInventory(originalItemThrown)) {
							worldObj.spawnEntityInWorld(new EntityItem(
									worldObj, posX, posY, posZ,
									originalItemThrown));
							System.out.println("Dropped on the ground ! ");
						} else {
							System.out.println("Added to the inventory !");
						}
					}
				}
			} else if (worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ) != null && worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ) == RcMod.crate
					|| worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ) == RcMod.tntCrate) {
				// Continue
			}
			else if(mop.entityHit != null)
			{
			    mop.entityHit.attackEntityFrom(DamageSource.causePlayerDamage(owner), 5f);
			}
		    else {
				this.setReturningToOwner(true);
			}
		}
	}

	public void onUpdate() {
		super.onUpdate();
		if (ticksLived <= 1) 
		{
		    if(owner == null)
		        owner = worldObj.getClosestPlayer(posX, posY, posZ, 2);
		}
		ticksLived++;
		if (owner == null || owner.isDead) {
			this.setDead();
		}

		if (ticksLived >= maxTicksBeforeReturn) {
			this.setReturningToOwner(true);
		}
		if (isReturningToOwner) {
			returnToOwner();

		}
		if (ticksLived == (maxTicksBeforeReturn * 2) + 2) {
			this.setDead();
		}

		if(!worldObj.isRemote)
		{
    		destroyBoltCrates();
    		destroyTNTCrates();
		}

	}

	protected void returnToOwner() {

		if (owner == null) {
			this.setDead();
		} else {
			double newX = owner.posX - this.posX;
			double newY = owner.posY - this.posY;
			double newZ = owner.posZ - this.posZ;
			setThrowableHeading(newX, newY, newZ, this.func_70182_d(), 0.0F);
		}

	}

	public boolean isReturningToOwner() {
		return isReturningToOwner;
	}

	public void setReturningToOwner(boolean isReturningToOwner) {
		this.isReturningToOwner = isReturningToOwner;
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

	@Override
	protected float getGravityVelocity() {
		return 0.00F;
	}

	@Override
	public void setThrower(Entity entity) {

	}

}
