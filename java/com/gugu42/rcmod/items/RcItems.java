package com.gugu42.rcmod.items;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.gugu42.rcmod.RcMod;

public class RcItems {
	//All global mod items goes there
	public static Item omniwrench3000;
	public static Item bolt;
	public static Item vendorCore;
	public static Item helipackHelice;
	public static Item clankCore;
	public static Item clank;
	public static Item DUMMY_pyrocitorFlame;
	public static Item metropolisRecord;
	
	// All items from RC1 goes there
	public static Item blaster;
	public static Item bombGlove;
	public static Item ryno;
	public static Item pyrocitor;
	public static Item walloper;
	public static Item visibombGun;
	public static Item decoyGlove;
	public static Item devastator;
	public static Item droneDevice;
	public static Item gloveOfDoom;
	public static Item mineGlove;
	public static Item morphORay;
	public static Item suckCannon;
	public static Item taunter;
	public static Item teslaClaw;
	
	// All gadgets from RC1 goes there
	
	public static Item swingShot;
	//Not really a gadget from the game, but still something, so yeah
	public static Item gadgetronHelper;
	

	public RcItems(){
		
	}
	
	public static void initRc1Items() {

		blaster = new ItemBlaster().setUnlocalizedName("blaster")
				.setTextureName("rcmod:blaster").setFull3D()
				.setCreativeTab(RcMod.rcWeapTab);
		GameRegistry.registerItem(blaster, "blaster");

		bombGlove = new ItemBombGlove().setUnlocalizedName("bombglove")
				.setTextureName("rcmod:bombglove").setFull3D()
				.setCreativeTab(RcMod.rcWeapTab);
		GameRegistry.registerItem(bombGlove, "bombglove");

		ryno = new ItemRYNO().setUnlocalizedName("ryno")
				.setTextureName("rcmod:ryno").setFull3D()
				.setCreativeTab(RcMod.rcWeapTab);
		GameRegistry.registerItem(ryno, "ryno");

		pyrocitor = new ItemPyrocitor().setUnlocalizedName("pyrocitor")
				.setTextureName("rcmod:pyrocitor")
				.setCreativeTab(RcMod.rcWeapTab);
		GameRegistry.registerItem(pyrocitor, "pyrocitor");

		walloper = new ItemWalloper().setUnlocalizedName("walloper")
				.setTextureName("rcmod:walloper")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.registerItem(walloper, "walloper");
		
		visibombGun = new ItemVisibombGun().setUnlocalizedName("visibombGun")
				.setTextureName("rcmod:visibombGun")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.registerItem(visibombGun, "visibombGun");
		
		decoyGlove = new ItemDecoyGlove().setUnlocalizedName("decoyGlove")
				.setTextureName("rcmod:decoyGlove")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.registerItem(decoyGlove, "decoyGlove");
		
		devastator = new ItemDevastator().setUnlocalizedName("devastator")
				.setTextureName("rcmod:devastator")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.registerItem(devastator, "devastator");
		
		droneDevice = new ItemDroneDevice().setUnlocalizedName("droneDevice")
				.setTextureName("rcmod:droneDevice")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.registerItem(droneDevice, "droneDevice");
		
		gloveOfDoom = new ItemGloveOfDoom().setUnlocalizedName("gloveOfDoom")
				.setTextureName("rcmod:gloveOfDoom")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.registerItem(gloveOfDoom, "gloveOfDoom");
		
		mineGlove = new ItemMineGlove().setUnlocalizedName("mineGlove")
				.setTextureName("rcmod:mineGlove")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.registerItem(mineGlove, "mineGlove");
		
		morphORay = new ItemMorphORay().setUnlocalizedName("morphORay")
				.setTextureName("rcmod:morphORay")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.registerItem(morphORay, "morphORay");
		
		suckCannon = new ItemSuckCannon().setUnlocalizedName("suckCannon")
				.setTextureName("rcmod:suckCannon")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.registerItem(suckCannon, "suckCannon");
		
		taunter = new ItemTaunter().setUnlocalizedName("taunter")
				.setTextureName("rcmod:taunter")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.registerItem(taunter, "taunter");
		
		teslaClaw = new ItemTeslaClaw().setUnlocalizedName("teslaClaw")
				.setTextureName("rcmod:teslaClaw")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.registerItem(teslaClaw, "teslaClaw");
		
		/*  GADGETS  */
		
		swingShot = new ItemSwingShot().setUnlocalizedName("swingShot").setTextureName("rcmod:swingShot").setCreativeTab(RcMod.rcGadgTab).setFull3D();
		GameRegistry.registerItem(swingShot, "swingShot");
		
		gadgetronHelper = new ItemGadgetronHelper().setUnlocalizedName("gadgetronHelper").setTextureName("rcmod:gadgetronHelper").setCreativeTab(RcMod.rcGadgTab).setFull3D();
		GameRegistry.registerItem(gadgetronHelper, "gadgetronHelper");
		
	}
	
	public static void initModItems(){
		omniwrench3000 = new ItemOmniWrench3000().setUnlocalizedName(
				"omwr3000").setTextureName("rcmod:omniwrench3000");
		GameRegistry.registerItem(omniwrench3000, "omwr3000");

		bolt = new ItemBolt().setUnlocalizedName("bolt").setTextureName(
				"rcmod:bolt");
		GameRegistry.registerItem(bolt, "bolt");
		
		vendorCore = new ItemVendorCore().setUnlocalizedName(
				"vendorCore").setTextureName("rcmod:vendorcore");
		GameRegistry.registerItem(vendorCore, "vendorCore");
		
		clankCore = new ItemRcSimple().setUnlocalizedName("clankCore").setTextureName("rcmod:clankcore").setCreativeTab(RcMod.rcTab);
		GameRegistry.registerItem(clankCore, "clankCore");
		
		clank = new ItemRcSimple().setUnlocalizedName("clank").setTextureName("rcmod:clank").setCreativeTab(RcMod.rcTab);
		GameRegistry.registerItem(clank, "clank");
		
		helipackHelice = new ItemRcSimple().setUnlocalizedName("helipackHelice").setTextureName("rcmod:helipackhelice").setCreativeTab(RcMod.rcTab);
		GameRegistry.registerItem(helipackHelice, "helipackHelice");
		
		DUMMY_pyrocitorFlame = new ItemRcSimple().setTextureName("rcmod:pyrocitorFlame");
		GameRegistry.registerItem(DUMMY_pyrocitorFlame, "DUMMY_pyrocitorFlame");
		
		metropolisRecord = new ItemRcRecord("rcmod:recordmetropolis", "Metropolis - Kerwan", "metropolisRecord").setUnlocalizedName("metropolisRecord").setTextureName("rcmod:recordRc").setCreativeTab(RcMod.rcTab);
		GameRegistry.registerItem(metropolisRecord, "metropolisRecord");
	}

}
