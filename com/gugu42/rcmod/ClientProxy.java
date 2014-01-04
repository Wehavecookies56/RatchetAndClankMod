package com.gugu42.rcmod;


import net.minecraftforge.client.MinecraftForgeClient;

import com.gugu42.rcmod.TESRInventoryRenderer.TESRIndex;
import com.gugu42.rcmod.weapons.ammo.EntityBlasterAmmo;
import com.gugu42.rcmod.weapons.ammo.RenderBlasterAmmo;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	public static int renderInventoryTESRId;
	
	@Override
	public void registerRenderInformation() {
		RenderingRegistry.registerEntityRenderingHandler(EntityTNTCrate.class,
				new RenderTNTCrate());
		RenderingRegistry.registerEntityRenderingHandler(EntityBlasterAmmo.class,
				new RenderBlasterAmmo(0.5f));
		MinecraftForgeClient.registerItemRenderer(RcMod.blaster.itemID, new BlasterRender());
		
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
