package com.github.sSuite.sChat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import com.github.sSuite.sLib.PluginLogger;

/**
 * @author Ethan
 *
 *         Event dispatcher so other plugins can listen to and capture chat
 *         events. Will be removed.
 * 
 *         Use Events instead. Bukkit.getPluginManager().callEvent(event); event
 *         is then modified, for example, if something cancels it.
 */
public class ChatDispatcher {

	private PluginLogger logger;
	private ArrayList<Object> instances;
	private ArrayList<Method> methods;

	public ChatDispatcher(Main plugin, Configuration configuration) {
		logger = new PluginLogger(plugin);
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
			logger.printStackTrace(e);
		} catch (NoSuchMethodException e) {
			logger.printStackTrace(e);
		}
		return false;
	}

	public void informChat(Player player, String message) {
		for (int i = 0; i < instances.size(); i++) {
			try {
				methods.get(i).invoke(instances.get(i), player, message);
			} catch (IllegalAccessException e) {
				logger.printStackTrace(e);
			} catch (IllegalArgumentException e) {
				logger.printStackTrace(e);
			} catch (InvocationTargetException e) {
				logger.printStackTrace(e);
			}
		}
	}
}