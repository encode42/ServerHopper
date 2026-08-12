package dev.encode42.serverhopper.data.messages;

import dev.encode42.serverhopper.messages.meta.FailedMessage;
import dev.encode42.serverhopper.messages.meta.SuccessfulMessage;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public class MessagesMeta {
	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String failedReload = FailedMessage.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String successfulReload = SuccessfulMessage.DEFAULT_VALUE;

	public FailedMessage getFailedReload() {
		return new FailedMessage(this.failedReload);
	}

	public SuccessfulMessage getSuccessfulReload() {
		return new SuccessfulMessage(this.successfulReload);
	}
}
