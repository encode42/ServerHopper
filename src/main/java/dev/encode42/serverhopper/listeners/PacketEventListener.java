package dev.encode42.serverhopper.listeners;

import com.github.retrooper.packetevents.event.EventManager;
import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import dev.encode42.serverhopper.ServerHopper;

public class PacketEventListener extends Listener implements PacketListener {
	private final EventManager eventManager = ServerHopper.packetEvents().getEventManager();

	public void register() {
		this.eventManager.registerListener(this, PacketListenerPriority.NORMAL);
	}
}
