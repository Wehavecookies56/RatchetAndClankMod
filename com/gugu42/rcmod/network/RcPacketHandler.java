package com.gugu42.rcmod.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.gugu42.rcmod.ContainerVendor;
import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.bolts.ExtendedPlayerBolt;
import com.gugu42.rcmod.items.EnumRcWeapons;
import com.gugu42.rcmod.items.ItemRcWeap;
import com.gugu42.rcmod.tileentity.TileEntityVendor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
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
		EntityPlayer sender = (EntityPlayer) player;

		if (packet.channel.equals("RCMD|bolt")) {
			handleExtendedProperties(packet, player);
		} else if (packet.channel.equals("RCMD|vend")) {
			handleVendor(packet, sender);
		} else if (packet.channel.equals("RCMD|refi")) {
			handleRefill(packet, sender);
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

	private void handleVendor(Packet250CustomPayload packet, EntityPlayer player) {
		try {
			DataInputStream datainputstream = new DataInputStream(
					new ByteArrayInputStream(packet.data));

			ExtendedPlayerBolt props = ExtendedPlayerBolt.get(player);
			int[] intData = new int[2];

			for(int i = 0; i < 2; i++){
				intData[i] = datainputstream.readInt();
			}
			
			
			
			if(props.consumeBolts(EnumRcWeapons.getItemForPageAndSlot(intData[0] , intData[1]).getPrice())){
				player.inventory.addItemStackToInventory(new ItemStack(EnumRcWeapons.getItemForPageAndSlot(intData[0], intData[1]).getWeapon(), 1, (EnumRcWeapons.getItemForPageAndSlot(intData[0], intData[1]).getWeapon().getMaxDamage() / 2)));
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	private void handleRefill(Packet250CustomPayload packet, EntityPlayer player) {
		try {
			DataInputStream datainputstream = new DataInputStream(
					new ByteArrayInputStream(packet.data));
			ContainerVendor containervendor = (ContainerVendor) player.openContainer;
			TileEntityVendor tev = containervendor.getVendor();
			ExtendedPlayerBolt props = ExtendedPlayerBolt.get(player);

			if (tev.getStackInSlot(1) != null
					&& tev.getStackInSlot(1).getItem() instanceof ItemRcWeap) {
				ItemRcWeap item = (ItemRcWeap) tev.getStackInSlot(1).getItem();
				props.consumeBolts(tev.getStackInSlot(1).getItemDamage() * item.getPrice());
				tev.getStackInSlot(1).setItemDamage(0);
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
