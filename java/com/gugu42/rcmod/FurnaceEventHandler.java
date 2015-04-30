package com.gugu42.rcmod;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gugu42.rcmod.items.RcItems;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemSmeltedEvent;

public class FurnaceEventHandler {
	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public void firePlayerSmeltedEvent(ItemSmeltedEvent event) {
		if (event.smelting.getItem() == Items.iron_ingot) {
			Random random = new Random();
			ItemStack itemStackToDrop = new ItemStack(RcItems.bolt, random.nextInt(3) + 3);
			event.player.worldObj.spawnEntityInWorld(new EntityItem(event.player.worldObj, event.player.posX, event.player.posY, event.player.posZ, itemStackToDrop));
		} else {
		}
	}
}
