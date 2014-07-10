package com.gugu42.rcmod.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiGadgetronHelper extends GuiScreen {

	public Minecraft mc;
	
	private ResourceLocation texture = new ResourceLocation("rcmod:textures/gui/gadgetron_helper.png");
	
	public int currentPage;
	public int totalPages;

	public GuiGadgetronHelper(EntityPlayer player) {

	}

	public final int xSizeOfTexture = 176;
	public final int ySizeOfTexture = 88;

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

}
