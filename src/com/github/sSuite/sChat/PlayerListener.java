package com.github.sSuite.sChat;

import java.util.Collection;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import com.github.sSuite.sLib.utility.ChatColorUtility;

public class PlayerListener implements Listener {

	private Main plugin;

	private final String colorCharacter = "%";

	public PlayerListener(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void playerJoin(PlayerJoinEvent event) {
		onPlayerJoin(event.getPlayer());
	}

	public void onLoad() {
		Collection<? extends Player> playerList = plugin.getServer().getOnlinePlayers();

		for (Player player : playerList) {
			onPlayerJoin(player);
		}
	}

	private void onPlayerJoin(Player player) {
		String playerColor = plugin.getConfig().getString("default-name-color");

		Configuration configuration = plugin.getConfig();
		ConfigurationSection playersSection = configuration.getConfigurationSection("players");
		if (playersSection == null) {
			playersSection = configuration.createSection("players");
		}

		ConfigurationSection playerSection = playersSection.getConfigurationSection(player.getUniqueId().toString());
		if (playerSection != null) {
			if (playerSection.getString("name-color") != null) {
				playerColor = playerSection.getString("name-color");
			}
		}

		String newName = ChatColorUtility.replaceColorPlain(playerColor) + player.getName() + ChatColor.RESET;
		player.setDisplayName(newName);
		player.setPlayerListName(newName);
	}

	@EventHandler(ignoreCancelled = true)
	public void playerChat(AsyncPlayerChatEvent event) {
		String prefix = "";
		String suffix = "";
		String chatFormat = plugin.getConfig().getString("chat-format");

		Configuration configuration = plugin.getConfig();
		ConfigurationSection playersSection = configuration.getConfigurationSection("players");
		if (playersSection == null) {
			playersSection = configuration.createSection("players");
		}

		ConfigurationSection playerSection = playersSection
				.getConfigurationSection(event.getPlayer().getUniqueId().toString());
		if (playerSection != null) {
			prefix = playerSection.getString("prefix") == null ? "" : playerSection.getString("prefix");
			suffix = playerSection.getString("suffix") == null ? "" : playerSection.getString("suffix");
			if (playerSection.getString("chat-format") != null) {
				chatFormat = playerSection.getString("chat-format");
			}
		}

		chatFormat = chatFormat.replace("<prefix>", prefix);
		chatFormat = chatFormat.replace("<suffix>", suffix);
		// Calls Player.getDisplayName()
		chatFormat = chatFormat.replace("<player>", "%1$s" + ChatColor.RESET);
		// Calls getMessage()
		chatFormat = chatFormat.replace("<message>", "%2$s");
		chatFormat = ChatColorUtility.replaceColor(colorCharacter, chatFormat);

		event.setMessage(ChatColor.RESET + ChatColor.WHITE.toString() + event.getMessage());
		event.setFormat(chatFormat);
	}

}
