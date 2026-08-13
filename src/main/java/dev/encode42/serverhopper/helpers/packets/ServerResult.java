package dev.encode42.serverhopper.helpers.packets;

public class ServerResult {
	public static final ServerResult EMPTY = new ServerResult(ServerStatus.EMPTY);

	private final ServerStatus status;

	public ServerResult(ServerStatus status) {
		this.status = status;
	}

	public ServerStatus getStatus() {
		return this.status;
	}
}
