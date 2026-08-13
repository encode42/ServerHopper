package dev.encode42.serverhopper.helpers.pings;

public class OnlinePingInfo extends PingInfo {
	private final int maxPlayers;
	private final int connectedPlayers;

	public OnlinePingInfo(
		String name,
		int maxPlayers,
		int connectedPlayers,
		boolean isSpecial
	) {
		super(true, name, isSpecial);

		this.maxPlayers = maxPlayers;
		this.connectedPlayers = connectedPlayers;
	}

	public int getMaxPlayers() {
		return this.maxPlayers;
	}

	public int getConnectedPlayers() {
		return this.connectedPlayers;
	}
}
