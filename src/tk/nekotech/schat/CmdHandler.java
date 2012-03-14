package tk.nekotech.sChat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CmdHandler{

	// Colors
	ChatColor aqua = ChatColor.AQUA;
	ChatColor black = ChatColor.BLACK;
	ChatColor blue = ChatColor.BLUE;
	ChatColor darkaqua = ChatColor.DARK_AQUA;
	ChatColor darkblue = ChatColor.DARK_BLUE;
	ChatColor darkgray = ChatColor.DARK_GRAY;
	ChatColor darkgreen = ChatColor.DARK_GREEN;
	ChatColor darkpurple = ChatColor.DARK_PURPLE;
	ChatColor darkred = ChatColor.DARK_RED;
	ChatColor gold = ChatColor.GOLD;
	ChatColor gray = ChatColor.GRAY;
	ChatColor green = ChatColor.GREEN;
	ChatColor lightpurple = ChatColor.LIGHT_PURPLE;
	ChatColor red = ChatColor.RED;
	ChatColor white = ChatColor.WHITE;
	ChatColor yellow = ChatColor.YELLOW;
	// End colors
	SChat p;
	String cmd;

	public CmdHandler(SChat p){
		this.p = p;
	}

	public boolean cmd(CommandSender i, Command j, String[] k){
		cmd = j.getName();
		if (isCmd(cmd, "pm") || isCmd(cmd, "tell") || isCmd(cmd, "whisper"))
			if (k.length < 1){
				help(i);
				return true;
			}
		return false;
	}

	private boolean isCmd(String sent, String cmd){
		if (sent.equalsIgnoreCase(cmd))
			return true;
		return false;
	}

	private void help(CommandSender i){
		i.sendMessage(darkred + "/tell");
		i.sendMessage(darkred + "Aliases: /pm, /whisper");
		i.sendMessage(gold + "Usage: /tell [player] [message]");
		i.sendMessage(gold + "Without arguments");
	}
}
