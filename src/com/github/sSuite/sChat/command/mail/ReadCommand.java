package com.github.sSuite.sChat.command.mail;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import com.github.sSuite.sChat.Main;
import com.github.sSuite.sChat.command.AbstractCommand;

public class ReadCommand extends AbstractCommand {

	public ReadCommand(Main plugin) {
		super(plugin);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) {
		if (args.length != 0) {
			return false;
		} else {
			if (!hasPermission(sender)) {
				sender.sendMessage(ChatColor.RED + "You do not have sufficient permissions to do that!");
				return true;
			}

			getPlugin().reloadCustomConfig();
			sender.sendMessage(ChatColor.GREEN + "Plugin configuration reloaded!");
		}
		return true;
	}

}
