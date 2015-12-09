package com.github.sSuite.sChat.commandHandler;

import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import com.github.sSuite.sChat.Main;
import com.github.sSuite.sChat.command.AbstractCommand;
import com.github.sSuite.sChat.command.mail.DeleteCommand;
import com.github.sSuite.sChat.command.mail.ListCommand;
import com.github.sSuite.sChat.command.mail.ReadCommand;
import com.github.sSuite.sChat.command.mail.SendCommand;
import com.github.sSuite.sLib.ConfigurationHandler;
import com.github.sSuite.sLib.utility.CommandHelpUtility;

public class MailCommandHandler implements Listener, CommandExecutor {

	private Main plugin;
	private ConfigurationHandler mailHandler;

	public MailCommandHandler(Main plugin) {
		this.plugin = plugin;
		mailHandler = plugin.getMailHandler();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			plugin.logger.severe("Only players can use /" + label + "!");
		}

		if (args.length < 1) {
			showHelp(sender);
			return true;
		}

		String[] newArgs = new String[args.length - 1];
		for (int i = 1; i < args.length; i++) {
			newArgs[i - 1] = args[i];
		}

		AbstractCommand commandClass;
		switch (args[0].toLowerCase()) {
			case "list":
				commandClass = new ListCommand(plugin);
				break;
			case "send":
				commandClass = new SendCommand(plugin);
				break;
			case "read":
				commandClass = new ReadCommand(plugin);
				break;
			case "delete":
				commandClass = new DeleteCommand(plugin);
				break;
			default:
				showHelp(sender);
				return true;
		}

		if (!commandClass.onExecute(sender, newArgs)) {
			showHelp(sender);
		}
		return true;
	}

	private void showHelp(CommandSender sender) {
		CommandHelpUtility.sendHeader("/mail Help", sender);
		CommandHelpUtility.sendCommand("/mail list [page]", "Lists your messages", sender, "schat.mail.receive");
		CommandHelpUtility.sendCommand("/mail send <player> <message>", "Sends mail to the specified player", sender,
				"schat.mail.send");
		CommandHelpUtility.sendCommand("/mail read [messageID]", "Reads the specified message", sender,
				"schat.mail.receive");
		CommandHelpUtility.sendCommand("/mail delete [messageID]", "Deletes the specified message", sender,
				"schat.mail.receive");
	}

	private String[] getMessages(Player player) {
		List<String> playerList = mailHandler.getConfig().getStringList(player.getName());
		String[] playerMail = new String[playerList.size()];
		playerList.toArray(playerMail);
		return playerMail;
	}

	@EventHandler
	public void playerJoin(PlayerJoinEvent event) {
		String[] playerMail = getMessages(event.getPlayer());

		if (playerMail.length != 0) {
			event.getPlayer().sendMessage(ChatColor.GREEN + "You have " + ChatColor.AQUA + playerMail.length
					+ ChatColor.GREEN + " new message(s).");
			event.getPlayer()
					.sendMessage("Use " + ChatColor.GOLD + "/mail list" + ChatColor.RESET + " to view all messages.");
		}
	}
}
