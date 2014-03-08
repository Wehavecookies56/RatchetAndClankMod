package com.gugu42.rcmod.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.jglrxavpok.glutils.TessellatorModel;
import org.lwjgl.opengl.GL11;

public class DevastatorRender implements IItemRenderer {

	private TessellatorModel model1;
   
	public DevastatorRender(){
    	model1 = new TessellatorModel("/assets/rcmod/models/Devastator.obj");
    	model1.regenerateNormals();
    }
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		{
			switch (type)
			{
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
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		if (type == ItemRenderType.ENTITY) 
		{
			GL11.glPushMatrix();
			GL11.glTranslated(0.3F, 0.0F, 0.5F);
	        GL11.glScalef(0.09F, 0.09F, 0.09F);
	        GL11.glScalef(0.5F, 0.5F, 0.5F);
	        GL11.glScalef(0.79F, 0.79F, 0.79F);
	        GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
	        GL11.glShadeModel(GL11.GL_SMOOTH);
	        model1.render();
	        GL11.glPopMatrix();
		}
		if (type == ItemRenderType.EQUIPPED) 
		{
			GL11.glPushMatrix();
			GL11.glTranslated(0.95F, 1.2F, 1F);
	        GL11.glScalef(0.5F, 0.5F, 0.5F);
	        GL11.glScalef(0.5F, 0.5F, 0.5F);
	        GL11.glScalef(0.5F, 0.5F, 0.5F);
	        GL11.glScalef(0.65F, 0.65F, 0.65F);
	        GL11.glRotatef(135F, 0.0F, 1.0F, 0.0F);
	        GL11.glRotatef(-67.5F, 0.0F, 0.0F, 1.0F);
	        GL11.glShadeModel(GL11.GL_SMOOTH);
	        model1.render();
	        GL11.glPopMatrix();
		}

		if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
			GL11.glPushMatrix();
			GL11.glTranslated(0.8F, 0.0F, 1F);
	        GL11.glScalef(0.5F, 0.5F, 0.5F);
	        GL11.glScalef(0.5F, 0.5F, 0.5F);
	        GL11.glScalef(0.5F, 0.5F, 0.5F);
	        GL11.glRotatef(35F, 0.0F, 1.0F, 0.0F);
	        GL11.glShadeModel(GL11.GL_SMOOTH);
	        model1.render();
	        GL11.glPopMatrix();
		}

	}

}