package com.gugu42.rcmod.network.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

import com.gugu42.rcmod.handler.ExtendedPlayerTarget;
import com.gugu42.rcmod.utils.ffmtutils.AbstractPacket;

public class PacketTarget extends AbstractPacket{

	private boolean hasDevastatorTarget;
	private double devastatorTargetPosX, devastatorTargetPosY, devastatorTargetPosZ;
	
	public PacketTarget(){
		
	}
	
	public PacketTarget(boolean hasDevTar, double devTarPosX, double devTarPosY, double devTarPosZ){
		this.hasDevastatorTarget = hasDevTar;
		this.devastatorTargetPosX = devTarPosX;
		this.devastatorTargetPosY = devTarPosY;
		this.devastatorTargetPosZ = devTarPosZ;
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeBoolean(hasDevastatorTarget);
		buffer.writeDouble(devastatorTargetPosX);
		buffer.writeDouble(devastatorTargetPosY);
		buffer.writeDouble(devastatorTargetPosZ);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		this.hasDevastatorTarget = buffer.readBoolean();
		this.devastatorTargetPosX = buffer.readDouble();
		this.devastatorTargetPosY = buffer.readDouble();
		this.devastatorTargetPosZ = buffer.readDouble();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		ExtendedPlayerTarget props = ExtendedPlayerTarget
				.get(player);	
		props.hasDevastatorTarget = this.hasDevastatorTarget;
		props.devastatorTargetPosX = this.devastatorTargetPosX;
		props.devastatorTargetPosY = this.devastatorTargetPosY;
		props.devastatorTargetPosZ = this.devastatorTargetPosZ;
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		ExtendedPlayerTarget props = ExtendedPlayerTarget
				.get(player);
		props.hasDevastatorTarget = this.hasDevastatorTarget;
		props.devastatorTargetPosX = this.devastatorTargetPosX;
		props.devastatorTargetPosY = this.devastatorTargetPosY;
		props.devastatorTargetPosZ = this.devastatorTargetPosZ;
	}

}
