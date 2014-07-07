package com.gugu42.rcmod.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.SimpleResource;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;

import com.gugu42.rcmod.RcMod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RcSimpleResourceManager implements IResourceManager
{

    private Set<String> domains;
    private ArrayList<ResourceLocation> resources;
    private HashMap<String, IResource> location2resource;

    public RcSimpleResourceManager()
    {
        resources = new ArrayList<ResourceLocation>();
        location2resource = new HashMap<String, IResource>();
        domains = new HashSet<String>();
        domains.add(RcMod.MODID);
        domains.add("minecraft");
    }
    
    @Override
    public Set getResourceDomains()
    {
        return domains;
    }

    @Override
    public IResource getResource(ResourceLocation var1) throws IOException
    {
        IResource result = null;
        if(!isResourceLoaded(var1))
        {
            load(var1);
        }
        result = get(var1.getResourcePath());
        if(result == null)
        {
            throw new FileNotFoundException("File "+var1.getResourcePath()+" not found");
        }
        return result;
    }

    private IResource get(String resourcePath)
    {
        for(String domain : domains)
        {
            if(location2resource.containsKey(domain+"/"+resourcePath))
                return location2resource.get(domain+"/"+resourcePath);
        }
        return null;
    }

    private void load(ResourceLocation var1)
    {
        for(String domain : domains)
        {
            InputStream in = getInputStream(domain, var1);
            if(in == null)continue;
            SimpleResource resource = new SimpleResource(var1, in, null, new IMetadataSerializer());
            location2resource.put(domain+"/"+var1.getResourcePath(), resource);
        }
    }

    private InputStream getInputStream(String domain, ResourceLocation var1)
    {
        return RcSimpleResourceManager.class.getResourceAsStream("/assets/"+domain+"/"+var1.getResourcePath());
    }

    private boolean isResourceLoaded(ResourceLocation resource)
    {
        for(ResourceLocation r : resources)
        {
            if(r.getResourcePath().equals(resource))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public List getAllResources(ResourceLocation var1) throws IOException
    {
        ArrayList<IResource> list = new ArrayList<IResource>();
        list.add(getResource(var1));
        return list;
    }

}
