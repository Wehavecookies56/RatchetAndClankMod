package com.gugu42.rcmod.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;

import com.gugu42.rcmod.ContainerVendor;
import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.handler.ExtendedPlayerBolt;
import com.gugu42.rcmod.items.EnumRcWeapons;
import com.gugu42.rcmod.items.InventoryGadgetronPDA;
import com.gugu42.rcmod.items.ItemRcWeap;
import com.gugu42.rcmod.network.packets.PacketRefill;
import com.gugu42.rcmod.network.packets.PacketVend;
import com.gugu42.rcmod.render.BlasterRender;
import com.gugu42.rcmod.tileentity.TileEntityVendor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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

	public int weaponIndex = 1;

	public int lastID = 0;
	public int centerID = 1;

	public GuiVendor(InventoryPlayer inventoryPlayer,
			TileEntityVendor tileEntity, EntityPlayer player,
			ContainerVendor container, InventoryGadgetronPDA inv) {
		super(new ContainerVendor(inventoryPlayer, tileEntity, inv));
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
				posY + 137, 35, 20, I18n.format("gui.vendor.buy")));
		this.buttonList.add(this.exitBtn = new GuiButton(1, posX + 181,
				posY + 137, 35, 20, I18n.format("gui.vendor.exit")));
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
				RenderManager.instance.renderEntityWithPosYaw(
						selectedItemEntity, 0, 0, 0, 0, 0);

			RenderHelper.disableStandardItemLighting();

			GL11.glPopMatrix();

			rotation -= 1f;

			if (getItemInInventory(player.inventory, selectedItem.getItem()) == null) {
				this.mc.fontRenderer.drawString(
						""
								+ EnumRcWeapons.getPriceFromItem(selectedItem
										.getItem()), 30, 105, 0xFFFFFF);
				this.buyBtn.displayString = I18n.format("gui.vendor.buy");
			} else {
				this.mc.fontRenderer.drawString(
						""
								+ getItemInInventory(player.inventory,
										selectedItem.getItem()).getItemDamage()
								* selectedItemWeap.getPrice(), 30, 105,
						0xFFFFFF);
				this.buyBtn.displayString = I18n.format("gui.vendor.refill");
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
		for (int i = 0; i < 9; i++) {
			if (EnumRcWeapons.getItemFromID(centerID + i) != null) {
				this.container.putStackInSlot(i, new ItemStack(EnumRcWeapons
						.getItemFromID(centerID + i).getWeapon()));
				lastID = i;
			} else {
				if (EnumRcWeapons.getItemFromID(i - lastID) != null) {
					this.container.putStackInSlot(
							i,
							new ItemStack(EnumRcWeapons.getItemFromID(
									i - lastID).getWeapon()));
				}
			}
		}
	}

	@Override
	public void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);
		for (int i = 0; i < 9; i++) {
			if (isMouseOverSlot(container.getSlot(i), mouseX, mouseY)) {
				selectedWeapon = i;

				selectedItem = container.getSlot(i).getStack();

				if (selectedItem != null) {
					selectedItemEntity = new EntityItem(this.mc.theWorld, 0, 0,
							0, selectedItem);

					selectedItemWeap = (ItemRcWeap) selectedItem.getItem();
				}
				mc.getSoundHandler()
						.playSound(
								PositionedSoundRecord
										.func_147674_a(new ResourceLocation(
												"rcmod:MenuSelect"), 1.0F));

				weaponIndex = weaponIndex + i;
				centerID = EnumRcWeapons.getIDFromItem(selectedItemWeap);
			} else {
				//ok you can happen if you want, but not too much pls
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
						PacketVend packetVend = new PacketVend(centerID);
						RcMod.rcModPacketHandler.sendToServer(packetVend);
					} catch (Exception exception) {
						exception.printStackTrace();
					}
				}
			} else {
				ItemRcWeap weap = (ItemRcWeap) selectedItem.getItem();
				if (weap.useAmmo) {
					PacketRefill packet = new PacketRefill(centerID);
					RcMod.rcModPacketHandler.sendToServer(packet);
				}
			}
			break;
		case 1:
			mc.getSoundHandler().playSound(
					PositionedSoundRecord.func_147674_a(new ResourceLocation(
							"rcmod:vendor.exit"), 1.0F));
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
		if (selectedItemEntity != null) { //Using this check to be sure that there is an item selecred, cuz that works.
			this.mc.fontRenderer.drawString(
					EnumRcWeapons.getItemFromID(centerID).getName(), 108, 16,
					0x00FF00);
		}

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
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x + 0, y + height, zLevel, 0, 1);
		tessellator.addVertexWithUV(x + width, y + height, zLevel, 1, 1);
		tessellator.addVertexWithUV(x + width, y + 0, zLevel, 1, 0);
		tessellator.addVertexWithUV(x + 0, y + 0, zLevel, 0, 0);
		tessellator.draw();
	}

	private boolean isMouseOverSlot(Slot par1Slot, int par2, int par3) {
		return this.func_146978_c(par1Slot.xDisplayPosition,
				par1Slot.yDisplayPosition, 16, 16, par2, par3);

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
