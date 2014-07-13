package com.gugu42.rcmod.handler;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Stack;

import com.gugu42.rcmod.CommonProxy;
import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.network.packets.PacketSuckCannonData;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import org.json.JSONArray;

public class ExtendedPropsSuckCannon implements IExtendedEntityProperties
{

	public static final String IDENTIFIER = "SuckCannonProperties";
	private Stack<String> nbtStack;
	private EntityPlayer owner;

	public ExtendedPropsSuckCannon(EntityPlayer owner)
	{
		nbtStack = new Stack<String>();
		this.owner = owner;
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound)
	{
		NBTTagCompound nbtData = new NBTTagCompound();
		
		String jsonData = getStackAsJsonArrayString();
		nbtData.setString("stack", jsonData);
		
		compound.setTag(IDENTIFIER, nbtData);
	}

	private String getStackAsJsonArrayString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		ArrayList<String> list = getStackAsList();
		for(int i =0;i<list.size();i++)
		{
			String s = list.get(i);
			s = s.replace("\"", "\\\"");
			builder.append("\""+s+"\"");
			if(i != list.size()-1)
				builder.append(",");
		}
		return builder.append("]").toString();
	}

	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
		NBTTagCompound data = compound.getCompoundTag(IDENTIFIER);
		createStackFromJsonArrayString(data.getString("stack"));
	}

	private void createStackFromJsonArrayString(String string)
	{
		nbtStack.clear();
		if(string == null || string.equals(""))
			return;
		JSONArray compound = new JSONArray(string);
		for(int i = 0;i<compound.length();i++)
		{
			String s = compound.getString(i);
			nbtStack.push(s);
		}
	}

	@Override
	public void init(Entity entity, World world)
	{

	}

	public static ExtendedPropsSuckCannon get(EntityPlayer owner)
	{
		if(owner.getExtendedProperties(IDENTIFIER) == null)
			owner.registerExtendedProperties(IDENTIFIER, new ExtendedPropsSuckCannon(owner));
		return (ExtendedPropsSuckCannon)owner.getExtendedProperties(IDENTIFIER);
	}

	public void pushToStack(EntityLiving entity)
	{
		NBTTagCompound nbtData = new NBTTagCompound();
		entity.writeEntityToNBT(nbtData);
		nbtData.setBoolean("hadCustomTagName", entity.hasCustomNameTag());
		nbtData.setString("id", EntityList.getStringFromID(EntityList.getEntityID(entity)));
		nbtStack.push(nbtData.toString());
	}
	
	public void setStack(Stack<String> stack)
	{
		this.nbtStack = stack;
	}

	public Stack<String> getStack()
	{
		return nbtStack;
	}
	
	public void sync()
	{
		ArrayList<String> list = getStackAsList();
		
		if(!owner.worldObj.isRemote)
		{
			RcMod.rcModPacketHandler.sendTo(new PacketSuckCannonData(list), (EntityPlayerMP)owner);
		}
	}
	
	public ArrayList<String> getStackAsList()
	{
		Enumeration<String> elements = nbtStack.elements();
		ArrayList<String> list = new ArrayList<String>();
		while(elements.hasMoreElements())
			list.add(elements.nextElement());
		return list;
	}

	public static void saveProxyData(EntityPlayer player) {
		ExtendedPropsSuckCannon playerData = ExtendedPropsSuckCannon.get(player);
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
		ExtendedPropsSuckCannon playerData = ExtendedPropsSuckCannon.get(player);
		NBTTagCompound savedData = CommonProxy
				.getEntityData(getSaveKey(player));

		if (savedData != null) {
			playerData.loadNBTData(savedData);
		}
		// note we renamed 'syncExtendedProperties' to 'syncProperties' because
		// yay, it's shorter
		playerData.sync();
	}

	private static String getSaveKey(EntityPlayer player) {
		return player.getDisplayName() + ":" + IDENTIFIER;
	}

	public String popStack()
	{
		if(nbtStack.isEmpty())
			return null;
		return nbtStack.pop();
	}
}
