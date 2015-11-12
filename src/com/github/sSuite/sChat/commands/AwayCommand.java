package com.github.sSuite.sChat.commands;

import java.util.ArrayList;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import com.github.sSuite.sChat.Main;

public class AwayCommand implements Listener, CommandExecutor {

	private Main plugin;
	private ArrayList<Player> afkList;

	public AwayCommand(Main plugin) {
		this.plugin = plugin;
		afkList = new ArrayList<Player>();
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			plugin.logger.severe("Only players can use /afk!");
		}
		Player player = (Player) sender;

		if (args.length > 0) {
			showHelp(sender);
		} else {
			toggleAway(player);
		}
		return true;
	}

	private void showHelp(CommandSender i) {
		ChatColor darkred = ChatColor.DARK_RED;
		ChatColor gold = ChatColor.GOLD;

		i.sendMessage(darkred + "/away");
		i.sendMessage(darkred + "Aliases: /afk");
		i.sendMessage(gold + "Usage: /away");
		i.sendMessage(gold + "Sets the player as away or not.");
	}

	@EventHandler
	public void playerQuit(PlayerQuitEvent event) {
		afkList.remove(event.getPlayer());
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void playerChat(AsyncPlayerChatEvent event) {
		markAway(event.getPlayer(), false);
	}

	@EventHandler
	public void playerMove(PlayerMoveEvent event) {
		markAway(event.getPlayer(), false);
	}

	private void toggleAway(Player player) {
		markAway(player, !afkList.contains(player));
	}

	private void markAway(Player player, boolean away) {
		if (away == afkList.contains(player)) {
			return;
		} else {
			if (away) {
				plugin.getServer().broadcastMessage(player.getName() + " is away.");
				player.setPlayerListName(ChatColor.RED + "[AFK] " + ChatColor.RESET + player.getName());
				afkList.add(player);
			} else {
				plugin.getServer().broadcastMessage(player.getName() + " is no longer away.");
				player.setPlayerListName(player.getName());
				afkList.remove(player);
			}
		}
	}
}
