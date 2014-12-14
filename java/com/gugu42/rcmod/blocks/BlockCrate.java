package com.gugu42.rcmod.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.items.RcItems;


public class BlockCrate extends BlockFalling{

    public static boolean fallInstantly;
	
	public BlockCrate(Material par2Material) {
		super(par2Material);
		setCreativeTab(RcMod.rcTab);
	}
	
	@Override
	public int quantityDropped(Random par1Random)
    {
		int quantity = par1Random.nextInt(7);
        return quantity;
    }

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return RcItems.bolt;
    }
    
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
    }

    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
    {
        worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
            this.checkFallable(worldIn, pos);
        }
    }

    private void checkFallable(World worldIn, BlockPos pos)
    {
        if (canFallInto(worldIn, pos.down()) && pos.getY() >= 0)
        {
            byte b0 = 32;

            if (!fallInstantly && worldIn.isAreaLoaded(pos.add(-b0, -b0, -b0), pos.add(b0, b0, b0)))
            {
                if (!worldIn.isRemote)
                {
                    EntityFallingBlock entityfallingblock = new EntityFallingBlock(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, worldIn.getBlockState(pos));
                    this.onStartFalling(entityfallingblock);
                    worldIn.spawnEntityInWorld(entityfallingblock);
                }
            }
            else
            {
                worldIn.setBlockToAir(pos);
                BlockPos blockpos1;

                for (blockpos1 = pos.down(); canFallInto(worldIn, blockpos1) && blockpos1.getY() > 0; blockpos1 = blockpos1.down())
                {
                    ;
                }

                if (blockpos1.getY() > 0)
                {
                    worldIn.setBlockState(blockpos1.up(), this.getDefaultState());
                }
            }
        }
    }

    protected void onStartFalling(EntityFallingBlock fallingEntity) {}

    public int tickRate(World worldIn)
    {
        return 2;
    }

    public static boolean canFallInto(World worldIn, BlockPos pos)
    {
        if (worldIn.isAirBlock(pos)) return true;
        Block block = worldIn.getBlockState(pos).getBlock();
        Material material = block.getMaterial();
        return block == Blocks.fire || material == Material.air || material == Material.water || material == Material.lava;
    }

    /**
     * Called when the falling block entity for this block hits the ground and turns back into a block
     */
    public void onFinishFalling(World par1World, int par2, int par3, int par4, int par5) {}
	
}
