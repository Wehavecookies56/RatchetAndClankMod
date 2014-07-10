package com.gugu42.rcmod.network.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import com.gugu42.rcmod.entity.OwnableEntity;
import com.gugu42.rcmod.utils.ffmtutils.AbstractPacket;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PacketUpdateOwnerID extends AbstractPacket
{

	private int entityAffected;
	private int ownerID;

	public PacketUpdateOwnerID()
	{
	}
	
	public PacketUpdateOwnerID(OwnableEntity entity, int ownerID)
	{
		this.entityAffected = entity.getOwnerID();
		this.ownerID = ownerID;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		buffer.writeInt(entityAffected);
		buffer.writeInt(ownerID);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		entityAffected = buffer.readInt();
		ownerID = buffer.readInt();
	}

	@Override
	public void handleClientSide(EntityPlayer player)
	{
		Entity e = player.worldObj.getEntityByID(entityAffected);
		if(e != null && e instanceof OwnableEntity)
			((OwnableEntity)e).setOwnerID(ownerID);
	}

	@Override
	public void handleServerSide(EntityPlayer player)
	{

	}

}
