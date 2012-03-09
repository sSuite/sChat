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
		toFix = toFix.replace("darkgreen", ChatColor.DARK_GREEN.toString());
		toFix = toFix.replace("darkred", ChatColor.DARK_RED.toString());
		toFix = toFix.replace("darkaqua", ChatColor.DARK_AQUA.toString());
		toFix = toFix.replace("darkblue", ChatColor.DARK_BLUE.toString());
		toFix = toFix.replace("darkgray", ChatColor.DARK_GRAY.toString());
		toFix = toFix.replace("darkpurple", ChatColor.DARK_PURPLE.toString());
		
		toFix = toFix.replace("red", ChatColor.RED.toString());
		toFix = toFix.replace("gold", ChatColor.GOLD.toString());
		toFix = toFix.replace("green", ChatColor.GREEN.toString());
		toFix = toFix.replace("aqua", ChatColor.AQUA.toString());
		toFix = toFix.replace("white", ChatColor.WHITE.toString());
		toFix = toFix.replace("blue", ChatColor.BLUE.toString());
		toFix = toFix.replace("black", ChatColor.BLACK.toString());
		toFix = toFix.replace("gray", ChatColor.GRAY.toString());
		toFix = toFix.replace("purple", ChatColor.LIGHT_PURPLE.toString());
		toFix = toFix.replace("yellow", ChatColor.YELLOW.toString());
		*/
		
		//"easter egg"
		configStr = configStr.replace("*magic", ChatColor.MAGIC.toString());
		
		return configStr;
	}
	
	public String replaceWithChatColor(String toFix) {
		/*
		 * IMPORTANT
		 * DARK COLORS HAS TO BE FIRST OR BAD THINGS WILL HAPPEN
		 */
		toFix = toFix.replace("*darkgreen", ChatColor.DARK_GREEN.toString());
		toFix = toFix.replace("*darkred", ChatColor.DARK_RED.toString());
		toFix = toFix.replace("*darkaqua", ChatColor.DARK_AQUA.toString());
		toFix = toFix.replace("*darkblue", ChatColor.DARK_BLUE.toString());
		toFix = toFix.replace("*darkgray", ChatColor.DARK_GRAY.toString());
		toFix = toFix.replace("*darkpurple", ChatColor.DARK_PURPLE.toString());
		
		toFix = toFix.replace("*red", ChatColor.RED.toString());
		toFix = toFix.replace("*gold", ChatColor.GOLD.toString());
		toFix = toFix.replace("*green", ChatColor.GREEN.toString());
		toFix = toFix.replace("*aqua", ChatColor.AQUA.toString());
		toFix = toFix.replace("*white", ChatColor.WHITE.toString());
		toFix = toFix.replace("*blue", ChatColor.BLUE.toString());
		toFix = toFix.replace("*black", ChatColor.BLACK.toString());
		toFix = toFix.replace("*gray", ChatColor.GRAY.toString());
		toFix = toFix.replace("*purple", ChatColor.LIGHT_PURPLE.toString());
		toFix = toFix.replace("*yellow", ChatColor.YELLOW.toString());
		
		return toFix;
	}
	
	public String replaceWithChatColorWithOutAsterix(String toFix) {
		/*
		 * IMPORTANT
		 * DARK COLORS HAS TO BE FIRST OR BAD THINGS WILL HAPPEN
		 */
		toFix = toFix.replace("darkgreen", ChatColor.DARK_GREEN.toString());
		toFix = toFix.replace("darkred", ChatColor.DARK_RED.toString());
		toFix = toFix.replace("darkaqua", ChatColor.DARK_AQUA.toString());
		toFix = toFix.replace("darkblue", ChatColor.DARK_BLUE.toString());
		toFix = toFix.replace("darkgray", ChatColor.DARK_GRAY.toString());
		toFix = toFix.replace("darkpurple", ChatColor.DARK_PURPLE.toString());
		
		toFix = toFix.replace("red", ChatColor.RED.toString());
		toFix = toFix.replace("gold", ChatColor.GOLD.toString());
		toFix = toFix.replace("green", ChatColor.GREEN.toString());
		toFix = toFix.replace("aqua", ChatColor.AQUA.toString());
		toFix = toFix.replace("white", ChatColor.WHITE.toString());
		toFix = toFix.replace("blue", ChatColor.BLUE.toString());
		toFix = toFix.replace("black", ChatColor.BLACK.toString());
		toFix = toFix.replace("gray", ChatColor.GRAY.toString());
		toFix = toFix.replace("purple", ChatColor.LIGHT_PURPLE.toString());
		toFix = toFix.replace("yellow", ChatColor.YELLOW.toString());
		
		return toFix;
	}
	
}
