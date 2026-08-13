package dev.encode42.serverhopper.data.messages;

import dev.encode42.serverhopper.data.Initializable;
import dev.encode42.serverhopper.messages.move.ReceivedMessage;
import dev.encode42.serverhopper.messages.move.SentAllMessage;
import dev.encode42.serverhopper.messages.move.SentMessage;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public class MessagesMove implements Initializable {
	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String received = ReceivedMessage.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String sentAll = SentAllMessage.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String sent = SentMessage.DEFAULT_VALUE;

	private transient ReceivedMessage receivedMessage;

	private transient SentAllMessage sentAllMessage;

	private transient SentMessage sentMessage;

	public @NotNull ReceivedMessage received() {
		return this.receivedMessage;
	}

	public @NotNull SentAllMessage sentAll() {
		return this.sentAllMessage;
	}

	public @NotNull SentMessage sent() {
		return this.sentMessage;
	}

	@Override
	public void init() {
		this.receivedMessage = new ReceivedMessage(this.received);
		this.sentAllMessage = new SentAllMessage(this.sentAll);
		this.sentMessage = new SentMessage(this.sent);
	}
}
