package dev.encode42.serverhopper.config;

import dev.encode42.serverhopper.config.messages.MessagesRoot;
import dev.encode42.serverhopper.connection.PingCache;
import org.slf4j.Logger;

import java.nio.file.Path;

public class ConfigManager {
	private static Logger logger;

	private static ConfigLoader<MessagesRoot> messagesConfigManager;
	private static ConfigLoader<ConfigRoot> rootConfigManager;

	public static void init(Logger logger, Path configDirectory) {
		ConfigManager.logger = logger;

		ConfigManager.messagesConfigManager = new ConfigLoader<>(configDirectory, "messages.json", MessagesRoot.class);
		ConfigManager.rootConfigManager = new ConfigLoader<>(configDirectory, "config.json", ConfigRoot.class);

		ConfigManager.load();
	}

	public static MessagesRoot messages() {
		return messagesConfigManager.getInstance();
	}

	public static ConfigRoot root() {
		return rootConfigManager.getInstance();
	}

	public static void load() throws RuntimeException {
		messagesConfigManager.load();
		rootConfigManager.load();
	}

	public static boolean reload() {
		try {
			ConfigManager.load();

			PingCache.reload();
		} catch (RuntimeException exception) {
			logger.error("Unable to load reload configuration files", exception);

			return false;
		}

		return true;
	}
}
