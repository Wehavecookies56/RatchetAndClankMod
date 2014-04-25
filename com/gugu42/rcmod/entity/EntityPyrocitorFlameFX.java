package com.gugu42.rcmod.entity;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityPyrocitorFlameFX extends EntityFX {

	protected EntityPyrocitorFlameFX(World par1World, double par2, double par4,
			double par6) {
		super(par1World, par2, par4, par6);

	}

	public EntityPyrocitorFlameFX(World par1World, double par2, double par4,
			double par6, double par8, double par10, double par12) {
		super(par1World, par2, par4, par6, par8, par10, par12);
		this.setVelocity(0.0d, 0.0d, 0.0d);
		this.particleAge = 0;
		this.particleMaxAge = 2;
	}

	@Override
	public void renderParticle(Tessellator par1Tessellator, float par2,
			float par3, float par4, float par5, float par6, float par7) {
		// func_98187_b() = bindTexture();
		FMLClientHandler.instance().getClient().renderEngine
				.bindTexture(new ResourceLocation(
						"rcmod:textures/particles/pyrocitor.png"));
		;
		float scale = 0.2F * this.particleScale;
		float xPos = (float) (this.prevPosX + (this.posX - this.prevPosX)
				* (double) par2 - interpPosX);
		float yPos = (float) (this.prevPosY + (this.posY - this.prevPosY)
				* (double) par2 - interpPosY);
		float zPos = (float) (this.prevPosZ + (this.posZ - this.prevPosZ)
				* (double) par2 - interpPosZ);
		float colorIntensity = 1.0F;
		par1Tessellator.setColorOpaque_F(this.particleRed * colorIntensity,
				this.particleGreen * colorIntensity, this.particleBlue
						* colorIntensity);// , 1.0F);

		par1Tessellator.addVertexWithUV((double) (xPos - par3 * scale - par6
				* scale), (double) (yPos - par4 * scale), (double) (zPos - par5
				* scale - par7 * scale), 0D, 1D);
		par1Tessellator.addVertexWithUV((double) (xPos - par3 * scale + par6
				* scale), (double) (yPos + par4 * scale), (double) (zPos - par5
				* scale + par7 * scale), 1D, 1D);
		par1Tessellator.addVertexWithUV((double) (xPos + par3 * scale + par6
				* scale), (double) (yPos + par4 * scale), (double) (zPos + par5
				* scale + par7 * scale), 1D, 0D);
		par1Tessellator.addVertexWithUV((double) (xPos + par3 * scale - par6
				* scale), (double) (yPos - par4 * scale), (double) (zPos + par5
				* scale - par7 * scale), 0D, 0D);
	}

}
