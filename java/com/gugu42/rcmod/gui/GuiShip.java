package com.gugu42.rcmod.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.shipsys.ShipSystem;
import com.gugu42.rcmod.tileentity.TileEntityShip;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiShip extends GuiScreen {

	private ResourceLocation texture = new ResourceLocation(RcMod.MODID
			+ ":/textures/gui/ship_gui.png");
	private TileEntityShip tileEntity;
	private EntityPlayer player;
	public final int xSizeOfTexture = 177;
	public final int ySizeOfTexture = 88;
	public String text;
	private int goTimer;
	
	private GuiButton goButton;
	
	public Minecraft mc;

	public GuiShip(TileEntityShip te, EntityPlayer ep) {
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

		drawString(mc.fontRenderer, "Destination :", posX + 8, posY + 60, 0x888888);
		drawString(mc.fontRenderer, text, posX + 70, posY + 60, 0xFF5F1F);

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
			this.mc.displayGuiScreen(new GuiShipSelectDest(this, player));
			this.canGo(false);
			break;
		case 1:
			if (text != null && text != "") {
				this.tileEntity.hasLaunched = true;

				this.tileEntity.wpData = ShipSystem.getWaypointByName(text)
						.toString();
				this.player.closeScreen();
			} else {
				this.player.addChatMessage(new ChatComponentText(
						I18n.format("gui.ship.nodest")));
			}
			break;
		default:
			break;
		}
	}

	public void initGui() {
		this.buttonList.clear();

		int posX = (this.width - xSizeOfTexture) / 2;
		int posY = (this.height - ySizeOfTexture) / 2;

		this.goButton = new GuiButton(1, posX + 60, posY + 32, 100, 20,
				I18n.format("gui.ship.go"));
		this.goButton.enabled = false;
		
		this.buttonList.add(new GuiButton(0, posX + 60, posY + 10, 100, 20,
				I18n.format("gui.ship.select")));

		this.buttonList.add(goButton);

	}

	public void canGo(boolean can){
		if(can){
			goButton.enabled = true;
		} else {
			goButton.enabled = false;
		}
	}
	
	public void setString(String string) {
		this.text = string;
	}
	
	public void updateScreen() {
		super.updateScreen();
		if(goTimer > 0){
			this.goTimer--;
		} else {
			canGo(true);
		}
	}
	
	public void setTimer(int time){
		this.goTimer = time;
	}

}
