package com.github.sSuite.sChat.command.mail;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import com.github.sSuite.sChat.Main;
import com.github.sSuite.sChat.command.AbstractCommand;

public class ListCommand extends AbstractCommand {

	public ListCommand(Main plugin) {
		super(plugin);
	}

	@Override
	public boolean onExecute(CommandSender sender, String[] args) {
		if (args.length != 0) {
			return false;
		} else {
			getPlugin().reloadCustomConfig();

			sender.sendMessage(ChatColor.GREEN + "Plugin configuration reloaded!");
		}
		return true;
	}

}
