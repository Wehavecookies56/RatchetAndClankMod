package com.gugu42.rcmod.render.armor;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

import org.jglrxavpok.glutils.mc.TessellatorModel;
import org.lwjgl.opengl.GL11;

public class RenderRatchetEars_Armor extends ModelBiped {

	private TessellatorModel model1;

	public RenderRatchetEars_Armor() {
		super();
		model1 = new TessellatorModel("/assets/rcmod/models/Ratchet_ears.obj");
		model1.regenerateNormals();
	}
	
	public void setLivingAnimations(EntityLivingBase ent, float x, float y, float partialTime)
	{
	    super.setRotationAngles(0,0,0,x*(180f/(float)Math.PI),y*(180f/(float)Math.PI),0.0625F, ent);
	}

	public void render(Entity par1Entity, float par2, float par3, float par4,
			float par5, float par6, float par7) {
		if (par1Entity instanceof EntityLivingBase) {
			EntityLivingBase entity = (EntityLivingBase)par1Entity;
			GL11.glPushMatrix();
			float y = 1.5f;
			if(entity.isSneaking())
			    y = 1.53f;
			GL11.glRotated(180, 0.0D, 0.0D, 1.0D);
			GL11.glRotated(180, 0.0d, 1.0d, 0.0d);
			GL11.glTranslatef(0.0f, -y, 0.0f);
			GL11.glScalef(0.045f, 0.045f, 0.045f);
			float factor = 1f / 0.045f;
			
			
			GL11.glTranslated(0.0d, 1.5f * factor, 0.0f);

			GL11.glRotated(-par5, 0.0f, 1.0f, 0.0f);
            
			GL11.glTranslated(0.0d, -y * factor, 0.0f);
			
	         GL11.glTranslated(0.0d, y * factor, 0.0f);
            GL11.glRotatef(par6, 1.0f, 0.0f, 0.0f);         
            GL11.glTranslated(0.0d, -y * factor, 0.0f);

			GL11.glShadeModel(GL11.GL_SMOOTH);
			model1.render();
			GL11.glPopMatrix();
		}
	}

    private float interpolateRotation(float par1, float par2, float par3)
    {
        float f3;

        for (f3 = par2 - par1; f3 < -180.0F; f3 += 360.0F)
        {
            ;
        }

        while (f3 >= 180.0F)
        {
            f3 -= 360.0F;
        }

        return par1 + par3 * f3;
    }
}
