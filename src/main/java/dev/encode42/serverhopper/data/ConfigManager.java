package dev.encode42.serverhopper.data;

import dev.encode42.serverhopper.data.config.ConfigRoot;
import dev.encode42.serverhopper.data.messages.MessagesRoot;
import dev.encode42.serverhopper.ping.PingCache;
import org.slf4j.Logger;

import java.nio.file.Path;

public class ConfigManager {
	private static Logger logger;

	private static DataLoader<MessagesRoot> messagesConfigManager;
	private static DataLoader<ConfigRoot> rootConfigManager;

	public static void init(Logger logger, Path configDirectory) {
		ConfigManager.logger = logger;

		ConfigManager.messagesConfigManager = new DataLoader<>(configDirectory, "messages.json", MessagesRoot.class);
		ConfigManager.rootConfigManager = new DataLoader<>(configDirectory, "config.json", ConfigRoot.class);

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
