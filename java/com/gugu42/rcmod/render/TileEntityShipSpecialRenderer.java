package com.gugu42.rcmod.render;

import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_FRONT_AND_BACK;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import com.gugu42.rcmod.tileentity.TileEntityShip;
import com.gugu42.rcmod.utils.glutils.TessellatorModel;
import com.gugu42.rcmod.utils.glutils.TessellatorModelEvent;
import com.gugu42.rcmod.utils.glutils.TessellatorModelEvent.RenderGroupEvent;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class TileEntityShipSpecialRenderer extends TileEntitySpecialRenderer
		implements IInventoryRenderer {

	private TessellatorModel model;
	
	private float doorRot = 0.0f;

	/**
	 * Model info :
	 * Object01 = Main body
	 * Object02 = The little panel on the nose of the ship, used in the first game shortcut where ratchet repairs the ship
	 * Object03 = The "door" ?
	 */
	

	public TileEntityShipSpecialRenderer() {
		model = new TessellatorModel("/assets/rcmod/models/RatchetShip.obj");
		model.regenerateNormals();
		model.setID("ship");
		TessellatorModel.MODEL_RENDERING_BUS.register(this);

	}
	
	
	@SubscribeEvent
    public void onPreRenderGroup(RenderGroupEvent.Pre event)
    {
        if(event.model.getID() != null)
        {
            if(event.model.getID().equals("ship"))
            {
                if(event.group.equals("Object03"))
                {
                    GL11.glPushMatrix();
                    double x = 0;
                    double y = 40;
                    double z = -11.5f;
                    GL11.glTranslated(x, y, z);
                    GL11.glRotatef(doorRot, 1.0f, 0.0f, 0.0f);
                    GL11.glTranslated(-x, -y, -z);
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onPostRenderGroup(RenderGroupEvent.Post event)
    {
        if(event.model.getID() != null)
        {
            if(event.model.getID().equals("ship"))
            {
                if(event.group.equals("Object03"))
                {
                    GL11.glPopMatrix();
                }
            }

        }
    }
	
	
	
	

	@Override
	public void renderInventory(double x, double y, double z) {
		GL11.glPushMatrix();
		GL11.glTranslated(x - 0.15f, y - 0.5d, z + 0.15f);
		//		this.bindTexture(textureLocation);
		GL11.glScalef(0.021f, 0.021f, 0.021f);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		model.render();
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z,
			float tick) {
		if (te instanceof TileEntityShip) {
			
			TileEntityShip tileEntity = (TileEntityShip) te;

			this.doorRot = tileEntity.renderDoorRot;
			
			GL11.glPushMatrix();
			GL11.glTranslated(x + 0.5d, y, z + 0.5d);
			
			//		this.bindTexture(textureLocation);
			GL11.glScalef(0.076f, 0.076f, 0.076f);
			GL11.glTranslatef(tileEntity.renderX, tileEntity.renderY, tileEntity.renderZ);
			if (te.blockMetadata == 0) {
				GL11.glRotatef(0, 0.0f, 1.0f, 0.0f);
			} else if (te.blockMetadata == 1) {
				GL11.glRotatef(-90, 0.0f, 1.0f, 0.0f);
			} else if (te.blockMetadata == 2) {
				GL11.glRotatef(180, 0.0f, 1.0f, 0.0f);
			} else if (te.blockMetadata == 3) {
				GL11.glRotatef(90, 0.0f, 1.0f, 0.0f);
			}
			GL11.glRotatef(tileEntity.pitch, 1.0f, 0.0f, 0.0f);
			GL11.glShadeModel(GL11.GL_SMOOTH);
			
			GL11.glEnable(GL11.GL_BLEND);
//			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA); // Uncomment this line to make the glass more blue-ish
			model.render();
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glPopMatrix();
		}
	}

}
