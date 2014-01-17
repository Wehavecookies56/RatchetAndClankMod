package com.gugu42.rcmod.handler;

import java.util.Random;

import com.gugu42.rcmod.BoltCommand;
import com.gugu42.rcmod.CommonProxy;
import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.items.ItemBlaster;
import com.gugu42.rcmod.items.ItemRcWeap;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

public class RcEventHandler {

	private CommonProxy proxy;

	@ForgeSubscribe
	public void onEntityConstructing(EntityConstructing event) {

		if (event.entity instanceof EntityPlayer
				&& ExtendedPlayerBolt.get((EntityPlayer) event.entity) == null)

			ExtendedPlayerBolt.register((EntityPlayer) event.entity);

		if (event.entity instanceof EntityPlayer
				&& event.entity
						.getExtendedProperties(ExtendedPlayerBolt.EXT_PROP_NAME) == null) {
			event.entity.registerExtendedProperties(
					ExtendedPlayerBolt.EXT_PROP_NAME, new ExtendedPlayerBolt(
							(EntityPlayer) event.entity));
		}

		if (event.entity instanceof EntityPlayer
				&& ExtendedEntityLivingBaseTarget
						.get((EntityPlayer) event.entity) == null) {
			ExtendedEntityLivingBaseTarget
					.register((EntityPlayer) event.entity);
		}
		if (event.entity instanceof EntityLivingBase) {
			ExtendedEntityLivingBaseTarget
					.register((EntityLivingBase) event.entity);
		}

	}

	@ForgeSubscribe
	public void onLivingDeathEvent(LivingDeathEvent event) {
		if (!event.entity.worldObj.isRemote
				&& event.entity instanceof EntityPlayer) {
			NBTTagCompound playerData = new NBTTagCompound();
			((ExtendedPlayerBolt) (event.entity
					.getExtendedProperties(ExtendedPlayerBolt.EXT_PROP_NAME)))
					.saveNBTData(playerData);
			proxy.storeEntityData(((EntityPlayer) event.entity).username,
					playerData);
			ExtendedPlayerBolt.saveProxyData((EntityPlayer) event.entity);
		}
	}

	@ForgeSubscribe
	public void onEntityJoinWorld(EntityJoinWorldEvent event) {
		if (!event.entity.worldObj.isRemote
				&& event.entity instanceof EntityPlayer) {
			NBTTagCompound playerData = proxy
					.getEntityData(((EntityPlayer) event.entity).username);
			if (playerData != null) {
				((ExtendedPlayerBolt) (event.entity
						.getExtendedProperties(ExtendedPlayerBolt.EXT_PROP_NAME)))
						.loadNBTData(playerData);
			}

			((ExtendedPlayerBolt) (event.entity
					.getExtendedProperties(ExtendedPlayerBolt.EXT_PROP_NAME)))
					.sync();
		}
		
		if (event.entity instanceof EntityLivingBase)
		{
		ExtendedEntityLivingBaseTarget props2 = ExtendedEntityLivingBaseTarget.get((EntityLivingBase) event.entity);
		props2.setTargeted(false);
		}
	}

	@ForgeSubscribe
	public void onItemPickup(EntityItemPickupEvent event) {
		ItemStack item = event.item.getEntityItem();
		ExtendedPlayerBolt props = ExtendedPlayerBolt.get(event.entityPlayer);
		if (item.itemID == RcMod.bolt.itemID) {
			props.addBolt(25);
			event.item.setDead();
			event.setCanceled(true);
		}
	}

	@SideOnly(Side.CLIENT)
	@ForgeSubscribe
	public void preRenderPlayer(RenderPlayerEvent.Pre event) {
		EntityPlayer player = event.entityPlayer;
		ItemStack is = player.getCurrentEquippedItem();
		if ((is != null) && ((is.getItem() instanceof ItemRcWeap))) {
			ItemRcWeap itemInHand = (ItemRcWeap) is.getItem();
			if (itemInHand.getHeldType() == 1) {
				ModelBiped modelMain = ObfuscationReflectionHelper
						.getPrivateValue(RenderPlayer.class, event.renderer, 1);
				ModelBiped modelArmorChestplate = ObfuscationReflectionHelper
						.getPrivateValue(RenderPlayer.class, event.renderer, 2);
				ModelBiped modelArmor = ObfuscationReflectionHelper
						.getPrivateValue(RenderPlayer.class, event.renderer, 3);
				modelMain.aimedBow = modelArmorChestplate.aimedBow = modelArmor.aimedBow = true;
				
			}
		}

	} 
	

}
