package dev.encode42.serverhopper.data.messages;

import dev.encode42.serverhopper.messages.invites.InvalidPlayerException;
import dev.encode42.serverhopper.messages.invites.InvalidServerException;
import dev.encode42.serverhopper.messages.invites.ReceivedMessage;
import dev.encode42.serverhopper.messages.invites.SentMessage;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public class MessagesInvites {
	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String invalidPlayer = InvalidPlayerException.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String invalidServer = InvalidServerException.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String received = ReceivedMessage.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String sent = SentMessage.DEFAULT_VALUE;

	public InvalidPlayerException getInvalidPlayer() {
		return new InvalidPlayerException(this.invalidPlayer);
	}

	public InvalidServerException getInvalidServer() {
		return new InvalidServerException(this.invalidServer);
	}

	public ReceivedMessage getReceived() {
		return new ReceivedMessage(this.received);
	}

	public SentMessage getSent() {
		return new SentMessage(this.sent);
	}
}
