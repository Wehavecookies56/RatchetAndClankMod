package com.gugu42.rcmod.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import com.gugu42.rcmod.handler.ExtendedPlayerBolt;
import com.gugu42.rcmod.items.ItemRcWeap;

@SideOnly(Side.CLIENT)
public class GuiBolt extends Gui {
	private Minecraft mc;
	private static final ResourceLocation texturepath = new ResourceLocation(
			"rcmod", "textures/gui/bolt.png");

	private int lastBolts;
	private long showBoltTimer;
	private int maxBoltShowTime;

	public GuiBolt(Minecraft mc) {
		super();
		this.lastBolts = 0;
		this.showBoltTimer = 0;
		this.maxBoltShowTime = 10000;
		this.mc = mc;
	}

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent event) {
		if (event.isCancelable() || event.type != ElementType.EXPERIENCE) {
			return;
		}

		ExtendedPlayerBolt props = ExtendedPlayerBolt.get(this.mc.thePlayer);

		if (props == null || props.getMaxBolts() == 0) {
			return;
		}

		if (props.getCurrentBolt() != lastBolts
				|| mc.currentScreen instanceof GuiIngameMenu
				|| mc.currentScreen instanceof GuiVendor
				|| mc.currentScreen instanceof GuiContainer
				|| mc.currentScreen instanceof GuiInventory) {
			showBoltTimer = System.currentTimeMillis();
		}
		lastBolts = props.getCurrentBolt();
		if (System.currentTimeMillis() - showBoltTimer < maxBoltShowTime) {
			GL11.glPushMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_LIGHTING);
			this.mc.getTextureManager().bindTexture(texturepath);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glDepthMask(false);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			drawTexturedQuadFit(360, 5, 16, 16, 0);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glDepthMask(true);
			GL11.glPopMatrix();

			mc.fontRendererObj.drawString("" + props.getCurrentBolt(), 380, 8,
					87 * 233 * 255);
		}

		ItemStack itemInHand = this.mc.thePlayer.inventory.getCurrentItem();
		if (itemInHand != null && itemInHand.getItem() instanceof ItemRcWeap) {
			ItemRcWeap weap = (ItemRcWeap) itemInHand.getItem();
			if (weap.isUsingAmmo()) {
				if (weap.hasAmmoImage) {
					GL11.glPushMatrix();
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					GL11.glDisable(GL11.GL_LIGHTING);
					this.mc.getTextureManager().bindTexture(
							new ResourceLocation("rcmod", weap
									.getAmmoImageTexturePath()));
					GL11.glEnable(GL11.GL_BLEND);
					GL11.glDisable(GL11.GL_DEPTH_TEST);
					GL11.glDepthMask(false);
					GL11.glBlendFunc(GL11.GL_SRC_ALPHA,
							GL11.GL_ONE_MINUS_SRC_ALPHA);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					GL11.glDisable(GL11.GL_ALPHA_TEST);
					drawTexturedQuadFit(10, 5, 16, 16, 0);
					GL11.glEnable(GL11.GL_DEPTH_TEST);
					GL11.glDepthMask(true);
					GL11.glPopMatrix();
				}
				mc.fontRendererObj
						.drawString(
								(itemInHand.getMaxDamage() - itemInHand
										.getItemDamage())
										+ "/"
										+ itemInHand.getMaxDamage(), 30, 8,
								87 * 233 * 255);

			}

			if (weap.hasCrosshair) {
				GL11.glPushMatrix();
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				GL11.glDisable(GL11.GL_LIGHTING);
				this.mc.getTextureManager().bindTexture(
						new ResourceLocation("rcmod", weap
								.getCrosshairImagePath()));
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glDisable(GL11.GL_DEPTH_TEST);
				GL11.glDepthMask(false);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				GL11.glDisable(GL11.GL_ALPHA_TEST);
				ScaledResolution sr = new ScaledResolution(this.mc);
				drawTexturedQuadFit((sr.getScaledWidth() / 2) - 16,
						(sr.getScaledHeight() / 2) - 16, 32, 32, 0);
				GL11.glEnable(GL11.GL_DEPTH_TEST);
				GL11.glDepthMask(true);
				GL11.glPopMatrix();

			}
		}
		
		this.mc.getTextureManager().bindTexture(icons);
	}

	@SideOnly(Side.CLIENT)
	public static void drawTexturedQuadFit(double x, double y, double width,
			double height, double zLevel) {
		WorldRenderer tessellator = Tessellator.getInstance().getWorldRenderer();
		tessellator.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		tessellator.pos(x + 0, y + height, zLevel).tex(0, 1).endVertex();
		tessellator.pos(x + width, y + height, zLevel).tex(1, 1).endVertex();;
		tessellator.pos(x + width, y + 0, zLevel).tex(1, 0).endVertex();;
		tessellator.pos(x + 0, y + 0, zLevel).tex(0, 0).endVertex();;
		Tessellator.getInstance().draw();
	}
}