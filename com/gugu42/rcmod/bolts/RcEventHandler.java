package com.gugu42.rcmod.bolts;

import com.gugu42.rcmod.CommonProxy;
import com.gugu42.rcmod.RcMod;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

public class RcEventHandler {

	private CommonProxy proxy;
	private ExtendedPlayerBolt epb;

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
	}
	
	@ForgeSubscribe
    public void onItemPickup(EntityItemPickupEvent event) {
		ItemStack item = event.item.getEntityItem();
    	ExtendedPlayerBolt props = ExtendedPlayerBolt.get(event.entityPlayer);
		if(item.itemID == RcMod.bolt.itemID){
			props.addBolt(50);
			event.item.setDead();
			event.setCanceled(true);
		}
	}

}
