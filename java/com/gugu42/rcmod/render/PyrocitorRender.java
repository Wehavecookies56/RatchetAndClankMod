package com.gugu42.rcmod.render;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

public class PyrocitorRender implements IItemRenderer {

	private IModelCustom model1;
	public static final ResourceLocation textureLocation = new ResourceLocation(
			"rcmod:models/Pyrocitor.png");

	public PyrocitorRender() {
		model1 = AdvancedModelLoader
				.loadModel(new ResourceLocation("rcmod:models/Pyrocitor.obj"));
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
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		switch (type) {
		case EQUIPPED: {
			GL11.glPushMatrix();
			Minecraft.getMinecraft().renderEngine.bindTexture(textureLocation);
			GL11.glTranslatef(0.62f, 0.21f, 0.03f);
			GL11.glRotatef(-5, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(220, 0.0f, 0.0f, 1.0f);
	//		GL11.glRotatef(-35, 1.0f, 0.0f, 0.0f);
			GL11.glScalef(-0.04f, -0.04f, -0.04f);
			model1.renderAll();

			GL11.glTranslatef(-0.5F, 0.0F, 0.09F);
			GL11.glPopMatrix();
			break;
		}
		case EQUIPPED_FIRST_PERSON: {
			GL11.glPushMatrix();
			Minecraft.getMinecraft().renderEngine.bindTexture(textureLocation);
			GL11.glTranslatef(0.1f, 0f, -0.1f);
			GL11.glRotatef(00, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(0, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(30, 0.0f, 0.0f, 1.0f);
			GL11.glScalef(0.05f, 0.05f, 0.05f);
			model1.renderAll();
			GL11.glPopMatrix();
			break;
		}
		case ENTITY: {
			GL11.glPushMatrix();
			Minecraft.getMinecraft().renderEngine.bindTexture(textureLocation);
			GL11.glScalef(0.04f, 0.04f, 0.04f);
			GL11.glTranslatef(-14f, 6f, 0.0f);
			model1.renderAll();
			GL11.glPopMatrix();
			break;
		}
		default:
			break;
		}

	}
}
