package com.gugu42.rcmod;

import java.util.Random;

import com.gugu42.rcmod.items.RcItems;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemSmeltedEvent;

public class FurnaceEventHandler
{
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public void firePlayerSmeltedEvent(ItemSmeltedEvent event)
	{
		Random random = new Random();
		if(event.smelting.getItem() == Items.iron_ingot)
		{
		     ItemStack itemStackToDrop = new ItemStack(RcItems.bolt, random.nextInt(3)+3);
		     Minecraft.getMinecraft().theWorld.spawnEntityInWorld(new EntityItem(Minecraft.getMinecraft().theWorld, Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ, itemStackToDrop));
		}else{}
	}
}
