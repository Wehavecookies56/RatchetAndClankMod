package com.gugu42.rcmod.network.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.io.UnsupportedEncodingException;

import net.minecraft.entity.player.EntityPlayer;

import com.gugu42.rcmod.shipsys.ShipWaypoint;
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

		player.setPositionAndUpdate(wp.posX, wp.posY, wp.posZ);
	}

}
