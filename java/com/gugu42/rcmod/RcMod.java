package com.gugu42.rcmod;

import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.stats.Achievement;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;

import org.apache.logging.log4j.Logger;

import com.gugu42.rcmod.blocks.BlockCrate;
import com.gugu42.rcmod.blocks.BlockShip;
import com.gugu42.rcmod.blocks.BlockShipFiller;
import com.gugu42.rcmod.blocks.BlockTNTCrate;
import com.gugu42.rcmod.blocks.BlockVendor;
import com.gugu42.rcmod.blocks.BlockVersaTargetGreen;
import com.gugu42.rcmod.entity.RcEntities;
import com.gugu42.rcmod.gui.GuiBolt;
import com.gugu42.rcmod.gui.GuiSuckCannon;
import com.gugu42.rcmod.handler.RcAchievementEventHandler;
import com.gugu42.rcmod.handler.RcEventHandler;
import com.gugu42.rcmod.handler.RcTickHandler;
import com.gugu42.rcmod.items.ItemClankBackpack;
import com.gugu42.rcmod.items.ItemRatchetEars;
import com.gugu42.rcmod.items.ItemShip;
import com.gugu42.rcmod.items.ItemThrusterPack;
import com.gugu42.rcmod.items.RcItems;
import com.gugu42.rcmod.network.GuiHandler;
import com.gugu42.rcmod.shipsys.ShipWaypointCommand;
import com.gugu42.rcmod.shipsys.ShipWaypointRemoveCommand;
import com.gugu42.rcmod.tileentity.TileEntityShip;
import com.gugu42.rcmod.tileentity.TileEntityShipFiller;
import com.gugu42.rcmod.tileentity.TileEntityVendor;
import com.gugu42.rcmod.tileentity.TileEntityVersaTargetG;
import com.gugu42.rcmod.utils.ffmtutils.FFMTPacketHandler;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = RcMod.MODID, version = "0.5.0", name = "RcMod")
public class RcMod {
	@SidedProxy(clientSide = "com.gugu42.rcmod.ClientProxy", serverSide = "com.gugu42.rcmod.CommonProxy")
	public static CommonProxy proxy;

	@Instance("rcmod")
	public static RcMod instance;
	public static Logger rcLogger;
	
	//Creative tabs
	public static RcCreativeTab rcTab;
	public static RcCreativeTab rcWeapTab;
	public static RcCreativeTab rcGadgTab;
	
	//Blocks
	public static Block tntCrate;
	public static Block crate;
	public static Block vendor;
	public static Block ship;
	public static Block shipFiller;
	public static Block versaTargetGreen;
	
	public static SoundType crateStepSound;

	public static Item clankBackpack;
	public static Item ratchetEars;
	public static Item thrusterPack;
	public static Item shipItem;
	
	public ArmorMaterial EnumArmorMaterialClank = EnumHelper
			.addArmorMaterial("Clank", 0, new int[] { 0, 0, 0, 0 }, 0);

	public RcTickHandler rcTickHandler;
	
	/*
	 * Packet Handler - Not that hard but yeah
	 */
	public static final FFMTPacketHandler rcModPacketHandler = new FFMTPacketHandler("com.gugu42.rcmod.network.packets");

    public static final String MODID = "rcmod";

	public static AchievementPage rcAchievementPage;
	
