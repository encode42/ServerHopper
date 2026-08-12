package dev.encode42.serverhopper.messages.move;

import dev.encode42.serverhopper.messages.Message;
import dev.encode42.serverhopper.messages.placeholders.ServerTranslation;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

public class SentAllMessage extends Message {
	public static final String DEFAULT_VALUE = "All players successfully moved to server <server>.";

	public SentAllMessage(String message) {
		super(message);
	}

	public Component parse(String server) {
		return super.parse(
			Placeholder.unparsed("server", server),
			ServerTranslation.resolve(server)
		);
	}
}
