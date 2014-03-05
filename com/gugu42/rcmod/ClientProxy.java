package com.gugu42.rcmod;

import net.minecraftforge.client.MinecraftForgeClient;

import com.gugu42.rcmod.entity.EntityBlasterAmmo;
import com.gugu42.rcmod.entity.EntityBombGloveAmmo;
import com.gugu42.rcmod.entity.EntityPyrocitorAmmo;
import com.gugu42.rcmod.entity.EntityRYNOAmmo;
import com.gugu42.rcmod.entity.EntityTNTCrate;
import com.gugu42.rcmod.entity.EntityVisibombAmmo;
import com.gugu42.rcmod.items.RcItems;
import com.gugu42.rcmod.render.BlasterRender;
import com.gugu42.rcmod.render.BombGloveRender;
import com.gugu42.rcmod.render.OmniWrench3000Render;
import com.gugu42.rcmod.render.PyrocitorRender;
import com.gugu42.rcmod.render.RYNORender;
import com.gugu42.rcmod.render.RenderBlasterAmmo;
import com.gugu42.rcmod.render.RenderBombGloveAmmo;
import com.gugu42.rcmod.render.RenderPyrocitorAmmo;
import com.gugu42.rcmod.render.RenderRYNOAmmo;
import com.gugu42.rcmod.render.RenderTNTCrate;
import com.gugu42.rcmod.render.TESRInventoryRenderer;
import com.gugu42.rcmod.render.RenderVisibombAmmo;
import com.gugu42.rcmod.render.VisibombRender;
import com.gugu42.rcmod.render.TESRInventoryRenderer.TESRIndex;
import com.gugu42.rcmod.render.TileEntityVendorSpecialRenderer;
import com.gugu42.rcmod.render.WalloperRender;
import com.gugu42.rcmod.tileentity.TileEntityVendor;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	public static int renderInventoryTESRId;
	
	@Override
	public void registerRenderInformation() {
		RenderingRegistry.registerEntityRenderingHandler(EntityTNTCrate.class,
				new RenderTNTCrate());
		RenderingRegistry.registerEntityRenderingHandler(EntityBlasterAmmo.class,
				new RenderBlasterAmmo(0.1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityRYNOAmmo.class,
				new RenderRYNOAmmo(0.1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityBombGloveAmmo.class,
				new RenderBombGloveAmmo(0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityPyrocitorAmmo.class,
				new RenderPyrocitorAmmo(0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityVisibombAmmo.class, 
				new RenderVisibombAmmo(0.1F));

		MinecraftForgeClient.registerItemRenderer(RcItems.blaster.itemID, new BlasterRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.bombGlove.itemID, new BombGloveRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.ryno.itemID, new RYNORender());
		MinecraftForgeClient.registerItemRenderer(RcItems.pyrocitor.itemID, new PyrocitorRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.walloper.itemID, new WalloperRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.omniwrench3000.itemID, new OmniWrench3000Render());
		MinecraftForgeClient.registerItemRenderer(RcItems.visibombGun.itemID, new VisibombRender());
		
		renderInventoryTESRId = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new TESRInventoryRenderer());
		TESRInventoryRenderer.blockByTESR.put(new TESRIndex(RcMod.vendor, 0), new TileEntityVendorSpecialRenderer());
	}

	@Override
	public void registerTileEntityRender()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVendor.class, new TileEntityVendorSpecialRenderer());
	}
	
}
