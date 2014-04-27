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

import org.lwjgl.opengl.GL11;

public class OmniWrench3000Render implements IItemRenderer
{
    private IModelCustom model1 = AdvancedModelLoader.loadModel(new ResourceLocation("rcmod:models/Omniwrench.obj"));
    public static final ResourceLocation textureLocation = new ResourceLocation("rcmod:models/Omniwrench.png");

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

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return true;
    }

    public void renderItem(ItemRenderType type, ItemStack item, Object ... data)
    {
    	switch (type) {
		case EQUIPPED: {
			GL11.glPushMatrix();
	        GL11.glTranslatef(1.5F, 1F, 1F);
	        GL11.glScalef(0.3F, 0.3F, 0.3F);
	        GL11.glScalef(0.3F, 0.3F, 0.3F);
	        GL11.glScalef(0.7F, 0.7F, 0.7F);
	        GL11.glRotatef(100.0F, 0.0F, 360.0F, 0.0F);
	        GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
	        GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
	        Minecraft.getMinecraft().renderEngine.bindTexture(textureLocation);
	        this.model1.renderAll();
	        GL11.glPopMatrix();
			break;
		}
		case EQUIPPED_FIRST_PERSON: {
			GL11.glPushMatrix();
	        GL11.glTranslatef(0.3F, 0.5F, 0.3F);
	        GL11.glScalef(0.3F, 0.3F, 0.3F);
	        GL11.glScalef(0.3F, 0.3F, 0.3F);
	        GL11.glScalef(0.4F, 0.4F, 0.4F);
	        GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
	        GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
	        Minecraft.getMinecraft().renderEngine.bindTexture(textureLocation);
	        this.model1.renderAll();
	        GL11.glPopMatrix();
			break;
		}
		case ENTITY: {
			GL11.glPushMatrix();
			GL11.glTranslatef(0.1F, 0.3F, 0.3F);
			GL11.glScalef(0.3F, 0.3F, 0.3F);
			GL11.glScalef(0.3F, 0.3F, 0.3F);
			GL11.glScalef(0.39F, 0.39F, 0.39F);
			GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
			Minecraft.getMinecraft().renderEngine.bindTexture(textureLocation);
			this.model1.renderAll();
			GL11.glPopMatrix();
			break;
		}
		default:
			break;
		}

	}
}
