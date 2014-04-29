package com.gugu42.rcmod.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.entity.EntityTNTCrate;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTNTCrate extends Block {
    @SideOnly(Side.CLIENT)
    private IIcon blockIconTop;
    @SideOnly(Side.CLIENT)
    private IIcon blockIconBottom;

    public BlockTNTCrate(Material material) {
        super(material);
        this.setCreativeTab(RcMod.rcTab);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        return p_149691_1_ == 0 ? this.blockIconBottom : (p_149691_1_ == 1 ? this.blockIconTop : this.blockIcon);
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int par2, int par3, int par4) {
        super.onBlockAdded(par1World, par2, par3, par4);

        if (par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
            this.onBlockDestroyedByPlayer(par1World, par2, par3, par4, 1);
            par1World.setBlockToAir(par2, par3, par4);
        }
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random) {
        return 0;
    }

    /**
     * Called upon the block being destroyed by an explosion
     */
    public void onBlockDestroyedByExplosion(World par1World, int par2,
            int par3, int par4, Explosion par5Explosion) {
        if (!par1World.isRemote) {
            EntityTNTCrate entitytntcrate = new EntityTNTCrate(par1World,
                    (double) ((float) par2 + 0.5F),
                    (double) ((float) par3 + 0.5F),
                    (double) ((float) par4 + 0.5F));
            par1World.setBlockToAir(par2, par3, par4);
            par1World.spawnEntityInWorld(entitytntcrate);
        }
    }

    /**
     * Called right before the block is destroyed by a player. Args: world, x,
     * y, z, metaData
     */
    public void onBlockDestroyedByPlayer(World par1World, int par2, int par3,
            int par4, int par5) {
        if (!par1World.isRemote) {
            EntityTNTCrate entitytntcrate = new EntityTNTCrate(par1World,
                    (double) ((float) par2 + 0.5F),
                    (double) ((float) par3 + 0.5F),
                    (double) ((float) par4 + 0.5F));
            par1World.setBlockToAir(par2, par3, par4);
            par1World.spawnEntityInWorld(entitytntcrate);
        }
    }

    /**
     * spawns the primed tnt and plays the fuse sound.
     */
    public void primeTnt(World par1World, int par2, int par3, int par4, int par5) {
        if (!par1World.isRemote) {
            EntityTNTCrate entitytntcrate = new EntityTNTCrate(par1World,
                    (double) ((float) par2 + 0.5F),
                    (double) ((float) par3 + 0.5F),
                    (double) ((float) par4 + 0.5F));
            par1World.setBlockToAir(par2, par3, par4);
            par1World.spawnEntityInWorld(entitytntcrate);
        }
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World par1World, int par2, int par3,
            int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
            float par8, float par9) {
        if (par5EntityPlayer.getCurrentEquippedItem() != null
                && par5EntityPlayer.getCurrentEquippedItem().getItem() != null && par5EntityPlayer.getCurrentEquippedItem().getItem() == Items.flint_and_steel) {
            this.primeTnt(par1World, par2, par3, par4, 1);
            par1World.setBlockToAir(par2, par3, par4);
            par5EntityPlayer.getCurrentEquippedItem().damageItem(1,
                    par5EntityPlayer);
            return true;
        } else {
            return super.onBlockActivated(par1World, par2, par3, par4,
                    par5EntityPlayer, par6, par7, par8, par9);
        }
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the
     * block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3,
            int par4, Entity par5Entity) {
        if (!par1World.isRemote) {
            this.primeTnt(par1World, par2, par3, par4, 1);
            par1World.setBlockToAir(par2, par3, par4);
        }
    }

    public void onEntityWalking(World par1World, int par2, int par3, int par4,
            Entity par5Entity) {
        this.primeTnt(par1World, par2, par3, par4, 1);
    }

    /**
     * Return whether this block can drop from an explosion.
     */
    public boolean canDropFromExplosion(Explosion par1Explosion) {
        return false;
    }

//  @SideOnly(Side.CLIENT)
//  public void registerIcons(IIconRegister par1IconRegister) {
//      this.blockIcon = par1IconRegister.registerIcon(this.getTextureName());
//      this.blockTop = this.blockBottom = par1IconRegister.registerIcon(this.getTextureName() + "top");
//  }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iiconregister)
    {
        this.blockIcon = iiconregister.registerIcon(this.getTextureName());
        this.blockIconTop = iiconregister.registerIcon(this.getTextureName() + "top");
        this.blockIconBottom = iiconregister.registerIcon(this.getTextureName() + "top");
    }
}