package com.gugu42.rcmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.gugu42.rcmod.tileentity.TileEntityShipFiller;

public class BlockShipFiller extends Block {

	public BlockShipFiller(Material p_i45394_1_) {
		super(p_i45394_1_);
		// TODO Auto-generated constructor stub
	}

	public boolean onBlockActivated(World par1World, int x, int y, int z,
			EntityPlayer par5EntityPlayer, int par6, float par7, float par8,
			float par9) {

		TileEntityShipFiller tileEntity = (TileEntityShipFiller) par1World
				.getTileEntity(x, y, z);
		if (tileEntity == null || par5EntityPlayer.isSneaking()) {
			return false;
		}

		tileEntity.activated(par5EntityPlayer, par1World);

		return true;

	}

	@Override
	public void breakBlock(World world, int i, int j, int k, Block p_149749_5_,
			int p_149749_6_) {
		if (world.getTileEntity(i, j, k) instanceof TileEntityShipFiller) {
			TileEntityShipFiller tileEntity = (TileEntityShipFiller) world
					.getTileEntity(i, j, k);
			if (tileEntity != null) {
				world.setBlockToAir(tileEntity.primary_x, tileEntity.primary_y,
						tileEntity.primary_z);
				world.removeTileEntity(tileEntity.primary_x,
						tileEntity.primary_y, tileEntity.primary_z);
			}

			world.removeTileEntity(i, j, k);
		}
		super.breakBlock(world, i, j, k, p_149749_5_, p_149749_6_);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z,
			Block block) {
		TileEntityShipFiller tileEntity = (TileEntityShipFiller) world
				.getTileEntity(x, y, z);
		if (tileEntity != null) {
			if (world.getTileEntity(tileEntity.primary_x, tileEntity.primary_y,
					tileEntity.primary_z) == null) {
				world.setBlockToAir(x, y, z);
				world.removeTileEntity(x, y, z);
			}
		}
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i,
			int j, int k, int l) {
		return false;
		// return true;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	public boolean hasTileEntity(int metadata) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityShipFiller();
	}

}
