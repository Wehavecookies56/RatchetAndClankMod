package com.gugu42.rcmod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.StatCollector;

import com.gugu42.rcmod.entity.projectiles.EntityBlasterAmmo;
import com.gugu42.rcmod.entity.projectiles.EntityRYNOAmmo;

public class DamageSourceRcWeap extends EntityDamageSource {
	
	//public static DamageSource rcweap;
	private Entity indirectEntity;

    protected DamageSourceRcWeap(String par1Str, Entity entity1, Entity entity2)
    {
            super(par1Str, entity1);
            this.indirectEntity = entity2;
    }
    
    public Entity getSourceOfDamage()
    {
        return this.damageSourceEntity;
    }

    public Entity getEntity()
    {
        return this.indirectEntity;
    }
    
    public static DamageSource causeRYNODamage(EntityRYNOAmmo par0EntityArrow, Entity par1Entity)
    {
        return (new EntityDamageSourceIndirect("rcweap", par0EntityArrow, par1Entity)).setProjectile();
    }
	
    public static DamageSource causeBlasterDamage(EntityBlasterAmmo par0EntityArrow, Entity par1Entity)
    {
        return (new EntityDamageSourceIndirect("rcweap", par0EntityArrow, par1Entity)).setProjectile();
    }
    
//    public ChatMessageComponent getDeathMessage(EntityLivingBase par1EntityLivingBase)
//    {
//        String s = this.indirectEntity == null ? this.damageSourceEntity.get : this.indirectEntity.getTranslatedEntityName();
//        ItemStack itemstack = this.indirectEntity instanceof EntityLivingBase ? ((EntityLivingBase)this.indirectEntity).getHeldItem() : null;
//        String s1 = "death.attack." + this.damageType;
//        String s2 = s1 + ".item";
//        return itemstack != null && itemstack.hasDisplayName() && StatCollector.func_94522_b(s2) ? ChatMessageComponent.createFromTranslationWithSubstitutions(s2, new Object[] {par1EntityLivingBase.getTranslatedEntityName(), s, itemstack.getDisplayName()}): ChatMessageComponent.createFromTranslationWithSubstitutions(s1, new Object[] {par1EntityLivingBase.getTranslatedEntityName(), s});
//    }

}
