package com.github.sSuite.sChat.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.sSuite.sChat.Main;

public abstract class AbstractCommand {

	private Main plugin;

	public AbstractCommand(Main plugin) {
		this.plugin = plugin;
	}

	public abstract boolean execute(CommandSender sender, String[] args);

	public boolean hasPermission(CommandSender sender) {
		if (sender instanceof Player) {
			return sender.isOp();
		}
		return true;
	}

	public Main getPlugin() {
		return plugin;
	}

}
