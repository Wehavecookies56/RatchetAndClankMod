package com.gugu42.rcmod.handler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedEntityLivingBaseTarget implements IExtendedEntityProperties {
	public final static String EXT_PROP_NAME = "ExtendedLivingBaseTarget";

	private final EntityLivingBase entity;

	private boolean isTargeted;

	public ExtendedEntityLivingBaseTarget(EntityLivingBase entity) {
		this.entity = entity;
	}

	/**
	 * Used to register these extended properties for the entity during
	 * EntityConstructing event This method is for convenience only; it will
	 * make your code look nicer
	 */
	public static final void register(EntityLivingBase entity) {
		entity.registerExtendedProperties(ExtendedEntityLivingBaseTarget.EXT_PROP_NAME,
				new ExtendedEntityLivingBaseTarget(entity));
	}

	/**
	 * Returns ExtendedLiving properties for entity This method is for
	 * convenience only; it will make your code look nicer
	 */
	public static final ExtendedEntityLivingBaseTarget get(EntityLivingBase entity) {
		return (ExtendedEntityLivingBaseTarget) entity.getExtendedProperties(EXT_PROP_NAME);
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		compound.setBoolean("isTargeted", isTargeted);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		this.isTargeted = false;
	}

	@Override
	public void init(Entity entity, World world) {
		this.isTargeted = false;
	}
	
	public void setTargeted(boolean targeted){
		this.isTargeted = targeted;
	}
	
	public boolean isTargeted(){
		return isTargeted;
	}
}