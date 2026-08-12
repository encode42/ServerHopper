package dev.encode42.serverhopper.listeners.event;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.player.ServerConnectedEvent;
import com.velocitypowered.api.proxy.ServerConnection;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import dev.encode42.serverhopper.ServerHopper;
import dev.encode42.serverhopper.listeners.ProxyEventListener;
import dev.encode42.serverhopper.ping.PingCache;

import java.time.Duration;
import java.util.Optional;

public class PlayerConnectionListener extends ProxyEventListener {
	private static final Duration DISCONNECT_DELAY = Duration.ofSeconds(1);

	@Subscribe
	public void onPlayerConnect(ServerConnectedEvent event) {
		PingCache.refresh(event.getServer());

		Optional<RegisteredServer> optionalServer = event.getPreviousServer();

		if (optionalServer.isEmpty()) {
			return;
		}

		RegisteredServer server = optionalServer.get();

		ServerHopper.proxy()
			.getScheduler()
			.buildTask(
				ServerHopper.instance(),
				() -> PingCache.refresh(server)
			)
			.delay(PlayerConnectionListener.DISCONNECT_DELAY)
			.schedule();
	}

	@Subscribe
	public void onPlayerDisconnect(DisconnectEvent event) {
		Optional<ServerConnection> optionalServerConnection = event.getPlayer().getCurrentServer();

		if (optionalServerConnection.isEmpty()) {
			return;
		}

		ServerConnection serverConnection = optionalServerConnection.get();

		ServerHopper.proxy()
			.getScheduler()
			.buildTask(
				ServerHopper.instance(),
				() -> PingCache.refresh(serverConnection.getServer())
			)
			.delay(PlayerConnectionListener.DISCONNECT_DELAY)
			.schedule();
	}
}
