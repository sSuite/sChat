package tk.nekotech.sChat;

import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class SChat extends JavaPlugin{

	CmdHandler CmdHandler = new CmdHandler(this);
	public static Configuration cfg;
	public static PluginDescriptionFile pdf;
	private final Listen listener = new Listen(this);

	@Override
	public void onEnable(){
		new Formatter(this);
		pdf = getDescription();
		getServer().getPluginManager().registerEvents(listener, this);
		getConfig().options().copyDefaults(true);
		saveConfig();
		String ver = pdf.getVersion();
		getLogger().info("sChat version " + ver + " loaded!");
	}

	@Override
	public void onDisable(){
		pdf = getDescription();
		String ver = pdf.getVersion();
		getLogger().info("sChat version " + ver + " disabled!");
	}
}
