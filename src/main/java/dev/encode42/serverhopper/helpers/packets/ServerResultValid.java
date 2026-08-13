package dev.encode42.serverhopper.helpers.packets;

import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.server.RegisteredServer;

public class ServerResultValid extends ServerResult {
	private final Player player;
	private final RegisteredServer server;

	public ServerResultValid(Player player, RegisteredServer server) {
		super(ServerStatus.SUCCESS);

		this.player = player;
		this.server = server;
	}

	public Player getPlayer() {
		return this.player;
	}

	public RegisteredServer getServer() {
		return this.server;
	}
}
