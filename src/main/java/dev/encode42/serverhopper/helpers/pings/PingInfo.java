package dev.encode42.serverhopper.helpers.pings;

public class PingInfo {
	private final long updated = System.currentTimeMillis();

	private final boolean isOnline;
	private final String name;

	private final boolean isSpecial;

	public PingInfo(
		boolean isOnline,
		String name,
		boolean isSpecial
	) {
		this.isOnline = isOnline;
		this.name = name;
		this.isSpecial = isSpecial;
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

	public boolean isSpecial() {
		return this.isSpecial;
	}
}
