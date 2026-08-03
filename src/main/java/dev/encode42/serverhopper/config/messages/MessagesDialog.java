package dev.encode42.serverhopper.config.messages;

import dev.encode42.serverhopper.messages.dialog.*;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public class MessagesDialog {
	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String title = TitleMessage.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String body = BodyMessage.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String button = ButtonMessage.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String offline = OfflineMessage.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String online = OnlineMessage.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String connectedTooltip = ConnectedTooltipMessage.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String offlineTooltip = OfflineTooltipMessage.DEFAULT_VALUE;

	public TitleMessage getTitle() {
		return new TitleMessage(this.title);
	}

	public BodyMessage getBody() {
		return new BodyMessage(this.body);
	}

	public ButtonMessage getButton() {
		return new ButtonMessage(this.button);
	}

	public OfflineMessage getOffline() {
		return new OfflineMessage(this.offline);
	}

	public OnlineMessage getOnline() {
		return new OnlineMessage(this.online);
	}

	public ConnectedTooltipMessage getConnectedTooltip() {
		return new ConnectedTooltipMessage(this.connectedTooltip);
	}

	public OfflineTooltipMessage getOfflineTooltip() {
		return new OfflineTooltipMessage(this.offlineTooltip);
	}
}
