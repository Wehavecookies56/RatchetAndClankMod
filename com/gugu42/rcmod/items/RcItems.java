package com.gugu42.rcmod.items;

import com.gugu42.rcmod.RcMod;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class RcItems {
	//All global mod items goes there
	public static Item omniwrench3000;
	public static Item bolt;
	public static Item vendorCore;
	public static Item helipackHelice;
	public static Item clankCore;
	public static Item clank;
	public static Item DUMMY_pyrocitorFlame;
	
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

	public RcItems(){
		
	}
	
	public static void initRc1Items() {

		blaster = new ItemBlaster(RcMod.blasterID).setUnlocalizedName("blaster")
				.setTextureName("rcmod:blaster").setFull3D()
				.setCreativeTab(RcMod.rcWeapTab);
		GameRegistry.registerItem(blaster, "blaster");

		bombGlove = new ItemBombGlove(RcMod.bombgloveID).setUnlocalizedName("bombglove")
				.setTextureName("rcmod:bombglove").setFull3D()
				.setCreativeTab(RcMod.rcWeapTab);
		GameRegistry.registerItem(bombGlove, "bombglove");

		ryno = new ItemRYNO(RcMod.rynoID).setUnlocalizedName("ryno")
				.setTextureName("rcmod:ryno").setFull3D()
				.setCreativeTab(RcMod.rcWeapTab);
		GameRegistry.registerItem(ryno, "ryno");

		pyrocitor = new ItemPyrocitor(RcMod.pyrocitorID).setUnlocalizedName("pyrocitor")
				.setTextureName("rcmod:pyrocitor")
				.setCreativeTab(RcMod.rcWeapTab);
		GameRegistry.registerItem(pyrocitor, "pyrocitor");

		walloper = new ItemWalloper(RcMod.walloperID).setUnlocalizedName("walloper")
				.setTextureName("rcmod:walloper")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.registerItem(walloper, "walloper");
		
		visibombGun = new ItemVisibombGun(RcMod.visibombGunID).setUnlocalizedName("visibombGun")
				.setTextureName("rcmod:visibombGun")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.registerItem(visibombGun, "visibombGun");
		
		decoyGlove = new ItemDecoyGlove(RcMod.decoyGloveID).setUnlocalizedName("decoyGlove")
				.setTextureName("rcmod:decoyGlove")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		
		devastator = new ItemDevastator(RcMod.devastatorID).setUnlocalizedName("devastator")
				.setTextureName("rcmod:devastator")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		
		droneDevice = new ItemDroneDevice(RcMod.droneDeviceID).setUnlocalizedName("droneDevice")
				.setTextureName("rcmod:droneDevice")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		gloveOfDoom = new ItemGloveOfDoom(RcMod.gloveOfDoomID).setUnlocalizedName("gloveOfDoom")
				.setTextureName("rcmod:gloveOfDoom")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		
		mineGlove = new ItemMineGlove(RcMod.mineGloveID).setUnlocalizedName("mineGlove")
				.setTextureName("rcmod:mineGlove")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		
		morphORay = new ItemMorphORay(RcMod.morphORayID).setUnlocalizedName("morphORay")
				.setTextureName("rcmod:morphORay")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		
		suckCannon = new ItemSuckCannon(RcMod.suckCannonID).setUnlocalizedName("suckCannon")
				.setTextureName("rcmod:suckCannon")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		
		taunter = new ItemTaunter(RcMod.taunterID).setUnlocalizedName("taunter")
				.setTextureName("rcmod:taunter")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		
		teslaClaw = new ItemTeslaClaw(RcMod.teslaClawID).setUnlocalizedName("teslaClaw")
				.setTextureName("rcmod:teslaClaw")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
	}
	
	public static void initModItems(){
		omniwrench3000 = new ItemOmniWrench3000(RcMod.wrench3000ID).setUnlocalizedName(
				"omwr3000").setTextureName("rcmod:omniwrench3000");
		GameRegistry.registerItem(omniwrench3000, "omwr3000");

		bolt = new ItemBolt(RcMod.boltID).setUnlocalizedName("bolt").setTextureName(
				"rcmod:bolt");
		GameRegistry.registerItem(bolt, "bolt");
		
		vendorCore = new ItemVendorCore(RcMod.vendorCoreID).setUnlocalizedName(
				"vendorCore").setTextureName("rcmod:vendorcore");
		GameRegistry.registerItem(vendorCore, "vendorCore");
		
		clankCore = new ItemRcSimple(RcMod.clankCoreID).setUnlocalizedName("clankCore").setTextureName("rcmod:clankcore").setCreativeTab(RcMod.rcTab);
		GameRegistry.registerItem(clankCore, "clankCore");
		
		clank = new ItemRcSimple(RcMod.clankID).setUnlocalizedName("clank").setTextureName("rcmod:clank").setCreativeTab(RcMod.rcTab);
		GameRegistry.registerItem(clank, "clank");
		
		helipackHelice = new ItemRcSimple(RcMod.helipackHeliceID).setUnlocalizedName("helipackHelice").setTextureName("rcmod:helipackhelice").setCreativeTab(RcMod.rcTab);
		GameRegistry.registerItem(helipackHelice, "helipackHelice");
		
		DUMMY_pyrocitorFlame = new ItemRcSimple(RcMod.DUMMY_pyrocitorFlameID).setTextureName("rcmod:pyrocitorFlame").setCreativeTab(RcMod.rcTab);
		GameRegistry.registerItem(DUMMY_pyrocitorFlame, "DUMMY_pyrocitorFlame");
	}

}
