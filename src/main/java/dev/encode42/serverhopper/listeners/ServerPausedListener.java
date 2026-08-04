package dev.encode42.serverhopper.listeners;

import com.velocitypowered.api.event.Subscribe;
import dev.encode42.serverhopper.ServerHopper;
import dev.encode42.serverhopper.connection.PingCache;
import org.projectpersistence.queue.events.ServerPausedEvent;
import org.projectpersistence.queue.events.ServerResumedEvent;

public class ServerPausedListener extends ExecutableListener {
	@Subscribe
	public void onServerPaused(ServerPausedEvent event) {
		String serverName = event.getServerName();

		ServerHopper.proxy()
			.getServer(serverName)
			.ifPresent(PingCache::remove);
	}

	@Subscribe
	public void onServerResumed(ServerResumedEvent event) {
		String serverName = event.getServerName();

		ServerHopper.proxy()
			.getServer(serverName)
			.ifPresent(PingCache::refresh);
	}
}
