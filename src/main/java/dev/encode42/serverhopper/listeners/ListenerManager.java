package dev.encode42.serverhopper.listeners;

import java.util.Set;

public class ListenerManager {
	private static final Set<ExecutableListener> LISTENERS = Set.of(
		new PlayerConnectionListener(),
		new ProxyRegistryListener(),
		new ServerPausedListener()
	);

	public static void registerAll() {
		for (ExecutableListener listener : ListenerManager.LISTENERS) {
			listener.register();
		}
	}
}
