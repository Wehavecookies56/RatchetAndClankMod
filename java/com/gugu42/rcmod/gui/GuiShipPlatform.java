package com.gugu42.rcmod.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.network.packets.PacketShipPlatform;
import com.gugu42.rcmod.tileentity.TileEntityShipPlatform;

public class GuiShipPlatform extends GuiScreen {

	private ResourceLocation       texture        = new ResourceLocation("rcmod", "textures/gui/gadgetron_helper.png");
	private TileEntityShipPlatform tileEntity;
	private EntityPlayer           player;
	public final int               xSizeOfTexture = 192;
	public final int               ySizeOfTexture = 192;

	private GuiTextField           textField;
	private GuiButton              privateBtn;

	private String                 ownerName;
	private int                    posX, posY, posZ;
	private String                 saveData       = "";
	
	private String warningMessage;

	public Minecraft               mc;

	public GuiShipPlatform(EntityPlayer player, TileEntityShipPlatform te) {
		this.player = player;
		this.tileEntity = te;
		this.mc = Minecraft.getMinecraft();
		this.warningMessage = "NAME MUST NOT CONTAIN SPACES OR BE EMPTY";
	}

	@Override
	public void drawScreen(int x, int y, float f) {
		drawDefaultBackground();

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(texture);

		int posX = (this.width - xSizeOfTexture) / 2;
		int posY = (this.height - ySizeOfTexture) / 2;

		drawTexturedModalRect(posX, posY, 0, 0, xSizeOfTexture, ySizeOfTexture);
		drawString(mc.fontRenderer, "Waypoint info :", posX + 10, posY + 10, 0xFF5F1F);
		drawString(mc.fontRenderer, "Name :", posX + 10, posY + 55, 0xFFFFFF);
		drawString(mc.fontRenderer, "Position : " + this.posX + " " + this.posY  + " " + this.posZ, posX + 10, posY + 80, 0xFFFFFF);
		drawString(mc.fontRenderer, "Owner : " + this.ownerName, posX + 10, posY + 100, 0xFFFFFF);
		drawString(mc.fontRenderer, "Private : ", posX + 10, posY + 125, 0xFFFFFF);
		drawString(mc.fontRenderer, warningMessage, posX + 15, posY + 28, 0xFF0000);
		this.textField.drawTextBox();

		super.drawScreen(x, y, f);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	protected void keyTyped(char par1, int par2) {
		super.keyTyped(par1, par2);

		if (par2 == 1) {
			this.mc.thePlayer.closeScreen();
		}

		this.textField.textboxKeyTyped(par1, par2);
	}

	protected void mouseClicked(int x, int y, int btn) {
		super.mouseClicked(x, y, btn);
		this.textField.mouseClicked(x, y, btn);
	}

	public void actionPerformed(GuiButton button) {
		switch (button.id) {
		case 0:
			this.saveData();
			System.out.println(saveData);
			try {
				PacketShipPlatform packet = new PacketShipPlatform(saveData);
				RcMod.rcModPacketHandler.sendToServer(packet);
				RcMod.rcModPacketHandler.sendToAll(packet);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
			break;
		case 1:
			if (this.privateBtn.displayString.equals("False")) {
				this.privateBtn.displayString = "True";
			} else {
				this.privateBtn.displayString = "False";
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

		this.privateBtn = new GuiButton(1, posX + 60, posY + 120, 90, 20, "False");
		this.textField = new GuiTextField(this.fontRendererObj, this.width / 2 - 50, this.height / 2 - 46, 137, 20);
		textField.setMaxStringLength(23);
		this.textField.setFocused(true);

		this.buttonList.add(new GuiButton(0, posX + 50, posY + 150, 100, 20, "Create waypoint"));
		this.buttonList.add(privateBtn);

		loadData();
	}

	private void loadData() {
		if (this.tileEntity.wpName != null) {
			textField.setText(this.tileEntity.wpName);
		} else {
			textField.setText("Waypoint name");
		}

		if (this.tileEntity.ownerName != null) {
			this.ownerName = this.tileEntity.ownerName;
		} else {
			this.ownerName = this.player.getDisplayName();
		}

		this.posX = tileEntity.xCoord;
		this.posY = tileEntity.yCoord + 1;
		this.posZ = tileEntity.zCoord;

		if (this.tileEntity.isPrivate) {
			this.privateBtn.displayString = "True";
		} else {
			this.privateBtn.displayString = "False";
		}
	}

	private void saveData() {

		if (this.textField.getText() != null) {
			//			this.tileEntity.setWpName(this.textField.getText());
			saveData += this.textField.getText() + ";";
		}

		if (this.ownerName != null) {
			//			this.tileEntity.setOwnerName(ownerName);
			saveData += this.ownerName + ";";
		}

//		if (this.privateBtn.displayString.equals("True")) {
			//			this.tileEntity.setPrivate(true);
			saveData += this.privateBtn.displayString + ";";
//		}

		saveData += this.tileEntity.xCoord + ";" + this.tileEntity.yCoord + ";" + this.tileEntity.zCoord;
	}

	public void updateScreen() {
		super.updateScreen();
		this.textField.updateCursorCounter();
		
		if(this.textField.getText() == "" || this.textField.getText().contains(" ")){
			GuiButton btn = (GuiButton) this.buttonList.get(0);
			btn.enabled = false;
			this.warningMessage = "NAME MUST NOT CONTAIN SPACES";
		} else {
			GuiButton btn = (GuiButton) this.buttonList.get(0);
			btn.enabled = true;
			this.warningMessage = "";
		}
	}
}
