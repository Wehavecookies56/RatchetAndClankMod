package com.gugu42.rcmod.handler;

import ibxm.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import com.gugu42.rcmod.CommonProxy;
import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.network.packets.PacketBolts;

public class ExtendedPlayerBolt implements IExtendedEntityProperties {

	public final static String EXT_PROP_NAME = "ExtendedPlayerBolt";

	private final EntityPlayer player;

	public int currentBolt, maxBolts;

	public ExtendedPlayerBolt(EntityPlayer player) {
		this.player = player;
		this.currentBolt = 0;
		this.maxBolts = 999999999;
	}

	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(ExtendedPlayerBolt.EXT_PROP_NAME,
				new ExtendedPlayerBolt(player));
	}

	public static final ExtendedPlayerBolt get(EntityPlayer player) {
		return (ExtendedPlayerBolt) player.getExtendedProperties(EXT_PROP_NAME);
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {

		NBTTagCompound properties = new NBTTagCompound();

		properties.setInteger("CurrentBolt", this.currentBolt);
		properties.setInteger("MaxBolts", this.maxBolts);

		compound.setTag(EXT_PROP_NAME, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = (NBTTagCompound) compound
				.getTag(EXT_PROP_NAME);
		this.currentBolt = properties.getInteger("CurrentBolt");
		this.maxBolts = properties.getInteger("MaxBolts");
	}

	@Override
	public void init(Entity entity, World world) {
	}

	public final void sync() {

		PacketBolts packetBolts = new PacketBolts(this.maxBolts, this.currentBolt);
		RcMod.rcModPacketHandler.sendToServer(packetBolts);
		

		if (!player.worldObj.isRemote) {
			EntityPlayerMP player1 = (EntityPlayerMP) player;
			RcMod.rcModPacketHandler.sendTo(packetBolts, player1);
		}
		
	
	}

	private static String getSaveKey(EntityPlayer player) {
		return player.getDisplayName() + ":" + EXT_PROP_NAME;
	}

	/**
	 * Does everything I did in onLivingDeathEvent and it's static, so you now
	 * only need to use the following in the above event:
	 * ExtendedPlayer.saveProxyData((EntityPlayer) event.entity));
	 */
	public static void saveProxyData(EntityPlayer player) {
		ExtendedPlayerBolt playerData = ExtendedPlayerBolt.get(player);
		NBTTagCompound savedData = new NBTTagCompound();

		playerData.saveNBTData(savedData);
		// Note that we made the CommonProxy method storeEntityData static,
		// so now we don't need an instance of CommonProxy to use it! Great!
		CommonProxy.storeEntityData(getSaveKey(player), savedData);
	}

	/**
	 * This cleans up the onEntityJoinWorld event by replacing most of the code
	 * with a single line: ExtendedPlayer.loadProxyData((EntityPlayer)
	 * event.entity));
	 */
	public static void loadProxyData(EntityPlayer player) {
		ExtendedPlayerBolt playerData = ExtendedPlayerBolt.get(player);
		NBTTagCompound savedData = CommonProxy
				.getEntityData(getSaveKey(player));

		if (savedData != null) {
			playerData.loadNBTData(savedData);
		}
		// note we renamed 'syncExtendedProperties' to 'syncProperties' because
		// yay, it's shorter
		playerData.sync();
	}

	public boolean consumeBolts(int amount) {
		boolean sufficient = amount <= this.currentBolt;

		if (sufficient) {
			this.currentBolt -= amount;
			this.sync();
		} else {
			return false;
		}

		return sufficient;
	}

	public void addBolt(int amount) {
		this.currentBolt += amount;
		this.sync();
	}

	public void giveMaxBolts() {
		this.currentBolt = this.maxBolts;
		this.sync();
	}

	public int getCurrentBolt() {
		return currentBolt;
	}

	public void setCurrentBolt(int currentBolt) {
		this.currentBolt = currentBolt;
		this.sync();
	}

	public int getMaxBolts() {
		return maxBolts;
	}

	public void setMaxBolts(int maxBolts) {
		this.maxBolts = maxBolts;
		this.sync();
	}

}
