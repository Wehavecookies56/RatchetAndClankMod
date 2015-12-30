package com.gugu42.rcmod;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

import com.gugu42.rcmod.handler.ExtendedPlayerBolt;
import com.gugu42.rcmod.handler.ExtendedPlayerTooltips;

public class ResetTooltipsCommand implements ICommand {
	private List aliases;

	public ResetTooltipsCommand() {
		this.aliases = new ArrayList();
		this.aliases.add("resetTooltips");
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCommandName() {
		return "resetTooltips";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "resetTooltips";
	}

	@Override
	public List getCommandAliases() {
		return this.aliases;
	}

	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring) {
		if (icommandsender instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) icommandsender;

			ExtendedPlayerTooltips props = ExtendedPlayerTooltips.get(player);
			if (props != null) {
				props.resetAllTips();
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
