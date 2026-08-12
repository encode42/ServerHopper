package dev.encode42.serverhopper.data.messages;

import dev.encode42.serverhopper.messages.connection.ExecutorConnectedException;
import dev.encode42.serverhopper.messages.connection.InvalidPermissionException;
import dev.encode42.serverhopper.messages.connection.InvalidServerException;
import dev.encode42.serverhopper.messages.connection.TargetConnectedException;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public class MessagesConnection {
	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String executorConnected = ExecutorConnectedException.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String invalidPermission = InvalidPermissionException.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String invalidServer = InvalidServerException.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String targetConnected = TargetConnectedException.DEFAULT_VALUE;

	public ExecutorConnectedException getExecutorConnected() {
		return new ExecutorConnectedException(this.executorConnected);
	}

	public InvalidPermissionException getInvalidPermission() {
		return new InvalidPermissionException(this.invalidPermission);
	}

	public InvalidServerException getInvalidServer() {
		return new InvalidServerException(this.invalidServer);
	}

	public TargetConnectedException getTargetConnected() {
		return new TargetConnectedException(this.targetConnected);
	}
}
