package com.gugu42.rcmod.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import com.gugu42.rcmod.entity.projectiles.EntityBombGloveAmmo;
import com.gugu42.rcmod.utils.glutils.TessellatorModel;

@SideOnly(Side.CLIENT)
public class RenderBombGloveAmmo extends Render {
	private float field_77002_a;
	
	private TessellatorModel modelBombGlove;

	public RenderBombGloveAmmo(RenderManager p_i46176_1_, float p_i46176_2_) {
		super(p_i46176_1_);
		modelBombGlove = new TessellatorModel("/assets/rcmod/models/Bomb0.obj");
	}

	public void doRenderBombGloveAmmo(
			EntityBombGloveAmmo par1EntityBombGloveAmmo, double par2,
			double par4, double par6, float par8, float par9) {
		
		GL11.glPushMatrix();
		GL11.glTranslatef((float)par2, (float)par4, (float)par6);
		GL11.glScalef(0.04f, 0.04f, 0.04f);
		modelBombGlove.render();

		GL11.glTranslatef(-0.5F, 0.0F, 0.09F);
		GL11.glPopMatrix();
	}

	protected ResourceLocation getBombGloveAmmoTextures(
			EntityBombGloveAmmo par1EntityBombGloveAmmo) {
		return TextureMap.locationBlocksTexture;
	}

	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return this.getBombGloveAmmoTextures((EntityBombGloveAmmo) par1Entity);
	}

	public void doRender(Entity par1Entity, double par2, double par4,
			double par6, float par8, float par9) {
		this.doRenderBombGloveAmmo((EntityBombGloveAmmo) par1Entity, par2,
				par4, par6, par8, par9);
	}
}
