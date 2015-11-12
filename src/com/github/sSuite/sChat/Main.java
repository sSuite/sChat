package com.github.sSuite.sChat;

import java.util.logging.Logger;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;
import com.github.sSuite.sChat.commands.AwayCommand;
import com.github.sSuite.sChat.commands.WhisperCommand;

public class Main extends JavaPlugin {

	private static Configuration configuration;
	private String pluginVersion;
	public Logger logger;
	// private ChatDispatcher dispatcher;

	@Override
	public void onEnable() {
		configuration = getConfig();
		configuration.options().copyDefaults(true);
		saveConfig();
		// logger = new PluginLogger(this, configuration, "debugMode",
		// "silentMode");
		logger = getLogger();
		pluginVersion = getDescription().getVersion();

		WhisperCommand whisperCommand = new WhisperCommand(this);
		AwayCommand awayCommand = new AwayCommand(this);
		getServer().getPluginManager().registerEvents(whisperCommand, this);
		getServer().getPluginManager().registerEvents(awayCommand, this);

		getCommand("whisper").setExecutor(whisperCommand);
		getCommand("tell").setExecutor(whisperCommand);
		getCommand("pm").setExecutor(whisperCommand);
		getCommand("away").setExecutor(awayCommand);
		getCommand("afk").setExecutor(awayCommand);

		// dispatcher = new ChatDispatcher(this, configuration);
		logger.info("sChat version " + pluginVersion + " loaded!");
	}

	@Override
	public void onDisable() {
		logger.info("sChat version " + pluginVersion + " disabled!");
	}
}
