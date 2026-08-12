package dev.encode42.serverhopper.messages.meta;

import dev.encode42.serverhopper.messages.Message;
import net.kyori.adventure.text.Component;

public class SuccessfulMessage extends Message {
	public static final String DEFAULT_VALUE = "<green>Configuration successfully reloaded!";

	public SuccessfulMessage(String message) {
		super(message);
	}

	public Component parse() {
		return super.parse();
	}
}
