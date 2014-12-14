package com.gugu42.rcmod;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.gugu42.rcmod.items.RcItems;

public class RcRecipes 
{
	public static void addRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(RcMod.vendor, 1), new Object[]{
			"XZX",
			"ZYZ",
			"XZX",
			'X', Blocks.iron_block, 'Y', RcItems.vendorCore, 'Z', Blocks.obsidian
		});
		GameRegistry.addRecipe(new ItemStack(RcItems.omniwrench3000, 1), new Object[]{
			"X X",
			" Y ",
			" X ",
			'X', Items.iron_ingot, 'Y', Blocks.iron_block
		});
		GameRegistry.addRecipe(new ItemStack(RcMod.crate, 1), new Object[]{
			"XXX",
			"XZX",
			"XXX",
			'X', Blocks.planks, 'Z', Items.iron_ingot
		});
		GameRegistry.addRecipe(new ItemStack(RcItems.vendorCore, 1), new Object[]{
			"XXX",
			"XZX",
			"XXX",
			'X', new ItemStack(Items.dye, 1, 14), 'Z', Items.diamond
		});
		
		GameRegistry.addRecipe(new ItemStack(RcItems.clankCore, 1), new Object[]{
			"GGG",
			"GDG",
			"GGG",
			'G', new ItemStack(Items.dye, 1, 10), 'D', Items.diamond
		});
		
		GameRegistry.addRecipe(new ItemStack(RcItems.clank, 1), new Object[]{
			"IRI",
			"ICI",
			"IBI",
			'I', Items.iron_ingot, 'R', Items.redstone, 'B', Blocks.redstone_block, 'C', RcItems.clankCore
		});
		
		GameRegistry.addRecipe(new ItemStack(RcItems.helipackHelice, 1), new Object[]{
			" I ",
			"IYI",
			" I ",
			'I', Items.iron_ingot, 'Y', new ItemStack(Items.dye, 1, 11)
		});
		
		GameRegistry.addRecipe(new ItemStack(RcMod.clankBackpack, 1), new Object[]{
			" H ",
			" I ",
			"HCH",
			'I', Blocks.iron_bars, 'C', RcItems.clank, 'H', RcItems.helipackHelice
		});
		GameRegistry.addRecipe(new ItemStack(RcMod.tntCrate, 4), new Object[]{
			" T ",
			"TYT",
			" T ",
			'T', Blocks.tnt, 'Y', new ItemStack(Items.dye, 1, 11)
		});
		
		GameRegistry.addRecipe(new ItemStack(RcMod.ratchetEars, 1), new Object[]{
			"Y Y",
			"YTY",
			'T', Items.string, 'Y', new ItemStack(Blocks.wool, 1, 4)
		});
		
		GameRegistry.addRecipe(new ItemStack(RcMod.thrusterPack, 1), new Object[]{
			"ICI",
			"H H",
			'I', Blocks.iron_bars, 'C', RcItems.clank, 'H', Items.flint_and_steel
		});
		
		GameRegistry.addRecipe(new ItemStack(RcItems.swingShot, 1), new Object[]{
			"ISI",
			"  I",
			'I', Items.iron_ingot, 'S', Items.string
		});
	}
}
