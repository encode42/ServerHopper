package dev.encode42.serverhopper.data.messages;

import dev.encode42.serverhopper.data.Initializable;
import dev.encode42.serverhopper.messages.invites.*;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public class MessagesInvites implements Initializable {
	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String invalidPlayer = InvalidPlayerException.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String invalidSelf = InvalidSelfException.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String invalidServer = InvalidServerException.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String received = ReceivedMessage.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String sent = SentMessage.DEFAULT_VALUE;

	private transient InvalidPlayerException invalidPlayerException;

	private transient InvalidSelfException invalidSelfException;

	private transient InvalidServerException invalidServerException;

	private transient ReceivedMessage receivedMessage;

	private transient SentMessage sentMessage;

	public @NotNull InvalidPlayerException invalidPlayer() {
		return this.invalidPlayerException;
	}

	public @NotNull InvalidSelfException invalidSelf() {
		return this.invalidSelfException;
	}

	public @NotNull InvalidServerException invalidServer() {
		return this.invalidServerException;
	}

	public @NotNull ReceivedMessage received() {
		return this.receivedMessage;
	}

	public @NotNull SentMessage sent() {
		return this.sentMessage;
	}

	@Override
	public void init() {
		this.invalidPlayerException = new InvalidPlayerException(this.invalidPlayer);
		this.invalidSelfException = new InvalidSelfException(this.invalidSelf);
		this.invalidServerException = new InvalidServerException(this.invalidServer);
		this.receivedMessage = new ReceivedMessage(this.received);
		this.sentMessage = new SentMessage(this.sent);
	}
}
