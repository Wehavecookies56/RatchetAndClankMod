package com.gugu42.rcmod;

import com.gugu42.rcmod.blocks.BlockCrate;
import com.gugu42.rcmod.blocks.BlockTNTCrate;
import com.gugu42.rcmod.blocks.BlockVendor;
import com.gugu42.rcmod.entity.EntityBlasterAmmo;
import com.gugu42.rcmod.entity.EntityBombGloveAmmo;
import com.gugu42.rcmod.entity.EntityRYNOAmmo;
import com.gugu42.rcmod.entity.EntityTNTCrate;
import com.gugu42.rcmod.gui.GuiBolt;
import com.gugu42.rcmod.handler.RcEventHandler;
import com.gugu42.rcmod.handler.RcTickHandler;
import com.gugu42.rcmod.items.ItemBlaster;
import com.gugu42.rcmod.items.ItemBolt;
import com.gugu42.rcmod.items.ItemBombGlove;
import com.gugu42.rcmod.items.ItemOmniWrench3000;
import com.gugu42.rcmod.items.ItemRYNO;
import com.gugu42.rcmod.network.GuiHandler;
import com.gugu42.rcmod.network.RcPacketHandler;
import com.gugu42.rcmod.tileentity.TileEntityVendor;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
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
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "rcmod", version = "0.1.7", name = "RcMod")
@NetworkMod(clientSideRequired = true, serverSideRequired = true, channels = {"RCMD|bolt", "RCMD|vend", "RCMD|refi"}, packetHandler = RcPacketHandler.class)
public class RcMod {
	@SidedProxy(clientSide = "com.gugu42.rcmod.ClientProxy", serverSide = "com.gugu42.rcmod.CommonProxy")
	public static CommonProxy proxy;

	@Instance("rcmod")
	public static RcMod instance;

	public static RcCreativeTab rcTab;
	public static RcCreativeTab rcWeapTab;
	
	public static Block tntCrate;
	public static Block crate;
	public static Block vendor;

	public static Item omniwrench3000;
	public static Item bolt;
	
	//Weapons - R&C 1
	public static Item blaster;
	public static Item bombGlove;
	public static Item ryno;
	
	public RcTickHandler rcTickHandler;

	@EventHandler
	public void PreInit(FMLPreInitializationEvent event) {
		rcTab = new RcCreativeTab("rcTab");
		rcWeapTab = new RcCreativeTab("rcWeapTab");
	}

	@EventHandler
	public void Init(FMLInitializationEvent event) {
		
		/* -----Entity----- */
		EntityRegistry.registerGlobalEntityID(EntityTNTCrate.class, "tntcrate",
				EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityTNTCrate.class, "tntcrate", 50,
				this, 256, 1, false);
		
		EntityRegistry.registerGlobalEntityID(EntityBlasterAmmo.class, "blasterammo", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityBlasterAmmo.class, "blasterammo", 52,
				this, 256, 1, false);
		
		EntityRegistry.registerGlobalEntityID(EntityBombGloveAmmo.class, "bombgloveammo", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityBombGloveAmmo.class, "bombgloveammo", 53,
				this, 256, 1, false);
		
		EntityRegistry.registerGlobalEntityID(EntityRYNOAmmo.class, "rynoammo", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityRYNOAmmo.class, "rynoammo", 54,
				this, 256, 1, false);

		/* -----Blocks----- */
		tntCrate = new BlockTNTCrate(201).setUnlocalizedName("tntCrate")
				.setTextureName("rcmod:tntcrate");
		GameRegistry.registerBlock(tntCrate, "tntCrate");
		crate = new BlockCrate(202, Material.wood).setUnlocalizedName("crate")
				.setTextureName("rcmod:crate").setHardness(0.0f);
		GameRegistry.registerBlock(crate, "crate");

		vendor = new BlockVendor(203, Material.iron).setUnlocalizedName("vendor")
				.setTextureName("rcmod:vendor").setHardness(10.0f);
		GameRegistry.registerBlock(vendor, "vendor");
		
		GameRegistry.registerTileEntity(TileEntityVendor.class, "vendor");
		
		/* -----Items----- */
		omniwrench3000 = new ItemOmniWrench3000(3000).setUnlocalizedName(
				"omwr3000").setTextureName("rcmod:omniwrench3000");
		GameRegistry.registerItem(omniwrench3000, "omwr3000");
		
		bolt = new ItemBolt(3001).setUnlocalizedName("bolt").setTextureName("rcmod:bolt");
		GameRegistry.registerItem(bolt, "bolt");
		
		blaster = new ItemBlaster(3002).setUnlocalizedName("blaster").setTextureName("rcmod:blaster").setFull3D().setCreativeTab(rcWeapTab);
		GameRegistry.registerItem(blaster, "blaster");
		
		bombGlove = new ItemBombGlove(3003).setUnlocalizedName("bombglove").setTextureName("rcmod:bombglove").setFull3D().setCreativeTab(rcWeapTab);
		GameRegistry.registerItem(bombGlove, "bombglove");
		
		ryno = new ItemRYNO(3004).setUnlocalizedName("ryno").setTextureName("rcmod:ryno").setFull3D().setCreativeTab(rcWeapTab);
		GameRegistry.registerItem(ryno, "ryno");
		
		/* -----Other----- */
		rcTab.setTabIconItem(bolt);
		rcWeapTab.setTabIconItem(blaster);
		
		this.rcTickHandler = new RcTickHandler();
		proxy.registerRenderInformation();
		proxy.registerTileEntityRender();
		TickRegistry.registerTickHandler(this.rcTickHandler, Side.SERVER);
		TickRegistry.registerTickHandler(this.rcTickHandler, Side.CLIENT);

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
