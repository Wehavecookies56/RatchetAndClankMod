package com.gugu42.rcmod.render;

import org.lwjgl.opengl.GL11;

import com.gugu42.rcmod.entity.projectiles.EntityDecoyGloveAmmo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDecoyGloveAmmo extends Render {

	private float field_77002_a;

	private IModelCustom model1;
	public static final ResourceLocation textureLocation = new ResourceLocation(
			"rcmod:models/Decoy.png");

	
	private float inflateSize;
	private float orientationYaw;
	
	public RenderDecoyGloveAmmo(float par1) {
		this.field_77002_a = par1;
		model1 = AdvancedModelLoader
				.loadModel(new ResourceLocation("rcmod:models/Decoy.obj"));
		this.inflateSize = 0.001f;
	}

	public void doRenderDecoyGloveAmmo(
			EntityDecoyGloveAmmo par1EntityDecoyGloveAmmo, double par2,
			double par4, double par6, float par8, float par9) {

		if(!par1EntityDecoyGloveAmmo.isActive){
			this.orientationYaw = par1EntityDecoyGloveAmmo.getOrientationYaw();
			GL11.glPushMatrix();
			Minecraft.getMinecraft().renderEngine.bindTexture(textureLocation);
			GL11.glTranslatef((float)par2, (float)par4, (float)par6);
			GL11.glRotatef(this.orientationYaw, 0.0f, 1.0f, 0.0f);
			GL11.glScalef(0.001f, 0.001f, 0.001f);
			model1.renderAll();
			GL11.glPopMatrix();
		} else {
			this.inflateSize = par1EntityDecoyGloveAmmo.inflateSize;
			GL11.glPushMatrix();
			Minecraft.getMinecraft().renderEngine.bindTexture(textureLocation);
			GL11.glTranslatef((float)par2, (float)par4, (float)par6);
			GL11.glScalef(inflateSize, inflateSize, inflateSize);
			GL11.glRotatef(par1EntityDecoyGloveAmmo.getOrientationYaw(), 0.0f, 1.0f, 0.0f);
			model1.renderAll();

			GL11.glTranslatef(-0.5F, 0.0F, 0.09F);
			GL11.glPopMatrix();
			
		}

	}

	protected ResourceLocation getDecoyGloveAmmoTextures(
			EntityDecoyGloveAmmo par1EntityDecoyGloveAmmo) {
		return TextureMap.locationItemsTexture;
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called
	 * unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return this.getDecoyGloveAmmoTextures((EntityDecoyGloveAmmo) par1Entity);
	}

	/**
	 * Actually renders the given argument. This is a synthetic bridge method,
	 * always casting down its argument and then handing it off to a worker
	 * function which does the actual work. In all probabilty, the class Render
	 * is generic (Render<T extends Entity) and this method has signature public
	 * void doRender(T entity, double d, double d1, double d2, float f, float
	 * f1). But JAD is pre 1.5 so doesn't do that.
	 */
	public void doRender(Entity par1Entity, double par2, double par4,
			double par6, float par8, float par9) {
		this.doRenderDecoyGloveAmmo((EntityDecoyGloveAmmo) par1Entity, par2,
				par4, par6, par8, par9);
	}
}
