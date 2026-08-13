package dev.encode42.serverhopper.data.messages;

import dev.encode42.serverhopper.data.Initializable;
import dev.encode42.serverhopper.messages.join.InvalidException;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public class MessagesJoin implements Initializable {
	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String invalid = InvalidException.DEFAULT_VALUE;

	private transient InvalidException invalidException;

	public @NotNull InvalidException invalid() {
		return this.invalidException;
	}

	@Override
	public void init() {
		this.invalidException = new InvalidException(this.invalid);
	}
}
