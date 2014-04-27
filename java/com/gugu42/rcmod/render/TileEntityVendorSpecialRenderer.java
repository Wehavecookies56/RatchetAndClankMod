package com.gugu42.rcmod.render;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import com.gugu42.rcmod.tileentity.TileEntityVendor;
import com.gugu42.rcmod.utils.glutils.TessellatorModel;

public class TileEntityVendorSpecialRenderer extends TileEntitySpecialRenderer
		implements IInventoryRenderer {

	public int changingRate;
	public int weapon;

	private RcModelManager modelManager;

	// Partie bleu
	private IModelCustom model;
	public static final ResourceLocation textureLocation = new ResourceLocation(
			"rcmod:models/Vendor0.png");

	// Antennes sur le cote
	private IModelCustom model2;
	public static final ResourceLocation textureLocation2 = new ResourceLocation(
			"rcmod:models/Vendor1.png");

	// Partie metallique interieure
	private IModelCustom model3;
	public static final ResourceLocation textureLocation3 = new ResourceLocation(
			"rcmod:models/Vendor2.png");

	// Texte Gadgetron
	private IModelCustom model4;
	public static final ResourceLocation textureLocation4 = new ResourceLocation(
			"rcmod:models/Vendor3.png");

	// G de Gadgetron
	private TessellatorModel model5;

	// Blaster
	private IModelCustom modelBlaster;
	public static final ResourceLocation textureLocationBlaster = new ResourceLocation(
			"rcmod:models/Blaster0.png");

	public TileEntityVendorSpecialRenderer() {
		modelManager = new RcModelManager();
		model = AdvancedModelLoader
				.loadModel(new ResourceLocation("rcmod:models/Vendor0.obj"));
		model2 = AdvancedModelLoader
				.loadModel(new ResourceLocation("rcmod:models/Vendor1.obj"));
		model3 = AdvancedModelLoader
				.loadModel(new ResourceLocation("rcmod:models/Vendor2.obj"));
		model4 = AdvancedModelLoader
				.loadModel(new ResourceLocation("rcmod:models/Vendor3.obj"));
		
		model5 = new TessellatorModel("/assets/rcmod/models/Vendor4.obj");
		model5.regenerateNormals();
		
		modelBlaster = AdvancedModelLoader
				.loadModel(new ResourceLocation("rcmod:models/Blaster0.obj"));
//		this.setTileEntityRenderer(TileEntityRenderer.instance);
		this.func_147497_a(TileEntityRendererDispatcher.instance);
		changingRate = 600;
		weapon = 0;
	}

	@Override
	public void renderInventory(double x, double y, double z) {
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5F, y + 0.0F, z + 0.5F);
		this.bindTexture(textureLocation3);
		GL11.glScalef(0.046f, 0.046f, 0.046f);
		model3.renderAll();
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5F, y + 0.0F, z + 0.5F);
		this.bindTexture(textureLocation2);
		GL11.glScalef(0.046f, 0.046f, 0.046f);
		model2.renderAll();
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5F, y + 0.0F, z + 0.5F);
		this.bindTexture(textureLocation);
		GL11.glScalef(0.046f, 0.046f, 0.046f);
		model.renderAll();
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z,
			float tick) {

		float f2 = (float) te.getWorldObj().getTotalWorldTime();
		byte b1 = 1;
		double d3 = (double) f2 * 0.025D * (1.0D - (double) (b1 & 1) * 2.5D);

		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5F, y + 0.0F, z + 0.5F);
		this.bindTexture(textureLocation3);
		GL11.glScalef(0.046f, 0.046f, 0.046f);
		model3.renderAll();
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5F, y + 0.0F, z + 0.5F);
		this.bindTexture(textureLocation2);
		GL11.glScalef(0.046f, 0.046f, 0.046f);
		model2.renderAll();
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5F, y + 0.0F, z + 0.5F);
		this.bindTexture(textureLocation);
		GL11.glScalef(0.046f, 0.046f, 0.046f);
		model.renderAll();
		GL11.glPopMatrix();

		TileEntityVendor vendor;
		if (te != null && te instanceof TileEntityVendor) {
			vendor = (TileEntityVendor) te;
		} else {
			vendor = new TileEntityVendor();
		}

		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5F, y - 0.4F, z + 0.5F);
		this.bindTexture(textureLocation4);
		GL11.glRotated(d3 * 5 * 5, 0.0D, 1.0D, 0.0D);
		GL11.glScalef(0.046f, 0.046f, 0.046f);
		model4.renderAll();
		GL11.glPopMatrix();

		if (!vendor.isPlayerNear) {
			GL11.glPushMatrix();
			GL11.glTranslated(x + 0.5F, y - 0.4F, z + 0.5F);
			GL11.glRotated(-d3 * 5 * 10, 0.0D, 1.0D, 0.0D);
			GL11.glScalef(0.046f, 0.046f, 0.046f);
			GL11.glShadeModel(GL11.GL_SMOOTH);
			model5.render();
			GL11.glPopMatrix();
			vendor.setRenderCountdown(0);
		} else {
			
			if (vendor.getRenderCountdown() <= 0) {
				vendor.setRenderCountdown(610);
				weapon = changeWeapon();
			}
			
			if (weapon == 0) {
				GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine
						.bindTexture(textureLocationBlaster);
				GL11.glTranslated(x + 0.5f, y + 1.7F, z + 0.5f);
				GL11.glRotated(-d3 * 5 * 10, 0.0D, 1.0D, 0.0D);
				GL11.glScalef(0.036f, 0.036f, 0.036f);
				modelBlaster.renderAll();
				GL11.glPopMatrix();
			} else if (weapon == 1) {
				GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine
						.bindTexture(modelManager.textureLocationBombGlove);
				GL11.glTranslated(x + 0.5f, y + 1.7F, z + 0.5f);
				GL11.glRotated(-d3 * 5 * 10, 0.0D, 1.0D, 0.0D);
				GL11.glScalef(0.036f, 0.036f, 0.036f);
				modelManager.modelBombGlove.renderAll();
				GL11.glPopMatrix();
			} else if (weapon == 2) {
				GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine
						.bindTexture(modelManager.textureLocationPyrocitor);
				GL11.glTranslated(x + 0.5f, y + 1.7F, z + 0.5f);
				GL11.glRotated(-d3 * 5 * 10, 0.0D, 1.0D, 0.0D);
				GL11.glScalef(0.036f, 0.036f, 0.036f);
				modelManager.modelPyrocitor.renderAll();
				GL11.glPopMatrix();
			} else if (weapon == 3) {
				GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine
						.bindTexture(modelManager.textureLocationRYNO);
				GL11.glTranslated(x + 0.5f, y + 1.7F, z + 0.5f);
				GL11.glRotated(-d3 * 5 * 10, 0.0D, 1.0D, 0.0D);
				GL11.glScalef(0.036f, 0.036f, 0.036f);
				modelManager.modelRYNO.renderAll();
				GL11.glPopMatrix();
			}
		}
	}

	public int getRandomWeapon() {
		Random rand = new Random();
		int toReturn = rand.nextInt(4);
		return toReturn;
	}
	
	public int changeWeapon(){
		int toReturn = getRandomWeapon();
		return toReturn;
	}

}
