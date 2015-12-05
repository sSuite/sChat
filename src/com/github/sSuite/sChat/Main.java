package com.github.sSuite.sChat;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.scanner.ScannerException;
import com.github.sSuite.sChat.commandHandler.AwayCommandHandler;
import com.github.sSuite.sChat.commandHandler.SubCommandHandler;
import com.github.sSuite.sChat.commandHandler.WhisperCommandHandler;
import com.github.sSuite.sLib.ConfigurationHandler;

public class Main extends JavaPlugin {

	private static Configuration configuration;
	private ConfigurationHandler mailHandler;
	private String pluginVersion;
	public Logger logger;
	private PlayerListener playerListener;
	// private ChatDispatcher dispatcher;

	@Override
	public void onEnable() {
		configuration = getConfig();
		FileConfigurationOptions options = (FileConfigurationOptions) configuration.options();
		options.copyDefaults(true);
		options.copyHeader();
		saveConfig();

		mailHandler = new ConfigurationHandler(this, "mail");

		// logger = new PluginLogger(this, configuration, "debugMode",
		// "silentMode");
		logger = getLogger();
		pluginVersion = getDescription().getVersion();

		playerListener = new PlayerListener(this);
		WhisperCommandHandler whisperCommand = new WhisperCommandHandler(this);
		AwayCommandHandler awayCommand = new AwayCommandHandler(this);
		// MailCommandHandler mailCommand = new MailCommandHandler(this);
		getServer().getPluginManager().registerEvents(playerListener, this);
		getServer().getPluginManager().registerEvents(whisperCommand, this);
		getServer().getPluginManager().registerEvents(awayCommand, this);
		// getServer().getPluginManager().registerEvents(mailCommand, this);

		getCommand("schat").setExecutor(new SubCommandHandler(this));
		getCommand("whisper").setExecutor(whisperCommand);
		getCommand("tell").setExecutor(whisperCommand);
		getCommand("pm").setExecutor(whisperCommand);
		getCommand("away").setExecutor(awayCommand);
		getCommand("afk").setExecutor(awayCommand);
		// getCommand("mail").setExecutor(mailCommand);

		playerListener.onLoad();

		// dispatcher = new ChatDispatcher(this, configuration);
		logger.info("sChat version " + pluginVersion + " loaded!");
	}

	@Override
	public void onDisable() {
		logger.info("sChat version " + pluginVersion + " disabled!");
	}

	public boolean reloadCustomConfig() {
		try {
			reloadConfig();
			configuration = getConfig();
			FileConfigurationOptions options = (FileConfigurationOptions) configuration.options();
			options.copyDefaults(true);
			options.copyHeader();
			saveConfig();
		} catch (ScannerException e) {
			e.printStackTrace();
			return false;
		}

		try {
			mailHandler.load();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		playerListener.onLoad();

		return true;
	}

	public ConfigurationHandler getMailHandler() {
		return mailHandler;
	}
}
