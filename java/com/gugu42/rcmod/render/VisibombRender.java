package com.gugu42.rcmod.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import com.gugu42.rcmod.utils.glutils.TessellatorModel;

public class VisibombRender implements IItemRenderer {

//	private IModelCustom model1;
//    private ResourceLocation textureLocation;
   
	private TessellatorModel modelVisibomb;
	
	public VisibombRender(){
//    	model1 = AdvancedModelLoader.loadModel(new ResourceLocation("rcmod:models/visibomb.obj"));
//    	textureLocation = new ResourceLocation("rcmod:models/visibomb.png");
		modelVisibomb = new TessellatorModel("/assets/rcmod/models/visibomb.obj");
		modelVisibomb.regenerateNormals();
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
		switch (type)
		{
		case ENTITY:
			{
				GL11.glPushMatrix();
			//GL11.glTranslatef((float)x, (float)y, (float)z);
				GL11.glTranslated(0.2F, 0.0F, 0.6F);
				GL11.glScalef(0.5F, 0.5F, 0.5F);
				GL11.glScalef(0.5F, 0.5F, 0.5F);
				GL11.glScalef(0.5F, 0.5F, 0.5F);
				GL11.glScalef(0.5F, 0.5F, 0.5F);
				GL11.glScalef(0.6F, 0.7F, 0.6F);
				GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
//				engine.bindTexture(textureLocation);
//				if (model1 != null) model1.renderAll();
				modelVisibomb.render();
				GL11.glPopMatrix();
				break;
			}
		case EQUIPPED:
			{
				GL11.glPushMatrix();
				//GL11.glTranslatef((float)x, (float)y, (float)z);
				GL11.glTranslated(1.5F, 1.1F, 1.0F);
				GL11.glScalef(0.5F, 0.5F, 0.5F);
				GL11.glScalef(0.5F, 0.5F, 0.5F);
				GL11.glScalef(0.5F, 0.5F, 0.5F);
				GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(-67.5F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
				modelVisibomb.render();
				GL11.glPopMatrix();
				break;
			}
		case EQUIPPED_FIRST_PERSON:
			{
				GL11.glPushMatrix();
				//GL11.glTranslatef((float)x, (float)y, (float)z);
				GL11.glTranslated(2.0F, 0.0F, 2.0F);
				GL11.glScalef(0.5F, 0.5F, 0.5F);
				GL11.glScalef(0.5F, 0.5F, 0.5F);
				GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);
				modelVisibomb.render();
				GL11.glPopMatrix();
				break;
			}
		default:
			{
				break;
			}

		}
	}
}
