package com.gugu42.rcmod.items;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTaunter extends ItemRcWeap
{

    public ItemTaunter()
    {
        super();
        this.useAmmo = false;
    }

    @SuppressWarnings("unchecked")
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
            EntityPlayer par3EntityPlayer)
    {
        super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
        par2World.playSoundAtEntity(par3EntityPlayer, "rcmod:TaunterSound",
                1.0f, 1.0f);

        if(!par2World.isRemote)
        {
            float size = 32f;
            List<Entity> entities = par2World
                    .getEntitiesWithinAABBExcludingEntity(par3EntityPlayer,
                            par3EntityPlayer.boundingBox.expand(size, size,
                                    size));
            for (Entity e : entities)
            {
                if(e instanceof EntityCreature)
                {
                    EntityCreature mob = (EntityCreature) e;
                    mob.setPathToEntity(mob.worldObj.getPathEntityToEntity(mob,
                            par3EntityPlayer, size, true, true, true, true));
                }
                if(e instanceof EntityLiving)
                {
                    EntityLiving living = (EntityLiving) e;
                    living.getNavigator().clearPathEntity();
                    living.getNavigator().setPath(
                            living.getNavigator().getPathToEntityLiving(
                                    par3EntityPlayer), 1.0);
                }
            }
        }
        return par1ItemStack;
    }
}
