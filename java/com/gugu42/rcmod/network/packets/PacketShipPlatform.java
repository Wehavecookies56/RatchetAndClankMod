package com.gugu42.rcmod.network.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.io.UnsupportedEncodingException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;

import com.gugu42.rcmod.shipsys.ShipSystem;
import com.gugu42.rcmod.shipsys.ShipWaypoint;
import com.gugu42.rcmod.tileentity.TileEntityShipPlatform;
import com.gugu42.rcmod.utils.ffmtutils.AbstractPacket;

public class PacketShipPlatform extends AbstractPacket {
	public String saveData;

	public PacketShipPlatform() {

	}

	public PacketShipPlatform(String saveData) {
		this.saveData = saveData;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		try {
			buffer.writeInt(saveData.length());
			buffer.writeBytes(saveData.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		int dataLength = buffer.readInt();
		ByteBuf textData = buffer.readBytes(dataLength);

		try {
			saveData = new String(textData.array(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		String[] infos = saveData.split(";");
		if (infos.length == 6) {
			TileEntity te = player.worldObj.getTileEntity(Integer.parseInt(infos[3]), Integer.parseInt(infos[4]), Integer.parseInt(infos[5]));
			if (te instanceof TileEntityShipPlatform) {
				TileEntityShipPlatform tileEntity = (TileEntityShipPlatform) te;
				tileEntity.setWpName(infos[0]);
				tileEntity.setOwnerName(infos[1]);
				if (infos[2].equals("True")) {
					tileEntity.setPrivate(true);
				} else {
					tileEntity.setPrivate(false);
				}
				
				int ypos = Integer.parseInt(infos[4]);
				String wpData = infos[0] + " " + infos[3] + " " + (ypos += 1) + " " + infos[5] + " " + infos[1] + " " + infos[2].toLowerCase();
				if (ShipSystem.isNameTaken(infos[0])) {
				} else {
					ShipWaypoint wp = new ShipWaypoint(wpData);
					if (wp != null) {
						ShipSystem.addWaypoint(wp);
					}
				}
			}
		}
	}

	@Override
	public void handleServerSide(EntityPlayer player) {

		String[] infos = saveData.split(";");
		if (infos.length == 6) {
			TileEntity te = player.worldObj.getTileEntity(Integer.parseInt(infos[3]), Integer.parseInt(infos[4]), Integer.parseInt(infos[5]));
			if (te instanceof TileEntityShipPlatform) {
				TileEntityShipPlatform tileEntity = (TileEntityShipPlatform) te;
				tileEntity.setWpName(infos[0]);
				tileEntity.setOwnerName(infos[1]);
				if (infos[2].equals("True")) {
					tileEntity.setPrivate(true);
				} else {
					tileEntity.setPrivate(false);
				}
				int ypos = Integer.parseInt(infos[4]);
				String wpData = infos[0] + " " + infos[3] + " " + (ypos += 1) + " " + infos[5] + " " + infos[1] + " " + infos[2].toLowerCase();
				if (ShipSystem.isNameTaken(infos[0])) {
					player.addChatMessage(new ChatComponentText("Name is already taken !"));
				} else {
					ShipWaypoint wp = new ShipWaypoint(wpData);
					if (wp != null) {
						ShipSystem.addWaypoint(wp);
						player.addChatMessage(new ChatComponentText("You waypoint is now activated !"));
					}
				}
			}
		}
	}
}
