package com.gugu42.rcmod.network;

import com.gugu42.rcmod.ContainerVendor;
import com.gugu42.rcmod.gui.GuiGadgetronHelper;
import com.gugu42.rcmod.gui.GuiShip;
import com.gugu42.rcmod.gui.GuiShipPlatform;
import com.gugu42.rcmod.gui.GuiVendor;
import com.gugu42.rcmod.tileentity.TileEntityShip;
import com.gugu42.rcmod.tileentity.TileEntityShipFiller;
import com.gugu42.rcmod.tileentity.TileEntityShipPlatform;
import com.gugu42.rcmod.tileentity.TileEntityVendor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	// returns an instance of the Container you made earlier
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity instanceof TileEntityVendor) {
			return new ContainerVendor(player.inventory, (TileEntityVendor) tileEntity);
		}

		if (tileEntity instanceof TileEntityShip) {
			return null;
		}

		if (id == 3) {
			return null;
		}

		return null;
	}

	// returns an instance of the Gui you made earlier
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		
		if (tileEntity instanceof TileEntityVendor) {
			return new GuiVendor(player.inventory, (TileEntityVendor) tileEntity, player, new ContainerVendor(player.inventory, (TileEntityVendor) tileEntity));
		}

		if (tileEntity instanceof TileEntityShip || tileEntity instanceof TileEntityShipFiller) {
			if (tileEntity instanceof TileEntityShip) {
				TileEntityShip te = (TileEntityShip) tileEntity;
				return new GuiShip(te, player);
			} else {
				TileEntityShipFiller te2 = (TileEntityShipFiller) tileEntity;
				TileEntityShip te = (TileEntityShip) te2.getOriginalTileEntity();
				System.out.println("Received data for gui : " + x + " " + y + " " + z);

				if (te != null) {
					return new GuiShip(te, player);
				} else {
					System.out.println("ERROR ! NULL TILEENTITY");
					return new GuiShip(null, player);
				}
			}
		}

		if (id == 3) {
			return new GuiGadgetronHelper(player);
		}

		if (id == 4 && tileEntity instanceof TileEntityShipPlatform) {
			TileEntityShipPlatform te = (TileEntityShipPlatform)tileEntity;
			return new GuiShipPlatform(player, te);
		}

		return null;

	}
}