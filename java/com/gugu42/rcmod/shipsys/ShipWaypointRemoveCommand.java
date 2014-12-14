package com.gugu42.rcmod.shipsys;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

public class ShipWaypointRemoveCommand implements ICommand {

	public List aliases;

	public ShipWaypointRemoveCommand() {
		this.aliases = new ArrayList();
		this.aliases.add("removeRcWaypoint");
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}

//	@Override
//	public String getCommandName() {
//		return "removeRcWaypoint";
//	}
//
	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return "removeRcWaypoint <Name>";
	}
//
//	@Override
//	public List getCommandAliases() {
//		return aliases;
//	}
//
//	@Override
//	public void processCommand(ICommandSender sender, String[] args) {
//
//		if (sender instanceof EntityPlayer) {
//			EntityPlayer player = (EntityPlayer) sender;
//
//			if (args.length == 1) {
//				String name = args[0];
//				if (ShipSystem.isNameTaken(name)) {
//					ShipSystem.removeWaypoint(name);
//					sender.addChatMessage(new ChatComponentText("This waypoint was successfully removed !."));
//				} else {
//					sender.addChatMessage(new ChatComponentText("This waypoint does not exist."));
//				}
//			} else {
//				sender.addChatMessage(new ChatComponentText("Correct usage : /removeRcWaypoint <Name>"));
//			}
//		}
//	}
//
//	@Override
//	public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
//		return true;
//	}
//
//	@Override
//	public List addTabCompletionOptions(ICommandSender p_71516_1_,
//			String[] p_71516_2_) {
//
//		return null;
//	}

	@Override
	public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {

		return false;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "removeRcWaypoint";
	}

	@Override
	public List getAliases() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void execute(ICommandSender sender, String[] args)
			throws CommandException {
		if (sender instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) sender;

			if (args.length == 1) {
				String name = args[0];
				if (ShipSystem.isNameTaken(name)) {
					ShipSystem.removeWaypoint(name);
					sender.addChatMessage(new ChatComponentText("This waypoint was successfully removed !."));
				} else {
					sender.addChatMessage(new ChatComponentText("This waypoint does not exist."));
				}
			} else {
				sender.addChatMessage(new ChatComponentText("Correct usage : /removeRcWaypoint <Name>"));
			}
		}
		
	}

	@Override
	public boolean canCommandSenderUse(ICommandSender sender) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] args,
			BlockPos pos) {
		// TODO Auto-generated method stub
		return null;
	}

}
