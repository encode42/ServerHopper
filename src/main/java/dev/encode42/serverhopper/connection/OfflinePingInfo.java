package dev.encode42.serverhopper.connection;

public class OfflinePingInfo extends PingInfo {
	public OfflinePingInfo(String name, boolean isSpecial) {
		super(false, name, isSpecial);
	}
}
