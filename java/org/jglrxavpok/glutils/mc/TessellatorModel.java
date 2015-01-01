package org.jglrxavpok.glutils.mc;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventBus;
import net.minecraft.client.renderer.Tessellator;

import org.jglrxavpok.glutils.IndexedModel;
import org.jglrxavpok.glutils.Mesh;
import org.jglrxavpok.glutils.Model;
import org.jglrxavpok.glutils.OBJLoader;
import org.jglrxavpok.glutils.ObjEvent;
import org.jglrxavpok.glutils.ObjEvent.EventType;
import org.jglrxavpok.glutils.ObjModel;
import org.jglrxavpok.glutils.ObjObject;
import org.jglrxavpok.glutils.TessellatorModelEvent;
import org.jglrxavpok.glutils.Vertex;
import org.lwjgl.opengl.GL11;

/**
 * @author jglrxavpok
 */
public class TessellatorModel extends ObjModel
{

    public static final EventBus MODEL_RENDERING_BUS = new EventBus();

    public TessellatorModel(String string)
    {
        super(string);
        try
        {
            String content = new String(read(Model.class.getResourceAsStream(string)), "UTF-8");
            HashMap<ObjObject, IndexedModel> map = new OBJLoader().loadModel(content);
            objObjects.clear();
            Set<ObjObject> keys = map.keySet();
            Iterator<ObjObject> it = keys.iterator();
            while(it.hasNext())
            {
                ObjObject object = it.next();
                Mesh mesh = new Mesh();
                object.mesh = mesh;
                objObjects.add(object);
                map.get(object).toMesh(mesh);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    @Override
    public void renderImpl()
    {
        GL11.glColor4f(1, 1, 1, 1);
        for(ObjObject object : objObjects)
        {
            renderGroup(object);
        }
    }

    @Override
    public void renderGroupsImpl(String group)
    {
        for(ObjObject object : objObjects)
        {
            if(object.getName().equals(group))
            {
                renderGroup(object);
            }
        }
    }

    @Override
    public void renderGroupImpl(ObjObject obj)
    {
        Tessellator tess = Tessellator.instance;
        if(obj.mesh == null)
            return;
        int[] indices = obj.mesh.indices;
        Vertex[] vertices = obj.mesh.vertices;
        tess.startDrawing(GL_TRIANGLES);
        for(int i = 0 ;i<indices.length;i+=3)
        {
            int i0 = indices[i];
            int i1 = indices[i+1];
            int i2 = indices[i+2];
            Vertex v0 = vertices[i0];
            Vertex v1 = vertices[i1];
            Vertex v2 = vertices[i2];
            tess.setNormal(v0.getNormal().x, v0.getNormal().y, v0.getNormal().z);
            tess.addVertexWithUV(v0.getPos().x, v0.getPos().y, v0.getPos().z, v0.getTexCoords().x, 1f-v0.getTexCoords().y);
            
            tess.setNormal(v1.getNormal().x, v1.getNormal().y, v1.getNormal().z);
            tess.addVertexWithUV(v1.getPos().x, v1.getPos().y, v1.getPos().z, v1.getTexCoords().x, 1f-v1.getTexCoords().y);
            
            tess.setNormal(v2.getNormal().x, v2.getNormal().y, v2.getNormal().z);
            tess.addVertexWithUV(v2.getPos().x, v2.getPos().y, v2.getPos().z, v2.getTexCoords().x, 1f-v2.getTexCoords().y);
        }
        tess.draw();
    }

    @Override
    public boolean fireEvent(ObjEvent event)
    {
        Event evt = null;
        if(event.type == EventType.PRE_RENDER_GROUP)
        {
            evt = new TessellatorModelEvent.RenderGroupEvent.Pre(((ObjObject)event.data[1]).getName(), this);
        }
        else if(event.type == EventType.POST_RENDER_GROUP)
        {
            evt = new TessellatorModelEvent.RenderGroupEvent.Post(((ObjObject)event.data[1]).getName(), this);
        }
        else if(event.type == EventType.PRE_RENDER_ALL)
        {
            evt = new TessellatorModelEvent.RenderPre(this);
        }
        else if(event.type == EventType.POST_RENDER_ALL)
        {
            evt = new TessellatorModelEvent.RenderPost(this);
        }
        if(evt != null)
            return !MODEL_RENDERING_BUS.post(evt);
        return true;
    }

    @Deprecated
    public void regenerateNormals()
    {
        
    }
}
