package dev.encode42.serverhopper.messages;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

public abstract class Message {
	private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

	private final String message;

	public Message(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	protected Component parse(TagResolver... resolvers) {
		return Message.MINI_MESSAGE.deserialize(this.message, resolvers);
	}
}
