package dev.encode42.serverhopper.data.messages;

import dev.encode42.serverhopper.messages.move.MoveReceivedMessage;
import dev.encode42.serverhopper.messages.move.MoveSentAllMessage;
import dev.encode42.serverhopper.messages.move.MoveSentMessage;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public class MessagesMove {
	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String moveReceived = MoveReceivedMessage.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String moveSentAll = MoveSentAllMessage.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String moveSent = MoveSentMessage.DEFAULT_VALUE;

	public MoveReceivedMessage getMoveReceived() {
		return new MoveReceivedMessage(this.moveReceived);
	}

	public MoveSentAllMessage getMoveSentAll() {
		return new MoveSentAllMessage(this.moveSentAll);
	}

	public MoveSentMessage getMoveSent() {
		return new MoveSentMessage(this.moveSent);
	}
}
