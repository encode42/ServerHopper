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
	private String offline = OfflineComponent.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String online = OnlineComponent.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String special = SpecialComponent.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String connectedTooltip = ConnectedTooltip.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String offlineTooltip = OfflineTooltip.DEFAULT_VALUE;

	public TitleMessage getTitle() {
		return new TitleMessage(this.title);
	}

	public BodyMessage getBody() {
		return new BodyMessage(this.body);
	}

	public ButtonMessage getButton() {
		return new ButtonMessage(this.button);
	}

	public OfflineComponent getOffline() {
		return new OfflineComponent(this.offline);
	}

	public OnlineComponent getOnline() {
		return new OnlineComponent(this.online);
	}

	public SpecialComponent getSpecial() {
		return new SpecialComponent(this.special);
	}

	public ConnectedTooltip getConnectedTooltip() {
		return new ConnectedTooltip(this.connectedTooltip);
	}

	public OfflineTooltip getOfflineTooltip() {
		return new OfflineTooltip(this.offlineTooltip);
	}
}
