package dev.encode42.serverhopper;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.PacketEventsAPI;
import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.PluginContainer;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.encode42.serverhopper.commands.CommandsManager;
import dev.encode42.serverhopper.config.ConfigManager;
import dev.encode42.serverhopper.config.ConfigRoot;
import dev.encode42.serverhopper.config.messages.MessagesRoot;
import dev.encode42.serverhopper.connection.PingCache;
import dev.encode42.serverhopper.listeners.ListenerManager;
import io.github.retrooper.packetevents.velocity.factory.VelocityPacketEventsBuilder;
import org.slf4j.Logger;

import java.nio.file.Path;

public class ServerHopper {
	public static final String ID = "serverhopper";

	private static ServerHopper instance;

	private final ProxyServer proxy;
	private final PluginContainer container;
	private final Path configDirectory;
	private final Logger logger;

	private PacketEventsAPI<?> packetEvents;
	private MessagesRoot messages = new MessagesRoot();
	private ConfigRoot config = new ConfigRoot();

	@Inject
	public ServerHopper(ProxyServer proxy, PluginContainer container, @DataDirectory Path configDirectory, Logger logger) {
		this.proxy = proxy;
		this.container = container;
		this.configDirectory = configDirectory;
		this.logger = logger;

		ServerHopper.instance = this;
	}

	public static ServerHopper instance() {
		return instance;
	}

	public static ProxyServer proxy() {
		return instance.proxy;
	}

	public static Path configDirectory() {
		return instance.configDirectory;
	}

	public static PacketEventsAPI<?> packetEvents() {
		return instance.packetEvents;
	}

	public static MessagesRoot messages() {
		return instance.messages;
	}

	public static ConfigRoot config() {
		return instance.config;
	}

	@Subscribe
	public void onProxyInitialization(ProxyInitializeEvent event) {
		PacketEvents.setAPI(
			VelocityPacketEventsBuilder.build(
				this.proxy,
				this.container,
				this.logger,
				this.configDirectory
			)
		);

		this.packetEvents = PacketEvents.getAPI();

		this.packetEvents.load();
		this.packetEvents.init();

		ConfigManager<MessagesRoot> messagesConfigManager = new ConfigManager<>("messages.json", MessagesRoot.class);
		ConfigManager<ConfigRoot> rootConfigManager = new ConfigManager<>("config.json", ConfigRoot.class);

		this.messages = messagesConfigManager.load();
		this.config = rootConfigManager.load();

		PingCache.init();

		CommandsManager.registerAll();
		ListenerManager.registerAll();
	}
}
