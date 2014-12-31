package com.gugu42.rcmod.render;

import static org.lwjgl.opengl.GL11.glColor3f;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import com.gugu42.rcmod.utils.glutils.TessellatorModel;
import com.gugu42.rcmod.utils.glutils.TessellatorModelEvent.RenderGroupEvent;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class SuckCannonRender implements IItemRenderer {

	private TessellatorModel model1;

	private long last;
	private float rotation;
	private boolean shouldMuzzleRotate = false;

	public SuckCannonRender() {
		model1 = new TessellatorModel("/assets/rcmod/models/SuckCannon2.obj");
		model1.regenerateNormals();
		model1.setID("suckcannon");
		TessellatorModel.MODEL_RENDERING_BUS.register(this);
	}

	@SubscribeEvent
	public void onPreRenderGroup(RenderGroupEvent.Pre event) {
		if (event.model.getID() != null) {
			if (event.model.getID().equals("suckcannon")) {
				if (event.group.equals("Muzzle")) {
					long deltaT = System.currentTimeMillis() - last;
					last = System.currentTimeMillis();
					float ratio = (deltaT / (1000 / 60));
					rotation += 10.0f * ratio;

					if (rotation >= 360f) {
						rotation = 0;
					}

					if (shouldMuzzleRotate) {
						GL11.glPushMatrix();
						double x = 0;
						double y = 0;
						double z = -1.15;
						GL11.glTranslated(x, y, z);
						GL11.glRotatef(rotation, 1.0f, 0.0f, 0.0f);
						GL11.glTranslated(-x, -y, -z);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onPostRenderGroup(RenderGroupEvent.Post event) {
		if (event.model.getID() != null) {
			if (event.model.getID().equals("suckcannon")) {
				if (event.group.equals("Muzzle")) {
					if (shouldMuzzleRotate)
						GL11.glPopMatrix();
				}
			}

		}
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
			if(data[1] instanceof EntityPlayer) // We check it's a player holding it
			{
				EntityPlayer owner = (EntityPlayer)data[1];
				if(owner.getEntityData().getBoolean("isUsingSuckCannon")){
					shouldMuzzleRotate = true;
				} else {
					shouldMuzzleRotate = false;
				}
				
			}
			
			GL11.glPushMatrix();
			GL11.glTranslatef(1.2f, 0.8f, 1.2f);
			GL11.glRotatef(135, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(-65, 0.0f, 0.0f, 1.0f);
			GL11.glScalef(0.09f, 0.09f, 0.09f);
			GL11.glShadeModel(GL11.GL_SMOOTH);
			model1.render();
			GL11.glPopMatrix();
			break;
		}
		case EQUIPPED_FIRST_PERSON: {
			if(data[1] instanceof EntityPlayer) // We check it's a player holding it
			{
				EntityPlayer owner = (EntityPlayer)data[1];
				if(owner.getEntityData().getBoolean("isUsingSuckCannon")){
					shouldMuzzleRotate = true;
				} else {
					shouldMuzzleRotate = false;
				}
			}
			
			GL11.glPushMatrix();
			GL11.glTranslatef(1.2f, 0.5f, 2.0f);
			GL11.glRotatef(50, 0.0f, 1.0f, 0.0f);
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
