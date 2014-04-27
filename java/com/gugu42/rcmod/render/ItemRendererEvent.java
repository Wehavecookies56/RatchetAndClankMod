package com.gugu42.rcmod.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.eventhandler.Event;

public class ItemRendererEvent extends Event{

	public float partialTick;
	public Minecraft mc;
	public ItemRenderer renderer;
	public ItemStack itemToRender;

	public static class RenderInFirstPerson extends ItemRendererEvent
	{
		public RenderInFirstPerson(float partialTick, Minecraft mc, ItemRenderer renderer, ItemStack itemToRender){
			super();
			this.partialTick = partialTick;
			this.mc = mc;
			this.renderer = renderer;
			this.itemToRender = itemToRender;
		}
	}

}