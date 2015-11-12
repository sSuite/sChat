package com.github.sSuite.sChat;

import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.sSuite.sLib.PluginLogger;

public class Main extends JavaPlugin{

	public static Configuration cfg;
	public static PluginDescriptionFile pdf;
	private final Listen listener = new Listen(this);
	public String pversion;
	public PluginLogger log;
	public ChatDispatcher Informer;

	@Override
	public void onEnable(){
		cfg = getConfig();
		log = new PluginLogger(this, cfg, "debugMode", "silentMode");
		pdf = getDescription();
		pversion = pdf.getVersion();
		new Formatter(this);
		getServer().getPluginManager().registerEvents(listener, this);
		getConfig().options().copyDefaults(true);
		saveConfig();
		String ver = pdf.getVersion();
		log.info("sChat version " + ver + " loaded!");
		Informer = new ChatDispatcher(this);
	}

	@Override
	public void onDisable(){
		cfg = getConfig();
		log = new PluginLogger(this, cfg, "debugMode", "silentMode");
		pdf = getDescription();
		String ver = pdf.getVersion();
		log.info("sChat version " + ver + " disabled!");
	}
}
