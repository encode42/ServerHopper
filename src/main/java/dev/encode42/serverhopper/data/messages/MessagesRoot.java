package dev.encode42.serverhopper.data.messages;

import dev.encode42.serverhopper.data.ConfigNode;
import dev.encode42.serverhopper.data.Initializable;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public class MessagesRoot extends ConfigNode implements Initializable {
	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private MessagesArguments arguments = new MessagesArguments();

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private MessagesConnection connection = new MessagesConnection();

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private MessagesDialog dialog = new MessagesDialog();

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private MessagesInvites invites = new MessagesInvites();

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private MessagesJoin join = new MessagesJoin();

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private MessagesMeta meta = new MessagesMeta();

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private MessagesMove move = new MessagesMove();

	public MessagesArguments arguments() {
		return this.arguments;
	}

	public MessagesConnection connection() {
		return this.connection;
	}

	public MessagesDialog dialog() {
		return this.dialog;
	}

	public MessagesInvites invites() {
		return this.invites;
	}

	public MessagesJoin join() {
		return this.join;
	}

	public MessagesMeta meta() {
		return this.meta;
	}

	public MessagesMove move() {
		return this.move;
	}

	@Override
	public void init() {
		this.arguments.init();
		this.connection.init();
		this.dialog.init();
		this.invites.init();
		this.join.init();
		this.meta.init();
		this.move.init();
	}
}

