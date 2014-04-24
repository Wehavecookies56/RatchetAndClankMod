package com.gugu42.rcmod.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

import org.lwjgl.opengl.GL11;

import com.gugu42.rcmod.utils.glutils.TessellatorModel;

public class RenderRatchetEars_Armor extends ModelBiped {

	private TessellatorModel model1;

	public RenderRatchetEars_Armor() {
		super();
		model1 = new TessellatorModel("/assets/rcmod/models/Ratchet_ears.obj");
		model1.regenerateNormals();

	}

	public void render(Entity par1Entity, float par2, float par3, float par4,
			float par5, float par6, float par7) {
		if (par1Entity instanceof EntityLivingBase) {
			EntityLivingBase entity = (EntityLivingBase)par1Entity;
			GL11.glPushMatrix();
			GL11.glRotated(180, 0.0D, 0.0D, 1.0D);
			GL11.glRotated(180, 0.0d, 1.0d, 0.0d);
			GL11.glTranslatef(0.0f, -1.5f, 0.0f);
			GL11.glScalef(0.045f, 0.045f, 0.045f);
			float factor = 1f / 0.045f;
			
			GL11.glTranslated(0.0d, 1.5f * factor, 0.0f);
			GL11.glRotatef(entity.rotationPitch, 1.0f, 0.0f, 0.0f);			
			GL11.glTranslated(0.0d, -1.5f * factor, 0.0f);
			
			GL11.glTranslated(0.0d, 1.5f * factor, 0.0f);

			GL11.glRotatef(-entity.rotationYawHead, 0.0f, 1.0f, 0.0f);
	//		GL11.glRotatef(entity.rotationYaw, 0.0f, 1.0f, 0.0f);
			GL11.glTranslated(0.0d, -1.5f * factor, 0.0f);
			GL11.glShadeModel(GL11.GL_SMOOTH);
			model1.render();
			GL11.glPopMatrix();
		}
	}
}
