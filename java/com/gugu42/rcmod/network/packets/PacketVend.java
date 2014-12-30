package com.gugu42.rcmod.network.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;

import com.gugu42.rcmod.handler.ExtendedPlayerBolt;
import com.gugu42.rcmod.items.EnumRcWeapons;
import com.gugu42.rcmod.utils.ffmtutils.AbstractPacket;

public class PacketVend extends AbstractPacket {

	private int id;

	//	private int page;

	public PacketVend() {

	}

	public PacketVend(int id) {
		this.id = id;
		//		this.page = page;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeInt(id);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		this.id = buffer.readInt();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		ExtendedPlayerBolt props = ExtendedPlayerBolt.get(player);

		if (!player.inventory.hasItem(EnumRcWeapons.getItemFromID(this.id)
				.getWeapon())) {
			if (player.inventory.getFirstEmptyStack() >= 0 && props.currentBolt >= EnumRcWeapons.getItemFromID(this.id).getPrice() && player.inventory.addItemStackToInventory(new ItemStack(
					EnumRcWeapons.getItemFromID(this.id).getWeapon(), 1,
					(EnumRcWeapons.getItemFromID(this.id).getWeapon()
							.getMaxDamage() / 2))) && props.consumeBolts(EnumRcWeapons.getItemFromID(this.id)
					.getPrice())) {
				player.addChatMessage(new ChatComponentText(I18n.format("gui.vendor.buy.success")));
				player.worldObj.playSoundAtEntity(player, "rcmod:vendor.buy", 1.0f, 1.0f);
			} else {
				player.addChatMessage(new ChatComponentText(I18n.format("gui.vendor.buy.error")));
				player.worldObj.playSoundAtEntity(player, "rcmod:vendor.maxAmmo", 1.0f, 1.0f);
			}
		} else {
			player.addChatMessage(new ChatComponentText(I18n.format("gui.vendor.buy.alreadyhave")));
		}

	}

}
