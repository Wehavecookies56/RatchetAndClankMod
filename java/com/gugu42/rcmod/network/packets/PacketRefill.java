package com.gugu42.rcmod.network.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTTagCompound;

import com.gugu42.rcmod.ContainerVendor;
import com.gugu42.rcmod.handler.ExtendedPlayerBolt;
import com.gugu42.rcmod.items.ItemRcWeap;
import com.gugu42.rcmod.tileentity.TileEntityVendor;
import com.gugu42.rcmod.utils.ffmtutils.AbstractPacket;

public class PacketRefill extends AbstractPacket {

	private ItemStack is;

	public PacketRefill() {

	}

	public PacketRefill(ItemStack is) {

	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream outputStream = new DataOutputStream(baos);

			if (this.is == null) {
				outputStream.writeUTF("null");
			} else {
				NBTTagCompound nbt = new NBTTagCompound();
				this.is.writeToNBT(nbt);
				outputStream.writeUTF(nbt.toString());
			}

			outputStream.flush();
			baos.flush();
			outputStream.close();
			baos.close();
			byte[] data = baos.toByteArray();
			buffer.writeBytes(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		try {
			byte[] readBytes = new byte[buffer.readableBytes()];
			buffer.readBytes(readBytes);
			ByteArrayInputStream bais = new ByteArrayInputStream(readBytes);
			DataInputStream inputStream = new DataInputStream(bais);

			String str = inputStream.readUTF();
			if (!str.equals("null"))
				this.is = ItemStack
						.loadItemStackFromNBT((NBTTagCompound) JsonToNBT
								.func_150315_a(str));
			inputStream.close();
			bais.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		ContainerVendor containervendor = (ContainerVendor) player.openContainer;
		TileEntityVendor tev = containervendor.getVendor();
		ExtendedPlayerBolt props = ExtendedPlayerBolt.get(player);

		if (tev.getStackInSlot(1) != null
				&& tev.getStackInSlot(1).getItem() instanceof ItemRcWeap) {
			ItemRcWeap item = (ItemRcWeap) tev.getStackInSlot(1).getItem();
			props.consumeBolts(tev.getStackInSlot(1).getItemDamage()
					* item.getPrice());
			tev.getStackInSlot(1).setItemDamage(0);
		}
	}

}
