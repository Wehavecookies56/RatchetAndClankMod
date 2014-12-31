package com.gugu42.rcmod.render;

import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glColor4f;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.jglrxavpok.glutils.obj.mc.TessellatorModel;
import org.lwjgl.opengl.GL11;

public class WalloperRender implements IItemRenderer {

	private RcModelManager modelManager;
	private TessellatorModel model;

	public WalloperRender() {
		modelManager = new RcModelManager();
		model = new TessellatorModel("/assets/rcmod/models/Walloper.obj");
		model.regenerateNormals();
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
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		switch (type) 
		{
    		case EQUIPPED: 
    		{
    			if(data[1] instanceof EntityPlayer) // We check it's a player holding it
    			{
    				EntityPlayer owner = (EntityPlayer)data[1];
        			float chargeState = (float)owner.getItemInUseDuration()/45f; // We get 'how much' we charged it 
        			if(chargeState >= 1.0f)
        				chargeState = 1.0f;
        			glColor3f(1, 1-chargeState, 1-chargeState); // And we "reddify" the glove
    			}
    			GL11.glPushMatrix();
    			GL11.glTranslatef(0.81f, 0.29f, 0.00f);
    			GL11.glRotatef(180, 0.0f, 1.0f, 0.0f);
    			GL11.glRotatef(-220, 0.0f, 0.0f, 1.0f);
    			GL11.glRotatef(270, 1.0f, 0.0f, 0.0f);
    			GL11.glScalef(-0.051f, -0.051f, -0.051f);
    			GL11.glShadeModel(GL11.GL_SMOOTH);
    			model.render();
    			GL11.glTranslatef(-0.5F, 0.0F, 0.09F);
    			GL11.glPopMatrix();
    			break;
    		}
    		case EQUIPPED_FIRST_PERSON: 
    		{
    			if(data[1] instanceof EntityPlayer) // We check it's a player holding it
    			{
    				EntityPlayer owner = (EntityPlayer)data[1];
        			float chargeState = (float)owner.getItemInUseDuration()/45f; // We get 'how much' we charged it 
        			if(chargeState >= 1.0f)
        				chargeState = 1.0f;
        			glColor3f(1, 1-chargeState, 1-chargeState); // And we "reddify" the glove
    			}
    			GL11.glPushMatrix();
    			GL11.glTranslatef(0.1f, 0.1f, -0.2f);
    			GL11.glRotatef(190, 0.0f, 1.0f, 0.0f);
    			GL11.glRotatef(-90, 1.0f, 0.0f, 0.0f);
    			GL11.glRotatef(60, 0.0f, 1.0f, 0.0f);
    			GL11.glScalef(0.046f, 0.046f, 0.046f);
    			GL11.glShadeModel(GL11.GL_SMOOTH);
    			model.render();
    			GL11.glPopMatrix();
    			break;
    		}
    		case ENTITY:
    		{
    			GL11.glPushMatrix();
    			GL11.glScalef(0.05f, 0.05f, 0.05f);
    			GL11.glTranslatef(-8.5f, 5.5f, 0.0f);
    			GL11.glRotatef(90, 1.0f, 0.0f, 0.0f);
    			GL11.glRotatef(180, 0.0f, 1.0f, 0.0f);
    			GL11.glShadeModel(GL11.GL_SMOOTH);
    			model.render();
    			GL11.glPopMatrix();
    			break;
    		}
    		default:
    			break;
		}

		glColor4f(1, 1, 1, 1);
	}

}
