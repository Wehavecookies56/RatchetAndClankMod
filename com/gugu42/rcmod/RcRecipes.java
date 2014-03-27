package com.gugu42.rcmod;

import com.gugu42.rcmod.items.RcItems;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class RcRecipes 
{
	public static void addRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(RcMod.vendor, 1), new Object[]{
			"XZX",
			"ZYZ",
			"XZX",
			'X', Block.blockIron, 'Y', RcItems.vendorCore, 'Z', Block.obsidian
		});
		GameRegistry.addRecipe(new ItemStack(RcItems.omniwrench3000, 1), new Object[]{
			"X X",
			" Y ",
			" X ",
			'X', Item.ingotIron, 'Y', Block.blockIron
		});
		GameRegistry.addRecipe(new ItemStack(RcMod.crate, 1), new Object[]{
			"XXX",
			"XZX",
			"XXX",
			'X', Block.planks, 'Z', Item.ingotIron
		});
		GameRegistry.addRecipe(new ItemStack(RcItems.vendorCore, 1), new Object[]{
			"XXX",
			"XZX",
			"XXX",
			'X', new ItemStack(Item.dyePowder, 1, 14), 'Z', Item.diamond
		});
		
		GameRegistry.addRecipe(new ItemStack(RcItems.clankCore, 1), new Object[]{
			"GGG",
			"GDG",
			"GGG",
			'G', new ItemStack(Item.dyePowder, 1, 10), 'D', Item.diamond
		});
		
		GameRegistry.addRecipe(new ItemStack(RcItems.clank, 1), new Object[]{
			"IRI",
			"ICI",
			"IBI",
			'I', Item.ingotIron, 'R', Item.redstone, 'B', Block.blockRedstone, 'C', RcItems.clankCore
		});
		
		GameRegistry.addRecipe(new ItemStack(RcItems.helipackHelice, 1), new Object[]{
			" I ",
			"IYI",
			" I ",
			'I', Item.ingotIron, 'Y', new ItemStack(Item.dyePowder, 1, 11)
		});
		
		GameRegistry.addRecipe(new ItemStack(RcMod.clankBackpack, 1), new Object[]{
			" H ",
			" I ",
			"HCH",
			'I', Block.fenceIron, 'C', RcItems.clank, 'H', RcItems.helipackHelice
		});
	}
}
