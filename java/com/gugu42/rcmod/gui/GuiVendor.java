package com.gugu42.rcmod.gui;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import com.gugu42.rcmod.ContainerVendor;
import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.handler.ExtendedPlayerBolt;
import com.gugu42.rcmod.items.EnumRcWeapons;
import com.gugu42.rcmod.items.ItemRcWeap;
import com.gugu42.rcmod.network.packets.PacketRefill;
import com.gugu42.rcmod.network.packets.PacketVend;
import com.gugu42.rcmod.tileentity.TileEntityVendor;

@SideOnly(Side.CLIENT)
public class GuiVendor extends GuiContainer {

	private static final ResourceLocation texturepath = new ResourceLocation(
			"rcmod", "textures/gui/vendor_new.png");

	private static final ResourceLocation boltTexturePath = new ResourceLocation(
			"rcmod", "textures/gui/bolt.png");

	private GuiButton buyBtn;
	private GuiButton exitBtn;

	private EnumRcWeapons weapons;

	private EntityPlayer player;
	private TileEntityVendor tileEntity;
	private ContainerVendor container;
	private Minecraft mc;

	private int selectedWeapon = -1;
	private int mouseX, mouseY;

	private ItemStack selectedItem;
	private ItemRcWeap selectedItemWeap;
	private EntityItem selectedItemEntity;

	public float rotation;

	public GuiVendor(InventoryPlayer inventoryPlayer,
			TileEntityVendor tileEntity, EntityPlayer player,
			ContainerVendor container) {
		super(new ContainerVendor(inventoryPlayer, tileEntity));
		this.player = player;
		this.tileEntity = tileEntity;
		this.container = container;
		this.mc = Minecraft.getMinecraft();

		this.xSize = 256;
		this.ySize = 190;
	}

	@Override
	public void initGui() {
		super.initGui();
		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;
		this.buttonList.clear();
		this.buttonList.add(this.buyBtn = new GuiButton(0, posX + 41,
				posY + 137, 35, 20, "Buy"));
		this.buttonList.add(this.exitBtn = new GuiButton(1, posX + 181,
				posY + 137, 35, 20, "Exit"));
	}

	@Override
	public void updateScreen() {
		putItemsInSlot();
	}

