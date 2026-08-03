package dev.encode42.serverhopper.connection;

public class OnlinePingInfo extends PingInfo {
	private final int maxPlayers;
	private final int connectedPlayers;


	public OnlinePingInfo(String name, int maxPlayers, int connectedPlayers) {
		super(true, name);

		this.maxPlayers = maxPlayers;
		this.connectedPlayers = connectedPlayers;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public int getConnectedPlayers() {
		return connectedPlayers;
	}
}
