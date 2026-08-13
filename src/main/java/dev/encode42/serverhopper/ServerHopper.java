package dev.encode42.serverhopper;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.PacketEventsAPI;
import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.PluginContainer;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.encode42.serverhopper.commands.CommandManager;
import dev.encode42.serverhopper.data.ConfigManager;
import dev.encode42.serverhopper.helpers.pings.PingCache;
import dev.encode42.serverhopper.integrations.QueueIntegration;
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

	private QueueIntegration queueIntegration;

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

	public static PacketEventsAPI<?> packetEvents() {
		return instance.packetEvents;
	}

	public static QueueIntegration queue() {
		return instance.queueIntegration;
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

		ConfigManager.init(
			this.logger,
			this.configDirectory
		);

		this.queueIntegration = new QueueIntegration();

		PingCache.init();

		CommandManager.registerAll();
		ListenerManager.registerAll();
	}
}
