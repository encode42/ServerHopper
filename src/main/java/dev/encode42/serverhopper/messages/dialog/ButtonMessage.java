package dev.encode42.serverhopper.messages.dialog;

import dev.encode42.serverhopper.messages.Message;
import dev.encode42.serverhopper.messages.placeholders.ServerTranslation;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

public class ButtonMessage extends Message {
	public static final String DEFAULT_VALUE = "<server><status><special>";

	public ButtonMessage(String message) {
		super(message);
	}

	public Component parse(String server, Component status, Component special) {
		return super.parse(
			Placeholder.component("status", status),
			Placeholder.component("special", special),
			Placeholder.unparsed("server", server),
			ServerTranslation.resolve(server)
		);
	}
}
