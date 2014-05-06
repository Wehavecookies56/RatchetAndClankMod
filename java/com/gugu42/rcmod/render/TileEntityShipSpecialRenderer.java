package com.gugu42.rcmod.render;

import org.lwjgl.opengl.GL11;

import com.gugu42.rcmod.utils.glutils.TessellatorModel;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class TileEntityShipSpecialRenderer extends TileEntitySpecialRenderer
		implements IInventoryRenderer {

	 private TessellatorModel model;

//	private IModelCustom model;
//	public static final ResourceLocation textureLocation = new ResourceLocation(
//			"rcmod:models/RatchetShip.png");

	public TileEntityShipSpecialRenderer() {
		 model = new TessellatorModel("/assets/rcmod/models/RatchetShip.obj");
		 model.regenerateNormals();
//		model = AdvancedModelLoader.loadModel(new ResourceLocation(
//				"rcmod:models/" + "RatchetShip.obj"));
	}

	@Override
	public void renderInventory(double x, double y, double z) {
		GL11.glPushMatrix();
		GL11.glTranslated(x - 0.15f, y - 0.5d, z + 0.15f);
//		this.bindTexture(textureLocation);
		GL11.glScalef(0.021f, 0.021f, 0.021f);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		model.render();
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z,
			float tick) {
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
//		this.bindTexture(textureLocation);
		GL11.glScalef(0.046f, 0.046f, 0.046f);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		model.render();
		GL11.glPopMatrix();
	}

}
