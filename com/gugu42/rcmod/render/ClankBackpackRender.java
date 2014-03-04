package com.gugu42.rcmod.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

public class ClankBackpackRender extends ModelBiped {

	RcModelManager modelManager;

	public ClankBackpackRender() {
		super();
		modelManager = new RcModelManager();
	}

	public void render(Entity par1Entity, float par2, float par3, float par4,
			float par5, float par6, float par7) {
		if (par1Entity.onGround) {
			GL11.glPushMatrix();
			GL11.glTranslated(0.0d, 0.7f, 0.13f);
			GL11.glRotated(180, 0.0D, 0.0D, 1.0D);
			GL11.glScalef(0.045f, 0.045f, 0.045f);
			modelManager.modelClankBackpack.renderAll();
			GL11.glPopMatrix();
		} else {
			if (par1Entity.getEntityData().getInteger("clankCooldown") >= 1) {
				GL11.glPushMatrix();
				GL11.glTranslated(0.0d, 0.7f, 0.13f);
				GL11.glRotated(180, 0.0D, 0.0D, 1.0D);
				GL11.glScalef(0.045f, 0.045f, 0.045f);
				modelManager.modelClankHeliBody.renderAll();

				Minecraft.getMinecraft().renderEngine.bindTexture(modelManager.textureLocationClankHeli);
				
				float f2 = (float) par1Entity.worldObj.getTotalWorldTime();
				byte b1 = 1;
				double d3 = (double) f2 * 0.025D
						* (1.0D - (double) (b1 & 1) * 2.5D);

				GL11.glTranslatef(0.0f, 35.0f, 3.5f);
				if (par1Entity.motionY < 0.0f || par1Entity.motionY > 0.0f)
					GL11.glRotated(d3 * 5 * 100, 0.0d, 1.0d, 0.0d);
				modelManager.modelClankHeli.renderAll();
				GL11.glPopMatrix();
				
				GL11.glPushMatrix();
				GL11.glTranslated(0.0d, 0.7f, 0.13f);
				GL11.glRotated(180, 0.0D, 0.0D, 1.0D);
				GL11.glScalef(0.045f, 0.045f, 0.045f);
				Minecraft.getMinecraft().renderEngine.bindTexture(modelManager.textureLocationClankHeli);	
				GL11.glTranslatef(9.0f, 8.5f, 8.0f);
				GL11.glRotatef(80, 1.0f, 0.0f, 0.0f);
				GL11.glRotatef(-35, 0.0f, 0.0f, 1.0f);
				//GL11.glRotatef(10, 0.0f, 0.0f, 0.0f);
				if (par1Entity.motionY < 0.0f || par1Entity.motionY > 0.0f)
					GL11.glRotated(d3 * 5 * 100, 0.0d, 1.0d, 0.0d);
				modelManager.modelClankHeli2.renderAll();
				GL11.glPopMatrix();
				
				GL11.glPushMatrix();
				GL11.glTranslated(0.0d, 0.7f, 0.13f);
				GL11.glRotated(180, 0.0D, 0.0D, 1.0D);
				GL11.glScalef(0.045f, 0.045f, 0.045f);
				Minecraft.getMinecraft().renderEngine.bindTexture(modelManager.textureLocationClankHeli);	
				GL11.glTranslatef(-9.0f, 8.5f, 8.0f);
				GL11.glScalef(-1, -1, -1);
				GL11.glRotatef(-90, 1.0f, 0.0f, 0.0f);
				GL11.glRotatef(-35, 0.0f, 0.0f, 1.0f);
				if (par1Entity.motionY < 0.0f || par1Entity.motionY > 0.0f)
					GL11.glRotated(d3 * 5 * 100, 0.0d, 1.0d, 0.0d);
				modelManager.modelClankHeli2.renderAll();
				GL11.glPopMatrix();
			} else {
				GL11.glPushMatrix();
				GL11.glTranslated(0.0d, 0.7f, 0.13f);
				GL11.glRotated(180, 0.0D, 0.0D, 1.0D);
				GL11.glScalef(0.045f, 0.045f, 0.045f);
				modelManager.modelClankBackpack.renderAll();
				GL11.glPopMatrix();
			}
		}
	}
}
