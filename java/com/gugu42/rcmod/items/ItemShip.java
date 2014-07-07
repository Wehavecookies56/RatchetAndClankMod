package com.gugu42.rcmod.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.tileentity.TileEntityShipFiller;

public class ItemShip extends Item {

	public ItemShip() {

	}

	public boolean onItemUse(ItemStack itemStack, EntityPlayer player,
			World world, int x, int y, int z, int side, float xOffset,
			float yOffset, float zOffset) {
		if (!player.capabilities.isCreativeMode) {
			--itemStack.stackSize;
		}

		if (!world.isRemote) {
			y++;
			TileEntityShipFiller teShipFiller = null;
			world.setBlock(x, y, z, RcMod.ship, 0, 2);
			boolean shouldPlaceBlock;
			// world.notifyBlockOfNeighborChange(x, y, z, RcMod.ship);
			// int[][] blockList = {{1, 0, 0}, {0, 0, 1}, {2, 0, 0}, {0, 0, 2},
			// {1, 0, 1}};
			// int[] individualBlock = {0, 0, 0};
			//
			// for(int i = 0; i < blockList.length; i++){
			// individualBlock = blockList[i];
			//
			// world.setBlock(individualBlock[0], individualBlock[1],
			// individualBlock[2], RcMod.shipFiller, 0, 2);
			//
			// teShipFiller = (TileEntityShipFiller) world
			// .getTileEntity(x + individualBlock[0], y + individualBlock[1], z
			// + individualBlock[2]);
			// if (teShipFiller != null) {
			// teShipFiller.primary_x = x;
			// teShipFiller.primary_y = y;
			// teShipFiller.primary_z = z;
			// } else {
			// System.out.println("TileEntity is null !");
			// }
			//
			// }

			for (int i = x - 2; i < x + 3; i++) {
				for (int j = y; j < y + 3; j++) {
					for (int k = z - 2; k < z + 3; k++) {
						shouldPlaceBlock = true;

//						if (i >= x - 1 && i <= x + 1 && j > y && k >= z && k <= z + 1 && j > y)
//							shouldPlaceBlock = false;
//						
//						if(i == x - 2 || i == x + 2 )
//							shouldPlaceBlock = false;

						if (shouldPlaceBlock) {
							if (world.isAirBlock(i, j, k)) {
								world.setBlock(i, j, k, RcMod.shipFiller, 0, 2);
								// world.notifyBlockOfNeighborChange(i, j, k,
								// RcMod.shipFiller);
								teShipFiller = (TileEntityShipFiller) world
										.getTileEntity(i, j, k);
								if (teShipFiller != null) {
									teShipFiller.primary_x = x;
									teShipFiller.primary_y = y;
									teShipFiller.primary_z = z;
								} else {
									System.out.println("TileEntity is null !");
								}
							}
						}
					}
				}
			}

		}
		return false;
	}

	// This function rotates the relative coordinates accordingly to the given
	// direction
	public int[] rotXZByDir(int x, int y, int z, int dir) {
		if (dir == 0) {
			return new int[] { x, y, z };
		} else if (dir == 1) {
			return new int[] { -z, y, x };
		} else if (dir == 2) {
			return new int[] { -x, y, -z };
		} else {
			return new int[] { z, y, -x };
		}
	}

}
