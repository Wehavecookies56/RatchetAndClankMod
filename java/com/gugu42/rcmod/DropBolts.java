package com.gugu42.rcmod;

import java.util.Random;

import com.gugu42.rcmod.items.RcItems;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;

//event handler class, it intercepts when events such as breaking ore blocks,
//breeding animals, killing mobs etc to give you bolts as well as xp and their respective drops

public class DropBolts
{
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public void onBlockBreakEvent(HarvestDropsEvent event)
	{
		if(event.isSilkTouching)return;
		Random random = new Random();
		if(event.block == Blocks.coal_ore)
		{
		     ItemStack itemStackToDrop = new ItemStack(RcItems.bolt, random.nextInt(3*(event.fortuneLevel+1))+3);
		     event.drops.add(itemStackToDrop);
		}
		if(event.block == Blocks.lapis_ore)
		{
		     ItemStack itemStackToDrop = new ItemStack(RcItems.bolt, random.nextInt(5*(event.fortuneLevel+1))+3);
		     event.drops.add(itemStackToDrop);
		}
		if(event.block == Blocks.redstone_ore)
		{
		     ItemStack itemStackToDrop = new ItemStack(RcItems.bolt, random.nextInt(1*(event.fortuneLevel+1))+3);
		     event.drops.add(itemStackToDrop);
		}
		if(event.block == Blocks.diamond_ore)
		{
		     ItemStack itemStackToDrop = new ItemStack(RcItems.bolt, random.nextInt(8*(event.fortuneLevel+1))+3);
		     event.drops.add(itemStackToDrop);
		}
		if(event.block == Blocks.quartz_ore)
		{
		     ItemStack itemStackToDrop = new ItemStack(RcItems.bolt, random.nextInt(5*(event.fortuneLevel+1))+3);
		     event.drops.add(itemStackToDrop);
		}
		if(event.block == Blocks.emerald_ore)
		{
		     ItemStack itemStackToDrop = new ItemStack(RcItems.bolt, random.nextInt(5*(event.fortuneLevel+1))+3);
		     event.drops.add(itemStackToDrop);
		}
	}
	//@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	//public void	
}
