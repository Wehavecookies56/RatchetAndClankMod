package com.gugu42.rcmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.tileentity.TileEntityShipFiller;

public class BlockShipFiller extends Block {

	public BlockShipFiller(Material p_i45394_1_) {
		super(p_i45394_1_);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onBlockActivated(World par1World, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {

		TileEntity tileEntity = par1World.getTileEntity(pos);

		if (tileEntity == null || player.isSneaking()) {
			return false;
		}

		player.openGui(RcMod.instance, 0, par1World, pos.getX(), pos.getY(), pos.getZ());

		return true;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		if (world.getTileEntity(pos) instanceof TileEntityShipFiller) {
			TileEntityShipFiller tileEntity = (TileEntityShipFiller) world
					.getTileEntity(pos);
			if (tileEntity != null) {
				world.setBlockState(new BlockPos(tileEntity.primary_x, tileEntity.primary_y,
						tileEntity.primary_z), Blocks.air.getDefaultState());
				world.removeTileEntity(new BlockPos(tileEntity.primary_x,
						tileEntity.primary_y, tileEntity.primary_z));
			}

			world.removeTileEntity(pos);
		}
		super.breakBlock(world, pos, state);
	}

	@Override
	public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block neighborBlock) {
		TileEntityShipFiller tileEntity = (TileEntityShipFiller) world
				.getTileEntity(pos);
		if (tileEntity != null) {
			if (world.getTileEntity(new BlockPos(tileEntity.primary_x, tileEntity.primary_y,
					tileEntity.primary_z)) == null) {
				world.setBlockToAir(pos);
				world.removeTileEntity(pos);
			}
		}
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
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
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityShipFiller();
	}

}
