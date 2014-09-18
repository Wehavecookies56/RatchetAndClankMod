package com.gugu42.rcmod.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import com.gugu42.rcmod.ClientProxy;
import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.shipsys.RcWorldSavedData;
import com.gugu42.rcmod.tileentity.TileEntityShip;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockShip extends Block {

	public BlockShip(Material p_i45394_1_) {
		super(p_i45394_1_);
		this.setCreativeTab(RcMod.rcTab);
		this.setBlockBounds(-3f, 0, -3f, 3f, 3f, 3f);
	}

	public boolean onBlockActivated(World par1World, int x, int y, int z,
			EntityPlayer par5EntityPlayer, int par6, float par7, float par8,
			float par9) {

		 TileEntity tileEntity = par1World.getTileEntity(x, y, z);
		 if (tileEntity == null || par5EntityPlayer.isSneaking()) {
			 return false;
		 }
		 
		 par5EntityPlayer.openGui(RcMod.instance, 0, par1World, x, y, z);
		 
		return true;
	}


	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityShip();
	}

	public boolean hasTileEntity(int metadata) {
		return true;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public int getRenderType() {
		return ClientProxy.renderInventoryTESRId;
	}

}
