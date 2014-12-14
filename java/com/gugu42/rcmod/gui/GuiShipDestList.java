package com.gugu42.rcmod.gui;

import java.util.ArrayList;
import java.util.List;

import com.gugu42.rcmod.shipsys.ShipSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.Tessellator;

public class GuiShipDestList extends GuiSlot {

	protected Minecraft mc;

	protected int slotHeight;

	private ArrayList strings = new ArrayList<String>();

	private GuiShip gui;

	public GuiShipDestList(GuiShip gui) {
		super(gui.mc, gui.width, gui.height, 32, gui.height, 24);
		this.slotHeight = 24;
		this.mc = gui.mc;
		this.gui = gui;

//		strings = new String[] { "This", "is", "a", "work", "in", "progress",
//				"GUI", "You", "should", "close", "it" };
		
		strings = ShipSystem.getWaypointsName();
		
	}

	/**
	 * Gets the size of the current slot list.
	 */
	protected int getSize() {
		return this.strings.size();
	}

	/**
	 * the element in the slot that was clicked, boolean for wether it was
	 * double clicked or not
	 */
	protected void elementClicked(int index, boolean twice, int var3, int var4) {
//		if (twice) {
			this.gui.setString((String) strings.get(index));
			this.mc.displayGuiScreen(gui);
//		}
	}

	/**
	 * returns true if the element passed in is currently selected
	 */
	protected boolean isSelected(int par1) {
		return false;
	}

	/**
	 * return the height of the content being scrolled
	 */
	protected int getContentHeight() {
		return this.getSize() * 24;
	}

	protected void drawSlot(int var1, int var2, int var3, int var4,
			Tessellator var5, int var6, int var7) {
		this.gui.mc.fontRendererObj.setBidiFlag(true);
		this.gui.drawCenteredString(this.gui.mc.fontRendererObj, (String) strings.get(var1),
				this.gui.width / 2, var3 + 1, 16777215);
	}

	@Override
	protected void drawBackground() {
		this.gui.drawDefaultBackground();
	}

	@Override
	protected void drawSlot(int entryID, int p_180791_2_, int p_180791_3_,
			int p_180791_4_, int p_180791_5_, int p_180791_6_) {
		// TODO Auto-generated method stub
		
	}

}
