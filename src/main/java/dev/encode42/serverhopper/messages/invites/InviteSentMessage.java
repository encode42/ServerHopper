package dev.encode42.serverhopper.messages.invites;

import dev.encode42.serverhopper.messages.Message;
import dev.encode42.serverhopper.messages.placeholders.ServerTranslation;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

public class InviteSentMessage extends Message {
	public static final String DEFAULT_VALUE = "Invite to <server> successfully sent to <username>.";

	public InviteSentMessage(String message) {
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