	public void handleSelectedWeapon() {

		if (selectedItemEntity != null) {
			selectedItemEntity.rotationYaw = 0;
			selectedItemEntity.hoverStart = 0;

			GL11.glPushMatrix();
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);

			GL11.glTranslatef(xSize - 200, ySize - 92, 100);

			GL11.glScalef(-60f, 60f, 60f);

			GL11.glRotatef(180, 0, 0, 1);
			GL11.glRotatef(rotation, 0, 1, 0);

			RenderHelper.enableStandardItemLighting();

			if (selectedWeapon >= 0 && selectedItem != null)
				Minecraft.getMinecraft().getRenderManager().renderEntityWithPosYaw(
						selectedItemEntity, 0, 0, 0, 0, 0);

			RenderHelper.disableStandardItemLighting();

			GL11.glPopMatrix();

			rotation -= 1f;

			if (getItemInInventory(player.inventory, selectedItem.getItem()) == null) {
				this.mc.fontRendererObj.drawString(""
						+ EnumRcWeapons.getItemFromID(selectedWeapon + 1)
								.getPrice(), 30, 105, 0xFFFFFF);
				this.buyBtn.displayString = "Buy";
			} else {
				this.mc.fontRendererObj.drawString(""
						+ getItemInInventory(player.inventory, selectedItem.getItem()).getItemDamage() * selectedItemWeap.getPrice(), 30, 105, 0xFFFFFF);
				this.buyBtn.displayString = "Refill";
			}
			this.buyBtn.enabled = true;

			GL11.glPushMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_LIGHTING);
			this.mc.getTextureManager().bindTexture(boltTexturePath);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glDepthMask(false);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			drawTexturedQuadFit(xSize - 185, ySize - 86, 8, 8, 0);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glDepthMask(true);
			GL11.glPopMatrix();
		} else {
			this.buyBtn.enabled = false;
		}
	}

	public void putItemsInSlot() {
		for (int i = 1; i < 10; i++) {
			if (EnumRcWeapons.getItemFromID(i) != null) {
				this.container.putStackInSlot(i - 1, new ItemStack(
						EnumRcWeapons.getItemFromID(i).getWeapon()));
			} else {
				this.container.putStackInSlot(i, null);
			}
		}
	}

	@Override
	public void mouseClicked(int par1, int par2, int par3) throws IOException {
		super.mouseClicked(par1, par2, par3);
		for (int i = 0; i < 9; i++) {
			if (isMouseOverSlot(container.getSlot(i), mouseX, mouseY)) {
				selectedWeapon = i;
				selectedItem = new ItemStack(EnumRcWeapons.getItemFromID(i + 1)
						.getWeapon(), 1);
				selectedItemEntity = new EntityItem(this.mc.theWorld, 0, 0, 0,
						selectedItem);
				selectedItemWeap = (ItemRcWeap)selectedItem.getItem();
			}
		}
	}

	@Override
	public void actionPerformed(GuiButton button) {
		switch (button.id) {
		case 0:
			if (!player.inventory.hasItem(selectedItem.getItem())) {
				ExtendedPlayerBolt props = ExtendedPlayerBolt.get(player);
				if (props.getCurrentBolt() > 0) {

					try {
						PacketVend packetVend = new PacketVend(
								selectedWeapon + 1);
						RcMod.rcModPacketHandler.sendToServer(packetVend);
						mc.getSoundHandler().playSound(
								PositionedSoundRecord
										.create(new ResourceLocation(
												"rcmod:vendor.buy"), 1.0F));
					} catch (Exception exception) {
						exception.printStackTrace();
					}
				}
			} else {
				ItemRcWeap weap = (ItemRcWeap) selectedItem.getItem();
				if (weap.useAmmo) {
					PacketRefill packet = new PacketRefill(selectedWeapon + 1);
					RcMod.rcModPacketHandler.sendToServer(packet);
				}
			}
			break;
		case 1:
			player.closeScreen();
			break;
		default:
			break;
		}
	}

	public boolean doesGuiPauseGame() {
		return false;
	}

	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		mouseX = par1;
		mouseY = par2;
		handleSelectedWeapon();
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2,
			int par3) {
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.renderEngine.bindTexture(texturepath);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedQuadFit(x, y, 256, 256, 0);
		GL11.glDisable(GL11.GL_BLEND);
	}

	@SideOnly(Side.CLIENT)
	public static void drawTexturedQuadFit(double x, double y, double width,
			double height, double zLevel) {
		WorldRenderer tessellator = Tessellator.getInstance().getWorldRenderer();
		tessellator.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		tessellator.pos(x + 0, y + height, zLevel).tex(0, 1).endVertex();
		tessellator.pos(x + width, y + height, zLevel).tex(1, 1).endVertex();
		tessellator.pos(x + width, y + 0, zLevel).tex(1, 0).endVertex();
		tessellator.pos(x + 0, y + 0, zLevel).tex(0, 0).endVertex();
		Tessellator.getInstance().draw();
	}

	private boolean isMouseOverSlot(Slot par1Slot, int par2, int par3) {
		return this.isMouseOverSlot(par1Slot, par2, par3);

	}

	private int getSlotContainingItem(InventoryPlayer inventory, Item item) {
		for (int i = 0; i < inventory.mainInventory.length; ++i) {
			if (inventory.mainInventory[i] != null
					&& inventory.mainInventory[i].getItem() == item) {
				return i;
			}
		}

		return -1;
	}

	public ItemStack getItemInInventory(InventoryPlayer inventory,
			Item p_146026_1_) {
		int i = this.getSlotContainingItem(inventory, p_146026_1_);

		if (i < 0) {
			return null;
		} else {
			return inventory.mainInventory[i];
		}
	}

}
