package dev.encode42.serverhopper.messages.move;

import dev.encode42.serverhopper.messages.Message;
import dev.encode42.serverhopper.messages.placeholders.ServerTranslation;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

public class SentMessage extends Message {
	public static final String DEFAULT_VALUE = "<username> successfully moved to server <server>.";

	public SentMessage(String message) {
		super(message);
	}

	public Component parse(String username, String server) {
		return super.parse(
			Placeholder.unparsed("username", username),
			Placeholder.unparsed("server", server),
			ServerTranslation.resolve(server)
		);
	}
}
