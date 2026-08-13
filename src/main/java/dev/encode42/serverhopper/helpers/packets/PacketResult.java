package dev.encode42.serverhopper.helpers.packets;

public class PacketResult {
	public static final PacketResult EMPTY = new PacketResult(PacketStatus.EMPTY);

	private final PacketStatus status;

	public PacketResult(PacketStatus status) {
		this.status = status;
	}

	public PacketStatus getStatus() {
		return this.status;
	}
}
