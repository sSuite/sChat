package com.github.sSuite.sChat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import com.github.sSuite.sLib.ErrorHandler;
import com.github.sSuite.sLib.PluginLogger;

/**
 * @author Ethan
 *
 *         Event dispatcher so other plugins can listen to and capture chat
 *         events. Possibly not necessary.
 */
public class ChatDispatcher {

	private ErrorHandler errorHandler;
	private PluginLogger logger;
	private ArrayList<Object> instances;
	private ArrayList<Method> methods;

	public ChatDispatcher(Main plugin, Configuration configuration) {
		errorHandler = new ErrorHandler(plugin, configuration, "debugMode", "silentMode");
		logger = new PluginLogger(plugin, configuration, "debugMode", "silentMode");
	}

	public boolean getListener(JavaPlugin listeningPlugin, Object object, String methodName) {
		try {
			Method method = object.getClass().getMethod(methodName, Player.class, String.class);

			for (int i = 0; i < instances.size(); i++) {
				if (instances.get(i).equals(object) && methods.get(i).getName().equals(methodName)) {
					logger.severe("Chat listener already created for " + object.getClass().getName() + "." + methodName
							+ " for plugin \"" + listeningPlugin.getDescription().getName() + "\"!");
					return false;
				}
			}
			instances.add(object);
			methods.add(method);
			return true;
		} catch (SecurityException e) {
			errorHandler.printStackTrace(e);
		} catch (NoSuchMethodException e) {
			errorHandler.printStackTrace(e);
		}
		return false;
	}

	public void informChat(Player player, String message) {
		for (int i = 0; i < instances.size(); i++) {
			try {
				methods.get(i).invoke(instances.get(i), player, message);
			} catch (IllegalAccessException e) {
				errorHandler.printStackTrace(e);
			} catch (IllegalArgumentException e) {
				errorHandler.printStackTrace(e);
			} catch (InvocationTargetException e) {
				errorHandler.printStackTrace(e);
			}
		}
	}
}