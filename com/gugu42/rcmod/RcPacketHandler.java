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
		if (packet.channel.equals("rcchannel")) {
			handleExtendedProperties(packet, player);
		}
	}

	private void handleExtendedProperties(Packet250CustomPayload packet,
			Player player) {
		DataInputStream inputStream = new DataInputStream(
				new ByteArrayInputStream(packet.data));
		ExtendedPlayerBolt props = ExtendedPlayerBolt
				.get((EntityPlayer) player);

		try {
			props.setMaxBolts(inputStream.readInt());
			props.setCurrentBolt(inputStream.readInt());
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
}
