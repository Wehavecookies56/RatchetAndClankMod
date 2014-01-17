package com.gugu42.rcmod.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class TileEntityVendorSpecialRenderer extends TileEntitySpecialRenderer implements IInventoryRenderer{

	//Partie bleu
	private IModelCustom model;
    public static final ResourceLocation textureLocation = new ResourceLocation("rcmod:models/Vendor0.png");
    
    //Antennes sur le cote
    private IModelCustom model2;
    public static final ResourceLocation textureLocation2 = new ResourceLocation("rcmod:models/Vendor1.png");
    
    //Partie metallique interieure
    private IModelCustom model3;
    public static final ResourceLocation textureLocation3 = new ResourceLocation("rcmod:models/Vendor2.png");
    
    //Texte Gadgetron
    private IModelCustom model4;
    public static final ResourceLocation textureLocation4 = new ResourceLocation("rcmod:models/Vendor3.png");
    
    //G de Gadgetron
    private IModelCustom model5;
    public static final ResourceLocation textureLocation5 = new ResourceLocation("rcmod:models/Vendor4.png");
	
    public TileEntityVendorSpecialRenderer(){
    	model = AdvancedModelLoader.loadModel("/assets/rcmod/models/Vendor0.obj");
    	model2 = AdvancedModelLoader.loadModel("/assets/rcmod/models/Vendor1.obj");
    	model3 = AdvancedModelLoader.loadModel("/assets/rcmod/models/Vendor2.obj");
    	model4 = AdvancedModelLoader.loadModel("/assets/rcmod/models/Vendor3.obj");
    	model5 = AdvancedModelLoader.loadModel("/assets/rcmod/models/Vendor4.obj");
    	this.setTileEntityRenderer(TileEntityRenderer.instance);
    }
    
	@Override
	public void renderInventory(double x, double y, double z)
	{
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5F, y + 0.0F, z + 0.5F);
        this.bindTexture(textureLocation3);
        GL11.glScalef(0.046f, 0.046f, 0.046f);
        model3.renderAll();
        GL11.glPopMatrix();
		
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5F, y + 0.0F, z + 0.5F);
        this.bindTexture(textureLocation2);
        GL11.glScalef(0.046f, 0.046f, 0.046f);
        model2.renderAll();
        GL11.glPopMatrix();
		
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5F, y + 0.0F, z + 0.5F);
        this.bindTexture(textureLocation);
        GL11.glScalef(0.046f, 0.046f, 0.046f);
        model.renderAll();
        GL11.glPopMatrix();
        
/*        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5F, y -0.4F, z + 0.5F);
        this.bindTexture(textureLocation5);
        GL11.glScalef(0.046f, 0.046f, 0.046f);
        model5.renderAll();
        GL11.glPopMatrix();
        
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5F, y -0.4F, z + 0.5F);
        this.bindTexture(textureLocation4);
        GL11.glScalef(0.046f, 0.046f, 0.046f);
        model4.renderAll();
        GL11.glPopMatrix();*/   //lol
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float tick)
	{
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5F, y + 0.0F, z + 0.5F);
        this.bindTexture(textureLocation3);
        GL11.glScalef(0.046f, 0.046f, 0.046f);
        model3.renderAll();
        GL11.glPopMatrix();
		
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5F, y + 0.0F, z + 0.5F);
        this.bindTexture(textureLocation2);
        GL11.glScalef(0.046f, 0.046f, 0.046f);
        model2.renderAll();
        GL11.glPopMatrix();
		
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5F, y + 0.0F, z + 0.5F);
        this.bindTexture(textureLocation);
        GL11.glScalef(0.046f, 0.046f, 0.046f);
        model.renderAll();
        GL11.glPopMatrix();
        
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5F, y -0.4F, z + 0.5F);
        this.bindTexture(textureLocation5);
        float f2 = (float)te.getWorldObj().getTotalWorldTime();
        byte b1 = 1;
        double d3 = (double)f2 * 0.025D * (1.0D - (double)(b1 & 1) * 2.5D);
        GL11.glRotated(-d3 * 5 * 10, 0.0D, 1.0D, 0.0D);
        GL11.glScalef(0.046f, 0.046f, 0.046f);
        model5.renderAll();
        GL11.glPopMatrix();
        
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5F, y -0.4F, z + 0.5F);
        this.bindTexture(textureLocation4);
        GL11.glRotated(d3 * 5 * 5, 0.0D, 1.0D, 0.0D);
        GL11.glScalef(0.046f, 0.046f, 0.046f);
        model4.renderAll();
        GL11.glPopMatrix();
	}
	
}
