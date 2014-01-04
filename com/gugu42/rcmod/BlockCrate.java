package com.gugu42.rcmod;

import java.util.Random;


import net.minecraft.block.Block;
import net.minecraft.block.material.Material;


public class BlockCrate extends Block{

	public BlockCrate(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(RcMod.rcTab);
	}
	
	public int quantityDropped(Random par1Random)
    {
		int quantity = par1Random.nextInt(7);
        return quantity;
    }

    public int idDropped(int par1, Random par2Random, int par3)
    {
        return RcMod.bolt.itemID;
    }
	
}
