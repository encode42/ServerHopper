package dev.encode42.serverhopper.packets;

import com.velocitypowered.api.proxy.Player;

public class ServerResultInvalid extends ServerResult {
	private final String serverName;
	private final Player player;

	public ServerResultInvalid(ServerStatus status, String serverName, Player player) {
		super(status);

		this.serverName = serverName;
		this.player = player;
	}

	public String getServerName() {
		return this.serverName;
	}

	public Player getPlayer() {
		return this.player;
	}
}
