package com.github.sSuite.sChat.commandHandler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.github.sSuite.sChat.Main;
import com.github.sSuite.sChat.command.AbstractCommand;
import com.github.sSuite.sChat.command.schat.PlayerCommand;
import com.github.sSuite.sChat.command.schat.ReloadCommand;
import com.github.sSuite.sLib.utility.CommandHelpUtility;

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

		if (!commandClass.onExecute(sender, newArgs)) {
			showHelp(sender);
		}
		return true;
	}

	private void showHelp(CommandSender sender) {
		sender.sendMessage(CommandHelpUtility.createHeader("/schat Help", sender));
		CommandHelpUtility.sendCommand("/schat reload", "Reloads the configuration file", sender, "schat.reload");
		CommandHelpUtility.sendCommand("/schat player <player> set <prefix|suffix|color>",
				"Sets a setting for a player", sender, "schat.player");
	}

}
