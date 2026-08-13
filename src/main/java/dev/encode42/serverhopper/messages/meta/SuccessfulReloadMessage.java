package dev.encode42.serverhopper.messages.meta;

import dev.encode42.serverhopper.messages.Message;
import net.kyori.adventure.text.Component;

public class SuccessfulReloadMessage extends Message {
	public static final String DEFAULT_VALUE = "<green>Configuration successfully reloaded!";

	public SuccessfulReloadMessage(String message) {
		super(message);
	}

	public Component parse() {
		return super.parse();
	}
}
