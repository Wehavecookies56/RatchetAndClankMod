package com.gugu42.rcmod.network.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.handler.ExtendedPropsSuckCannon;
import com.gugu42.rcmod.utils.ffmtutils.AbstractPacket;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;

public class PacketSuckCannonData extends AbstractPacket
{

    private List<NBTTagCompound> data;

    public PacketSuckCannonData() // For Netty
    {
        data = new ArrayList<NBTTagCompound>();
    }

    public PacketSuckCannonData(ArrayList<NBTTagCompound> list)
    {
        this.data = list;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
    {
        try
        {
            buffer.writeInt(data.size());
            PacketBuffer packBuff = new PacketBuffer(buffer);
            for(NBTTagCompound nbt : data)
            {
                packBuff.writeNBTTagCompoundToBuffer(nbt);
            }
        }
        catch(Exception e)
        {
            RcMod.rcLogger.error("Error while encoding packet SuckCannonData", e);
        }
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
    {
        PacketBuffer packBuff = new PacketBuffer(buffer);
        int size = buffer.readInt();
        for(int i = 0; i < size; i++)
        {
            try
            {
                data.add(packBuff.readNBTTagCompoundFromBuffer());
            }
            catch(Exception e)
            {
                RcMod.rcLogger.error("Error while decoding packet SuckCannonData", e);
            }
        }
    }

    @Override
    public void handleClientSide(EntityPlayer player)
    {
        ExtendedPropsSuckCannon props = ExtendedPropsSuckCannon.get(player);
        Stack<NBTTagCompound> stack = new Stack<NBTTagCompound>();
        for(NBTTagCompound compound : data)
            stack.add(compound);
        props.setStack(stack);
    }

    @Override
    public void handleServerSide(EntityPlayer player)
    {
        ExtendedPropsSuckCannon props = ExtendedPropsSuckCannon.get(player);
        Stack<NBTTagCompound> stack = new Stack<NBTTagCompound>();
        for(NBTTagCompound compound : data)
            stack.add(compound);
        props.setStack(stack);
    }

}
