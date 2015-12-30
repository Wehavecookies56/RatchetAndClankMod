package com.gugu42.rcmod.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;

import com.gugu42.rcmod.handler.ExtendedPlayerTooltips;
import com.gugu42.rcmod.items.ItemRcWeap;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class GuiTooltips extends GuiScreen {

	private ResourceLocation texture        = new ResourceLocation("rcmod", "textures/gui/tooltip.png");
	public final int         xSizeOfTexture = 256;
	public final int         ySizeOfTexture = 256;
	public String            text;

	public String            desc1, desc2, desc3, desc4;
	private long             showTimer;
	private long             maxShowTime    = 5000;
	private boolean          mustSeeTip     = false;

	public Minecraft         mc;

	//Tooltips line can be up to 28 characters.
	public GuiTooltips() {
		super();
		this.mc = Minecraft.getMinecraft();
		this.maxShowTime = 5000;
	}

	public void drawScreen(ScaledResolution sr) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(texture);

		drawTexturedModalRect((sr.getScaledWidth() / 2) - 128, (sr.getScaledHeight() / 2) - 130, 0, 0, xSizeOfTexture, ySizeOfTexture);
	}

	public void drawTooltip(ScaledResolution sr) {

		drawString(mc.fontRenderer, desc1, (sr.getScaledWidth() / 2) - 83, (sr.getScaledHeight() / 2) + 49, 0xFFFFFF);
		drawString(mc.fontRenderer, desc2, (sr.getScaledWidth() / 2) - 83, (sr.getScaledHeight() / 2) + 60, 0xFFFFFF);
		drawString(mc.fontRenderer, desc3, (sr.getScaledWidth() / 2) - 83, (sr.getScaledHeight() / 2) + 71, 0xFFFFFF);
		drawString(mc.fontRenderer, desc4, (sr.getScaledWidth() / 2) - 83, (sr.getScaledHeight() / 2) + 82, 0xFFFFFF);
	}

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent event) {
		if (event.isCancelable() || event.type != ElementType.EXPERIENCE) {
			return;
		}

		ExtendedPlayerTooltips props = ExtendedPlayerTooltips.get(this.mc.thePlayer);

		ItemStack itemInHand = this.mc.thePlayer.inventory.getCurrentItem();
		if (itemInHand != null && itemInHand.getItem() instanceof ItemRcWeap) {
			ItemRcWeap weap = (ItemRcWeap) itemInHand.getItem();
			if (!props.hasSeenTip(weap.weaponName)) {
				this.showTip(weap.weaponName);
				props.setTipSeen(weap.weaponName);
				showTimer = System.currentTimeMillis();
			}

			if (System.currentTimeMillis() - showTimer < maxShowTime && mustSeeTip) {
				ScaledResolution sr = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
				drawScreen(sr);
				drawTooltip(sr);
			}
			if (System.currentTimeMillis() - showTimer > maxShowTime) {
				this.mustSeeTip = false;
			}
		}
	}

	public void showTip(String tipName) {
		this.desc1 = I18n.format("gui.tooltip." + tipName + ".1");
		this.desc2 = I18n.format("gui.tooltip." + tipName + ".2");
		this.desc3 = I18n.format("gui.tooltip." + tipName + ".3");
		this.desc4 = I18n.format("gui.tooltip." + tipName + ".4");
		this.mustSeeTip = true;
	}
}
