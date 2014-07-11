package com.gugu42.rcmod;

import net.minecraft.client.resources.IResourceManager;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

import com.gugu42.rcmod.entity.EntityTNTCrate;
import com.gugu42.rcmod.entity.projectiles.EntityBlasterAmmo;
import com.gugu42.rcmod.entity.projectiles.EntityBombGloveAmmo;
import com.gugu42.rcmod.entity.projectiles.EntityDecoyGloveAmmo;
import com.gugu42.rcmod.entity.projectiles.EntityMineGloveAmmo;
import com.gugu42.rcmod.entity.projectiles.EntityPyrocitorAmmo;
import com.gugu42.rcmod.entity.projectiles.EntityRYNOAmmo;
import com.gugu42.rcmod.entity.projectiles.EntitySuckCannonProj;
import com.gugu42.rcmod.entity.projectiles.EntitySwingShotHook;
import com.gugu42.rcmod.entity.projectiles.EntityVisibombAmmo;
import com.gugu42.rcmod.entity.projectiles.EntityWrenchThrown;
import com.gugu42.rcmod.handler.RcSoundHandler;
import com.gugu42.rcmod.items.RcItems;
import com.gugu42.rcmod.render.BlasterRender;
import com.gugu42.rcmod.render.BombGloveRender;
import com.gugu42.rcmod.render.DecoyGloveRender;
import com.gugu42.rcmod.render.DevastatorRender;
import com.gugu42.rcmod.render.DroneDeviceRender;
import com.gugu42.rcmod.render.GadgetronPDARender;
import com.gugu42.rcmod.render.GloveOfDoomRender;
import com.gugu42.rcmod.render.MineGloveRender;
import com.gugu42.rcmod.render.MorphORayRender;
import com.gugu42.rcmod.render.OmniWrench3000Render;
import com.gugu42.rcmod.render.PyrocitorRender;
import com.gugu42.rcmod.render.RYNORender;
import com.gugu42.rcmod.render.RenderBlasterAmmo;
import com.gugu42.rcmod.render.RenderBombGloveAmmo;
import com.gugu42.rcmod.render.RenderDecoyGloveAmmo;
import com.gugu42.rcmod.render.RenderMineGloveAmmo;
import com.gugu42.rcmod.render.RenderPyrocitorAmmo;
import com.gugu42.rcmod.render.RenderRYNOAmmo;
import com.gugu42.rcmod.render.RenderSuckCannonProj;
import com.gugu42.rcmod.render.RenderSwingShotHook;
import com.gugu42.rcmod.render.RenderTNTCrate;
import com.gugu42.rcmod.render.RenderThrownWrench;
import com.gugu42.rcmod.render.RenderVisibombAmmo;
import com.gugu42.rcmod.render.SuckCannonRender;
import com.gugu42.rcmod.render.SwingShotRender;
import com.gugu42.rcmod.render.TESRInventoryRenderer;
import com.gugu42.rcmod.render.TESRInventoryRenderer.TESRIndex;
import com.gugu42.rcmod.render.TaunterRender;
import com.gugu42.rcmod.render.TeslaClawRender;
import com.gugu42.rcmod.render.TileEntityShipSpecialRenderer;
import com.gugu42.rcmod.render.TileEntityVendorSpecialRenderer;
import com.gugu42.rcmod.render.TileEntityVersaTargetGSpecialRenderer;
import com.gugu42.rcmod.render.VisibombRender;
import com.gugu42.rcmod.render.WalloperRender;
import com.gugu42.rcmod.tileentity.TileEntityShip;
import com.gugu42.rcmod.tileentity.TileEntityVendor;
import com.gugu42.rcmod.tileentity.TileEntityVersaTargetG;
import com.gugu42.rcmod.utils.RcSimpleResourceManager;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	// THIS NEEDS TO BE CLIENT SIDE ONLY !
	public static IResourceManager rcResourceManager = new RcSimpleResourceManager();
	public static int renderInventoryTESRId;

	@Override
	public void registerRenderInformation() {
		RenderingRegistry.registerEntityRenderingHandler(EntityTNTCrate.class,
				new RenderTNTCrate());
		RenderingRegistry.registerEntityRenderingHandler(
				EntityBlasterAmmo.class, new RenderBlasterAmmo(0.1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityRYNOAmmo.class,
				new RenderRYNOAmmo(0.1f));
		RenderingRegistry.registerEntityRenderingHandler(
				EntityBombGloveAmmo.class, new RenderBombGloveAmmo(0.5f));
		RenderingRegistry.registerEntityRenderingHandler(
				EntityPyrocitorAmmo.class, new RenderPyrocitorAmmo(0.5f));
		RenderingRegistry.registerEntityRenderingHandler(
				EntityVisibombAmmo.class, new RenderVisibombAmmo(0.1F));
		RenderingRegistry.registerEntityRenderingHandler(
				EntityWrenchThrown.class, new RenderThrownWrench(0.1F));
		RenderingRegistry.registerEntityRenderingHandler(
				EntityMineGloveAmmo.class, new RenderMineGloveAmmo(0.5f));
		RenderingRegistry.registerEntityRenderingHandler(
				EntityDecoyGloveAmmo.class, new RenderDecoyGloveAmmo(0.5f));

		RenderingRegistry.registerEntityRenderingHandler(
				EntitySwingShotHook.class, new RenderSwingShotHook(0.5f));

		RenderingRegistry.registerEntityRenderingHandler(
				EntitySuckCannonProj.class, new RenderSuckCannonProj());

		MinecraftForgeClient.registerItemRenderer(RcItems.blaster,
				new BlasterRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.bombGlove,
				new BombGloveRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.ryno,
				new RYNORender());
		MinecraftForgeClient.registerItemRenderer(RcItems.pyrocitor,
				new PyrocitorRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.walloper,
				new WalloperRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.omniwrench3000,
				new OmniWrench3000Render());
		MinecraftForgeClient.registerItemRenderer(RcItems.visibombGun,
				new VisibombRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.decoyGlove,
				new DecoyGloveRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.devastator,
				new DevastatorRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.droneDevice,
				new DroneDeviceRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.gloveOfDoom,
				new GloveOfDoomRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.mineGlove,
				new MineGloveRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.morphORay,
				new MorphORayRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.suckCannon,
				new SuckCannonRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.taunter,
				new TaunterRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.teslaClaw,
				new TeslaClawRender());

		/* GADGETS */

		MinecraftForgeClient.registerItemRenderer(RcItems.swingShot,
				new SwingShotRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.gadgetronHelper,
				new GadgetronPDARender());

		renderInventoryTESRId = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new TESRInventoryRenderer());
		TESRInventoryRenderer.blockByTESR.put(new TESRIndex(RcMod.vendor, 0),
				new TileEntityVendorSpecialRenderer());
		TESRInventoryRenderer.blockByTESR.put(new TESRIndex(RcMod.ship, 0),
				new TileEntityShipSpecialRenderer());
		TESRInventoryRenderer.blockByTESR.put(new TESRIndex(
				RcMod.versaTargetGreen, 0),
				new TileEntityVersaTargetGSpecialRenderer());
		MinecraftForge.EVENT_BUS.register(new RcSoundHandler());
	}

	@Override
	public void registerTileEntityRender() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVendor.class,
				new TileEntityVendorSpecialRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityShip.class,
				new TileEntityShipSpecialRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(
				TileEntityVersaTargetG.class,
				new TileEntityVersaTargetGSpecialRenderer());
	}

}