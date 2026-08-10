package dev.encode42.serverhopper.config.messages;

import dev.encode42.serverhopper.config.ConfigNode;
import dev.encode42.serverhopper.messages.meta.FailedReloadMessage;
import dev.encode42.serverhopper.messages.meta.SuccessfulReloadMessage;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public class MessagesMeta extends ConfigNode {
	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String successfulReload = SuccessfulReloadMessage.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String failedReload = FailedReloadMessage.DEFAULT_VALUE;

	public SuccessfulReloadMessage getSuccessfulReload() {
		return new SuccessfulReloadMessage(this.successfulReload);
	}

	public FailedReloadMessage getFailedReload() {
		return new FailedReloadMessage(this.failedReload);
	}
}
