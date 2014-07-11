package com.gugu42.rcmod.gui;

import java.io.InputStream;
import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiGadgetronHelper extends GuiScreen {

	public Minecraft mc;

	private EntityPlayer player;
	private ResourceLocation texture = new ResourceLocation(
			"rcmod:textures/gui/gadgetron_helper.png");

	public int currentPage;
	public int totalPages;

	public GuiGadgetronHelper(EntityPlayer player) {
		mc = Minecraft.getMinecraft();
		this.player = player;
	}

	@Override
	public void initGui() {

	}

	public final int xSizeOfTexture = 192;
	public final int ySizeOfTexture = 192;

	@Override
	public void drawScreen(int x, int y, float f) {
		drawDefaultBackground();

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(texture);

		int posX = (this.width - xSizeOfTexture) / 2;
		int posY = (this.height - ySizeOfTexture) / 2;

		drawTexturedModalRect(posX, posY, 0, 0, xSizeOfTexture, ySizeOfTexture);

		super.drawScreen(x, y, f);
	}

	public void drawPageContent(int x, int y, float f, String pageName) {


	}

	public enum GadgetronHelperPage {

		PAGE1("Menu", new String[]{"Welcome to the Gadgetron Helper ! This will help you get informations about the various weapons and gadgets we are selling !"});
		
		public String name;
		public String[] pageLines;

		private GadgetronHelperPage(String pageName, String[] pageLines) {
			this.name = pageName;
			this.pageLines = pageLines;
		}

		public String[] getPageLines() {
			return pageLines;
		}

	}

}
