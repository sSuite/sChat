package com.github.sSuite.sChat.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.sSuite.sChat.Main;

public abstract class AbstractCommand {

	private static final String PERMISSION_ROOT = "schat.";

	private Main plugin;
	private String permissionNode;

	public AbstractCommand(Main plugin) {
		this.plugin = plugin;
	}

	public AbstractCommand(Main plugin, String permissionNode) {
		this.plugin = plugin;
		this.permissionNode = permissionNode;
	}

	public boolean execute(CommandSender sender, String[] args) {
		if (!hasPermission(sender)) {
			sender.sendMessage(ChatColor.RED + "You do not have sufficient permissions to do that!");
			return true;
		}

		return onExecute(sender, args);
	}

	public abstract boolean onExecute(CommandSender sender, String[] args);

	public boolean hasPermission(CommandSender sender) {
		if (sender instanceof Player) {
			return permissionNode != null && sender.hasPermission(PERMISSION_ROOT + permissionNode);
		}
		return true;
	}

	public Main getPlugin() {
		return plugin;
	}

}
