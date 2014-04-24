package com.gugu42.rcmod;

import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

import com.gugu42.rcmod.entity.EntityTNTCrate;
import com.gugu42.rcmod.entity.projectiles.EntityBlasterAmmo;
import com.gugu42.rcmod.entity.projectiles.EntityBombGloveAmmo;
import com.gugu42.rcmod.entity.projectiles.EntityPyrocitorAmmo;
import com.gugu42.rcmod.entity.projectiles.EntityRYNOAmmo;
import com.gugu42.rcmod.entity.projectiles.EntityVisibombAmmo;
import com.gugu42.rcmod.entity.projectiles.EntityWrenchThrown;
import com.gugu42.rcmod.handler.RcSoundHandler;
import com.gugu42.rcmod.items.RcItems;
import com.gugu42.rcmod.render.*;
import com.gugu42.rcmod.render.TESRInventoryRenderer.TESRIndex;
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
		RenderingRegistry.registerEntityRenderingHandler(EntityWrenchThrown.class, 
				new RenderThrownWrench(0.1F));

		MinecraftForgeClient.registerItemRenderer(RcItems.blaster.itemID, new BlasterRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.bombGlove.itemID, new BombGloveRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.ryno.itemID, new RYNORender());
		MinecraftForgeClient.registerItemRenderer(RcItems.pyrocitor.itemID, new PyrocitorRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.walloper.itemID, new WalloperRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.omniwrench3000.itemID, new OmniWrench3000Render());
		MinecraftForgeClient.registerItemRenderer(RcItems.visibombGun.itemID, new VisibombRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.decoyGlove.itemID, new DecoyGloveRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.devastator.itemID, new DevastatorRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.droneDevice.itemID, new DroneDeviceRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.gloveOfDoom.itemID, new GloveOfDoomRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.mineGlove.itemID, new MineGloveRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.morphORay.itemID, new MorphORayRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.suckCannon.itemID, new SuckCannonRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.taunter.itemID, new TaunterRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.teslaClaw.itemID, new TeslaClawRender());
		
		renderInventoryTESRId = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new TESRInventoryRenderer());
		TESRInventoryRenderer.blockByTESR.put(new TESRIndex(RcMod.vendor, 0), new TileEntityVendorSpecialRenderer());
		MinecraftForge.EVENT_BUS.register(new RcSoundHandler());
	}

	@Override
	public void registerTileEntityRender()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVendor.class, new TileEntityVendorSpecialRenderer());
	}
	
}
