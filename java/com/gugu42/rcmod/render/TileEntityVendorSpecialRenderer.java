package com.gugu42.rcmod.render;

import java.util.Random;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.gugu42.rcmod.tileentity.TileEntityVendor;
import com.gugu42.rcmod.utils.glutils.TessellatorModel;

public class TileEntityVendorSpecialRenderer extends TileEntitySpecialRenderer
		implements IInventoryRenderer {

	private TessellatorModel vendorBody;
	private TessellatorModel vendorAntennas;
	private TessellatorModel vendorInterior;
	private TessellatorModel vendorText;
	private TessellatorModel vendorG;

	public TileEntityVendorSpecialRenderer() {
		vendorBody = new TessellatorModel("/assets/rcmod/models/Vendor0.obj");
		vendorBody.regenerateNormals();
		vendorAntennas = new TessellatorModel(
				"/assets/rcmod/models/Vendor1.obj");
		vendorAntennas.regenerateNormals();
		vendorInterior = new TessellatorModel(
				"/assets/rcmod/models/Vendor2.obj");
		vendorInterior.regenerateNormals();
		vendorText = new TessellatorModel("/assets/rcmod/models/Vendor3.obj");
		vendorText.regenerateNormals();
		vendorG = new TessellatorModel("/assets/rcmod/models/Vendor4.obj");
		vendorG.regenerateNormals();
	}

	@Override
	public void renderInventory(double x, double y, double z) {
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5F, y + 0.0F, z + 0.5F);
		GL11.glScalef(0.046f, 0.046f, 0.046f);
		vendorInterior.render();
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5F, y + 0.0F, z + 0.5F);
		GL11.glScalef(0.046f, 0.046f, 0.046f);
		vendorAntennas.render();
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5F, y + 0.0F, z + 0.5F);
		GL11.glScalef(0.046f, 0.046f, 0.046f);
		vendorBody.render();
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z,
			float tick, int par6) {

		float f2 = (float) te.getWorld().getTotalWorldTime();
		byte b1 = 1;
		double d3 = (double) f2 * 0.025D * (1.0D - (double) (b1 & 1) * 2.5D);

		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5F, y + 0.0F, z + 0.5F);
		GL11.glScalef(0.046f, 0.046f, 0.046f);
		vendorInterior.render();
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5F, y + 0.0F, z + 0.5F);
		GL11.glScalef(0.046f, 0.046f, 0.046f);
		vendorAntennas.render();
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5F, y + 0.0F, z + 0.5F);
		GL11.glScalef(0.046f, 0.046f, 0.046f);
		vendorBody.render();
		GL11.glPopMatrix();

		TileEntityVendor vendor;
		if (te != null && te instanceof TileEntityVendor) {
			vendor = (TileEntityVendor) te;
		} else {
			vendor = new TileEntityVendor();
		}

		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5F, y - 0.4F, z + 0.5F);
		GL11.glRotated(d3 * 5 * 5, 0.0D, 1.0D, 0.0D);
		GL11.glScalef(0.046f, 0.046f, 0.046f);
		vendorText.render();
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5F, y - 0.4F, z + 0.5F);
		GL11.glRotated(-d3 * 5 * 10, 0.0D, 1.0D, 0.0D);
		GL11.glScalef(0.046f, 0.046f, 0.046f);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		vendorG.render();
		GL11.glPopMatrix();
	}

}
