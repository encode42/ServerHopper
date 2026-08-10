package dev.encode42.serverhopper.config.messages;

import dev.encode42.serverhopper.config.ConfigNode;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public class MessagesRoot extends ConfigNode {
	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private MessagesArguments arguments = new MessagesArguments();

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private MessagesMeta meta = new MessagesMeta();

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private MessagesDialog dialog = new MessagesDialog();

	public MessagesMeta meta() {
		return this.meta;
	}

	public MessagesArguments arguments() {
		return this.arguments;
	}

	public MessagesDialog dialog() {
		return this.dialog;
	}
}

