package com.gugu42.rcmod.handler;

import java.util.ArrayList;
import java.util.List;

import com.gugu42.rcmod.CommonProxy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPlayerTooltips implements IExtendedEntityProperties {

	public final static String EXT_PROP_NAME = "ExtPropTooltip";

	private final EntityPlayer player;
	private NBTTagCompound     tagCompound;
	private boolean            blasterTip;
	private boolean            bombGloveTip;

	private List<String>       seenTips = new ArrayList<String>();

	public ExtendedPlayerTooltips(EntityPlayer player) {
		this.player = player;
		this.blasterTip = false;
	}

	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(ExtendedPlayerTooltips.EXT_PROP_NAME, new ExtendedPlayerTooltips(player));
	}

	public static final ExtendedPlayerTooltips get(EntityPlayer player) {
		return (ExtendedPlayerTooltips) player.getExtendedProperties(EXT_PROP_NAME);
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = new NBTTagCompound();

		NBTTagList list = new NBTTagList();
		for (String s : seenTips) {
			NBTTagString string = new NBTTagString(s);
			list.appendTag(string);
		}
		//		properties.setBoolean("blasterTip", this.blasterTip);
		//		properties.setBoolean("bombGloveTip", this.bombGloveTip);
		properties.setTag("seenTips", list);

		compound.setTag(EXT_PROP_NAME, properties);
	}
 
	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
		this.tagCompound = properties;
		//		this.blasterTip = properties.getBoolean("blasterTip");
		//		this.bombGloveTip = properties.getBoolean("bombGloveTip");
		this.seenTips = (List<String>) properties.getTag("seenTips");
	}

	public static void saveProxyData(EntityPlayer player) {
		ExtendedPlayerTooltips playerData = ExtendedPlayerTooltips.get(player);
		NBTTagCompound savedData = new NBTTagCompound();

		playerData.saveNBTData(savedData);
		CommonProxy.storeEntityData(getSaveKey(player), savedData);
	}

	public static void loadProxyData(EntityPlayer player) {
		ExtendedPlayerTooltips playerData = ExtendedPlayerTooltips.get(player);
		NBTTagCompound savedData = CommonProxy.getEntityData(getSaveKey(player));

		if (savedData != null) {
			playerData.loadNBTData(savedData);
		}
		playerData.sync();
	}

	private static String getSaveKey(EntityPlayer player) {
		return player.getDisplayName() + ":" + EXT_PROP_NAME;
	}

	public final void sync() {
		//Doesn't need to be synced.
	}

	@Override
	public void init(Entity entity, World world) {
		// TODO Auto-generated method stub

	}

	public void setTipSeen(String name) {
		if (name != null) {
			this.seenTips.add(new String(name));
		}
	}

	public boolean hasSeenTip(String name) {
		if (name != null) {
			if (this
					.seenTips
					.contains(new String(name)))
				return true;
			else
				return false;
		}
		return true;
	}
	
	public void resetAllTips(){
		this.seenTips.clear();
	}

}
