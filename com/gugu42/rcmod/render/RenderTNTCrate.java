package com.gugu42.rcmod.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.entity.EntityTNTCrate;

@SideOnly(Side.CLIENT)
public class RenderTNTCrate extends Render
{
    private RenderBlocks blockRenderer = new RenderBlocks();

    public RenderTNTCrate()
    {
        this.shadowSize = 0.5F;
    }

    public void renderPrimedTNT(EntityTNTCrate par1EntityTNTCrate, double par2, double par4, double par6, float par8, float par9)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par2, (float)par4, (float)par6);
        float f2;

        if ((float)par1EntityTNTCrate.fuse - par9 + 1.0F < 10.0F)
        {
            f2 = 1.0F - ((float)par1EntityTNTCrate.fuse - par9 + 1.0F) / 10.0F;

            if (f2 < 0.0F)
            {
                f2 = 0.0F;
            }

            if (f2 > 1.0F)
            {
                f2 = 1.0F;
            }

            f2 *= f2;
            f2 *= f2;
            float f3 = 1.0F + f2 * 0.3F;
            GL11.glScalef(f3, f3, f3);
        }

        f2 = (1.0F - ((float)par1EntityTNTCrate.fuse - par9 + 1.0F) / 100.0F) * 0.8F;
        this.bindEntityTexture(par1EntityTNTCrate);
        this.blockRenderer.renderBlockAsItem(RcMod.tntCrate, 0, par1EntityTNTCrate.getBrightness(par9));

        if (par1EntityTNTCrate.fuse / 5 % 2 == 0)
        {
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_DST_ALPHA);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, f2);
            this.blockRenderer.renderBlockAsItem(RcMod.tntCrate, 0, 1.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
        }

        GL11.glPopMatrix();
    }

    protected ResourceLocation func_110808_a(EntityTNTCrate par1EntityTNTCrate)
    {
        return TextureMap.locationBlocksTexture;
    }

    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.func_110808_a((EntityTNTCrate)par1Entity);
    }


    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderPrimedTNT((EntityTNTCrate)par1Entity, par2, par4, par6, par8, par9);
    }
}
