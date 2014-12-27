package com.gugu42.rcmod.gui;

import static org.lwjgl.opengl.GL11.glColor4f;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.gugu42.rcmod.handler.ExtendedPropsSuckCannon;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.Type;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class GuiSuckCannon extends Gui
{

    private Minecraft mc;
    private HashMap<Integer, Entity> loadedEntities = new HashMap<Integer, Entity>();
    private HashMap<Integer, Float> rotations = new HashMap<Integer, Float>();

    public GuiSuckCannon(Minecraft mc)
    {
        super();
        this.mc = mc;
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onTick(TickEvent event)
    {
        if(event.type == Type.WORLD && event.phase == Phase.END)
        {
            Collection<Entity> entities = loadedEntities.values();
            Object[] array = entities.toArray();
            for(int i = 0; i < entities.size(); i++)
            {
                if(i >= array.length)
                    break;
                try
                {
                    // ((Entity)array[i]).onUpdate();
                }
                catch(Exception e1)
                {
                    entities.remove(((Entity)array[i]));
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onRenderExperienceBar(RenderGameOverlayEvent event)
    {
        if(event.isCancelable() || event.type != ElementType.EXPERIENCE)
        {
            return;
        }

        glColor4f(1, 1, 1, 1);
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        if(player != null)
        {
            ExtendedPropsSuckCannon props = ExtendedPropsSuckCannon.get(player);

            ArrayList<NBTTagCompound> list = props.getStackAsList();
            for(int i = 0; i <= 8 - list.size(); i++)
            {
                rotations.put(8 - i, 0f);
                loadedEntities.put(8 - i, null);
            }
            for(int index = 0; index < list.size(); index++)
            {
                float v = 0f;
                if(rotations.containsKey(index))
                    v = rotations.get(index);

                if(!loadedEntities.containsKey(index) || loadedEntities.get(index) == null)
                {
                    Entity e = EntityList.createEntityFromNBT(list.get(index), player.worldObj);
                    loadedEntities.put(index, e);
                }
                rotations.put(index, v + 2.5f);

                if(loadedEntities.get(index) != null)
                {
                    Entity e = loadedEntities.get(index);
                    ScaledResolution res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
                    drawEntity(res.getScaledWidth() - 25 - (index % 2 == 0 ? 20 : 0), res.getScaledHeight() - 5 - (index / 2 * 35), 15, rotations.get(index), -10, (EntityLivingBase)e);
                }
            }
        }
        this.mc.getTextureManager().bindTexture(icons);
    }

    @SideOnly(Side.CLIENT)
    /**
     * From GuiInventory
     */
    public static void drawEntity(int x, int y, int scale, float yaw, float pitch, EntityLivingBase entity)
    {
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x, (float)y, 50.0F);
        GL11.glScalef((float)(-scale), (float)scale, (float)scale);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        float f2 = entity.renderYawOffset;
        float f3 = entity.rotationYaw;
        float f4 = entity.rotationPitch;
        float f5 = entity.prevRotationYawHead;
        float f6 = entity.rotationYawHead;
        GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-((float)Math.atan((double)(pitch / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        entity.renderYawOffset = yaw;
        entity.rotationYaw = yaw;
        entity.rotationPitch = -((float)Math.atan((double)(pitch / 40.0F))) * 20.0F;
        entity.rotationYawHead = entity.rotationYaw;
        entity.prevRotationYawHead = entity.rotationYaw;
        GL11.glTranslatef(0.0F, entity.yOffset, 0.0F);
        RenderManager.instance.playerViewY = 180.0F;
        RenderManager.instance.func_147939_a(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, true);
        entity.renderYawOffset = f2;
        entity.rotationYaw = f3;
        entity.rotationPitch = f4;
        entity.prevRotationYawHead = f5;
        entity.rotationYawHead = f6;
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);

        glColor4f(1, 1, 1, 1);
    }

    @SideOnly(Side.CLIENT)
    public static void drawTexturedQuadFit(double x, double y, double width, double height, double zLevel)
    {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x + 0, y + height, zLevel, 0, 1);
        tessellator.addVertexWithUV(x + width, y + height, zLevel, 1, 1);
        tessellator.addVertexWithUV(x + width, y + 0, zLevel, 1, 0);
        tessellator.addVertexWithUV(x + 0, y + 0, zLevel, 0, 0);
        tessellator.draw();
    }
}
