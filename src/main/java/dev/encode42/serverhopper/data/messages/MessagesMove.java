package dev.encode42.serverhopper.data.messages;

import dev.encode42.serverhopper.messages.move.ReceivedMessage;
import dev.encode42.serverhopper.messages.move.SentAllMessage;
import dev.encode42.serverhopper.messages.move.SentMessage;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public class MessagesMove {
	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String received = ReceivedMessage.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String sentAll = SentAllMessage.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String sent = SentMessage.DEFAULT_VALUE;

	public ReceivedMessage getReceived() {
		return new ReceivedMessage(this.received);
	}

	public SentAllMessage getSentAll() {
		return new SentAllMessage(this.sentAll);
	}

	public SentMessage getSent() {
		return new SentMessage(this.sent);
	}
}
