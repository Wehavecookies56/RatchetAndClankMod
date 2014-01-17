package com.gugu42.rcmod;

import java.util.ArrayList;
import java.util.List;

import com.gugu42.rcmod.handler.ExtendedPlayerBolt;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class BoltCommand implements ICommand {

	private List aliases;

	public BoltCommand() {
		this.aliases = new ArrayList();
		this.aliases.add("bolt");
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCommandName() {
		return "bolt";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "bolt <bolts>";
	}

	@Override
	public List getCommandAliases() {
		return this.aliases;
	}

	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring) {
		if (icommandsender instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) icommandsender;

			ExtendedPlayerBolt props = ExtendedPlayerBolt.get(player);
			if (props != null) {
				int amount;
				amount = Integer.parseInt(astring[0]);
				if (amount > props.getMaxBolts()) {
					player.addChatMessage("Number too high ! You entered "
							+ amount + " while the maximum is "
							+ props.getMaxBolts() + " !");
				} else {
					player.addChatMessage("You had " + props.getCurrentBolt() + " bolts. You now have " + amount + " bolts.");
					props.setCurrentBolt(amount);
				}
			}
		}
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender icommandsender) {
		return true;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender icommandsender,
			String[] astring) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] astring, int i) {
		// TODO Auto-generated method stub
		return false;
	}

}
