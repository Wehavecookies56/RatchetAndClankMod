package com.gugu42.rcmod.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import com.gugu42.rcmod.utils.glutils.TessellatorModel;

public class TileEntityVersaTargetGSpecialRenderer extends
		TileEntitySpecialRenderer implements IInventoryRenderer {

	private TessellatorModel model;

	private long last;
	private float rotation;

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
			float tick) {
		rotation += 2.5f * tick;
		if (rotation >= 360f) {
			rotation = 0;
		}
		
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5d, y+0.5f, z + 0.5d);
		GL11.glScalef(0.076f, 0.076f, 0.076f);
		GL11.glRotated(rotation, 0.0d, 1.0d, 0.0d);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		model.render();
		GL11.glPopMatrix();

	}

}
