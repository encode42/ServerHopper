package dev.encode42.serverhopper.integrations;

import com.velocitypowered.api.proxy.server.RegisteredServer;
import org.projectpersistence.queue.Queue;
import org.projectpersistence.queue.QueueAPI;

public class QueueIntegration {
	private final QueueAPI queue;

	public QueueIntegration() {
		this.queue = Queue.getInstance();
	}

	public boolean isPaused(RegisteredServer server) {
		String serverName = server.getServerInfo().getName();

		return queue.getPausedServers().contains(serverName);
	}
}
