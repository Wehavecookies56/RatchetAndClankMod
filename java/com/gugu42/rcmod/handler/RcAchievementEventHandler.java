package com.gugu42.rcmod.handler;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

import com.gugu42.rcmod.RcMod;

public class RcAchievementEventHandler {

	@SubscribeEvent
	public void onCrafting(ItemCraftedEvent event) {
		if (event.player instanceof EntityPlayerMP) {
			EntityPlayerMP playerMP = (EntityPlayerMP) event.player;

			if (event.crafting.getItem() == Item.getItemFromBlock(RcMod.vendor)
					&& !playerMP.getStatFile().hasAchievementUnlocked(
							RcMod.achievement_VendorCraft)) {
				event.player.triggerAchievement(RcMod.achievement_VendorCraft);
				event.player.worldObj.playSoundAtEntity(event.player,
						"rcmod:achievement", 1.0f, 1.0f);
			}
			if (event.crafting.getItem() == RcMod.clankBackpack
					&& !playerMP.getStatFile().hasAchievementUnlocked(
							RcMod.achievement_HelipackCraft)) {
				event.player
						.triggerAchievement(RcMod.achievement_HelipackCraft);
				event.player.worldObj.playSoundAtEntity(event.player,
						"rcmod:achievement", 1.0f, 1.0f);
			}
		}
	}
	
}
