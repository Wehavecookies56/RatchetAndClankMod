package com.gugu42.rcmod.network.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.gugu42.rcmod.handler.ExtendedPropsSuckCannon;
import com.gugu42.rcmod.utils.ffmtutils.AbstractPacket;

import net.minecraft.entity.player.EntityPlayer;

public class PacketSuckCannonData extends AbstractPacket
{

	private List<String> data;

	public PacketSuckCannonData() // For Netty
	{
		data = new ArrayList<String>();
	}
	
	public PacketSuckCannonData(List<String> data)
	{
		this.data = data;
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		buffer.writeInt(data.size());
		for(String s : data)
		{
			try
			{
				buffer.writeInt(s.getBytes("UTF-8").length);
				buffer.writeBytes(s.getBytes("UTF-8"));
			}
			catch(UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
		}
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		int size = buffer.readInt();
		for(int i = 0;i<size;i++)
		{
			int textDataLength = buffer.readInt();
			ByteBuf textData = buffer.readBytes(textDataLength);
			try
			{
				data.add(new String(textData.array(), "UTF-8"));
			}
			catch(UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
		}
	}

	@Override
	public void handleClientSide(EntityPlayer player)
	{
		ExtendedPropsSuckCannon props = ExtendedPropsSuckCannon.get(player);
		Stack<String> stack = new Stack<String>();
		for(String json : data)
			stack.add(json);
		props.setStack(stack);
	}

	@Override
	public void handleServerSide(EntityPlayer player)
	{
		
	}

}
