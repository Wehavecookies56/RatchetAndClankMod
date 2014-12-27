package com.gugu42.rcmod.items;

import java.util.List;

import com.gugu42.rcmod.entity.projectiles.EntitySuckCannonProj;
import com.gugu42.rcmod.handler.ExtendedPropsSuckCannon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemSuckCannon extends ItemRcWeap
{

    public ItemSuckCannon()
    {
        super();
        this.maxAmmo = 8;
        this.setMaxDamage(maxAmmo);
        this.heldType = 1;
        this.maxStackSize = 1;
    }

    public void onUpdate(ItemStack stack, World w, Entity owner, int i, boolean held)
    {
        if(owner instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)owner;
            ExtendedPropsSuckCannon props = ExtendedPropsSuckCannon.get(player);
            int loaded = props.getStack().size();
            if(loaded != maxAmmo - stack.getItemDamage())
                stack.setItemDamage(maxAmmo - loaded);
        }
    }

    @SuppressWarnings("unchecked")
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer owner)
    {
        if(stack.getItemDamage() == 0)
            return stack;
        double radius = 10;
        List<EntityLiving> entities = world.getEntitiesWithinAABB(EntityLiving.class, owner.boundingBox.expand(radius, radius, radius));

        for(int i = 0; i < entities.size(); i++)
        {
            Vec3 look = owner.getLookVec();
            EntityLiving entity = entities.get(i);

            if(entity.canEntityBeSeen(owner))
            {
                Vec3 playerPos = Vec3.createVectorHelper(owner.posX, owner.posY + (owner.getEyeHeight() - owner.getDefaultEyeHeight()), owner.posZ);
                Vec3 entPos = Vec3.createVectorHelper(entity.posX, entity.posY, entity.posZ);

                if(entity instanceof EntityDragon || entity instanceof EntityWither)
                    continue;

                Vec3 a = playerPos.subtract(entPos).normalize();

                double dotProduct = a.dotProduct(look);
                double angle = Math.acos(dotProduct);

                if(angle < 0.25 * Math.PI && angle > -0.25 * Math.PI)
                {
                    if(entity.getDistanceToEntity(owner) >= 2.5)
                    {
                        Vec3 b = Vec3.createVectorHelper(a.xCoord, a.yCoord, a.zCoord);
                        double suckingPower = 5;
                        customKnockBack(entity, 0, b.xCoord * suckingPower, b.yCoord * suckingPower, b.zCoord * suckingPower);
                    }
                    else
                    {
                        entity.setDead();
                        entity.worldObj.removeEntity(entity);
                        stack.damageItem(-1, owner);
                        if(!world.isRemote)
                        {
                            ExtendedPropsSuckCannon props = ExtendedPropsSuckCannon.get(owner);
                            props.pushToStack(entity);
                            props.sync();
                        }
                    }
                }
            }
        }

        return stack;
    }

    public boolean onEntitySwing(EntityLivingBase owner, ItemStack stack)
    {
        if(owner instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)owner;
            ExtendedPropsSuckCannon props = ExtendedPropsSuckCannon.get(player);
            NBTTagCompound compound = props.popStack();
            if(compound == null)
                return false;

            stack.damageItem(1, owner);
            if(owner.worldObj.isRemote)
                return true;
            props.sync();

            EntityLiving e = (EntityLiving)EntityList.createEntityFromNBT(compound, owner.worldObj);
            if(e != null)
            {
                if(!compound.getBoolean("hadCustomTagName"))
                    e.setCustomNameTag("");
                EntitySuckCannonProj proj = new EntitySuckCannonProj(player.worldObj, player);

                e.mountEntity(proj);
                proj.setPosition(player.posX, player.posY + 1, player.posZ);
                e.setPosition(player.posX, player.posY + 1, player.posZ);
                owner.worldObj.spawnEntityInWorld(proj);
                owner.worldObj.spawnEntityInWorld(e);
                proj.setOwnerID(owner.getEntityId());
                owner.worldObj.playSoundAtEntity(owner, "rcmod:SuckCannonShot", 1.0f, 1.0f);
            }
        }
        return true;
    }

    public void customKnockBack(EntityLivingBase par1Entity, float par2, double par3, double par4, double par5)
    {
        if(par1Entity.worldObj.rand.nextDouble() >= par1Entity.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue())
        {
            par1Entity.isAirBorne = true;
            float f1 = MathHelper.sqrt_double(par3 * par3 + par5 * par5);
            float f2 = 0.4F;
            par1Entity.motionX /= 2.0D;
            par1Entity.motionY /= 2.0D;
            par1Entity.motionZ /= 2.0D;
            par1Entity.motionX -= par3 / (double)f1 * (double)f2;
            par1Entity.motionY -= par4 / (double)f1 * (double)f2;
            par1Entity.motionZ -= par5 / (double)f1 * (double)f2;

            if(par1Entity.motionY > 0.4000000059604645D)
            {
                par1Entity.motionY = 0.4000000059604645D;
            }
        }
    }
}
