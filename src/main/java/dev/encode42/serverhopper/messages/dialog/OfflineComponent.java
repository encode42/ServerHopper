package dev.encode42.serverhopper.messages.dialog;

import dev.encode42.serverhopper.messages.Message;
import net.kyori.adventure.text.Component;

public class OfflineComponent extends Message {
	public static final String DEFAULT_VALUE = " <red>Offline</red>";

	public OfflineComponent(String message) {
		super(message);
	}

	public Component parse() {
		return super.parse();
	}
}
