package com.gugu42.rcmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.shipsys.ShipSystem;
import com.gugu42.rcmod.tileentity.TileEntityShipPlatform;

public class BlockShipPlatform extends Block {

	public BlockShipPlatform() {
		super(Material.iron);
	}

	public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		if (par5EntityPlayer.isSneaking()) {
			return false;
		}

		TileEntity te = par1World.getTileEntity(x, y, z);
		if (te instanceof TileEntityShipPlatform) {
			TileEntityShipPlatform tileEntity = (TileEntityShipPlatform) te;
			if (tileEntity.ownerName == "") {
				tileEntity.setOwnerName(par5EntityPlayer.getDisplayName());
			}
		}

		par5EntityPlayer.openGui(RcMod.instance, 4, par1World, x, y, z);
		return true;
	}

	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityShipPlatform();
	}

	public boolean hasTileEntity(int metadata) {
		return true;
	}

	public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z)
    {
		if (!world.isRemote) {
			TileEntity te = world.getTileEntity(x, y, z);
			if (te instanceof TileEntityShipPlatform) {
				TileEntityShipPlatform tileEntity = (TileEntityShipPlatform) te;
				
				if (player.getDisplayName().equals(tileEntity.ownerName) && ShipSystem.isNameTaken(tileEntity.wpName)) {
					ShipSystem.removeWaypoint(tileEntity.wpName);
					player.addChatMessage(new ChatComponentText("Your waypoint \"" + tileEntity.wpName + "\" was deleted !"));
					return world.setBlockToAir(x, y, z);
				} else if (player.capabilities.isCreativeMode){
					player.addChatMessage(new ChatComponentText("This waypoint does not belong to you ! Since you're in creative, I let you break it. Please tell " + tileEntity.ownerName + " that you did it."));
					return world.setBlockToAir(x, y, z);
				} else {
					player.addChatMessage(new ChatComponentText("This waypoint does not belong to you !!!"));
					return false;
				}
			} else {
				return world.setBlockToAir(x, y, z);
			}
		} else {
			return false;
		}
		
		
	}

	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		super.onBlockAdded(par1World, par2, par3, par4);

		TileEntity te = par1World.getTileEntity(par2, par3, par4);
		if (te instanceof TileEntityShipPlatform) {
			TileEntityShipPlatform tileEntity = (TileEntityShipPlatform) te;
			tileEntity.setOwnerName("");
			tileEntity.setWpName("Waypoint name");
		}

	}

}
