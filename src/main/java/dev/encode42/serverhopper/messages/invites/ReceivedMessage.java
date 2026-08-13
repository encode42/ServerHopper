package dev.encode42.serverhopper.messages.invites;

import dev.encode42.serverhopper.helpers.packets.PacketHelper;
import dev.encode42.serverhopper.messages.Message;
import dev.encode42.serverhopper.messages.placeholders.ServerTranslation;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

public class ReceivedMessage extends Message {
	public static final String DEFAULT_VALUE = "<username> has invited you to their server, <server>!\n<blue><underlined><click_event>Click here to join them.";

	public ReceivedMessage(String message) {
		super(message);
	}

	public Component parse(String username, String serverName) {
		ClickEvent clickEvent = PacketHelper.getClickEvent(serverName).asAdventure();

		return super.parse(
			Placeholder.unparsed("username", username),
			Placeholder.unparsed("server", serverName),
			Placeholder.styling("click_event", clickEvent),
			ServerTranslation.resolve(serverName)
		);
	}


}
