package com.gugu42.rcmod.network.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.io.UnsupportedEncodingException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.shipsys.ShipWaypoint;
import com.gugu42.rcmod.tileentity.TileEntityShipFiller;
import com.gugu42.rcmod.utils.ffmtutils.AbstractPacket;

public class PacketShipTeleportation extends AbstractPacket {

	private String waypointData;

	public PacketShipTeleportation() {
		waypointData = "";
	}

	public PacketShipTeleportation(String data) {
		this.waypointData = data;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		try {
			buffer.writeInt(waypointData.length());
			buffer.writeBytes(waypointData.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		int dataLength = buffer.readInt();
		ByteBuf textData = buffer.readBytes(dataLength);
		try {
			waypointData = new String(textData.array(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void handleClientSide(EntityPlayer player) {

	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		ShipWaypoint wp = new ShipWaypoint(waypointData);

		player.setPositionAndUpdate(wp.posX, wp.posY + 5, wp.posZ);
		
		World world = player.worldObj;
		int x = wp.posX;
		int y = wp.posY;
		int z = wp.posZ;
		
		y -= 1;
		if (!world.isRemote) {
			y++;
			TileEntityShipFiller teShipFiller = null;
			int direction = MathHelper
					.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
			world.setBlock(x, y, z, RcMod.ship, direction, 2);
			boolean shouldPlaceBlock;

			for (int i = x - 2; i < x + 3; i++) {
				for (int j = y; j < y + 3; j++) {
					for (int k = z - 2; k < z + 3; k++) {
						shouldPlaceBlock = true;
						if (shouldPlaceBlock) {
							if (world.isAirBlock(i, j, k)) {
								world.setBlock(i, j, k, RcMod.shipFiller, 0, 2);
								teShipFiller = (TileEntityShipFiller) world
										.getTileEntity(i, j, k);
								if (teShipFiller != null) {
									teShipFiller.primary_x = x;
									teShipFiller.primary_y = y;
									teShipFiller.primary_z = z;
									
								} else {
									System.out.println("TileEntity is null !");
								}
							}
						}
					}
				}
			}

		}
	}

}
