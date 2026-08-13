package dev.encode42.serverhopper.listeners.packet;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import dev.encode42.serverhopper.data.ConfigManager;
import dev.encode42.serverhopper.helpers.connections.ConnectionHelper;
import dev.encode42.serverhopper.helpers.connections.ConnectionStatus;
import dev.encode42.serverhopper.helpers.packets.PacketHelper;
import dev.encode42.serverhopper.helpers.packets.PacketResult;
import dev.encode42.serverhopper.helpers.packets.PacketResultInvalid;
import dev.encode42.serverhopper.helpers.packets.PacketResultValid;
import dev.encode42.serverhopper.listeners.PacketEventListener;
import org.jspecify.annotations.NonNull;

public class ServerHopListener extends PacketEventListener {
	@Override
	public void onPacketReceive(@NonNull PacketReceiveEvent event) {
		if (event.getPacketType() != PacketType.Play.Client.CUSTOM_CLICK_ACTION) {
			return;
		}

		PacketResult result = PacketHelper.validate(event);

		if (result instanceof PacketResultInvalid invalidResult) {
			String serverName = invalidResult.getServerName();
			Player player = invalidResult.getPlayer();

			switch (result.getStatus()) {
				case FAILURE -> player.sendMessage(
					ConfigManager.messages()
						.connection()
						.getInvalidServer()
						.parse(serverName)
				);

				case NO_PERMISSION -> player.sendMessage(
					ConfigManager.messages()
						.connection()
						.getInvalidPermission()
						.parse(serverName)
				);
			}

			return;
		}

		if (result instanceof PacketResultValid validResult) {
			Player player = validResult.getPlayer();
			RegisteredServer server = validResult.getServer();

			ConnectionStatus connectionStatus = ConnectionHelper.connect(player, server);

			if (connectionStatus != ConnectionStatus.SUCCESS) {
				player.sendMessage(
					ConfigManager.messages()
						.connection()
						.getExecutorConnected()
						.parse()
				);
			}
		}
	}
}
