package com.gugu42.rcmod;

import com.gugu42.rcmod.bolts.ExtendedPlayerBolt;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockVendor extends Block{

	public BlockVendor(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setCreativeTab(RcMod.rcTab);
	}
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		ExtendedPlayerBolt props = ExtendedPlayerBolt.get(par5EntityPlayer);
        if (par5EntityPlayer.getCurrentEquippedItem() != null && par5EntityPlayer.getCurrentEquippedItem().itemID == RcMod.blaster.itemID)
        {
            int ammoToFill = par5EntityPlayer.getCurrentEquippedItem().getItemDamage();
            if(props.consumeBolts(ammoToFill)){
            	par5EntityPlayer.getCurrentEquippedItem().setItemDamage(100 - par5EntityPlayer.getCurrentEquippedItem().getMaxDamage());
            	return true;
            } else {
            	return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
            }
            
        }
        else
        {
            return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
        }
    }
	
	
	public TileEntity createTileEntity(World world, int metadata)
    {
            return new TileEntityVendor();
    }

    public boolean hasTileEntity(int metadata)
    {
            return true;
    }

    public boolean renderAsNormalBlock()
    {
            return false;
    }

    public boolean isOpaqueCube()
    {
            return false;
    }

    
    @SideOnly(Side.CLIENT)
	public int getRenderType()
	{
		return ClientProxy.renderInventoryTESRId;
	}
}
