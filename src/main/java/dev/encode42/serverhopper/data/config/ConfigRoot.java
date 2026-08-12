package dev.encode42.serverhopper.data.config;

import com.velocitypowered.api.proxy.server.RegisteredServer;
import dev.encode42.serverhopper.data.ConfigNode;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.util.ArrayList;
import java.util.List;

@ConfigSerializable
public class ConfigRoot extends ConfigNode {
	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private List<String> ignoredServers = new ArrayList<>();

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private List<String> specialServers = new ArrayList<>();

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private boolean willSortSpecial = true;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private boolean willSortOffline = false;

	public List<String> getIgnoredServers() {
		return this.ignoredServers;
	}

	public List<String> getSpecialServers() {
		return specialServers;
	}

	public boolean willSortSpecial() {
		return this.willSortSpecial;
	}

	public boolean willSortOffline() {
		return this.willSortOffline;
	}

	public boolean isIgnored(RegisteredServer server) {
		String serverName = server.getServerInfo().getName();

		return this.ignoredServers.contains(serverName);
	}

	public boolean isSpecial(RegisteredServer server) {
		String serverName = server.getServerInfo().getName();

		return this.specialServers.contains(serverName);
	}
}
