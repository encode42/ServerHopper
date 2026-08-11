package dev.encode42.serverhopper.messages.dialog;

import dev.encode42.serverhopper.messages.Message;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

public class OnlineComponent extends Message {
	public static final String DEFAULT_VALUE = " <gray><connected_players>/<max_players></gray>";

	public OnlineComponent(String message) {
		super(message);
	}

	public Component parse(int connectedPlayers, int maxPlayers) {
		return super.parse(
			Placeholder.unparsed("connected_players", String.valueOf(connectedPlayers)),
			Placeholder.unparsed("max_players", String.valueOf(maxPlayers))
		);
	}
}
