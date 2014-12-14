package com.gugu42.rcmod.shipsys;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;

public class RcWorldSavedData extends WorldSavedData {

	public int keyMaxNumber = 1;
	public static String key = "rcmod";

	public RcWorldSavedData() {
		super(key);
	}

	public RcWorldSavedData(String key) {
		super(key);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttag) {
		ShipSystem.fetchSaveData(nbttag.getString("waypoints"));
	}

	@Override
	public void writeToNBT(NBTTagCompound nbttag) {
		nbttag.setString("waypoints", ShipSystem.getSaveData());
	}

	public static RcWorldSavedData forWorld(World world) {
		// Retrieves this class instance for the given world, creating it if necessary
		MapStorage storage = world.getMapStorage();
		RcWorldSavedData result = (RcWorldSavedData) storage.loadData(
				RcWorldSavedData.class, key);
		if (result == null) {
			result = new RcWorldSavedData();
			storage.setData(key, result);
		}
		return result;
	}

}
