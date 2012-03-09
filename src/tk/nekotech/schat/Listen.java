package tk.nekotech.schat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class Listen implements Listener {
	
	private SChat plugin;
	
	public Listen(SChat instance) {
		this.plugin = instance;
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void playerChat(PlayerChatEvent event) {
		event.setCancelled(true);
		String formatted = plugin.getFormattedMessage(event.getPlayer(), event.getMessage());
		plugin.getServer().broadcastMessage(formatted);
	}

}
