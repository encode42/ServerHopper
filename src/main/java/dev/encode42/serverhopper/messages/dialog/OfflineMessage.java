package dev.encode42.serverhopper.messages.dialog;

import dev.encode42.serverhopper.messages.Message;
import net.kyori.adventure.text.Component;

public class OfflineMessage extends Message {
	public static final String DEFAULT_VALUE = "<red>Offline";

	public OfflineMessage(String message) {
		super(message);
	}

	public Component parse() {
		return super.parse();
	}
}
