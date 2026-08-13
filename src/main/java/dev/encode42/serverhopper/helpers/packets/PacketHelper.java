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

public class PacketHelper {
	public static String PACKET_KEY = "hop";
	public static String DATA_KEY = "server";

	public static ResourceLocation PACKET_EVENTS_KEY = new ResourceLocation(ServerHopper.ID, PacketHelper.PACKET_KEY);

	public static CustomClickEvent getClickEvent(RegisteredServer server) {
		return PacketHelper.getClickEvent(server.getServerInfo().getName());
	}

	public static CustomClickEvent getClickEvent(String serverName) {
		NBTCompound data = new NBTCompound();

		data.setTag(PacketHelper.DATA_KEY, new NBTString(serverName));

		return new CustomClickEvent(
			PacketHelper.PACKET_EVENTS_KEY,
			data
		);
	}

	public static String getServerName(WrapperPlayClientCustomClickAction packet) {
		if (!packet.getId().equals(PacketHelper.PACKET_EVENTS_KEY)) {
			return null;
		}

		NBT payload = packet.getPayload();

		if (payload instanceof NBTCompound data) {
			return data.getStringTagValueOrNull(PacketHelper.DATA_KEY);
		}

		if (payload instanceof NBTString data) {
			CompoundBinaryTag tag;

			try {
				tag = TagStringIO.tagStringIO().asCompound(data.getValue());
			} catch (IOException exception) {
				return null;
			}

			return tag.getString(PacketHelper.DATA_KEY);
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

	public static PacketResult validate(PacketReceiveEvent event) {
		WrapperPlayClientCustomClickAction clickAction = new WrapperPlayClientCustomClickAction(event);

		String serverName = PacketHelper.getServerName(clickAction);
		if (serverName == null) {
			return PacketResult.EMPTY;
		}

		Player player = PacketHelper.getPlayer(event);
		if (player == null) {
			return PacketResult.EMPTY;
		}

		RegisteredServer server = PacketHelper.getServer(serverName);

		if (server == null) {
			return new PacketResultInvalid(PacketStatus.FAILURE, serverName, player);
		}

		if (!ServerPermission.hasDefaultPermission(player, serverName)) {
			return new PacketResultInvalid(PacketStatus.NO_PERMISSION, serverName, player);
		}

		return new PacketResultValid(player, server);
	}
}
