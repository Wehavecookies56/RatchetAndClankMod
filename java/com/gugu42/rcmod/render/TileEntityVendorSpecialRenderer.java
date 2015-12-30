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
	private long last;
	private float rotation;

	public TileEntityVendorSpecialRenderer() {
		modelManager = new RcModelManager();
		model = AdvancedModelLoader.loadModel(new ResourceLocation(
				"rcmod:models/Vendor0.obj"));
		model2 = AdvancedModelLoader.loadModel(new ResourceLocation(
				"rcmod:models/Vendor1.obj"));
		model3 = AdvancedModelLoader.loadModel(new ResourceLocation(
				"rcmod:models/Vendor2.obj"));
		model4 = AdvancedModelLoader.loadModel(new ResourceLocation(
				"rcmod:models/Vendor3.obj"));

		model5 = new TessellatorModel("/assets/rcmod/models/Vendor4.obj");
		model5.regenerateNormals();

		modelBlaster = AdvancedModelLoader.loadModel(new ResourceLocation(
				"rcmod:models/Blaster0.obj"));
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
		rotation += 1.0f * tick;

		if (rotation >= 360f) {
			rotation = 0;
		}

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
		GL11.glRotated(rotation, 0.0D, 1.0D, 0.0D);
		GL11.glScalef(0.046f, 0.046f, 0.046f);
		model4.renderAll();
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5F, y - 0.4F, z + 0.5F);
		GL11.glRotated(-rotation, 0.0D, 1.0D, 0.0D);
		GL11.glScalef(0.046f, 0.046f, 0.046f);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		model5.render();
		GL11.glPopMatrix();
		vendor.setRenderCountdown(0);

	}

	public int getRandomWeapon() {
		Random rand = new Random();
		int toReturn = rand.nextInt(4);
		return toReturn;
	}

	public int changeWeapon() {
		int toReturn = getRandomWeapon();
		return toReturn;
	}

}
