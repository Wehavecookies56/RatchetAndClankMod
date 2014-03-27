package com.gugu42.rcmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;

import com.gugu42.rcmod.blocks.BlockCrate;
import com.gugu42.rcmod.blocks.BlockTNTCrate;
import com.gugu42.rcmod.blocks.BlockVendor;
import com.gugu42.rcmod.entity.RcEntities;
import com.gugu42.rcmod.gui.GuiBolt;
import com.gugu42.rcmod.handler.RcEventHandler;
import com.gugu42.rcmod.handler.RcTickHandler;
import com.gugu42.rcmod.items.ItemClankBackpack;
import com.gugu42.rcmod.items.RcItems;
import com.gugu42.rcmod.network.GuiHandler;
import com.gugu42.rcmod.network.RcPacketHandler;
import com.gugu42.rcmod.tileentity.TileEntityVendor;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = "rcmod", version = "0.2.6b", name = "RcMod")
@NetworkMod(clientSideRequired = true, serverSideRequired = true, channels = {
		"RCMD|bolt", "RCMD|vend", "RCMD|refi" }, packetHandler = RcPacketHandler.class)
public class RcMod {
	@SidedProxy(clientSide = "com.gugu42.rcmod.ClientProxy", serverSide = "com.gugu42.rcmod.CommonProxy")
	public static CommonProxy proxy;

	@Instance("rcmod")
	public static RcMod instance;

	public static RcCreativeTab rcTab;
	public static RcCreativeTab rcWeapTab;

	public static int tntCrateID, crateID, vendorID;
	
	//global items ID
	public static int wrench3000ID, vendorCoreID, clankBackpackID, boltID, clankCoreID, clankID, helipackHeliceID;
	
	
	//R&C1 weapons id
	public static int blasterID, bombgloveID, rynoID, pyrocitorID, walloperID, visibombGunID, decoyGloveID, devastatorID, droneDeviceID, gloveOfDoomID, mineGloveID, morphORayID, suckCannonID, taunterID, teslaClawID;
	
	
	public static Block tntCrate;
	public static Block crate;
	public static Block vendor;

	public static Item clankBackpack;
	public EnumArmorMaterial EnumArmorMaterialClank = new EnumHelper()
			.addArmorMaterial("Clank", 0, new int[] { 0, 0, 0, 0 }, 0);

	public RcTickHandler rcTickHandler;

	@EventHandler
	public void PreInit(FMLPreInitializationEvent event) {
		rcTab = new RcCreativeTab("rcTab");
		rcWeapTab = new RcCreativeTab("rcWeapTab");
		Configuration config = new Configuration(
				event.getSuggestedConfigurationFile());

		config.load();

		tntCrateID = config.getBlock("explosive_crate", 201).getInt();
		crateID = config.getBlock("bolt_crate", 202).getInt();
		vendorID = config.getBlock("vendor", 203).getInt();
		
		wrench3000ID = config.getItem("wrench3000", 3000).getInt();
		boltID = config.getItem("bolt", 3001).getInt();
		vendorCoreID = config.getItem("vendorcore", 3008).getInt();
		clankCoreID = config.getItem("clankcore", 3018).getInt();
		clankID = config.getItem("clank", 3019).getInt();
		helipackHeliceID = config.getItem("helipackHelice", 3020).getInt();
		
		blasterID = config.getItem("blaster", 3002).getInt();
		bombgloveID = config.getItem("bombglove", 3003).getInt();
		rynoID = config.getItem("ryno", 3004).getInt();
		pyrocitorID = config.getItem("pyrocitor", 3005).getInt();
		walloperID = config.getItem("walloper", 3006).getInt();
		visibombGunID = config.getItem("visibombGun", 3007).getInt();
		
		decoyGloveID = config.getItem("decoyglove", 3009).getInt();
		devastatorID = config.getItem("devastator", 3010).getInt();
		droneDeviceID = config.getItem("droneDevice", 3011).getInt();
		gloveOfDoomID = config.getItem("gloveOfDoom", 3012).getInt();
		mineGloveID = config.getItem("mineGlove", 3013).getInt();
		morphORayID = config.getItem("morphORay", 3014).getInt();
		suckCannonID = config.getItem("suckCannon", 3015).getInt();
		taunterID = config.getItem("taunter", 3016).getInt();
		teslaClawID = config.getItem("teslaClaw", 3017).getInt();
		
		
		
		config.save();
	}

	@EventHandler
	public void Init(FMLInitializationEvent event) {

		/* -----Entity----- */

		RcEntities.initModEntities();
		RcEntities.initRc1Entities();

		/* -----Blocks----- */
		tntCrate = new BlockTNTCrate(tntCrateID).setUnlocalizedName("tntCrate")
				.setTextureName("rcmod:tntcrate");
		GameRegistry.registerBlock(tntCrate, "tntCrate");
		crate = new BlockCrate(crateID, Material.wood).setUnlocalizedName("crate")
				.setTextureName("rcmod:crate").setHardness(0.0f);
		GameRegistry.registerBlock(crate, "crate");

		vendor = new BlockVendor(vendorID, Material.iron)
				.setUnlocalizedName("vendor").setTextureName("rcmod:vendor")
				.setHardness(10.0f);
		GameRegistry.registerBlock(vendor, "vendor");

		GameRegistry.registerTileEntity(TileEntityVendor.class, "vendor");

		/* -----Items----- */

		RcItems.initModItems();
		RcItems.initRc1Items();

		/* -----Other Items----- */
		clankBackpack = new ItemClankBackpack(3050, EnumArmorMaterialClank, 1,
				1).setUnlocalizedName("clankHeli").setTextureName(
				"rcmod:clankheli");

		/* -----Other----- */
		if (event.getSide() == Side.CLIENT)
			setCreativeTabsIcon();

		this.rcTickHandler = new RcTickHandler();
		proxy.registerRenderInformation();
		proxy.registerTileEntityRender();
		RcRecipes.addRecipes();
		TickRegistry.registerTickHandler(this.rcTickHandler, Side.SERVER);
		TickRegistry.registerTickHandler(this.rcTickHandler, Side.CLIENT);

	}

	@SideOnly(Side.CLIENT)
	public void setCreativeTabsIcon() {
		rcTab.setTabIconItem(RcItems.bolt);
		rcWeapTab.setTabIconItem(RcItems.blaster);
	}

	@EventHandler
	public void PostInit(FMLPostInitializationEvent event) {

		NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
		MinecraftForge.EVENT_BUS.register(new RcEventHandler());

		if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
			MinecraftForge.EVENT_BUS.register(new GuiBolt(Minecraft
					.getMinecraft()));
		}
	}

	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		event.registerServerCommand(new BoltCommand());
	}
}
