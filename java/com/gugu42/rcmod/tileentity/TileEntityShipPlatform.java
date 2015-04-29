package com.gugu42.rcmod.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityShipPlatform extends TileEntity {

	public String  ownerName;
	public String  wpName;
	public boolean isPrivate;

	public TileEntityShipPlatform() {
		super();
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setString("oName", ownerName);
		par1NBTTagCompound.setString("wpName", wpName);
		par1NBTTagCompound.setBoolean("isPrivate", isPrivate);
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		this.ownerName = par1NBTTagCompound.getString("oName");
		this.wpName = par1NBTTagCompound.getString("wpName");
		this.isPrivate = par1NBTTagCompound.getBoolean("isPrivate");
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
		readFromNBT(packet.func_148857_g());
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public void setWpName(String wpName) {
		this.wpName = wpName;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

}
