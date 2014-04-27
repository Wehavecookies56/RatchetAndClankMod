package com.gugu42.rcmod;

import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;

import com.gugu42.rcmod.blocks.BlockCrate;
import com.gugu42.rcmod.blocks.BlockTNTCrate;
import com.gugu42.rcmod.blocks.BlockVendor;
import com.gugu42.rcmod.entity.RcEntities;
import com.gugu42.rcmod.gui.GuiBolt;
import com.gugu42.rcmod.handler.RcEventHandler;
import com.gugu42.rcmod.handler.RcTickHandler;
import com.gugu42.rcmod.items.ItemClankBackpack;
import com.gugu42.rcmod.items.ItemRatchetEars;
import com.gugu42.rcmod.items.RcItems;
import com.gugu42.rcmod.network.GuiHandler;
import com.gugu42.rcmod.tileentity.TileEntityVendor;
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

@Mod(modid = "rcmod", version = "0.4.0", name = "RcMod")
public class RcMod {
	@SidedProxy(clientSide = "com.gugu42.rcmod.ClientProxy", serverSide = "com.gugu42.rcmod.CommonProxy")
	public static CommonProxy proxy;

	@Instance("rcmod")
	public static RcMod instance;
	public static Logger rcLogger;
	
	//Creative tabs
	public static RcCreativeTab rcTab;
	public static RcCreativeTab rcWeapTab;
	
	//Blocks
	public static Block tntCrate;
	public static Block crate;
	public static Block vendor;
	
	public static SoundType crateStepSound;

	public static Item clankBackpack;
	public static Item ratchetEars;
	
	public ArmorMaterial EnumArmorMaterialClank = EnumHelper
			.addArmorMaterial("Clank", 0, new int[] { 0, 0, 0, 0 }, 0);

	public RcTickHandler rcTickHandler;
	
	/*
	 * Packet Handler - Kind of hard to use
	 */
	public static final FFMTPacketHandler rcModPacketHandler = new FFMTPacketHandler("com.gugu42.rcmod.network.packets");

	@EventHandler
	public void PreInit(FMLPreInitializationEvent event) {
		rcTab = new RcCreativeTab("rcTab");
		rcWeapTab = new RcCreativeTab("rcWeapTab");
		Configuration config = new Configuration(
				event.getSuggestedConfigurationFile());

		config.load();		
		
		config.save();
	}

	@EventHandler
	public void Init(FMLInitializationEvent event) {

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
				.setBlockTextureName("rcmod:tntcrate");
		GameRegistry.registerBlock(tntCrate, "tntCrate");
		crate = new BlockCrate(Material.wood).setBlockName("crate")
				.setBlockTextureName("rcmod:crate").setHardness(0.0f).setStepSound(crateStepSound);
		GameRegistry.registerBlock(crate, "crate");

		vendor = new BlockVendor(Material.iron)
				.setBlockName("vendor").setBlockTextureName("rcmod:vendor")
				.setHardness(10.0f);
		GameRegistry.registerBlock(vendor, "vendor");

		GameRegistry.registerTileEntity(TileEntityVendor.class, "vendor");

		/* -----Items----- */

		RcItems.initModItems();
		RcItems.initRc1Items();

		/* -----Other Items----- */
		clankBackpack = new ItemClankBackpack(EnumArmorMaterialClank, 1,
				1).setUnlocalizedName("clankHeli").setTextureName(
				"rcmod:clankheli");
		GameRegistry.registerItem(clankBackpack, "clankHeli");
		ratchetEars = new ItemRatchetEars(EnumArmorMaterialClank, 1, 0).setUnlocalizedName("ratchetEars").setTextureName("rcmod:ratchetears");
		GameRegistry.registerItem(ratchetEars, "ratchetEars");
		
		/* -----Other----- */
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
	}

	@EventHandler
	public void PostInit(FMLPostInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
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
