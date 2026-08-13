package dev.encode42.serverhopper.data.messages;

import dev.encode42.serverhopper.data.Initializable;
import dev.encode42.serverhopper.messages.dialog.*;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

@ConfigSerializable
public class MessagesDialog implements Initializable {
	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String body = BodyMessage.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	@Setting("connected-tooltip")
	private String buttonConnected = ButtonConnectedTooltip.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String button = ButtonMessage.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	@Setting("offline-tooltip")
	private String buttonOffline = ButtonOfflineTooltip.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String offline = OfflineComponent.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String online = OnlineComponent.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String special = SpecialComponent.DEFAULT_VALUE;

	@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
	private String title = TitleMessage.DEFAULT_VALUE;

	private transient BodyMessage bodyMessage;

	private transient ButtonConnectedTooltip buttonConnectedTooltip;

	private transient ButtonMessage buttonMessage;

	private transient ButtonOfflineTooltip buttonOfflineTooltip;

	private transient OfflineComponent offlineComponent;

	private transient OnlineComponent onlineComponent;

	private transient SpecialComponent specialComponent;

	private transient TitleMessage titleMessage;

	public @NotNull BodyMessage body() {
		return this.bodyMessage;
	}

	public @NotNull ButtonConnectedTooltip buttonConnected() {
		return this.buttonConnectedTooltip;
	}

	public @NotNull ButtonMessage button() {
		return this.buttonMessage;
	}

	public @NotNull ButtonOfflineTooltip buttonOffline() {
		return this.buttonOfflineTooltip;
	}

	public @NotNull OfflineComponent offline() {
		return this.offlineComponent;
	}

	public @NotNull OnlineComponent online() {
		return this.onlineComponent;
	}

	public @NotNull SpecialComponent special() {
		return this.specialComponent;
	}

	public @NotNull TitleMessage title() {
		return this.titleMessage;
	}

	@Override
	public void init() {
		this.bodyMessage = new BodyMessage(this.body);
		this.buttonConnectedTooltip = new ButtonConnectedTooltip(this.buttonConnected);
		this.buttonMessage = new ButtonMessage(this.button);
		this.buttonOfflineTooltip = new ButtonOfflineTooltip(this.buttonOffline);
		this.offlineComponent = new OfflineComponent(this.offline);
		this.onlineComponent = new OnlineComponent(this.online);
		this.specialComponent = new SpecialComponent(this.special);
		this.titleMessage = new TitleMessage(this.title);
	}
}
