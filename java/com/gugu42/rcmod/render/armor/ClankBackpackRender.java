package com.gugu42.rcmod.render.armor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.opengl.GL11;

import com.gugu42.rcmod.ClientProxy;
import com.gugu42.rcmod.render.RcModelManager;
import com.gugu42.rcmod.utils.glutils.TessellatorModel;

public class ClankBackpackRender extends ModelBiped {
	
	//Clank body with arms
	public TessellatorModel heliBackpack;	
	
	//Clank body, without arms
	public TessellatorModel heliBody;
	
	//Big heli
	public TessellatorModel heli1;
	
	//Small heli, used two times
	public TessellatorModel heli2;
	

	public EntityPlayer playerWearingTheArmor;
	private long last;
	private float rotationHeli;

	public ClankBackpackRender() {
		super();
		heliBackpack = new TessellatorModel("/assets/rcmod/models/ClankBackpack.obj");
		heliBody = new TessellatorModel("/assets/rcmod/models/BodyHeli.obj");
		heli1 = new TessellatorModel("/assets/rcmod/models/Heli.obj");
		heli2 = new TessellatorModel("/assets/rcmod/models/Heli2.obj");
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
				heliBody.regenerateNormals();
				heliBody.render();

//				Minecraft.getMinecraft().renderEngine
//						.bindTexture(modelManager.textureLocationClankHeli);

				GL11.glTranslatef(0.0f, 35.0f, 3.5f);
				GL11.glRotated(rotationHeli, 0.0d, 1.0d, 0.0d);
				heli1.regenerateNormals();
				heli1.render();
				GL11.glPopMatrix();

				GL11.glPushMatrix();
				GL11.glTranslated(0.0d, 0.7f, rotation);
				GL11.glRotated(180, 0.0D, 0.0D, 1.0D);
				GL11.glRotated(angle, -y, 0.0D, 0.0D);
				GL11.glScalef(0.045f, 0.045f, 0.045f);
//				Minecraft.getMinecraft().renderEngine
//						.bindTexture(modelManager.textureLocationClankHeli);
				GL11.glTranslatef(9.0f, 8.5f, 8.0f);
				GL11.glRotatef(80, 1.0f, 0.0f, 0.0f);
				GL11.glRotatef(-35, 0.0f, 0.0f, 1.0f);
				GL11.glRotated(rotationHeli, 0.0d, 1.0d, 0.0d);
				heli2.regenerateNormals();
				heli2.render();
				GL11.glPopMatrix();

				GL11.glPushMatrix();
				GL11.glTranslated(0.0d, 0.7f, rotation);
				GL11.glRotated(180, 0.0D, 0.0D, 1.0D);
				GL11.glRotated(angle, -y, 0.0D, 0.0D);
				GL11.glScalef(0.045f, 0.045f, 0.045f);
//				Minecraft.getMinecraft().renderEngine
//						.bindTexture(modelManager.textureLocationClankHeli);
				GL11.glTranslatef(-9.0f, 8.5f, 8.0f);
				GL11.glScalef(-1, -1, -1);
				GL11.glRotatef(-90, 1.0f, 0.0f, 0.0f);
				GL11.glRotatef(-35, 0.0f, 0.0f, 1.0f);
				GL11.glRotated(rotationHeli, 0.0d, 1.0d, 0.0d);
				heli2.regenerateNormals();
				heli2.render();
				GL11.glPopMatrix();
			} else {
				GL11.glPushMatrix();
				GL11.glTranslated(0.0d, 0.7f, rotation);
				GL11.glRotated(180, 0.0D, 0.0D, 1.0D);
				GL11.glRotated(angle, -y, 0.0D, 0.0D);
				GL11.glScalef(0.045f, 0.045f, 0.045f);
				heliBackpack.regenerateNormals();
				heliBackpack.render();
				GL11.glPopMatrix();

			}
		}
	}
}