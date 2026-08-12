package dev.encode42.serverhopper.data.messages;

import dev.encode42.serverhopper.messages.join.InvalidJoinException;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public class MessagesJoin {
	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private final String invalidJoin = InvalidJoinException.DEFAULT_VALUE;

	public InvalidJoinException getInvalidJoin() {
		return new InvalidJoinException(this.invalidJoin);
	}
}
