package com.gugu42.rcmod.shipsys;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

public class ShipWaypointCommand implements ICommand {

	public List aliases;

	public ShipWaypointCommand() {
		this.aliases = new ArrayList();
		this.aliases.add("addRcWaypoint");
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}

	@Override
	public String getCommandName() {
		return "addRcWaypoint";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return "addRcWaypoint <Name> <PosX> <PosY> <PosZ> <Is Private ( true / false )>";
	}

	@Override
	public List getCommandAliases() {
		return aliases;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {

		if (sender instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) sender;

			if (args.length == 5) {
				String name = args[0];
				int posX = Integer.parseInt(args[1]);
				int posY = Integer.parseInt(args[2]);
				int posZ = Integer.parseInt(args[3]);
				boolean isPrivate = Boolean.parseBoolean(args[4]);

				if (!ShipSystem.isNameTaken(name)) {
					ShipSystem.addWaypoint(new ShipWaypoint(name, posX, posY,
							posZ, player.getDisplayName(), isPrivate));
				} else {
					sender.addChatMessage(new ChatComponentText("Name is already taken !"));
				}
			} else {
				sender.addChatMessage(new ChatComponentText("Correct usage : /addRcWaypoint <Name> <PosX> <PosY> <PosZ> <Is Private ( true / false )>"));
			}

		}

	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
		return true;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender p_71516_1_,
			String[] p_71516_2_) {

		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {

		return false;
	}

}
