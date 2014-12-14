package com.gugu42.rcmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.gugu42.rcmod.ClientProxy;
import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.tileentity.TileEntityVendor;

public class BlockVendor extends Block {

	public BlockVendor(Material par2Material) {
		super(par2Material);
		this.setCreativeTab(RcMod.rcTab);
	}

	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer par5EntityPlayer, EnumFacing side, float hitX, float hitY, float hitZ) {

		TileEntity tileEntity = world.getTileEntity(pos);
		if (tileEntity == null || par5EntityPlayer.isSneaking()) {
			return false;
		}
		
		par5EntityPlayer.openGui(RcMod.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
		world.playSoundAtEntity(par5EntityPlayer, "rcmod:MenuOpen", 1.0F, 1.0F);
//		par1World.playSoundAtEntity(par5EntityPlayer, "rcmod:VendorSalesman.vendor_speech_wait", 1.0f, 1.0f);
		return true;
	}

	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityVendor();
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
