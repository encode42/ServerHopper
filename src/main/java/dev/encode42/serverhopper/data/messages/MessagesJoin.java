package dev.encode42.serverhopper.data.messages;

import dev.encode42.serverhopper.messages.join.InvalidException;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public class MessagesJoin {
	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private final String invalid = InvalidException.DEFAULT_VALUE;

	public InvalidException getInvalid() {
		return new InvalidException(this.invalid);
	}
}
