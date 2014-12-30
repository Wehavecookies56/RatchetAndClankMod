package com.gugu42.rcmod.tileentity;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.network.packets.PacketShipTeleportation;

public class TileEntityShip extends TileEntity {

	public boolean hasLaunched = false;
	public boolean isLanding = false;
	public int tickSinceLaunched = 0;
	public int tickSinceLanding = 0;
	public int blockRot;

	public float renderY = 0;
	public float renderAngle = 0;
	public float renderDoorRot = 0;

	public String wpData;

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
				} else if (tickSinceLaunched > 50 && tickSinceLaunched < 100) {
					renderY += 10.0f;
				} else if (tickSinceLaunched > 100) {
					PacketShipTeleportation packet = new PacketShipTeleportation(
							wpData, xCoord, yCoord, zCoord);
					RcMod.rcModPacketHandler.sendToServer(packet);
					this.worldObj.removeTileEntity(xCoord, yCoord, zCoord);
					this.worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air,
							0, 1);

					Block bl = this.worldObj.getBlock(xCoord, yCoord + 1,
							zCoord);
					bl.breakBlock(worldObj, xCoord, yCoord + 1, zCoord, bl, 0);
				}
			}
		}

		if (isLanding) {
			tickSinceLanding++;
			if (tickSinceLanding > 0) {
				if (tickSinceLanding <= 50) {
					renderY -= 10.0f;
				} else if (tickSinceLanding > 50 && tickSinceLanding <= 60) {
					renderAngle -= 9.0f;
				} else if (tickSinceLanding > 60 && tickSinceLanding <= 80) {
					renderY -= 2.0f;
				} else if (tickSinceLanding > 80 && tickSinceLanding < 100) {
					renderDoorRot -= 2.8f;
				} else if (tickSinceLanding > 100) {
					this.isLanding = false;
				}
			}
		}

	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("rot", this.blockMetadata);
		par1NBTTagCompound.setBoolean("isLanding", this.isLanding);
		par1NBTTagCompound.setFloat("renderAngle", this.renderAngle);
		par1NBTTagCompound.setFloat("renderDoorRot", this.renderDoorRot);
		par1NBTTagCompound.setFloat("renderY", this.renderY);
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		this.blockMetadata = par1NBTTagCompound.getInteger("rot");
		this.blockRot = par1NBTTagCompound.getInteger("rot");
		this.isLanding = par1NBTTagCompound.getBoolean("isLanding");
		this.renderAngle = par1NBTTagCompound.getFloat("renderAngle");
		this.renderDoorRot = par1NBTTagCompound.getFloat("renderDoorRot");
		this.renderY = par1NBTTagCompound.getFloat("renderY");
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
