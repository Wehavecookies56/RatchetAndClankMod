package com.gugu42.rcmod.tileentity;

import com.gugu42.rcmod.RcMod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityShipFiller extends TileEntity {

	public int primary_x;
	public int primary_y;
	public int primary_z;

	public TileEntityShipFiller(){
		super();
	}
	
	
    @Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("px", primary_x);
		par1NBTTagCompound.setInteger("py", primary_y);
		par1NBTTagCompound.setInteger("pz", primary_z);
	}

    @Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		this.primary_x = par1NBTTagCompound.getInteger("px");
		this.primary_y = par1NBTTagCompound.getInteger("py");
		this.primary_z = par1NBTTagCompound.getInteger("pz");
	}
    
    @Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net,
			S35PacketUpdateTileEntity packet) {
		readFromNBT(packet.func_148857_g());
	}
    
    public TileEntity getOriginalTileEntity(){
    	TileEntity te = this.worldObj.getTileEntity(primary_x, primary_y, primary_z);
    	return te;
    }
    
    public void activated(EntityPlayer player, World world){
    	player.openGui(RcMod.instance, 0, world, primary_x, primary_y, primary_z);
//    	System.out.println("Should have opened the GUI .." + primary_x + " " + primary_y + " " + primary_z);
    }
}
