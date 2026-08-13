package dev.encode42.serverhopper.data.messages;

import dev.encode42.serverhopper.data.Initializable;
import dev.encode42.serverhopper.messages.meta.FailedReloadMessage;
import dev.encode42.serverhopper.messages.meta.SuccessfulReloadMessage;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public class MessagesMeta implements Initializable {
	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String failedReload = FailedReloadMessage.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String successfulReload = SuccessfulReloadMessage.DEFAULT_VALUE;

	private transient FailedReloadMessage failedReloadMessage;

	private transient SuccessfulReloadMessage successfulReloadMessage;

	public @NotNull FailedReloadMessage failedReload() {
		return this.failedReloadMessage;
	}

	public @NotNull SuccessfulReloadMessage successfulReload() {
		return this.successfulReloadMessage;
	}

	@Override
	public void init() {
		this.failedReloadMessage = new FailedReloadMessage(this.failedReload);
		this.successfulReloadMessage = new SuccessfulReloadMessage(this.successfulReload);
	}
}
