package com.gugu42.rcmod.items;

import com.gugu42.rcmod.RcMod;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class RcItems {
	//All global mod items goes there
	public static Item omniwrench3000;
	public static Item bolt;
	public static Item vendorCore;
	
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

		blaster = new ItemBlaster(3002).setUnlocalizedName("blaster")
				.setTextureName("rcmod:blaster").setFull3D()
				.setCreativeTab(RcMod.rcWeapTab);
		GameRegistry.registerItem(blaster, "blaster");

		bombGlove = new ItemBombGlove(3003).setUnlocalizedName("bombglove")
				.setTextureName("rcmod:bombglove").setFull3D()
				.setCreativeTab(RcMod.rcWeapTab);
		GameRegistry.registerItem(bombGlove, "bombglove");

		ryno = new ItemRYNO(3004).setUnlocalizedName("ryno")
				.setTextureName("rcmod:ryno").setFull3D()
				.setCreativeTab(RcMod.rcWeapTab);
		GameRegistry.registerItem(ryno, "ryno");

		pyrocitor = new ItemPyrocitor(3005).setUnlocalizedName("pyrocitor")
				.setTextureName("rcmod:pyrocitor")
				.setCreativeTab(RcMod.rcWeapTab);
		GameRegistry.registerItem(pyrocitor, "pyrocitor");

		walloper = new ItemWalloper(3006).setUnlocalizedName("walloper")
				.setTextureName("rcmod:walloper")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.registerItem(walloper, "walloper");
		
		visibombGun = new ItemVisibombGun(3007).setUnlocalizedName("visibombGun")
				.setTextureName("rcmod:visibombGun")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.registerItem(visibombGun, "visibombGun");
		
		decoyGlove = new ItemDecoyGlove(3009).setUnlocalizedName("decoyGlove")
				.setTextureName("rcmod:decoyGlove")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		
		devastator = new ItemDevastator(3010).setUnlocalizedName("devastator")
				.setTextureName("rcmod:devastator")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		
		droneDevice = new ItemDroneDevice(3011).setUnlocalizedName("droneDevice")
				.setTextureName("rcmod:droneDevice")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		gloveOfDoom = new ItemGloveOfDoom(3012).setUnlocalizedName("gloveOfDoom")
				.setTextureName("rcmod:gloveOfDoom")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		
		mineGlove = new ItemMineGlove(3013).setUnlocalizedName("mineGlove")
				.setTextureName("rcmod:mineGlove")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		
		morphORay = new ItemMorphORay(3014).setUnlocalizedName("morphORay")
				.setTextureName("rcmod:morphORay")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		
		suckCannon = new ItemSuckCannon(3015).setUnlocalizedName("suckCannon")
				.setTextureName("rcmod:suckCannon")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		
		taunter = new ItemTaunter(3016).setUnlocalizedName("taunter")
				.setTextureName("rcmod:taunter")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		
		teslaClaw = new ItemTeslaClaw(3017).setUnlocalizedName("teslaClaw")
				.setTextureName("rcmod:teslaClaw")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
	}
	
	public static void initModItems(){
		omniwrench3000 = new ItemOmniWrench3000(3000).setUnlocalizedName(
				"omwr3000").setTextureName("rcmod:omniwrench3000");
		GameRegistry.registerItem(omniwrench3000, "omwr3000");

		bolt = new ItemBolt(3001).setUnlocalizedName("bolt").setTextureName(
				"rcmod:bolt");
		GameRegistry.registerItem(bolt, "bolt");
		
		vendorCore = new ItemVendorCore(3008).setUnlocalizedName(
				"vendorCore").setTextureName("rcmod:vendorcore");
		GameRegistry.registerItem(vendorCore, "vendorCore");
	}

}
