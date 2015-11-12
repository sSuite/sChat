package com.github.sSuite.sChat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Formatter{

	private Main plugin;

	public Formatter(Main p){
		this.plugin = p;
	}

	public String Format(Player player, String message){
		String configStr = plugin.getConfig().getString("chat-format");
		configStr = configStr.replace("$name", player.getDisplayName());
		configStr = configStr.replace("$message", message);
		// colors
		configStr = Color(configStr, false);
		return configStr;
	}

	public String Color(String string, boolean plain){
		if (plain){
			string = string.replace("darkgreen", ChatColor.DARK_GREEN.toString());
			string = string.replace("darkred", ChatColor.DARK_RED.toString());
			string = string.replace("darkaqua", ChatColor.DARK_AQUA.toString());
			string = string.replace("darkblue", ChatColor.DARK_BLUE.toString());
			string = string.replace("darkgray", ChatColor.DARK_GRAY.toString());
			string = string.replace("darkpurple", ChatColor.DARK_PURPLE.toString());
			string = string.replace("red", ChatColor.RED.toString());
			string = string.replace("gold", ChatColor.GOLD.toString());
			string = string.replace("green", ChatColor.GREEN.toString());
			string = string.replace("aqua", ChatColor.AQUA.toString());
			string = string.replace("white", ChatColor.WHITE.toString());
			string = string.replace("blue", ChatColor.BLUE.toString());
			string = string.replace("black", ChatColor.BLACK.toString());
			string = string.replace("gray", ChatColor.GRAY.toString());
			string = string.replace("purple", ChatColor.LIGHT_PURPLE.toString());
			string = string.replace("yellow", ChatColor.YELLOW.toString());
			// "easter egg"
			string = string.replace("magic", ChatColor.MAGIC.toString());
			return string;
		}else{
			/*
			 * IMPORTANT - DARK COLORS MUST BE FIRST
			 */
			string = string.replace("$darkgreen", ChatColor.DARK_GREEN.toString());
			string = string.replace("$darkred", ChatColor.DARK_RED.toString());
			string = string.replace("$darkaqua", ChatColor.DARK_AQUA.toString());
			string = string.replace("$darkblue", ChatColor.DARK_BLUE.toString());
			string = string.replace("$darkgray", ChatColor.DARK_GRAY.toString());
			string = string.replace("$darkpurple", ChatColor.DARK_PURPLE.toString());
			string = string.replace("$red", ChatColor.RED.toString());
			string = string.replace("$gold", ChatColor.GOLD.toString());
			string = string.replace("$green", ChatColor.GREEN.toString());
			string = string.replace("$aqua", ChatColor.AQUA.toString());
			string = string.replace("$white", ChatColor.WHITE.toString());
			string = string.replace("$blue", ChatColor.BLUE.toString());
			string = string.replace("$black", ChatColor.BLACK.toString());
			string = string.replace("$gray", ChatColor.GRAY.toString());
			string = string.replace("$purple", ChatColor.LIGHT_PURPLE.toString());
			string = string.replace("$yellow", ChatColor.YELLOW.toString());
			// "easter egg"
			string = string.replace("$magic", ChatColor.MAGIC.toString());
			return string;
		}
	}
}
