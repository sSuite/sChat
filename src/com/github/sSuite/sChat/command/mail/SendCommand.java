package com.github.sSuite.sChat.command.mail;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import com.github.sSuite.sChat.Main;
import com.github.sSuite.sChat.command.AbstractCommand;
import com.github.sSuite.sLib.exception.AmbiguousPlayerNameException;
import com.github.sSuite.sLib.exception.NoSuchPlayerException;
import com.github.sSuite.sLib.utility.PlayerUtility;

public class SendCommand extends AbstractCommand {

	public SendCommand(Main plugin) {
		super(plugin);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) {
		if (args.length < 2) {
			return false;
		} else {
			Player target;

			// Wat? This isnt needed. Mail is mostly for offline players.
			try {
				target = PlayerUtility.getOnlinePlayerByName(args[0].toLowerCase());
			} catch (AmbiguousPlayerNameException e) {
				sender.sendMessage(ChatColor.RED + "That player name is ambiguous!");
				return true;
			} catch (NoSuchPlayerException e) {
				sender.sendMessage(ChatColor.RED + "That player could not be found!");
				return true;
			}

			Configuration configuration = getPlugin().getMailHandler().getConfig();
			ConfigurationSection warpSection = configuration.getConfigurationSection(target.getName());
			if (warpSection == null) {
				warpSection = configuration.createSection(args[0]);
			} else {
				sender.sendMessage(
						ChatColor.RED + "The warp " + ChatColor.RESET + args[0] + ChatColor.RED + " already exists!");
				return true;
			}

			// warpSection.set("world", world);
			// warpSection.set("x", x);
			// warpSection.set("y", y);
			// warpSection.set("z", z);

			getPlugin().getMailHandler().save();
		}
		return true;
	}

}
