package com.gugu42.rcmod.items;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

public class ItemRcGun extends ItemRcWeap
{
	public int id;
	public void setID(int ID)
	{
		this.id = ID;
	}
	public int getID()
	{
		return this.id;
	}
	public boolean refill(ItemStack stack, ItemRcGun thisthing, EntityItemPickupEvent event, int ammotogive)
	{
		while(ammotogive > 0 && stack.getItemDamage()
				!=thisthing.getMaxDamage())
		{
			stack.damageItem(-1, event.entityPlayer);
			ammotogive--;
		}
		return ammotogive==10;
	}	
}