	public static Achievement achievement_VendorCraft, achievement_HelipackCraft;
	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event) {
		rcTab = new RcCreativeTab("rcTab");
		rcWeapTab = new RcCreativeTab("rcWeapTab");
		rcGadgTab = new RcCreativeTab("rcGadgTab");
		Configuration config = new Configuration(
				event.getSuggestedConfigurationFile());

		config.load();		
		
		config.save();
	}

	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
	    
		/* -----Packet channels-----*/
		rcModPacketHandler.initialise("RCMD|bolt");
		rcModPacketHandler.initialise("RCMD|vend");
		rcModPacketHandler.initialise("RCMD|refill");

		
		/* -----Entity----- */

		RcEntities.initModEntities();
		RcEntities.initRc1Entities();

		/* -----Others before blocks ( stepsound )----- */
		
		crateStepSound = new RcCustomStepSound("CrateWoodBreak", 0.1f, 1.0f, Block.soundTypeWood, Block.soundTypeWood);
		
		/* -----Blocks----- */
		tntCrate = new BlockTNTCrate(Material.tnt).setBlockName("tntCrate")
				.setBlockTextureName(MODID+":tntcrate");
		GameRegistry.registerBlock(tntCrate, "tntCrate");
		crate = new BlockCrate(Material.wood).setBlockName("crate")
				.setBlockTextureName(MODID+":crate").setHardness(0.0f).setStepSound(crateStepSound);
		GameRegistry.registerBlock(crate, "crate");

		vendor = new BlockVendor(Material.iron)
				.setBlockName("vendor").setBlockTextureName(MODID+":vendor")
				.setHardness(10.0f);
		GameRegistry.registerBlock(vendor, "vendor");
		
		ship = new BlockShip(Material.iron).setBlockName("ship").setBlockTextureName(MODID+":ship").setHardness(5.0f);
		GameRegistry.registerBlock(ship, "ship");
		
		shipFiller = new BlockShipFiller(Material.iron).setBlockName("shipFiller");
		GameRegistry.registerBlock(shipFiller, "shipFiller");
		
		versaTargetGreen = new BlockVersaTargetGreen(Material.iron).setBlockName("versaTargetGreen");
		GameRegistry.registerBlock(versaTargetGreen, "versaTargetGreen");

		GameRegistry.registerTileEntity(TileEntityVendor.class, "vendor");
		GameRegistry.registerTileEntity(TileEntityShip.class, "ship");
		GameRegistry.registerTileEntity(TileEntityShipFiller.class, "shipFiller");
		GameRegistry.registerTileEntity(TileEntityVersaTargetG.class, "versaTargetG");
		
		/* -----Items----- */

		RcItems.initModItems();
		RcItems.initRc1Items();

		/* -----Other Items----- */
		clankBackpack = new ItemClankBackpack(EnumArmorMaterialClank, 1,
				1).setUnlocalizedName("clankHeli").setTextureName(
				"rcmod:clankheli");
		GameRegistry.registerItem(clankBackpack, "clankHeli");
		ratchetEars = new ItemRatchetEars(EnumArmorMaterialClank, 1, 0).setUnlocalizedName("ratchetEars").setTextureName(MODID+":ratchetEars");
		GameRegistry.registerItem(ratchetEars, "ratchetEars");
		thrusterPack = new ItemThrusterPack(EnumArmorMaterialClank, 1, 1).setUnlocalizedName("thrusterpack").setTextureName(MODID+":thrusterpack");
		GameRegistry.registerItem(thrusterPack, "thrusterpack");
		
		shipItem = new ItemShip().setMaxStackSize(1).setTextureName(MODID+":shipItem").setCreativeTab(rcTab);
		GameRegistry.registerItem(shipItem, "shipItem");
		
		/* -----Other----- */

		achievement_VendorCraft = new Achievement("achievement.vendor", "vendor", 0, -1, this.vendor, (Achievement)null).registerStat().setSpecial();
		achievement_HelipackCraft = new Achievement("achievement.helipack", "helipack", 0, 1, this.clankBackpack, achievement_VendorCraft).registerStat();
		
		rcAchievementPage = new AchievementPage("Ratchet & Clank Mod", achievement_VendorCraft, achievement_HelipackCraft);
		AchievementPage.registerAchievementPage(rcAchievementPage);
		
		if (event.getSide() == Side.CLIENT)
			setCreativeTabsIcon();

		this.rcTickHandler = new RcTickHandler();
		proxy.registerRenderInformation();
		proxy.registerTileEntityRender();
		RcRecipes.addRecipes();
		
		FMLCommonHandler.instance().bus().register(new RcTickHandler());

	}

	@SideOnly(Side.CLIENT)
	public void setCreativeTabsIcon() {
		rcTab.setTabIconItem(RcItems.bolt);
		rcWeapTab.setTabIconItem(RcItems.blaster);
		rcGadgTab.setTabIconItem(RcItems.swingShot);
	}

	@EventHandler
	public void PostInit(FMLPostInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		MinecraftForge.EVENT_BUS.register(new RcEventHandler());
		FMLCommonHandler.instance().bus().register(new RcAchievementEventHandler());

		if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
			MinecraftForge.EVENT_BUS.register(new GuiBolt(Minecraft
					.getMinecraft()));
			
			GuiSuckCannon suckCannonGui = new GuiSuckCannon(Minecraft
					.getMinecraft());
			MinecraftForge.EVENT_BUS.register(suckCannonGui);
			FMLCommonHandler.instance().bus().register(suckCannonGui);
		}
	} 

	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		event.registerServerCommand(new BoltCommand());
		event.registerServerCommand(new ShipWaypointCommand());
		event.registerServerCommand(new ShipWaypointRemoveCommand());
	}
}
