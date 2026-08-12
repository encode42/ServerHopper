package dev.encode42.serverhopper.data.messages;

import dev.encode42.serverhopper.messages.invites.InvalidInviteException;
import dev.encode42.serverhopper.messages.invites.InvalidServerInviteException;
import dev.encode42.serverhopper.messages.invites.InviteReceivedMessage;
import dev.encode42.serverhopper.messages.invites.InviteSentMessage;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public class MessagesInvites {
	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String invalidInvite = InvalidInviteException.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String invalidServerInvite = InvalidServerInviteException.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String inviteReceived = InviteReceivedMessage.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String inviteSent = InviteSentMessage.DEFAULT_VALUE;

	public InvalidInviteException getInvalidInvite() {
		return new InvalidInviteException(this.invalidInvite);
	}

	public InvalidServerInviteException getInvalidServerInvite() {
		return new InvalidServerInviteException(this.invalidServerInvite);
	}

	public InviteReceivedMessage getInviteReceived() {
		return new InviteReceivedMessage(this.inviteReceived);
	}

	public InviteSentMessage getInviteSent() {
		return new InviteSentMessage(this.inviteSent);
	}
}
