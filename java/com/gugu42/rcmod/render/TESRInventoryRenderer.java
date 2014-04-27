package com.gugu42.rcmod.render;

import java.util.HashMap;

import com.gugu42.rcmod.ClientProxy;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class TESRInventoryRenderer implements ISimpleBlockRenderingHandler {

	public static class TESRIndex {
		Block block;
		int metadata;

		public TESRIndex(Block block, int metadata) {
			this.block = block;
			this.metadata = metadata;
		}

		@Override
		public int hashCode() {
			return block.hashCode() + metadata;
		}

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof TESRIndex))
				return false;

			TESRIndex tesr = (TESRIndex) o;

			return tesr.block == block && tesr.metadata == metadata;
		}
	}

	public static HashMap<TESRIndex, IInventoryRenderer> blockByTESR = new HashMap<TESRIndex, IInventoryRenderer>();

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
		TESRIndex index = new TESRIndex(block, metadata);
		if(blockByTESR.containsKey(index))
		{
			blockByTESR.get(index).renderInventory(-0.5, -0.5, -0.5);
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getRenderId() {
		// TODO Auto-generated method stub
		return ClientProxy.renderInventoryTESRId;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		// TODO Auto-generated method stub
		return true;
	}

}
