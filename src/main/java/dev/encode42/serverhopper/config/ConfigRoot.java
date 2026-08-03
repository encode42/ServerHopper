package dev.encode42.serverhopper.config;

import com.velocitypowered.api.proxy.server.RegisteredServer;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.util.ArrayList;
import java.util.List;

@ConfigSerializable
public class ConfigRoot extends ConfigNode {
	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private List<String> ignoredServers = new ArrayList<>();

	public List<String> getIgnoredServers() {
		return this.ignoredServers;
	}

	public boolean isIgnored(RegisteredServer server) {
		String serverName = server.getServerInfo().getName();

		return this.ignoredServers.contains(serverName);
	}
}
