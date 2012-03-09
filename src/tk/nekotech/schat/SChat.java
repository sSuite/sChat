package tk.nekotech.schat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SChat extends JavaPlugin {
	
	private Listen listener = new Listen(this);
	
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(listener, this);
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}
	
	public void onDisable() {
		
	}
	
	public String getFormattedMessage(Player player, String message) {
		String configStr = this.getConfig().getString("chat-format");
		configStr = configStr.replace("*name", player.getDisplayName());
		configStr = configStr.replace("*message", message);
		
		//colors
		configStr = this.replaceWithChatColor(configStr);
		
		/*
		configStr = configStr.replace("*red", ChatColor.RED.toString());
		configStr = configStr.replace("*gold", ChatColor.GOLD.toString());
		configStr = configStr.replace("*green", ChatColor.GREEN.toString());
		configStr = configStr.replace("*darkgreen", ChatColor.DARK_GREEN.toString());
		configStr = configStr.replace("*darkred", ChatColor.DARK_RED.toString());
		configStr = configStr.replace("*aqua", ChatColor.AQUA.toString());
		configStr = configStr.replace("*darkaqua", ChatColor.DARK_AQUA.toString());
		configStr = configStr.replace("*white", ChatColor.WHITE.toString());
		configStr = configStr.replace("*blue", ChatColor.BLUE.toString());
		configStr = configStr.replace("*darkblue", ChatColor.DARK_BLUE.toString());
		configStr = configStr.replace("*black", ChatColor.BLACK.toString());
		configStr = configStr.replace("*darkgray", ChatColor.DARK_GRAY.toString());
		configStr = configStr.replace("*gray", ChatColor.GRAY.toString());
		configStr = configStr.replace("*darkpurple", ChatColor.DARK_PURPLE.toString());
		configStr = configStr.replace("*purple", ChatColor.LIGHT_PURPLE.toString());
		configStr = configStr.replace("*yellow", ChatColor.YELLOW.toString());
		*/
		
		//"easter egg"
		configStr = configStr.replace("*magic", ChatColor.MAGIC.toString());
		
		return configStr;
	}
	
	public String replaceWithChatColor(String toFix) {
		toFix = toFix.replace("*red", ChatColor.RED.toString());
		toFix = toFix.replace("*gold", ChatColor.GOLD.toString());
		toFix = toFix.replace("*green", ChatColor.GREEN.toString());
		toFix = toFix.replace("*darkgreen", ChatColor.DARK_GREEN.toString());
		toFix = toFix.replace("*darkred", ChatColor.DARK_RED.toString());
		toFix = toFix.replace("*aqua", ChatColor.AQUA.toString());
		toFix = toFix.replace("*darkaqua", ChatColor.DARK_AQUA.toString());
		toFix = toFix.replace("*white", ChatColor.WHITE.toString());
		toFix = toFix.replace("*blue", ChatColor.BLUE.toString());
		toFix = toFix.replace("*darkblue", ChatColor.DARK_BLUE.toString());
		toFix = toFix.replace("*black", ChatColor.BLACK.toString());
		toFix = toFix.replace("*darkgray", ChatColor.DARK_GRAY.toString());
		toFix = toFix.replace("*gray", ChatColor.GRAY.toString());
		toFix = toFix.replace("*darkpurple", ChatColor.DARK_PURPLE.toString());
		toFix = toFix.replace("*purple", ChatColor.LIGHT_PURPLE.toString());
		toFix = toFix.replace("*yellow", ChatColor.YELLOW.toString());
		
		return toFix;
	}
	
}
