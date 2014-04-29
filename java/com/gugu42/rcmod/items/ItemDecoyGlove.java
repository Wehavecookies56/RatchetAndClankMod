package com.gugu42.rcmod.items;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.entity.projectiles.EntityDecoyGloveAmmo;

public class ItemDecoyGlove extends ItemRcWeap{

    private int ammo;
    private int cooldown;
    
    public ItemDecoyGlove() {
        super();
        this.ammo = 20;
        this.maxAmmo = 20;
        this.weaponName = "bombGlove";
        this.hasAmmoImage = true;
        this.ammoPrice = 10;
        this.cooldown = 20;
        this.setMaxDamage(maxAmmo);
        this.maxStackSize = 1;
        this.setCreativeTab(RcMod.rcTab);
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
            EntityPlayer par3EntityPlayer) {
        super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
        if (maxAmmo - par1ItemStack.getItemDamage() > 0) {
            if (!par2World.isRemote) {
                if (cooldown <= 0) {
                    EntityDecoyGloveAmmo decoy = new EntityDecoyGloveAmmo(
                            par2World, par3EntityPlayer, par3EntityPlayer.rotationYawHead);
                    par2World.spawnEntityInWorld(decoy);
                    par1ItemStack.damageItem(1, par3EntityPlayer);
                    cooldown = 20;
                    par3EntityPlayer.swingItem();
                }
            }
        }
        return par1ItemStack;
    }

    public boolean canHarvestBlock(Block par1Block) {
        return false;
    }

    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
        return 0.0f;
    }

    public void onUpdate(ItemStack stack, World w, Entity ent, int i,
            boolean flag) {
        if (cooldown > 0) {
            cooldown--;
        }

    }

    public void onPlayerStoppedUsing(ItemStack stack, World w,
            EntityPlayer player, int i) {
    }
    
}