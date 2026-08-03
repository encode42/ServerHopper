package dev.encode42.serverhopper.config.messages;

import dev.encode42.serverhopper.messages.arguments.NotPlayerException;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public class MessagesArguments {
	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String notPlayer = NotPlayerException.DEFAULT_VALUE;

	public NotPlayerException getNotPlayer() {
		return new NotPlayerException(this.notPlayer);
	}
}
