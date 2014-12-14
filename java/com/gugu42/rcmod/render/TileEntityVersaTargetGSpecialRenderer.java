package com.gugu42.rcmod.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import com.gugu42.rcmod.utils.glutils.TessellatorModel;

public class TileEntityVersaTargetGSpecialRenderer extends
		TileEntitySpecialRenderer implements IInventoryRenderer {

	private TessellatorModel model;


	public TileEntityVersaTargetGSpecialRenderer() {
		model = new TessellatorModel("/assets/rcmod/models/Versa-TargetGreen.obj");
		model.regenerateNormals();
	}

	@Override
	public void renderInventory(double x, double y, double z) {
		GL11.glPushMatrix();
		GL11.glTranslated(x - 0.0f, y + 0.1d, z + 0.0f);
		GL11.glScalef(0.08f, 0.08f, 0.08f);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		model.render();
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z,
			float tick, int par6) {
		float f2 = (float) te.getWorld().getTotalWorldTime();
		byte b1 = 1;
		double d3 = (double) f2 * 0.025D * (1.0D - (double) (b1 & 1) * 2.5D);
		
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5d, y+0.5f, z + 0.5d);
		GL11.glScalef(0.076f, 0.076f, 0.076f);
		GL11.glRotated(d3 * 5 * 10, 0.0d, 1.0d, 0.0d);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		model.render();
		GL11.glPopMatrix();

	}

}
