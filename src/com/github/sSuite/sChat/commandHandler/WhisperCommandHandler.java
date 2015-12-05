package com.github.sSuite.sChat.commandHandler;

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
import org.bukkit.event.player.PlayerQuitEvent;
import com.github.sSuite.sChat.Main;
import com.github.sSuite.sLib.exception.AmbiguousPlayerNameException;
import com.github.sSuite.sLib.exception.NoSuchPlayerException;
import com.github.sSuite.sLib.utility.PlayerUtility;

public class WhisperCommandHandler implements Listener, CommandExecutor {

	private Main plugin;
	private HashMap<Player, Player> whisperMap;

	public WhisperCommandHandler(Main plugin) {
		this.plugin = plugin;
		whisperMap = new HashMap<Player, Player>();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			plugin.logger.severe("Only players can use /" + label + "!");
		}
		Player player = (Player) sender;

		if (args.length < 1) {
			if (!whisperMap.containsKey(player)) {
				showHelp(sender);
			} else {
				player.sendMessage(ChatColor.GREEN + "Stopped whispering to " + ChatColor.RESET
						+ whisperMap.remove(player).getName() + ChatColor.GREEN + ".");
			}
		} else {
			try {
				Player targetPlayer = PlayerUtility.getOnlinePlayerByName(args[0].toLowerCase());

				if (args.length == 1) {
					whisperMap.put(player, targetPlayer);
					player.sendMessage(ChatColor.GREEN + "Started whispering to " + ChatColor.RESET
							+ targetPlayer.getName() + ChatColor.GREEN + ".");
					player.sendMessage("Use /" + label + " again to stop whispering.");
				} else {
					String message = "";
					for (int i = 1; i < args.length; i++) {
						message += args[i] + (i == args.length - 1 ? "" : " ");
					}
					whisperTo(player, targetPlayer, message);
				}
			} catch (AmbiguousPlayerNameException e) {
				player.sendMessage(ChatColor.RED + "That player name is ambiguous!");
			} catch (NoSuchPlayerException e) {
				player.sendMessage(ChatColor.RED + "No player found matching that name!");
			}
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
	public void playerQuit(PlayerQuitEvent event) {
		whisperMap.remove(event.getPlayer());
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void playerChat(AsyncPlayerChatEvent event) {
		if (whisperMap.containsKey(event.getPlayer())) {
			event.setCancelled(true);

			Player origin = event.getPlayer();
			Player target = whisperMap.get(origin);
			whisperTo(origin, target, event.getMessage());
		}
	}
}
