package dev.encode42.serverhopper.messages.dialog;

import dev.encode42.serverhopper.messages.Message;
import dev.encode42.serverhopper.messages.placeholders.ServerTranslation;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

public class OfflineTooltip extends Message {
	public static final String DEFAULT_VALUE = "<red>You cannot connect to offline servers.";

	public OfflineTooltip(String message) {
		super(message);
	}

	public Component parse(String server) {
		return super.parse(
			Placeholder.unparsed("server", server),
			ServerTranslation.resolve(server)
		);
	}
}
