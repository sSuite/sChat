package com.github.sSuite.sChat.Utility;

import java.util.Collection;
import org.bukkit.entity.Player;
import com.github.sSuite.sChat.Main;
import com.github.sSuite.sChat.exception.AmbiguousPlayerNameException;
import com.github.sSuite.sChat.exception.NoSuchPlayerException;

public class PlayerUtility {

	private Main plugin;

	public PlayerUtility(Main plugin) {
		this.plugin = plugin;
	}

	public Player getPlayerByName(String name) throws AmbiguousPlayerNameException, NoSuchPlayerException {
		name = name.toLowerCase();
		Collection<? extends Player> playerList = plugin.getServer().getOnlinePlayers();
		Player match = null;
		int count = 0;

		for (Player player : playerList) {
			if (player.getName().toLowerCase().contains(name)) {
				count = count + 1;
				match = player;
			}
		}
		if (count > 1) {
			throw new AmbiguousPlayerNameException("The name " + name + " is ambiguous.");
		} else if (count < 1) {
			throw new NoSuchPlayerException("The player " + name + " could not be found.");
		}
		return match;
	}

}
