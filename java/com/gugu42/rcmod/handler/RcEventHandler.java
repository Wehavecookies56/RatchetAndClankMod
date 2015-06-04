package com.gugu42.rcmod.handler;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

import com.gugu42.rcmod.CommonProxy;
import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.entity.projectiles.EntityVisibombAmmo;
import com.gugu42.rcmod.entity.projectiles.EntityVisibombCamera;
import com.gugu42.rcmod.items.EnumRcWeapons;
import com.gugu42.rcmod.items.ItemAmmo;
import com.gugu42.rcmod.items.ItemBlaster;
import com.gugu42.rcmod.items.ItemRcGun;
import com.gugu42.rcmod.items.ItemRcWeap;
import com.gugu42.rcmod.items.RcItems;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RcEventHandler {

	private CommonProxy proxy;

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {

		if (event.entity instanceof EntityPlayer && ExtendedPlayerBolt.get((EntityPlayer) event.entity) == null)
			ExtendedPlayerBolt.register((EntityPlayer) event.entity);

		if (event.entity instanceof EntityPlayer && event.entity.getExtendedProperties(ExtendedPlayerBolt.EXT_PROP_NAME) == null) {
			event.entity.registerExtendedProperties(ExtendedPlayerBolt.EXT_PROP_NAME, new ExtendedPlayerBolt((EntityPlayer) event.entity));
		}
		if (event.entity instanceof EntityPlayer && event.entity.getExtendedProperties(ExtendedPropsSuckCannon.IDENTIFIER) == null) {
			event.entity.registerExtendedProperties(ExtendedPropsSuckCannon.IDENTIFIER, new ExtendedPropsSuckCannon((EntityPlayer) event.entity));
		}

		if (event.entity instanceof EntityPlayer && event.entity.getExtendedProperties(ExtendedPlayerTarget.EXT_PROP_NAME) == null) {
			event.entity.registerExtendedProperties(ExtendedPlayerTarget.EXT_PROP_NAME, new ExtendedPlayerTarget((EntityPlayer) event.entity));
		}

		if (event.entity instanceof EntityPlayer && ExtendedPlayerTooltips.get((EntityPlayer) event.entity) == null)
			ExtendedPlayerTooltips.register((EntityPlayer) event.entity);

		if (event.entity != null) {
			event.entity.getEntityData().setInteger("missilesTargeting", 0);
		}

	}

	@SubscribeEvent
	public void onLivingJumpEvent(LivingJumpEvent event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			if (player.inventory.armorItemInSlot(2) != null && player.isSneaking()) {
				if (player.inventory.armorItemInSlot(2).getItem() == RcMod.thrusterPack || player.inventory.armorItemInSlot(2).getItem() == RcMod.clankBackpack) {
					if (player.inventory.armorItemInSlot(2).getItem() == RcMod.clankBackpack)
						event.entity.motionY += 0.3D;
					else
						event.entity.motionY += 0.35D;
					event.entity.getEntityData().setBoolean("clankJumped", true);
					event.entity.getEntityData().setInteger("clankCooldown", 2);
				}
			}

			if (player.inventory.armorItemInSlot(2) != null && player.isSprinting()) {
				if (player.inventory.armorItemInSlot(2).getItem() == RcMod.thrusterPack || player.inventory.armorItemInSlot(2).getItem() == RcMod.clankBackpack) {
					double x = Math.cos(Math.toRadians(player.rotationYawHead + 90.0F)) * 0.15d;

					double z = Math.sin(Math.toRadians(player.rotationYawHead + 90.0F)) * 0.15d;

					player.motionX += x;
					player.motionZ += z;
					event.entity.getEntityData().setBoolean("clankJumped", true);
					event.entity.getEntityData().setInteger("clankCooldown", 2);

				}
			}
		}
	}

	@SubscribeEvent
	public void onLivingFallEvent(LivingFallEvent event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			if (player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(2).getItem() == RcMod.clankBackpack || player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(2).getItem() == RcMod.thrusterPack) {
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public void onLivingUpdateEvent(LivingUpdateEvent event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			if (player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(2).getItem() == RcMod.clankBackpack || player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(2).getItem() == RcMod.thrusterPack) {
				if (player.fallDistance > 1.6F) {
					event.entity.getEntityData().setBoolean("clankJumped", true);
					event.entity.getEntityData().setInteger("clankCooldown", 2);
				}
			}
		}
	}

	@SubscribeEvent
	public void onLivingDeathEvent(LivingDeathEvent event) {
		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) {
			NBTTagCompound playerData = new NBTTagCompound();
			((ExtendedPlayerBolt) (event.entity.getExtendedProperties(ExtendedPlayerBolt.EXT_PROP_NAME))).saveNBTData(playerData);
			proxy.storeEntityData(((EntityPlayer) event.entity).getDisplayName(), playerData);
			ExtendedPlayerBolt.saveProxyData((EntityPlayer) event.entity);
			ExtendedPropsSuckCannon.saveProxyData((EntityPlayer) event.entity);
		} else {

		}

		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) {
			NBTTagCompound playerData = new NBTTagCompound();
			((ExtendedPlayerTooltips) (event.entity.getExtendedProperties(ExtendedPlayerTooltips.EXT_PROP_NAME))).saveNBTData(playerData);
			proxy.storeEntityData(((EntityPlayer) event.entity).getDisplayName(), playerData);
			ExtendedPlayerTooltips.saveProxyData((EntityPlayer) event.entity);
		} else {

		}
	}

	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event) {
		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) {
			NBTTagCompound playerData = proxy.getEntityData(((EntityPlayer) event.entity).getDisplayName());
			if (playerData != null) {
				((ExtendedPlayerBolt) (event.entity.getExtendedProperties(ExtendedPlayerBolt.EXT_PROP_NAME))).loadNBTData(playerData);

				((ExtendedPropsSuckCannon) (event.entity.getExtendedProperties(ExtendedPropsSuckCannon.IDENTIFIER))).loadNBTData(playerData);
			}

			((ExtendedPlayerBolt) (event.entity.getExtendedProperties(ExtendedPlayerBolt.EXT_PROP_NAME))).sync();
			ExtendedPropsSuckCannon.get((EntityPlayer) event.entity).sync();
			ExtendedPlayerTarget.get((EntityPlayer) event.entity).sync();
		}

		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) {
			NBTTagCompound playerData = proxy.getEntityData(((EntityPlayer) event.entity).getDisplayName());
			if (playerData != null) {
				((ExtendedPlayerTooltips) (event.entity.getExtendedProperties(ExtendedPlayerTooltips.EXT_PROP_NAME))).loadNBTData(playerData);
			}

			((ExtendedPlayerTooltips) (event.entity.getExtendedProperties(ExtendedPlayerTooltips.EXT_PROP_NAME))).sync();
		}

		// EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;

		if (event.entity instanceof EntityVisibombAmmo) {
			EntityVisibombAmmo arrow = (EntityVisibombAmmo) event.entity;
			EntityPlayer arrowShooter = arrow.worldObj.getClosestPlayerToEntity(arrow, 2);
			if (arrowShooter == null)
				return;
			if (event.world.isRemote && isEntityForThisClient(arrow, arrowShooter)) {
				EntityVisibombCamera.getInstance().startCam(arrow, true);
			}

		}

		if (event.entity != null) {
			event.entity.getEntityData().setInteger("missilesTargeting", 0);
		}
	}

	@SideOnly(Side.CLIENT)
	private boolean isEntityForThisClient(EntityVisibombAmmo missile, EntityPlayer shooter) {
		if (shooter == FMLClientHandler.instance().getClientPlayerEntity()) {
			return true;
		}
		return false;
	}

	@SubscribeEvent
	public void onItemPickup(EntityItemPickupEvent event) {
		ItemStack item = event.item.getEntityItem();
		ExtendedPlayerBolt props = ExtendedPlayerBolt.get(event.entityPlayer);
		if (item.getItem() != null && item.getItem() == RcItems.bolt) {
			props.addBolt(25);
			event.entityPlayer.worldObj.playSoundAtEntity(event.entityPlayer, "rcmod:BoltCollect", 0.3f, 1.0f);
			event.item.setDead();
			event.setCanceled(true);
		}

		//ItemStack weapon = this.getItemInInventory(player.inventory, EnumRcWeapons.getItemFromID(id).getWeapon());

		if (item.getItem() != null && item.getItem() instanceof ItemAmmo) {
			ItemAmmo ammo = (ItemAmmo) item.getItem();
			ItemStack[] inv = event.entityPlayer.inventory.mainInventory;
			for (int i = 0; i < inv.length; i++) {
				if (inv[i] != null) {
					if (ammo.getGun() == inv[i].getItem()) {
						ItemRcGun weapon = (ItemRcGun) inv[i].getItem();
						if (weapon.refill(inv[i], weapon, event, ammo.getAmmount())) {
							event.entityPlayer.worldObj.playSoundAtEntity(event.entityPlayer, "rcmod:AmmoCollect", 0.3f, 1.0f);
							event.item.setDead();
							event.setCanceled(true);
						}
					}
				}
			}
			event.setCanceled(true);
		}

	}

	/*switch(p_149650_2_.nextInt(15))
		{
		case 0: return RcItems.ammoblaster;
		case 1: return RcItems.ammobombGlove;
		case 2: return RcItems.ammodecoyGlove;
		case 3: return RcItems.ammodevastator;
		case 4: return RcItems.ammodroneDevice;
		case 5: return RcItems.ammogloveOfDoom;
		case 6: return RcItems.ammomineGlove;
		case 8: return RcItems.ammopyrocitor;
		case 9: return RcItems.ammoryno;
		case 12: return RcItems.ammoteslaClaw;
		case 13: return RcItems.ammovisibombGun;
		default: return RcItems.ammoblaster;
		}
	 
	 */

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void preRenderPlayer(RenderPlayerEvent.Pre event) {
		EntityPlayer player = event.entityPlayer;
		ItemStack is = player.getCurrentEquippedItem();
		if ((is != null) && (is.getItem() instanceof ItemRcWeap)) {
			ItemRcWeap itemInHand = (ItemRcWeap) is.getItem();
			if (itemInHand.getHeldType() == 1) {
				ModelBiped modelMain = ObfuscationReflectionHelper.getPrivateValue(RenderPlayer.class, event.renderer, 1);
				ModelBiped modelArmorChestplate = ObfuscationReflectionHelper.getPrivateValue(RenderPlayer.class, event.renderer, 2);
				ModelBiped modelArmor = ObfuscationReflectionHelper.getPrivateValue(RenderPlayer.class, event.renderer, 3);
				modelMain.aimedBow = modelArmorChestplate.aimedBow = modelArmor.aimedBow = true;

			}
		}

	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void renderGameOverlay(RenderGameOverlayEvent event) {
		if (Minecraft.getMinecraft().thePlayer == null || Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem() == null)
			return;
		if (Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem().getItem() instanceof ItemRcWeap) {
			ItemRcWeap item = (ItemRcWeap) Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem().getItem();
			if (item.hasCrosshair || item.hideCrosshair) {
				if (event.type == RenderGameOverlayEvent.ElementType.CROSSHAIRS) {
					event.setCanceled(true);
					Minecraft.getMinecraft().getTextureManager().bindTexture(Gui.icons);
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void renderWorldLastEvent(RenderWorldLastEvent event) {
		final Minecraft mc = Minecraft.getMinecraft();

		ExtendedPlayerTarget props = ExtendedPlayerTarget.get(mc.thePlayer);
		if (props.hasDevastatorTarget) {
			//TODO: Render an green circle on the target.
		}

	}

}
