package com.github.sSuite.sChat.command.schat;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import com.github.sSuite.sChat.Main;
import com.github.sSuite.sChat.command.AbstractCommand;
import com.github.sSuite.sLib.utility.PlayerUtility;

public class PlayerCommand extends AbstractCommand {

	public PlayerCommand(Main plugin) {
		super(plugin);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) {
		if (args.length < 3) {
			return false;
		}
		if (!hasPermission(sender)) {
			sender.sendMessage(ChatColor.RED + "You do not have sufficient permissions to do that!");
			return true;
		}

		OfflinePlayer targetPlayer = PlayerUtility.getOfflinePlayerByName(args[0]);
		if (targetPlayer == null) {
			sender.sendMessage("That player could not be found/has never joined the server!");
			return true;
		}

		Configuration configuration = getPlugin().getConfig();
		ConfigurationSection playersSection = configuration.getConfigurationSection("players");
		if (playersSection == null) {
			playersSection = configuration.createSection("players");
		}

		ConfigurationSection playerSection = playersSection
				.getConfigurationSection(targetPlayer.getUniqueId().toString());
		if (playerSection == null) {
			playerSection = playersSection.createSection(targetPlayer.getUniqueId().toString());
		}

		if (!args[1].equalsIgnoreCase("set")) {
			return false;
		}

		String value = "";
		for (int i = 3; i < args.length; i++) {
			value += args[i] + (i == args.length - 1 ? "" : " ");
		}

		switch (args[2]) {
			case "prefix":
				playerSection.set("prefix", value);
				break;
			case "suffix":
				playerSection.set("suffix", value);
				break;
			case "color":
				playerSection.set("name-color", value);
				break;
			default:
				return false;
		}

		getPlugin().saveConfig();
		sender.sendMessage(ChatColor.GREEN + "Successfully set value!");
		return true;
	}

}
