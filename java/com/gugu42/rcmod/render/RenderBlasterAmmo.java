package com.gugu42.rcmod.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.gugu42.rcmod.entity.projectiles.EntityBlasterAmmo;

@SideOnly(Side.CLIENT)
public class RenderBlasterAmmo extends Render
{
	 private float scale;
	    private static final String __OBFID = "CL_00000995";

	    public RenderBlasterAmmo(RenderManager p_i46176_1_, float p_i46176_2_)
	    {
	        super(p_i46176_1_);
	        this.scale = p_i46176_2_;
	    }

	    public void doRender(EntityBlasterAmmo entity, double x, double y, double z, float p_76986_8_, float partialTicks)
	    {
	        GlStateManager.pushMatrix();
	        this.bindEntityTexture(entity);
	        GlStateManager.translate((float)x, (float)y, (float)z);
	        GlStateManager.enableRescaleNormal();
	        float f2 = this.scale;
	        GlStateManager.scale(f2 / 1.0F, f2 / 1.0F, f2 / 1.0F);
	        TextureAtlasSprite textureatlassprite = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getParticleIcon(Items.fire_charge);
	        Tessellator tessellator = Tessellator.getInstance();
	        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
	        float f3 = textureatlassprite.getMinU();
	        float f4 = textureatlassprite.getMaxU();
	        float f5 = textureatlassprite.getMinV();
	        float f6 = textureatlassprite.getMaxV();
	        float f7 = 1.0F;
	        float f8 = 0.5F;
	        float f9 = 0.25F;
	        GlStateManager.rotate(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
	        GlStateManager.rotate(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
	        worldrenderer.startDrawingQuads();
	        worldrenderer.setNormal(0.0F, 1.0F, 0.0F);
	        worldrenderer.addVertexWithUV((double)(0.0F - f8), (double)(0.0F - f9), 0.0D, (double)f3, (double)f6);
	        worldrenderer.addVertexWithUV((double)(f7 - f8), (double)(0.0F - f9), 0.0D, (double)f4, (double)f6);
	        worldrenderer.addVertexWithUV((double)(f7 - f8), (double)(1.0F - f9), 0.0D, (double)f4, (double)f5);
	        worldrenderer.addVertexWithUV((double)(0.0F - f8), (double)(1.0F - f9), 0.0D, (double)f3, (double)f5);
	        tessellator.draw();
	        GlStateManager.disableRescaleNormal();
	        GlStateManager.popMatrix();
	        super.doRender(entity, x, y, z, p_76986_8_, partialTicks);
	    }

	    protected ResourceLocation func_180556_a(EntityBlasterAmmo p_180556_1_)
	    {
	        return TextureMap.locationBlocksTexture;
	    }

	    protected ResourceLocation getEntityTexture(Entity entity)
	    {
	        return this.func_180556_a((EntityBlasterAmmo)entity);
	    }

	    public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks)
	    {
	        this.doRender((EntityBlasterAmmo)entity, x, y, z, p_76986_8_, partialTicks);
	    }
}
