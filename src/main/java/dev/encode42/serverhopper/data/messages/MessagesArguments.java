package dev.encode42.serverhopper.data.messages;

import dev.encode42.serverhopper.messages.arguments.InvalidPlayerException;
import dev.encode42.serverhopper.messages.arguments.InvalidServerException;
import dev.encode42.serverhopper.messages.arguments.NotPlayerException;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public class MessagesArguments {
	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String invalidPlayer = InvalidPlayerException.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String invalidServer = InvalidServerException.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String notPlayer = NotPlayerException.DEFAULT_VALUE;

	public InvalidPlayerException getInvalidPlayer() {
		return new InvalidPlayerException(this.invalidPlayer);
	}

	public InvalidServerException getInvalidServer() {
		return new InvalidServerException(this.invalidServer);
	}

	public NotPlayerException getNotPlayer() {
		return new NotPlayerException(this.notPlayer);
	}
}
