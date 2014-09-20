package com.gugu42.rcmod.render.armor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.opengl.GL11;

import com.gugu42.rcmod.ClientProxy;
import com.gugu42.rcmod.render.RcModelManager;

public class ClankBackpackRender extends ModelBiped {

	RcModelManager modelManager = new RcModelManager();

	public EntityPlayer playerWearingTheArmor;
	private long last;
	private float rotationHeli;

	public ClankBackpackRender() {
		super();
	}

	public void render(Entity par1Entity, float par2, float par3, float par4,
			float par5, float par6, float par7) {
		long deltaT = System.currentTimeMillis() - last;
		last = System.currentTimeMillis();
		float ratio = (deltaT / (1000 / 60));
		rotationHeli += 10.0f * ratio;

		if (rotationHeli >= 360f) {
			rotationHeli = 0;
		}

		double y = 0D;
		double angle = 0;
		float rotation = 0.13f;
		if (par1Entity.isSneaking()) {
			y = 1D;
			angle = 22.5;
			rotation = 0.35f;
		}

		if (par1Entity instanceof EntityPlayer) {
			playerWearingTheArmor = (EntityPlayer) par1Entity;
			if (playerWearingTheArmor.getCurrentArmor(2).getItemDamage() == 1) {
				GL11.glPushMatrix();
				GL11.glTranslated(0.0d, 0.7f, rotation);
				GL11.glRotated(180, 0.0D, 0.0D, 1.0D);
				GL11.glRotated(angle, -y, 0.0D, 0.0D);
				GL11.glScalef(0.045f, 0.045f, 0.045f);
//				modelManager.modelClankHeliBody.regenerateNormals();
				modelManager.modelClankHeliBody.renderAll();

				Minecraft.getMinecraft().renderEngine
						.bindTexture(modelManager.textureLocationClankHeli);

				GL11.glTranslatef(0.0f, 35.0f, 3.5f);
				GL11.glRotated(rotationHeli, 0.0d, 1.0d, 0.0d);
//				modelManager.modelClankHeli.regenerateNormals();
				modelManager.modelClankHeli.renderAll();
				GL11.glPopMatrix();

				GL11.glPushMatrix();
				GL11.glTranslated(0.0d, 0.7f, rotation);
				GL11.glRotated(180, 0.0D, 0.0D, 1.0D);
				GL11.glRotated(angle, -y, 0.0D, 0.0D);
				GL11.glScalef(0.045f, 0.045f, 0.045f);
				Minecraft.getMinecraft().renderEngine
						.bindTexture(modelManager.textureLocationClankHeli);
				GL11.glTranslatef(9.0f, 8.5f, 8.0f);
				GL11.glRotatef(80, 1.0f, 0.0f, 0.0f);
				GL11.glRotatef(-35, 0.0f, 0.0f, 1.0f);
				GL11.glRotated(rotationHeli, 0.0d, 1.0d, 0.0d);
//				modelManager.modelClankHeli2.regenerateNormals();
				modelManager.modelClankHeli2.renderAll();
				GL11.glPopMatrix();

				GL11.glPushMatrix();
				GL11.glTranslated(0.0d, 0.7f, rotation);
				GL11.glRotated(180, 0.0D, 0.0D, 1.0D);
				GL11.glRotated(angle, -y, 0.0D, 0.0D);
				GL11.glScalef(0.045f, 0.045f, 0.045f);
				Minecraft.getMinecraft().renderEngine
						.bindTexture(modelManager.textureLocationClankHeli);
				GL11.glTranslatef(-9.0f, 8.5f, 8.0f);
				GL11.glScalef(-1, -1, -1);
				GL11.glRotatef(-90, 1.0f, 0.0f, 0.0f);
				GL11.glRotatef(-35, 0.0f, 0.0f, 1.0f);
				GL11.glRotated(rotationHeli, 0.0d, 1.0d, 0.0d);
//				modelManager.modelClankHeli2.regenerateNormals();
				modelManager.modelClankHeli2.renderAll();
				GL11.glPopMatrix();
			} else {
				GL11.glPushMatrix();
				GL11.glTranslated(0.0d, 0.7f, rotation);
				GL11.glRotated(180, 0.0D, 0.0D, 1.0D);
				GL11.glRotated(angle, -y, 0.0D, 0.0D);
				GL11.glScalef(0.045f, 0.045f, 0.045f);
//				modelManager.modelClankBackpack.regenerateNormals();
				modelManager.modelClankBackpack.renderAll();
				GL11.glPopMatrix();

			}
		}
	}
}