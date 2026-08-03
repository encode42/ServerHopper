package dev.encode42.serverhopper.config.messages;

import dev.encode42.serverhopper.config.ConfigNode;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public class MessagesRoot extends ConfigNode {
	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private MessagesArguments arguments = new MessagesArguments();

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private MessagesDialog dialog = new MessagesDialog();

	public MessagesArguments arguments() {
		return this.arguments;
	}

	public MessagesDialog dialog() {
		return this.dialog;
	}
}

