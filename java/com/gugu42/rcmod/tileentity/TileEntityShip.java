package com.gugu42.rcmod.tileentity;

import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

import com.google.common.base.Function;
import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.network.packets.PacketShipTeleportation;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityShip extends TileEntity {

	public boolean hasLaunched = false;
	public boolean isLanding = false;
	public int tickSinceLaunched = 0;
	public int tickSinceLanding = 0;
	public int blockRot;

	public float renderX = 0;
	public float renderY = 0;
	public float renderZ = 0;
	public float pitch = 0;
	public float renderDoorRot = 0;

	public String wpData;
	
	private static Function<Long, Vector3f> offsetFunction;
	private static Function<Long, Float> glassPosFunction;
	
	static
	{
	    glassPosFunction = new Function<Long, Float>()
        {
            
            @Override
            public Float apply(Long tickSinceLaunched)
            {
                if(tickSinceLaunched < 20)
                {
                    return (tickSinceLaunched)*2.8f;
                }
                return (19)*2.8f;
            }
        };
        final Function<Float, Float> func = new Function<Float, Float>()
        {
            @Override
            public Float apply(Float input)
            {
                return input*input;
            }
        };
	    offsetFunction = new Function<Long, Vector3f>()
        {
            @Override
            public Vector3f apply(Long tickSinceLaunched)
            {
                float hOffset = 0;
                float yOffset = 0;
                float angle = 0f;
                /*if (tickSinceLaunched > 20 && tickSinceLaunched < 40) 
                {
                    yOffset = (tickSinceLaunched-20)*2.0f;
                }
                else if (tickSinceLaunched >= 40 && tickSinceLaunched < 50) 
                {
                    yOffset = (40-1-20)*2.0f;
                    angle = (tickSinceLaunched-40)*(-9.0f);
                }
                else if (tickSinceLaunched >= 50 && tickSinceLaunched <= 100) 
                {
                    yOffset = (tickSinceLaunched-50)*10.0f + ((50-1-20)*2.0f);
                }*/
                if(tickSinceLaunched <= 20)
                {
                    hOffset = tickSinceLaunched/4f;
                    yOffset = hOffset * 3f;
                }
                else if(tickSinceLaunched > 20)
                {
                    float x = (tickSinceLaunched-20)/2f;
                    Vector2f v1 = new Vector2f(x,func.apply(x));
                    
                    hOffset = 20/4f;
                    yOffset = hOffset * 3f;
                    
                    hOffset += (v1.x)*5f;
                    yOffset += v1.y*2f;
                    v1.y /= 2f;
                    angle = -(float)Math.toDegrees(v1.angle(new Vector2f(1,0)));
                }
                return new Vector3f(hOffset, yOffset, angle);
            }
        };
	}

	public TileEntityShip() {
		super();
	
	}

	@Override
	public void updateEntity() {
		if (hasLaunched) {
			tickSinceLaunched++;
			if(tickSinceLaunched <= 100)
			{
			    handleRotationNStuff(tickSinceLaunched);
			}
			else
			{
				PacketShipTeleportation packet = new PacketShipTeleportation(
						wpData, xCoord, yCoord, zCoord);
				RcMod.rcModPacketHandler.sendToServer(packet);
				this.worldObj.removeTileEntity(xCoord, yCoord, zCoord);
				this.worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air,
						0, 1);

				Block bl = this.worldObj.getBlock(xCoord, yCoord + 1,
						zCoord);
				bl.breakBlock(worldObj, xCoord, yCoord + 1, zCoord, bl, 0);
			}
		}

		if (isLanding) {
			tickSinceLanding++;
			if (tickSinceLanding > 0) {
				if(tickSinceLanding <= 100)
				{
				    handleRotationNStuff(100-tickSinceLanding);
				    pitch = -pitch;
				    renderX = -renderX;
				    renderZ = -renderZ;
				}
				else if (tickSinceLanding > 100) 
				{
					this.isLanding = false;
				}
			}
		}
	}

	private void handleRotationNStuff(int tickSinceLaunched)
    {
	    renderDoorRot = glassPosFunction.apply((long)tickSinceLaunched);
        Vector3f offsetData = offsetFunction.apply((long)tickSinceLaunched);
        float xOffsetMultiplier = 0f;
        float zOffsetMultiplier = 0f;
        this.renderY = offsetData.y;
        switch(blockMetadata)
        {
            case 0:
            {
                zOffsetMultiplier = 1f;
            }
            break;
            
            case 1:
            {
                xOffsetMultiplier = -1f;
            }
            break;
            
            case 2:
            {
                zOffsetMultiplier = -1f;
            }
            break;
            
            case 3:
            {
                xOffsetMultiplier = 1f;
            }
            break;
        }
        this.renderX = xOffsetMultiplier*offsetData.x;
        this.renderZ = zOffsetMultiplier*offsetData.x;
        pitch = offsetData.z;
    }

    @Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("rot", this.blockMetadata);
		par1NBTTagCompound.setBoolean("isLanding", this.isLanding);
		par1NBTTagCompound.setFloat("renderAngle", this.pitch);
		par1NBTTagCompound.setFloat("renderDoorRot", this.renderDoorRot);
		par1NBTTagCompound.setFloat("renderY", this.renderY);
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		this.blockMetadata = par1NBTTagCompound.getInteger("rot");
		this.blockRot = par1NBTTagCompound.getInteger("rot");
		this.isLanding = par1NBTTagCompound.getBoolean("isLanding");
		this.pitch = par1NBTTagCompound.getFloat("renderAngle");
		this.renderDoorRot = par1NBTTagCompound.getFloat("renderDoorRot");
		this.renderY = par1NBTTagCompound.getFloat("renderY");
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
}
