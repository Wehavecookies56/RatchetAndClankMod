package com.gugu42.rcmod.render;

import org.lwjgl.opengl.GL11;

import com.gugu42.rcmod.entity.projectiles.EntityVisibombAmmo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class RenderVisibombAmmo extends Render
{
    private float field_77002_a;
    private IModelCustom model1;
    private ResourceLocation textureLocation;
   
    public RenderVisibombAmmo(float par1){
    	model1 = AdvancedModelLoader.loadModel(new ResourceLocation("rcmod:models/visibombrocket.obj"));
    	textureLocation = new ResourceLocation("rcmod:models/visibombrocket.png");
    }
    public void doRenderVisibombAmmo(EntityVisibombAmmo par1EntityVisibombAmmo, double x, double y, double z, float par8, float par9)
    {
		TextureManager engine = Minecraft.getMinecraft().getTextureManager();
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x + 0.3F, (float)y, (float)z);
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        GL11.glRotatef(0.0F, 0.0F, 360.0F, 0.0F);
        GL11.glRotatef(par1EntityVisibombAmmo.rotationYaw, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(par1EntityVisibombAmmo.rotationPitch, -1.0F, 0.0F, 0.0F);
        engine.bindTexture(textureLocation);
        this.model1.renderAll();
        GL11.glPopMatrix();
    }
    protected ResourceLocation getVisibombAmmoTextures(EntityVisibombAmmo par1EntityVisibombAmmo)
    {
        return TextureMap.locationItemsTexture;
    }

    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getVisibombAmmoTextures((EntityVisibombAmmo)par1Entity);
    }

    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.doRenderVisibombAmmo((EntityVisibombAmmo)par1Entity, par2, par4, par6, par8, par9);
    }
}
