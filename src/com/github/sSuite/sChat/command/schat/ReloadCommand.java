package com.github.sSuite.sChat.command.schat;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import com.github.sSuite.sChat.Main;
import com.github.sSuite.sChat.command.AbstractCommand;

public class ReloadCommand extends AbstractCommand {

	public ReloadCommand(Main plugin) {
		super(plugin);
	}

	@Override
	public boolean onExecute(CommandSender sender, String[] args) {
		if (args.length != 0) {
			return false;
		} else {
			if (!getPlugin().reloadCustomConfig()) {
				sender.sendMessage(ChatColor.RED
						+ "Plugin configuration failed to reload! Check the console for the stack trace!");
				return true;
			}

			sender.sendMessage(ChatColor.GREEN + "Plugin configuration reloaded!");
		}
		return true;
	}

}
