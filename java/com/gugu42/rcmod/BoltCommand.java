package com.gugu42.rcmod;

import java.util.ArrayList;
import java.util.List;

import com.gugu42.rcmod.handler.ExtendedPlayerBolt;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

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
	public String getCommandUsage(ICommandSender icommandsender) {
		return "bolt <bolts>";
	}



	@Override
	public boolean isUsernameIndex(String[] astring, int i) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "bolt";
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

			ExtendedPlayerBolt props = ExtendedPlayerBolt.get(player);
			if (props != null) {
				int amount;
				amount = Integer.parseInt(args[0]);
				if (amount > props.getMaxBolts()) {
					player.addChatMessage(new ChatComponentText("Number too high ! You entered "
							+ amount + " while the maximum is "
							+ props.getMaxBolts() + " !"));
				} else {
					player.addChatMessage(new ChatComponentText("You had " + props.getCurrentBolt()
							+ " bolts. You now have " + amount + " bolts."));
					props.setCurrentBolt(amount);
				}

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
