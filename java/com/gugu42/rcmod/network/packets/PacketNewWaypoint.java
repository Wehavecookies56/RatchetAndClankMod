package com.gugu42.rcmod.network.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.io.UnsupportedEncodingException;

import net.minecraft.entity.player.EntityPlayer;

import com.gugu42.rcmod.shipsys.ShipSystem;
import com.gugu42.rcmod.shipsys.ShipWaypoint;
import com.gugu42.rcmod.utils.ffmtutils.AbstractPacket;

public class PacketNewWaypoint extends AbstractPacket {

	public String wpData;

	public PacketNewWaypoint() {

	}

	public PacketNewWaypoint(String wpData) {
		this.wpData = wpData;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		try {
			buffer.writeInt(wpData.length());
			buffer.writeBytes(wpData.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		int dataLength = buffer.readInt();
		ByteBuf textData = buffer.readBytes(dataLength);

		try {
			wpData = new String(textData.array(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		ShipSystem.addWaypoint(new ShipWaypoint(wpData));
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		ShipSystem.addWaypoint(new ShipWaypoint(wpData));
	}

}
