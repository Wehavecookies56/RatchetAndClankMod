package com.gugu42.rcmod.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.gugu42.rcmod.RcMod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiShip extends GuiScreen {

	private ResourceLocation texture = new ResourceLocation(RcMod.MODID
			+ ":/textures/gui/shipGui.png");
	private TileEntity tileEntity;
	private EntityPlayer player;
	public final int xSizeOfTexture = 176;
	public final int ySizeOfTexture = 88;
	public String text;

	public Minecraft mc;

	public GuiShip(TileEntity te, EntityPlayer ep) {
		super();
		this.tileEntity = te;
		this.player = ep;
		this.mc = Minecraft.getMinecraft();
	}

	@Override
	public void drawScreen(int x, int y, float f) {
		drawDefaultBackground();

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(texture);

		int posX = (this.width - xSizeOfTexture) / 2;
		int posY = (this.height - ySizeOfTexture) / 2;

		drawTexturedModalRect(posX, posY, 0, 0, xSizeOfTexture, ySizeOfTexture);

		drawString(mc.fontRenderer, text, posX + 50, posY + 50, 0x000000);

		super.drawScreen(x, y, f);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	protected void keyTyped(char par1, int par2) {
		if (par2 == 1) {
			this.mc.thePlayer.closeScreen();
		}
	}

	public void actionPerformed(GuiButton button) {
		switch (button.id) {
		case 0:
			this.mc.displayGuiScreen(new GuiShipSelectDest(this));
			break;
		case 1:
			
			break;
		default:
			break;
		}
	}

	public void initGui() {
		this.buttonList.clear();

		int posX = (this.width - xSizeOfTexture) / 2;
		int posY = (this.height - ySizeOfTexture) / 2;

		this.buttonList.add(new GuiButton(0, posX + 120, posY + 10, 100, 20,
				"Select destination"));
		
		this.buttonList.add(new GuiButton(1, posX + 120, posY + 32, 100, 20, "Go to destination"));

	}

	public void setString(String string) {
		this.text = string;
	}
	
	

}
