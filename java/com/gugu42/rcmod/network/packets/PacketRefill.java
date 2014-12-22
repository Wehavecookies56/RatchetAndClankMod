package com.gugu42.rcmod.network.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;

import com.gugu42.rcmod.ContainerVendor;
import com.gugu42.rcmod.handler.ExtendedPlayerBolt;
import com.gugu42.rcmod.items.EnumRcWeapons;
import com.gugu42.rcmod.items.ItemRcWeap;
import com.gugu42.rcmod.tileentity.TileEntityVendor;
import com.gugu42.rcmod.utils.ffmtutils.AbstractPacket;

public class PacketRefill extends AbstractPacket {

	private int id;

	public PacketRefill() {

	}

	public PacketRefill(int id) {
		this.id = id;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
//		try {
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			DataOutputStream outputStream = new DataOutputStream(baos);
//
//			if (this.is == null) {
//				outputStream.writeUTF("null");
//			} else {
//				NBTTagCompound nbt = new NBTTagCompound();
//				this.is.writeToNBT(nbt);
//				outputStream.writeUTF(nbt.toString());
//			}
//
//			outputStream.flush();
//			baos.flush();
//			outputStream.close();
//			baos.close();
//			byte[] data = baos.toByteArray();
//			buffer.writeBytes(data);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		buffer.writeInt(id);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
//		try {
//			byte[] readBytes = new byte[buffer.readableBytes()];
//			buffer.readBytes(readBytes);
//			ByteArrayInputStream bais = new ByteArrayInputStream(readBytes);
//			DataInputStream inputStream = new DataInputStream(bais);
//
//			String str = inputStream.readUTF();
//			if (!str.equals("null"))
//				this.is = ItemStack
//						.loadItemStackFromNBT((NBTTagCompound) JsonToNBT
//								.func_150315_a(str));
//			inputStream.close();
//			bais.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		this.id = buffer.readInt();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleServerSide(EntityPlayer player) {
//		ContainerVendor containervendor = (ContainerVendor) player.openContainer;
//		TileEntityVendor tev = containervendor.getVendor();
		ExtendedPlayerBolt props = ExtendedPlayerBolt.get(player);

//		if (is != null
//				&& is.getItem() instanceof ItemRcWeap) {
//			ItemRcWeap item = (ItemRcWeap) is.getItem();
//			props.consumeBolts(is.getItemDamage()
//					* item.getPrice());
//			is.setItemDamage(0);
//			player.addChatMessage(new ChatComponentText("Your weapon has been refilled !"));
//		} else {
//			player.addChatMessage(new ChatComponentText("Error : Couldn't refill !"));
//		}
		
		ItemStack item = this.getItemInInventory(player.inventory, EnumRcWeapons.getItemFromID(id).getWeapon());
		if(item != null){
			ItemRcWeap itemWeap = (ItemRcWeap)item.getItem();
			if(props.consumeBolts(item.getItemDamage() * itemWeap.getPrice())){
				item.setItemDamage(0);
				player.addChatMessage(new ChatComponentText("Your weapon has been refilled !"));
				player.worldObj.playSoundAtEntity(player, "rcmod:vendor.buy", 1.0f, 1.0f);
			} else {
				player.addChatMessage(new ChatComponentText("You don't seem to have enough bolts to pay for the ammo !"));
				player.worldObj.playSoundAtEntity(player, "rcmod:vendor.maxAmmo", 1.0f, 1.0f);
			}
		} else {
			player.addChatMessage(new ChatComponentText("There was an error when trying to refill your weapon."));
		}
	}
	
	private int getSlotContainingItem(InventoryPlayer inventory, Item item){
		for (int i = 0; i < inventory.mainInventory.length; ++i)
        {
            if (inventory.mainInventory[i] != null && inventory.mainInventory[i].getItem() == item)
            {
                return i;
            }
        }

        return -1;
	}
	
	public ItemStack getItemInInventory(InventoryPlayer inventory, Item p_146026_1_)
    {
        int i = this.getSlotContainingItem(inventory, p_146026_1_);

        if (i < 0)
        {
            return null;
        }
        else
        {
            return inventory.mainInventory[i];
        }
    }

}
