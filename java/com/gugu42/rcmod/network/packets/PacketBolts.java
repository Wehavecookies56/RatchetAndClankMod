package com.gugu42.rcmod.network.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

import com.gugu42.rcmod.handler.ExtendedPlayerBolt;
import com.gugu42.rcmod.utils.ffmtutils.AbstractPacket;

public class PacketBolts extends AbstractPacket{

	private int maxBolts, bolts;
	
	public PacketBolts(){
		
	}
	
	public PacketBolts(int maxBolts, int bolts){
		this.maxBolts = maxBolts;
		this.bolts = bolts;
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeInt(maxBolts);
		buffer.writeInt(bolts);
		
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		this.maxBolts = buffer.readInt();
		this.bolts = buffer.readInt();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		ExtendedPlayerBolt props = ExtendedPlayerBolt
				.get(player);	
		props.maxBolts = this.maxBolts;
		props.currentBolt = this.bolts;
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		ExtendedPlayerBolt props = ExtendedPlayerBolt
				.get(player);
		props.maxBolts = this.maxBolts;
		props.currentBolt = this.bolts;
	}

}
