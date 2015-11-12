package com.github.sSuite.sChat.commands;

import java.util.Collection;
import java.util.HashMap;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import com.github.sSuite.sChat.Main;

public class WhisperCommand implements Listener, CommandExecutor {

	private Main plugin;
	private HashMap<Player, Player> whisperMap;

	public WhisperCommand(Main plugin) {
		this.plugin = plugin;
		whisperMap = new HashMap<Player, Player>();
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			plugin.logger.severe("Only players can use /whisper!");
		}
		Player player = (Player) sender;

		if (args.length < 1) {
			if (!whisperMap.containsKey(player)) {
				showHelp(sender);
			} else {
				player.sendMessage("Stopped whispering to " + whisperMap.remove(player).getName() + ".");
			}
		} else {
			String targetName = args[0].toLowerCase();
			Collection<? extends Player> playerList = plugin.getServer().getOnlinePlayers();
			for (Player targetPlayer : playerList) {
				// Use .contains for searching
				if (targetPlayer.getName().toLowerCase().equals(targetName)) {
					if (args.length == 1) {
						whisperMap.put(player, targetPlayer);
						player.sendMessage(ChatColor.GREEN + "Started whispering to " + ChatColor.RESET
								+ targetPlayer.getName() + ChatColor.GREEN + ".");
					} else {
						String message = "";
						for (int i = 1; i < args.length; i++) {
							message += args[i] + (i == args.length - 1 ? "" : " ");
						}
						whisperTo(player, targetPlayer, message);
					}
					return true;
				}
			}
			player.sendMessage(ChatColor.RED + "No player found matching that name!");
		}
		return true;
	}

	private void showHelp(CommandSender sender) {
		ChatColor darkred = ChatColor.DARK_RED;
		ChatColor gold = ChatColor.GOLD;

		sender.sendMessage(darkred + "/whisper");
		sender.sendMessage(darkred + "Aliases: /tell, /pm");
		sender.sendMessage(gold + "Usage: /whisper [player] [message]");
		sender.sendMessage(gold + "Privately messages the specified player the specified message.");
		sender.sendMessage(gold + "If only the player is specified, whisper mode is turned on.");
		sender.sendMessage(gold + "While whisper mode is on, anything you say will be sent");
		sender.sendMessage(gold + "to the specified player.");
		sender.sendMessage(gold + "Use \"/whisper\" to turn whisper mode off.");
	}

	private void whisperTo(Player origin, Player target, String message) {
		origin.sendMessage(
				"To " + ChatColor.AQUA + target.getName() + ChatColor.RESET + ": " + ChatColor.ITALIC + message);
		target.sendMessage(
				"From " + ChatColor.GOLD + origin.getName() + ChatColor.RESET + ": " + ChatColor.ITALIC + message);
	}

	@EventHandler
	public void playerJoin(PlayerJoinEvent event) {
		String newName = replaceColor(plugin.getConfig().getString("default-name-color"), true)
				+ event.getPlayer().getName();
		event.getPlayer().setDisplayName(newName);
		event.getPlayer().setPlayerListName(newName);
		plugin.getLogger().warning("newName: " + newName);
	}

	@EventHandler
	public void playerQuit(PlayerQuitEvent event) {
		whisperMap.remove(event.getPlayer());
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void playerChat(AsyncPlayerChatEvent event) {
		if (whisperMap.containsKey(event.getPlayer())) {
			event.setCancelled(true);

			Player origin = event.getPlayer();
			Player target = whisperMap.get(origin);
			whisperTo(origin, target, event.getMessage());
		} else {
			String chatFormat = plugin.getConfig().getString("chat-format");
			chatFormat = chatFormat.replace("$prefix", "");
			chatFormat = chatFormat.replace("$suffix", "");
			// Calls Player.getDisplayName()
			chatFormat = chatFormat.replace("$player", "%1$s" + ChatColor.RESET);
			// Calls getMessage()
			chatFormat = chatFormat.replace("$message", "%2$s");
			chatFormat = replaceColor(chatFormat, false);

			event.setMessage(ChatColor.RESET + ChatColor.WHITE.toString() + event.getMessage());
			event.setFormat(chatFormat);
		}
	}

	private String replaceColor(String string, boolean isPlain) {
		String plain = isPlain ? "" : "$";
		// IMPORTANT - DARK COLORS MUST BE FIRST
		string = string.replace(plain + "darkgreen", ChatColor.DARK_GREEN.toString());
		string = string.replace(plain + "darkred", ChatColor.DARK_RED.toString());
		string = string.replace(plain + "darkaqua", ChatColor.DARK_AQUA.toString());
		string = string.replace(plain + "darkblue", ChatColor.DARK_BLUE.toString());
		string = string.replace(plain + "darkgray", ChatColor.DARK_GRAY.toString());
		string = string.replace(plain + "darkpurple", ChatColor.DARK_PURPLE.toString());
		string = string.replace(plain + "red", ChatColor.RED.toString());
		string = string.replace(plain + "gold", ChatColor.GOLD.toString());
		string = string.replace(plain + "green", ChatColor.GREEN.toString());
		string = string.replace(plain + "aqua", ChatColor.AQUA.toString());
		string = string.replace(plain + "white", ChatColor.WHITE.toString());
		string = string.replace(plain + "blue", ChatColor.BLUE.toString());
		string = string.replace(plain + "black", ChatColor.BLACK.toString());
		string = string.replace(plain + "gray", ChatColor.GRAY.toString());
		string = string.replace(plain + "purple", ChatColor.LIGHT_PURPLE.toString());
		string = string.replace(plain + "yellow", ChatColor.YELLOW.toString());
		string = string.replace(plain + "reset", ChatColor.RESET.toString());
		// "easter egg"
		string = string.replace(plain + "magic", ChatColor.MAGIC.toString());
		return string;
	}
}
