package dev.encode42.serverhopper.data.messages;

import dev.encode42.serverhopper.messages.meta.FailedReloadMessage;
import dev.encode42.serverhopper.messages.meta.SuccessfulReloadMessage;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public class MessagesMeta {
	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String failedReload = FailedReloadMessage.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String successfulReload = SuccessfulReloadMessage.DEFAULT_VALUE;

	public FailedReloadMessage getFailedReload() {
		return new FailedReloadMessage(this.failedReload);
	}

	public SuccessfulReloadMessage getSuccessfulReload() {
		return new SuccessfulReloadMessage(this.successfulReload);
	}
}
