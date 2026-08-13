package dev.encode42.serverhopper.messages.dialog;

import dev.encode42.serverhopper.messages.Message;
import dev.encode42.serverhopper.messages.placeholders.ServerTranslation;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

public class ConnectedTooltip extends Message {
	public static final String DEFAULT_VALUE = "<red>You are already connected to this server!";

	public ConnectedTooltip(String message) {
		super(message);
	}

	public Component parse(String serverName) {
		return super.parse(
			Placeholder.unparsed("server", serverName),
			ServerTranslation.resolve(serverName)
		);
	}
}
