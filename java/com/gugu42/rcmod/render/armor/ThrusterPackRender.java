package com.gugu42.rcmod.render.armor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.opengl.GL11;

import com.gugu42.rcmod.render.RcModelManager;
import com.gugu42.rcmod.utils.glutils.TessellatorModel;

public class ThrusterPackRender extends ModelBiped {
	public EntityPlayer playerWearingTheArmor;
	private TessellatorModel model1;

	public ThrusterPackRender() {
		super();
		model1 = new TessellatorModel("/assets/rcmod/models/Thrusterpack.obj");
		model1.regenerateNormals();
	}

	public void render(Entity par1Entity, float par2, float par3, float par4,
			float par5, float par6, float par7) {

		double y = 0D;
		double angle = 0;
		float rotation = 0.13f;
		if (par1Entity.isSneaking()) {
			y = 1D;
			angle = 22.5;
			rotation = 0.35f;
		}

		GL11.glPushMatrix();
		GL11.glTranslated(0.0d, 0.7f, rotation);
		GL11.glRotated(180, 0.0D, 0.0D, 1.0D);
		GL11.glRotated(angle, -y, 0.0D, 0.0D);
		GL11.glScalef(0.045f, 0.045f, 0.045f);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		model1.render();
		GL11.glPopMatrix();

	}
}
