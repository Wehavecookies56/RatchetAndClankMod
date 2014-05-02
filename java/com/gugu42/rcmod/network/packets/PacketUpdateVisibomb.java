package com.gugu42.rcmod.network.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.utils.ffmtutils.AbstractPacket;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PacketUpdateVisibomb extends AbstractPacket
{

    private double mx;
    private double my;
    private double mz;
    private float yaw;
    private float pitch;
    private int id;
    private int ownerID;

    public PacketUpdateVisibomb(){}
    
    public PacketUpdateVisibomb(double motionX, double motionY, double motionZ,
            float rotationYaw, float rotationPitch, int id, int id2)
    {
        this.id = id;
        this.ownerID = id2;
        mx = motionX;
        my = motionY;
        mz = motionZ;
        yaw = rotationYaw;
        pitch = rotationPitch;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
    {
        buffer.writeDouble(mx);
        buffer.writeDouble(my);
        buffer.writeDouble(mz);
        buffer.writeFloat(yaw);
        buffer.writeFloat(pitch);
        buffer.writeInt(id);
        buffer.writeInt(ownerID);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
    {
        mx = buffer.readDouble();
        my = buffer.readDouble();
        mz = buffer.readDouble();
        yaw = buffer.readFloat();
        pitch = buffer.readFloat();
        id = buffer.readInt();
        ownerID = buffer.readInt();
    }

    @Override
    public void handleClientSide(EntityPlayer player)
    {
        handleBothSides(player);
    }

    @Override
    public void handleServerSide(EntityPlayer player)
    {
        handleBothSides(player);
        RcMod.rcModPacketHandler.sendToAll(this);
    }

    private void handleBothSides(EntityPlayer player)
    {
        Entity e = player.worldObj.getEntityByID(id);
        if(e != null)
        {
            e.motionX = mx;
            e.motionY = my;
            e.motionZ = mz;
            e.rotationYaw = yaw;
            e.rotationPitch = pitch;
        }
        
        e = player.worldObj.getEntityByID(ownerID);
        if(e != null)
        {
            e.rotationYaw = yaw;
            e.rotationPitch = pitch;
        }
    }

}
