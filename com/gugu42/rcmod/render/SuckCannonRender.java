package com.gugu42.rcmod.render;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import com.gugu42.rcmod.utils.glutils.TessellatorModel;

public class SuckCannonRender implements IItemRenderer {

	private TessellatorModel model1;
	
	public SuckCannonRender() {
		model1 = new TessellatorModel("/assets/rcmod/models/SuckCannon.obj");
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
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		switch (type) {
		case EQUIPPED: {
			GL11.glPushMatrix();
			GL11.glTranslatef(1.2f, 0.8f, 1.2f);
			GL11.glRotatef(135, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(-65, 0.0f, 0.0f, 1.0f);
			//GL11.glRotatef(-45, 1.0f, 0.0f, 0.0f);
			GL11.glScalef(0.09f, 0.09f, 0.09f);
			GL11.glShadeModel(GL11.GL_SMOOTH);
			model1.render();
			
			//GL11.glTranslatef(-0.5F, 0.0F, 0.09F);
			GL11.glPopMatrix();
			break;
		}
		case EQUIPPED_FIRST_PERSON: {
			GL11.glPushMatrix();
			GL11.glTranslatef(1.1f, 0.5f, 1.1f);
			GL11.glRotatef(35, 0.0f, 1.0f, 0.0f);
			//GL11.glRotatef(-10, 0.0f, 0.0f, 1.0f);
			GL11.glScalef(0.09f, 0.09f, 0.09f);
			GL11.glShadeModel(GL11.GL_SMOOTH);
			model1.render();
			GL11.glPopMatrix();
			break;
		}
		case ENTITY: {
			GL11.glPushMatrix();
			GL11.glScalef(0.04f, 0.04f, 0.04f);
			GL11.glTranslatef(0.0f, 0f, 12.0f);
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

}
