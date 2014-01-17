package com.gugu42.rcmod.handler;

import java.util.EnumSet;
import java.util.List;

import com.gugu42.rcmod.RcMod;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class RcTickHandler implements ITickHandler {

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		EntityPlayer player = (EntityPlayer) tickData[0];
		World world = player.worldObj;

		List entities = world.getEntitiesWithinAABB(EntityItem.class,
				AxisAlignedBB.getBoundingBox(player.posX - 16.0D,
						player.posY - 16.0D, player.posZ - 16.0D,
						player.posX + 16.0D, player.posY + 16.0D,
						player.posZ + 16.0D));
		for (int i1 = 0; i1 < entities.size(); i1++) {
			if ((entities.get(i1) instanceof EntityItem)) {
				updateEntityItem((EntityItem) entities.get(i1));
			}
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.PLAYER);
	}

	@Override
	public String getLabel() {
		return "RatchetClank";
	}

	private void updateEntityItem(EntityItem par1) {
		double closeness = 16.0D;
		EntityPlayer player = par1.worldObj.getClosestPlayerToEntity(par1,
				closeness);

		if ((player != null) && par1.getEntityItem().itemID == RcMod.bolt.itemID) {
			double var3 = (player.posX - par1.posX) / closeness;
			double var5 = (player.posY + player.getEyeHeight() - par1.posY)
					/ closeness;
			double var7 = (player.posZ - par1.posZ) / closeness;
			double var9 = Math.sqrt(var3 * var3 + var5 * var5 + var7 * var7);
			double var11 = 1.0D - var9;

			if (var11 > 0.0D) {
				var11 *= var11;
				par1.motionX += var3 / var9 * var11 * 0.1D;
				par1.motionY += var5 / var9 * var11 * 0.1D;
				par1.motionZ += var7 / var9 * var11 * 0.1D;
			}
		}
	}

}
