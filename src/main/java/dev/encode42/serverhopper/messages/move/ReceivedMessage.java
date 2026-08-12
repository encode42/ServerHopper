package dev.encode42.serverhopper.messages.move;

import dev.encode42.serverhopper.messages.Message;
import dev.encode42.serverhopper.messages.placeholders.ServerTranslation;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

public class ReceivedMessage extends Message {
	public static final String DEFAULT_VALUE = "You have been moved to server <server>.";

	public ReceivedMessage(String message) {
		super(message);
	}

	public Component parse(String server) {
		return super.parse(
			Placeholder.unparsed("server", server),
			ServerTranslation.resolve(server)
		);
	}
}
