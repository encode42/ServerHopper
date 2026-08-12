package dev.encode42.serverhopper.listeners.event;

import com.velocitypowered.api.event.Subscribe;
import dev.encode42.serverhopper.ServerHopper;
import dev.encode42.serverhopper.connection.PingCache;
import dev.encode42.serverhopper.listeners.ProxyEventListener;
import org.projectpersistence.queue.events.ServerPausedEvent;
import org.projectpersistence.queue.events.ServerResumedEvent;

public class ServerPausedListener extends ProxyEventListener {
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
