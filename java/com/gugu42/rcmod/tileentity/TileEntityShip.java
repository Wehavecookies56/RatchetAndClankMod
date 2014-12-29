package com.gugu42.rcmod.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityShip extends TileEntity {

	public boolean hasLaunched = false;
	public int tickSinceLaunched = 0;
	public int blockRot;

	public float renderY = 0;
	public float renderAngle = 0;
	public float renderDoorRot = 0;

	public TileEntityShip() {
		super();
	}

	@Override
	public void updateEntity() {
		if (hasLaunched) {
			tickSinceLaunched++;
			if (tickSinceLaunched > 0) {
				if (tickSinceLaunched < 20) {
					renderDoorRot += 2.8f;
				} else if (tickSinceLaunched > 20 && tickSinceLaunched < 40) {
					renderY += 2.0f;
				} else if (tickSinceLaunched >= 40 && tickSinceLaunched < 50) {
					renderAngle -= 9.0f;
				} else if (tickSinceLaunched > 50) {
					renderY += 10.0f;
				} else {

				}
			}
		}

	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("rot", this.blockMetadata);
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		this.blockMetadata = par1NBTTagCompound.getInteger("rot");
		this.blockRot = par1NBTTagCompound.getInteger("rot");
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net,
			S35PacketUpdateTileEntity packet) {
		readFromNBT(packet.func_148857_g());
	}
}
