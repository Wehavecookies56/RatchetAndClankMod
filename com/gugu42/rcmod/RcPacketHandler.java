package com.gugu42.rcmod;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.gugu42.rcmod.bolts.ExtendedPlayerBolt;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class RcPacketHandler implements IPacketHandler {
	// Don't need to do anything here.
	public RcPacketHandler() {
	}

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		// This is a good place to parse through channels if you have multiple
		// channels
		if (packet.channel.equals("rcchannel")) {
			handleExtendedProperties(packet, player);
		}
	}

	// Making different methods to handle each channel helps keep things tidy:
	private void handleExtendedProperties(Packet250CustomPayload packet,
			Player player) {
		DataInputStream inputStream = new DataInputStream(
				new ByteArrayInputStream(packet.data));
		ExtendedPlayerBolt props = ExtendedPlayerBolt
				.get((EntityPlayer) player);

		// Everything we read here should match EXACTLY the order in which we
		// wrote it
		// to the output stream in our ExtendedPlayer sync() method.
		try {
			props.setMaxBolts(inputStream.readInt());
			props.setCurrentBolt(inputStream.readInt());
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		// Just so you can see in the console that it's working:
		System.out.println("[PACKET] Bolts from packet: "
				+ props.getCurrentBolt());
	}
}
