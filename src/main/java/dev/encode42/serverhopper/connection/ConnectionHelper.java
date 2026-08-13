package dev.encode42.serverhopper.connection;

import com.velocitypowered.api.proxy.ConnectionRequestBuilder;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ServerConnection;
import com.velocitypowered.api.proxy.server.RegisteredServer;

import java.util.Optional;

public class ConnectionHelper {
	public static ConnectionStatus connect(Player player, RegisteredServer server) {
		ServerConnection sourceServerConnection = ConnectionHelper.getConnection(player);
		RegisteredServer sourceServer = sourceServerConnection.getServer();

		if (sourceServer.equals(server)) {
			return ConnectionStatus.ALREADY_CONNECTED;
		}

		ConnectionRequestBuilder connectionRequestBuilder = player.createConnectionRequest(server);
		connectionRequestBuilder.connectWithIndication();

		return ConnectionStatus.SUCCESS;
	}

	public static ConnectionStatus connect(Player sourcePlayer, Player destinationPlayer) {
		ServerConnection destinationServerConnection = ConnectionHelper.getConnection(destinationPlayer);

		if (destinationServerConnection == null) {
			return ConnectionStatus.FAILURE;
		}

		RegisteredServer destinationServer = destinationServerConnection.getServer();

		return ConnectionHelper.connect(sourcePlayer, destinationServer);
	}

	public static ServerConnection getConnection(Player player) {
		Optional<ServerConnection> optionalServerConnection = player.getCurrentServer();

		return optionalServerConnection.orElse(null);
	}
}
