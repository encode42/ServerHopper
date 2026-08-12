package dev.encode42.serverhopper.listeners;

import dev.encode42.serverhopper.listeners.event.PlayerConnectionListener;
import dev.encode42.serverhopper.listeners.event.ProxyRegistryListener;
import dev.encode42.serverhopper.listeners.event.ServerPausedListener;
import dev.encode42.serverhopper.listeners.packet.ServerHopListener;

import java.util.Set;

public class ListenerManager {
	private static final Set<Listener> LISTENERS = Set.of(
		new PlayerConnectionListener(),
		new ProxyRegistryListener(),
		new ServerPausedListener(),
		new ServerHopListener()
	);

	public static void registerAll() {
		for (Listener listener : ListenerManager.LISTENERS) {
			listener.register();
		}
	}
}
