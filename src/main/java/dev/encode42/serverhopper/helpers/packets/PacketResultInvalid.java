package dev.encode42.serverhopper.helpers.packets;

import com.velocitypowered.api.proxy.Player;

public class PacketResultInvalid extends PacketResult {
	private final String serverName;
	private final Player player;

	public PacketResultInvalid(PacketStatus status, String serverName, Player player) {
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
