package dev.encode42.serverhopper.permissions;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.server.RegisteredServer;

public class ServerPermission {
	private static final Permission PERMISSION = new Permission("server");

	private static Permission getPermission(RegisteredServer server) {
		return ServerPermission.getPermission(server.getServerInfo().getName());
	}

	private static Permission getPermission(String serverName) {
		return ServerPermission.PERMISSION.append(serverName);
	}

	public static boolean hasPermission(CommandSource commandSource, RegisteredServer server) {
		return ServerPermission.getPermission(server).hasPermission(commandSource);
	}

	public static boolean hasPermission(CommandSource commandSource, String serverName) {
		return ServerPermission.getPermission(serverName).hasPermission(commandSource);
	}

	public static boolean hasDefaultPermission(CommandSource commandSource, RegisteredServer server) {
		return ServerPermission.getPermission(server).hasDefaultPermission(commandSource);
	}

	public static boolean hasDefaultPermission(CommandSource commandSource, String serverName) {
		return ServerPermission.getPermission(serverName).hasDefaultPermission(commandSource);
	}
}
