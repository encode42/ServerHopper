package dev.encode42.serverhopper.helpers.packets;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.chat.clickevent.CustomClickEvent;
import com.github.retrooper.packetevents.protocol.nbt.NBT;
import com.github.retrooper.packetevents.protocol.nbt.NBTCompound;
import com.github.retrooper.packetevents.protocol.nbt.NBTString;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.resources.ResourceLocation;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientCustomClickAction;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import dev.encode42.serverhopper.ServerHopper;
import dev.encode42.serverhopper.permissions.ServerPermission;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.TagStringIO;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public class ServerHelper {
	public static String PACKET_KEY = "hop";
	public static String DATA_KEY = "server";

	public static ResourceLocation PACKET_EVENTS_KEY = new ResourceLocation(ServerHopper.ID, ServerHelper.PACKET_KEY);

	public static CustomClickEvent getClickEvent(RegisteredServer server) {
		return ServerHelper.getClickEvent(server.getServerInfo().getName());
	}

	public static CustomClickEvent getClickEvent(String serverName) {
		NBTCompound data = new NBTCompound();

		data.setTag(ServerHelper.DATA_KEY, new NBTString(serverName));

		return new CustomClickEvent(
			ServerHelper.PACKET_EVENTS_KEY,
			data
		);
	}

	public static String getServerName(WrapperPlayClientCustomClickAction packet) {
		if (!packet.getId().equals(ServerHelper.PACKET_EVENTS_KEY)) {
			return null;
		}

		NBT payload = packet.getPayload();

		if (payload instanceof NBTCompound data) {
			return data.getStringTagValueOrNull(ServerHelper.DATA_KEY);
		}

		if (payload instanceof NBTString data) {
			CompoundBinaryTag tag;

			try {
				tag = TagStringIO.tagStringIO().asCompound(data.getValue());
			} catch (IOException exception) {
				return null;
			}

			return tag.getString(ServerHelper.DATA_KEY);
		}

		return null;
	}

	private static RegisteredServer getServer(String serverName) {
		Optional<RegisteredServer> optionalServer = ServerHopper.proxy()
			.getServer(serverName);

		return optionalServer.orElse(null);
	}

	private static Player getPlayer(PacketReceiveEvent event) {
		User user = event.getUser();
		UUID userUniqueId = user.getUUID();

		Optional<Player> optionalPlayer = ServerHopper.proxy().getPlayer(userUniqueId);

		return optionalPlayer.orElse(null);
	}

	public static ServerResult validate(PacketReceiveEvent event) {
		WrapperPlayClientCustomClickAction clickAction = new WrapperPlayClientCustomClickAction(event);

		String serverName = ServerHelper.getServerName(clickAction);
		if (serverName == null) {
			return ServerResult.EMPTY;
		}

		Player player = ServerHelper.getPlayer(event);
		if (player == null) {
			return ServerResult.EMPTY;
		}

		RegisteredServer server = ServerHelper.getServer(serverName);

		if (server == null) {
			return new ServerResultInvalid(ServerStatus.FAILURE, serverName, player);
		}

		if (!ServerPermission.hasDefaultPermission(player, serverName)) {
			return new ServerResultInvalid(ServerStatus.NO_PERMISSION, serverName, player);
		}

		return new ServerResultValid(player, server);
	}
}
