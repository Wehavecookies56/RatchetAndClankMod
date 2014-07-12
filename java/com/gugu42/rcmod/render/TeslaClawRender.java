package com.gugu42.rcmod.render;

import static org.lwjgl.opengl.GL11.*;

import com.gugu42.rcmod.utils.glutils.TessellatorModel;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class TeslaClawRender implements IItemRenderer {

	private TessellatorModel model1;
	
	public TeslaClawRender() {
		model1 = new TessellatorModel("/assets/rcmod/models/TeslaClaw.obj");
		model1.regenerateNormals();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch (type) {
		case EQUIPPED:
			return true;
		case EQUIPPED_FIRST_PERSON:
			return true;
		case ENTITY:
			return true;
		default:
			return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		switch(type)
		{
			case EQUIPPED:
			{
				GL11.glPushMatrix();
				GL11.glTranslatef(0.8f, 0.2f, 0.8f);
				GL11.glRotatef(135, 0.0f, 1.0f, 0.0f);
				GL11.glRotatef(-65, 0.0f, 0.0f, 1.0f);
				// GL11.glRotatef(-45, 1.0f, 0.0f, 0.0f);
				GL11.glScalef(0.09f, 0.09f, 0.09f);
				GL11.glShadeModel(GL11.GL_SMOOTH);
				model1.render();

				 GL11.glTranslatef(12, 4, 0);
				 glRotated(180, 1,0, 0);
				drawLightningBolt((Entity)data[1]);
				GL11.glPopMatrix();
				
				break;
			}
			case EQUIPPED_FIRST_PERSON:
			{
				GL11.glPushMatrix();
				GL11.glTranslatef(0.9f, 0.5f, 0.9f);
				GL11.glRotatef(40, 0.0f, 1.0f, 0.0f);
				// GL11.glRotatef(-10, 0.0f, 0.0f, 1.0f);
				GL11.glScalef(0.09f, 0.09f, 0.09f);
				GL11.glShadeModel(GL11.GL_SMOOTH);
				model1.render();
				GL11.glPopMatrix();
				
				GL11.glPushMatrix();
				GL11.glRotatef(-40, 0.0f, 1.0f, 0.0f);
				
				GL11.glRotatef(90, 0.0f, 0.0f, 1.0f);
				GL11.glRotatef(90, 0.0f, 1.0f, 0.0f);
				GL11.glRotatef(-90, 1.0f, 0.0f, 0.0f);
				GL11.glRotatef(-10, 0.0f, 0.0f, 1.0f);
				GL11.glTranslatef(1.5f,-0.45f, -1.25f);
				
				GL11.glRotatef(-5, 0.0f, 1.0f, 0.0f);
				GL11.glRotatef(3.5f, 0.0f, 0.0f, 1.0f);
				GL11.glScalef(0.09f, 0.09f, 0.09f);
				GL11.glShadeModel(GL11.GL_SMOOTH);
				drawLightningBolt((Entity)data[1]);
				GL11.glPopMatrix();
				
				break;
			}
			case ENTITY:
			{
				GL11.glPushMatrix();
				GL11.glScalef(0.04f, 0.04f, 0.04f);
				GL11.glTranslatef(0f, 0f, 10.0f);
				GL11.glScalef(0.6f, 0.6f, 0.6f);
				GL11.glRotatef(90, 0.0f, 1.0f, 0.0f);
				GL11.glShadeModel(GL11.GL_SMOOTH);
				model1.render();
				GL11.glPopMatrix();
				break;
			}
			default:
				break;
		}

	}

	private void drawLightningBolt(Entity entity)
	{
		glDisable(GL_TEXTURE_2D);
		glDisable(GL_LIGHTING);
		
		glColor3f(0, 0.6f, 1);
		glBegin(GL_QUADS);
		int lastX = 0;
		int lastY = 0;
		for(int i = 0;i<60;i++)
		{
			int x = i*2;
			int y = (int)(Math.sin(i*(Math.PI/4) - entity.ticksExisted*2.05f)*2.5f);
			
			glColor3f(1, 1f, 1);
			glVertex3d(x+0, y+0, 0);
			glVertex3d(x+0, y+1, 0);			
			glVertex3d(lastX, lastY+1, 0);
			glVertex3d(lastX, lastY, 0);
			
			glColor3f(0, 0.6f, 1);
			glVertex3d(x+0, y+1, 0);
			glVertex3d(x+0, y+1.25, 0);			
			glVertex3d(lastX, lastY+1.25, 0);
			glVertex3d(lastX, lastY+1, 0);
			
			glVertex3d(x+0, y-0.25, 0);
			glVertex3d(x+0, y, 0);			
			glVertex3d(lastX, lastY, 0);
			glVertex3d(lastX, lastY-0.25, 0);
			lastX = x;
			lastY = y;
		}
		glEnd();
		glEnable(GL_LIGHTING);
		glEnable(GL_TEXTURE_2D);
	}

}
