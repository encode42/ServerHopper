package dev.encode42.serverhopper.listeners;

import com.velocitypowered.api.event.EventManager;
import dev.encode42.serverhopper.ServerHopper;

public class ProxyEventListener extends Listener {
	private final EventManager eventManager = ServerHopper.proxy().getEventManager();

	public void register() {
		this.eventManager.register(ServerHopper.instance(), this);
	}
}
