package com.gugu42.rcmod.network.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.gugu42.rcmod.handler.ExtendedPlayerBolt;
import com.gugu42.rcmod.items.EnumRcWeapons;
import com.gugu42.rcmod.utils.ffmtutils.AbstractPacket;

public class PacketVend extends AbstractPacket {

	private int slot;
	private int page;

	public PacketVend() {

	}

	public PacketVend(int slot, int page) {
		this.slot = slot;
		this.page = page;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeInt(slot);
		buffer.writeInt(page);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		this.slot = buffer.readInt();
		this.page = buffer.readInt();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		ExtendedPlayerBolt props = ExtendedPlayerBolt.get(player);

		if (props.consumeBolts(EnumRcWeapons.getItemForPageAndSlot(this.page,
				this.slot).getPrice())) {
			player.inventory.addItemStackToInventory(new ItemStack(
					EnumRcWeapons.getItemForPageAndSlot(this.page, this.slot)
							.getWeapon(), 1, (EnumRcWeapons
							.getItemForPageAndSlot(this.page, this.slot)
							.getWeapon().getMaxDamage() / 2)));
		}

	}

}
