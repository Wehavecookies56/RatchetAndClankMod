package com.gugu42.rcmod.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StringTranslate;

public class GuiShipSelectDest extends GuiScreen {

	private GuiShip parent;
	protected GuiShipDestList list;

	public GuiShipSelectDest(GuiShip parent) {
		this.parent = parent;
		this.list = new GuiShipDestList(parent);
	}

	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */
	public void initGui() {

		this.buttonList
				.add(new GuiButton(0, 25, this.height - 38, 120, 20, "Done"));
	}

	/**
	 * Fired when a control is clicked. This is the equivalent of
	 * ActionListener.actionPerformed(ActionEvent e).
	 */
	protected void actionPerformed(GuiButton par1GuiButton) {
		if (par1GuiButton.enabled) {
			switch (par1GuiButton.id) {
			case 0:
				this.mc.displayGuiScreen(this.parent);
			}
		}
	}

	public void drawScreen(int par1, int par2, float par3) {
		this.list.drawScreen(par1, par2, par3);
		super.drawScreen(par1, par2, par3);
	}

}
