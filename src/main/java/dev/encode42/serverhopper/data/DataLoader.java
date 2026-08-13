package dev.encode42.serverhopper.data;

import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.gson.GsonConfigurationLoader;
import org.spongepowered.configurate.serialize.SerializationException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataLoader<T extends ConfigNode> {
	private final Path configDirectory;
	private final Path configFile;

	private final GsonConfigurationLoader configurationLoader;
	private final Class<T> configClass;

	private T configInstance;

	public DataLoader(Path configDirectory, String configName, Class<T> configClass) {
		this.configDirectory = configDirectory;
		this.configFile = configDirectory.resolve(configName);

		this.configClass = configClass;

		this.configurationLoader = GsonConfigurationLoader.builder()
			.path(this.configFile)
			.indent(4)
			.build();
	}

	public void ensureDirectory() throws RuntimeException {
		try {
			Files.createDirectories(this.configDirectory);
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	public T load() throws RuntimeException {
		this.ensureDirectory();

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

		if (this.configInstance instanceof Initializable initializableConfig) {
			initializableConfig.init();
		}

		this.save();

		return this.configInstance;
	}

	public T getInstance() {
		return this.configInstance;
	}

	public void save() throws RuntimeException {
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
