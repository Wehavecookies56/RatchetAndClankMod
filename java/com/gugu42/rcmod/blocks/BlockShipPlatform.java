package com.gugu42.rcmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
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
			if(tileEntity.ownerName == ""){
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

	public void onBlockDestroyedByPlayer(World par1World, int par2, int par3, int par4, int par5) {
		TileEntity te = par1World.getTileEntity(par2, par3, par4);
		if (te instanceof TileEntityShipPlatform) {
			TileEntityShipPlatform tileEntity = (TileEntityShipPlatform) te;
			if (ShipSystem.isNameTaken(tileEntity.wpName))
				ShipSystem.removeWaypoint(tileEntity.wpName);
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
