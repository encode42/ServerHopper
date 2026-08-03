package dev.encode42.serverhopper.listeners;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.server.ServerRegisteredEvent;
import com.velocitypowered.api.event.proxy.server.ServerUnregisteredEvent;
import dev.encode42.serverhopper.connection.PingCache;

public class ProxyRegistryListener {
	@Subscribe
	public void onServerRegistered(ServerRegisteredEvent event) {
		PingCache.refresh(event.registeredServer());
	}

	@Subscribe
	public void onServerUnregistered(ServerUnregisteredEvent event) {
		PingCache.remove(event.unregisteredServer());
	}
}
