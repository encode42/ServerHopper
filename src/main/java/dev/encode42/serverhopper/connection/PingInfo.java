package dev.encode42.serverhopper.connection;

public class PingInfo {
	private final long updated = System.currentTimeMillis();

	private final boolean isOnline;
	private final String name;

	public PingInfo(boolean isOnline, String name) {
		this.isOnline = isOnline;
		this.name = name;
	}

	public boolean isOnline() {
		return this.isOnline;
	}

	public String getName() {
		return this.name;
	}

	public long getUpdated() {
		return this.updated;
	}
}
