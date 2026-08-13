package dev.encode42.serverhopper.data.messages;

import dev.encode42.serverhopper.data.Initializable;
import dev.encode42.serverhopper.messages.connection.ExecutorConnectedException;
import dev.encode42.serverhopper.messages.connection.InvalidPermissionException;
import dev.encode42.serverhopper.messages.connection.InvalidServerException;
import dev.encode42.serverhopper.messages.connection.TargetConnectedException;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public class MessagesConnection implements Initializable {
	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String executorConnected = ExecutorConnectedException.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String invalidPermission = InvalidPermissionException.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String invalidServer = InvalidServerException.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String targetConnected = TargetConnectedException.DEFAULT_VALUE;

	private transient ExecutorConnectedException executorConnectedException;

	private transient InvalidPermissionException invalidPermissionException;

	private transient InvalidServerException invalidServerException;

	private transient TargetConnectedException targetConnectedException;

	public @NotNull ExecutorConnectedException executorConnected() {
		return this.executorConnectedException;
	}

	public @NotNull InvalidPermissionException invalidPermission() {
		return this.invalidPermissionException;
	}

	public @NotNull InvalidServerException invalidServer() {
		return this.invalidServerException;
	}

	public @NotNull TargetConnectedException targetConnected() {
		return this.targetConnectedException;
	}

	@Override
	public void init() {
		this.executorConnectedException = new ExecutorConnectedException(this.executorConnected);
		this.invalidPermissionException = new InvalidPermissionException(this.invalidPermission);
		this.invalidServerException = new InvalidServerException(this.invalidServer);
		this.targetConnectedException = new TargetConnectedException(this.targetConnected);
	}
}
