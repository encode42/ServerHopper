package dev.encode42.serverhopper.listeners;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.player.ServerConnectedEvent;
import com.velocitypowered.api.proxy.ServerConnection;
import dev.encode42.serverhopper.ServerHopper;
import dev.encode42.serverhopper.connection.PingCache;

import java.time.Duration;
import java.util.Optional;

public class PlayerConnectionListener {
	@Subscribe
	public void onPlayerConnect(ServerConnectedEvent event) {
		PingCache.refresh(event.getServer());

		event.getPreviousServer()
			.ifPresent(PingCache::refresh);
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
			.delay(Duration.ofSeconds(1))
			.schedule();
	}
}
