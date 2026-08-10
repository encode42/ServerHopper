package dev.encode42.serverhopper.messages.meta;

import dev.encode42.serverhopper.messages.Message;
import net.kyori.adventure.text.Component;

public class FailedReloadMessage extends Message {
	public static final String DEFAULT_VALUE = "<red>Configuration failed to reload, check console for exception.";

	public FailedReloadMessage(String message) {
		super(message);
	}

	public Component parse() {
		return super.parse();
	}
}
