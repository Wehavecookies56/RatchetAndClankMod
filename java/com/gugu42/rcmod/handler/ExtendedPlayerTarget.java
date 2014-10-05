package com.gugu42.rcmod.handler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import com.gugu42.rcmod.CommonProxy;
import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.network.packets.PacketTarget;

public class ExtendedPlayerTarget implements IExtendedEntityProperties {

	public final static String EXT_PROP_NAME = "ExtendedPlayerTarget";

	private final EntityPlayer player;
	
	public boolean hasDevastatorTarget;
	public double devastatorTargetPosX, devastatorTargetPosY, devastatorTargetPosZ;


	public ExtendedPlayerTarget(EntityPlayer player) {
		this.player = player;
		
	}

	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(ExtendedPlayerTarget.EXT_PROP_NAME,
				new ExtendedPlayerTarget(player));
	}

	public static final ExtendedPlayerTarget get(EntityPlayer player) {
		return (ExtendedPlayerTarget) player.getExtendedProperties(EXT_PROP_NAME);
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {

		NBTTagCompound properties = new NBTTagCompound();
		//We don't want to save the target, since it must be dynamic

		compound.setTag(EXT_PROP_NAME, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = (NBTTagCompound) compound
				.getTag(EXT_PROP_NAME);
		//We don't want to load anything, since we don't save anything.
	}

	@Override
	public void init(Entity entity, World world) {
		
	}

	public final void sync() {

		PacketTarget packetTarget = new PacketTarget(this.hasDevastatorTarget, this.devastatorTargetPosX, this.devastatorTargetPosY, this.devastatorTargetPosZ);
		RcMod.rcModPacketHandler.sendToServer(packetTarget);
		

		if (!player.worldObj.isRemote) {
			EntityPlayerMP player1 = (EntityPlayerMP) player;
			RcMod.rcModPacketHandler.sendTo(packetTarget, player1);
		}
		
	
	}

	private static String getSaveKey(EntityPlayer player) {
		return player.getDisplayName() + ":" + EXT_PROP_NAME;
	}

	public static void saveProxyData(EntityPlayer player) {
		ExtendedPlayerTarget playerData = ExtendedPlayerTarget.get(player);
		NBTTagCompound savedData = new NBTTagCompound();

		playerData.saveNBTData(savedData);
		CommonProxy.storeEntityData(getSaveKey(player), savedData);
	}

	public static void loadProxyData(EntityPlayer player) {
		ExtendedPlayerTarget playerData = ExtendedPlayerTarget.get(player);
		NBTTagCompound savedData = CommonProxy
				.getEntityData(getSaveKey(player));

		if (savedData != null) {
			playerData.loadNBTData(savedData);
		}

		playerData.sync();
	}
	
	public void setDevastatorTarget(Entity target){
		
		if(target != null && !target.isDead){
			this.hasDevastatorTarget = true;
			this.devastatorTargetPosX = target.posX;
			this.devastatorTargetPosY = target.posY;
			this.devastatorTargetPosZ = target.posZ;
//			this.sync();
		}
		
	}
	
	public void removeDevastatorTarget(){
		this.hasDevastatorTarget = false;
		this.devastatorTargetPosX = 0;
		this.devastatorTargetPosY = 0;
		this.devastatorTargetPosZ = 0;
//		this.sync();
	}
}
