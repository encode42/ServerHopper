package dev.encode42.serverhopper.data.messages;

import dev.encode42.serverhopper.data.Initializable;
import dev.encode42.serverhopper.messages.arguments.InvalidPlayerException;
import dev.encode42.serverhopper.messages.arguments.InvalidServerException;
import dev.encode42.serverhopper.messages.arguments.NotPlayerException;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public class MessagesArguments implements Initializable {
	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String invalidPlayer = InvalidPlayerException.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String invalidServer = InvalidServerException.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String notPlayer = NotPlayerException.DEFAULT_VALUE;

	private transient InvalidPlayerException invalidPlayerException;

	private transient InvalidServerException invalidServerException;

	private transient NotPlayerException notPlayerException;

	public @NotNull InvalidPlayerException invalidPlayer() {
		return this.invalidPlayerException;
	}

	public @NotNull InvalidServerException invalidServer() {
		return this.invalidServerException;
	}

	public @NotNull NotPlayerException notPlayer() {
		return this.notPlayerException;
	}

	@Override
	public void init() {
		this.invalidPlayerException = new InvalidPlayerException(this.invalidPlayer);
		this.invalidServerException = new InvalidServerException(this.invalidServer);
		this.notPlayerException = new NotPlayerException(this.notPlayer);
	}
}
