package dev.encode42.serverhopper.messages.dialog;

import dev.encode42.serverhopper.messages.Message;
import net.kyori.adventure.text.Component;

public class SpecialComponent extends Message {
	public static final String DEFAULT_VALUE = " <gold>⭐</gold>";

	public SpecialComponent(String message) {
		super(message);
	}

	public Component parse() {
		return super.parse();
	}
}
