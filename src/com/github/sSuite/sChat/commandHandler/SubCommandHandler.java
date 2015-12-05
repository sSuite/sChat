package com.github.sSuite.sChat.commandHandler;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.github.sSuite.sChat.Main;
import com.github.sSuite.sChat.command.AbstractCommand;
import com.github.sSuite.sChat.command.schat.PlayerCommand;
import com.github.sSuite.sChat.command.schat.ReloadCommand;

public class SubCommandHandler implements CommandExecutor {

	private Main plugin;

	public SubCommandHandler(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length < 1) {
			showHelp(sender);
			return true;
		}

		String[] newArgs = new String[args.length - 1];
		for (int i = 1; i < args.length; i++) {
			newArgs[i - 1] = args[i];
		}

		AbstractCommand commandClass;
		switch (args[0].toLowerCase()) {
			case "reload":
				commandClass = new ReloadCommand(plugin);
				break;
			case "player":
				commandClass = new PlayerCommand(plugin);
				break;
			default:
				showHelp(sender);
				return true;
		}

		if (!commandClass.execute(sender, newArgs)) {
			showHelp(sender);
		}
		return true;
	}

	private void showHelp(CommandSender sender) {
		ChatColor gold = ChatColor.GOLD;

		sender.sendMessage(ChatColor.GREEN + "---------------------/schat Help---------------------");
		sender.sendMessage(gold + "/schat reload" + ChatColor.RESET + " - Reloads the configuration file");
		sender.sendMessage(gold + "/schat player <player> set <prefix|suffix|color>" + ChatColor.RESET
				+ " - Sets a setting for a player");
	}

}
