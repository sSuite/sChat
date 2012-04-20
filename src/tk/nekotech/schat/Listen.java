package tk.nekotech.sChat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class Listen implements Listener{

	private SChat plugin;
	private Formatter formatter;

	public Listen(SChat instance){
		this.plugin = instance;
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void playerChat(PlayerChatEvent event){
		event.setCancelled(true);
		final String formatted = formatter.Format(event.getPlayer(), event.getMessage());
		plugin.getServer().broadcastMessage(formatted);
	}

	@EventHandler
	public void playerJoin(PlayerJoinEvent event){
		String newname = null;
		newname = formatter.Color(plugin.getConfig().getString("default-name-color"), true) + event.getPlayer().getName();
		event.getPlayer().setDisplayName(newname);
		event.getPlayer().setPlayerListName(newname);
	}
}
