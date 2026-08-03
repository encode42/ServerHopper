package dev.encode42.serverhopper.config;

import dev.encode42.serverhopper.ServerHopper;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.gson.GsonConfigurationLoader;
import org.spongepowered.configurate.serialize.SerializationException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigManager<T extends ConfigNode> {
	private static final Path CONFIG_DIRECTORY = ServerHopper.configDirectory();

	private final GsonConfigurationLoader configurationLoader;
	private final Class<T> configClass;

	private T configInstance;

	public ConfigManager(String configName, Class<T> configClass) {
		Path configPath = ConfigManager.CONFIG_DIRECTORY.resolve(configName);

		this.configClass = configClass;

		this.configurationLoader = GsonConfigurationLoader.builder()
			.path(configPath)
			.indent(4)
			.build();
	}

	public static void ensureDirectory() {
		try {
			Files.createDirectories(ConfigManager.CONFIG_DIRECTORY);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public T load() {
		ConfigManager.ensureDirectory();

		ConfigurationNode configurationNode;

		try {
			configurationNode = this.configurationLoader.load();
		} catch (ConfigurateException exception) {
			throw new RuntimeException(exception);
		}

		try {
			this.configInstance = configurationNode.get(this.configClass);
		} catch (SerializationException exception) {
			throw new RuntimeException(exception);
		}

		this.save();

		return this.configInstance;
	}

	public T getInstance() {
		return this.configInstance;
	}

	public void save() {
		ConfigurationNode configurationNode = configurationLoader.createNode();

		try {
			configurationNode.set(this.configClass, this.configInstance);
		} catch (SerializationException exception) {
			throw new RuntimeException(exception);
		}

		try {
			configurationLoader.save(configurationNode);
		} catch (ConfigurateException e) {
			throw new RuntimeException(e);
		}
	}
}
