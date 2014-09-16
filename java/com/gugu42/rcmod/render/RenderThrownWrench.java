package com.gugu42.rcmod.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import com.gugu42.rcmod.entity.projectiles.EntityWrenchThrown;

public class RenderThrownWrench extends Render {
	private float field_77002_a;
	private IModelCustom model1;
	private ResourceLocation textureLocation;

	public RenderThrownWrench(float par1) {
		model1 = AdvancedModelLoader
				.loadModel(new ResourceLocation("rcmod:models/Omniwrench.obj"));
		textureLocation = new ResourceLocation("rcmod:models/Omniwrench.png");
	}

	public void doRenderWrenchThrown(EntityWrenchThrown par1EntityWrenchThrown,
			double x, double y, double z, float yaw, float partialRenderTick) {
		float f2 = (float) par1EntityWrenchThrown.worldObj.getTotalWorldTime();
		byte b1 = 1;
		double d3 = (double) f2 * 0.025D * (1.0D - (double) (b1 & 1) * 2.5D);

		TextureManager engine = Minecraft.getMinecraft().getTextureManager();
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		GL11.glScalef(0.5F, 0.5F, 0.5F);

		GL11.glRotatef(90, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(90, 0.0f, 1.0f, 0.0f);
		GL11.glRotated(d3 * 5 * 300, 0.0D, 0.0D, 1.0D);

		// GL11.glRotatef(0.0F - this.renderManager.playerViewY, 0.0F, 1.0F,
		// 0.0F);
		// GL11.glRotatef(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		engine.bindTexture(textureLocation);
		this.model1.renderAll();
		GL11.glPopMatrix();

	}

	protected ResourceLocation getWrenchThrownTextures(
			EntityWrenchThrown par1EntityWrenchThrown) {
		return TextureMap.locationItemsTexture;
	}

	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return this.getWrenchThrownTextures((EntityWrenchThrown) par1Entity);
	}

	public void doRender(Entity par1Entity, double par2, double par4,
			double par6, float par8, float par9) {
		this.doRenderWrenchThrown((EntityWrenchThrown) par1Entity, par2, par4,
				par6, par8, par9);
	}
}
