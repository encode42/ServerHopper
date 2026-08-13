package dev.encode42.serverhopper.dialogs;

import com.github.retrooper.packetevents.protocol.chat.clickevent.ClickEvent;
import com.github.retrooper.packetevents.protocol.dialog.CommonDialogData;
import com.github.retrooper.packetevents.protocol.dialog.Dialog;
import com.github.retrooper.packetevents.protocol.dialog.DialogAction;
import com.github.retrooper.packetevents.protocol.dialog.MultiActionDialog;
import com.github.retrooper.packetevents.protocol.dialog.action.StaticAction;
import com.github.retrooper.packetevents.protocol.dialog.body.DialogBody;
import com.github.retrooper.packetevents.protocol.dialog.body.PlainMessage;
import com.github.retrooper.packetevents.protocol.dialog.body.PlainMessageDialogBody;
import com.github.retrooper.packetevents.protocol.dialog.button.ActionButton;
import com.github.retrooper.packetevents.protocol.dialog.button.CommonButtonData;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ServerConnection;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import com.velocitypowered.api.proxy.server.ServerInfo;
import dev.encode42.serverhopper.data.ConfigManager;
import dev.encode42.serverhopper.helpers.connections.ConnectionHelper;
import dev.encode42.serverhopper.helpers.packets.PacketHelper;
import dev.encode42.serverhopper.helpers.pings.PingCache;
import dev.encode42.serverhopper.helpers.pings.PingInfo;
import dev.encode42.serverhopper.helpers.pings.PingInfoOffline;
import dev.encode42.serverhopper.helpers.pings.PingInfoOnline;
import dev.encode42.serverhopper.permissions.ServerPermission;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.List;

public class ServerDialog {
	private final Player player;
	private final String serverName;

	private final int buttonWidth;
	private final int totalColumns;

	public ServerDialog(Player player) {
		this(player, 150, 2);
	}

	public ServerDialog(Player player, int buttonWidth, int totalColumns) {
		this.player = player;

		ServerConnection serverConnection = ConnectionHelper.getConnection(player);

		if (serverConnection == null) {
			this.serverName = "unknown";
		} else {
			RegisteredServer server = serverConnection.getServer();
			ServerInfo serverInfo = server.getServerInfo();

			this.serverName = serverInfo.getName();
		}

		this.buttonWidth = buttonWidth;
		this.totalColumns = totalColumns;
	}

	public Dialog create() {
		Component dialogTitle = ConfigManager.messages()
			.dialog()
			.getTitle()
			.parse(this.serverName);

		DialogBody dialogBody = this.createBody();

		CommonDialogData dialogData = new CommonDialogData(
			dialogTitle,
			null,
			true,
			false,
			DialogAction.CLOSE,
			List.of(
				dialogBody
			),
			List.of()
		);

		List<ActionButton> buttons = this.createButtons();

		return new MultiActionDialog(
			dialogData,
			buttons,
			null,
			this.totalColumns
		);
	}

	private DialogBody createBody() {
		Component dialogBody = ConfigManager.messages()
			.dialog()
			.getBody()
			.parse(this.serverName);

		PlainMessage dialogBodyMessage = new PlainMessage(
			dialogBody,
			this.buttonWidth * this.totalColumns
		);

		return new PlainMessageDialogBody(dialogBodyMessage);
	}

	private List<ActionButton> createButtons() {
		List<ActionButton> buttons = new ArrayList<>();

		boolean willSortSpecial = ConfigManager.root().willSortSpecial();
		boolean willSortOffline = ConfigManager.root().willSortOffline();

		if (!willSortSpecial && !willSortOffline) {
			buttons.addAll(this.createAllButtons(OnlineStatus.ANY, SpecialStatus.ANY));
		} else {
			if (willSortSpecial) {
				buttons.addAll(this.createAllButtons(OnlineStatus.ANY, SpecialStatus.REQUIRED));
			}

			SpecialStatus specialStatus = willSortSpecial ? SpecialStatus.SKIP : SpecialStatus.ANY;

			if (willSortOffline) {
				buttons.addAll(this.createAllButtons(OnlineStatus.ONLINE, specialStatus));
				buttons.addAll(this.createAllButtons(OnlineStatus.OFFLINE, specialStatus));
			} else {
				buttons.addAll(this.createAllButtons(OnlineStatus.ANY, specialStatus));
			}
		}

		return buttons;
	}

	private List<ActionButton> createAllButtons(OnlineStatus onlineStatus, SpecialStatus specialStatus) {
		List<ActionButton> buttons = new ArrayList<>();

		for (PingInfo pingInfo : PingCache.getAll()) {
			if (!ServerPermission.hasDefaultPermission(this.player, pingInfo.getName())) {
				continue;
			}

			switch (onlineStatus) {
				case ONLINE -> {
					if (!pingInfo.isOnline()) {
						continue;
					}
				}

				case OFFLINE -> {
					if (pingInfo.isOnline()) {
						continue;
					}
				}
			}

			switch (specialStatus) {
				case REQUIRED -> {
					if (!pingInfo.isSpecial()) {
						continue;
					}
				}

				case SKIP -> {
					if (pingInfo.isSpecial()) {
						continue;
					}
				}
			}

			ActionButton button = this.createButton(pingInfo);

			buttons.add(button);
		}

		return buttons;
	}

	private ActionButton createButton(PingInfo pingInfo) {
		if (pingInfo.isOnline()) {
			return this.createOnlineButton((PingInfoOnline) pingInfo);
		} else {
			return this.createOfflineButton((PingInfoOffline) pingInfo);
		}
	}

	private ActionButton createOnlineButton(PingInfoOnline pingInfo) {
		String pingName = pingInfo.getName();

		boolean isConnected = pingName.equals(this.serverName);

		Component onlineStatus = ConfigManager.messages()
			.dialog()
			.getOnline()
			.parse(
				pingInfo.getConnectedPlayers(),
				pingInfo.getMaxPlayers()
			);

		Component connectedTooltip = null;

		if (isConnected) {
			connectedTooltip = ConfigManager.messages()
				.dialog()
				.getConnectedTooltip()
				.parse(this.serverName);
		}

		Component buttonLabel = this.parseButtonLabel(pingName, onlineStatus, pingInfo.isSpecial());

		CommonButtonData buttonData = new CommonButtonData(
			buttonLabel,
			connectedTooltip,
			this.buttonWidth
		);

		if (isConnected) {
			return new ActionButton(buttonData, null);
		}

		ClickEvent clickEvent = PacketHelper.getClickEvent(pingName);
		StaticAction clickAction = new StaticAction(clickEvent);

		return new ActionButton(buttonData, clickAction);
	}

	private ActionButton createOfflineButton(PingInfoOffline pingInfo) {
		String pingName = pingInfo.getName();

		Component offlineStatus = ConfigManager.messages()
			.dialog()
			.getOffline()
			.parse();

		Component offlineTooltip = ConfigManager.messages()
			.dialog()
			.getOfflineTooltip()
			.parse(this.serverName);

		Component buttonLabel = this.parseButtonLabel(pingName, offlineStatus, pingInfo.isSpecial());

		CommonButtonData buttonData = new CommonButtonData(
			buttonLabel,
			offlineTooltip,
			this.buttonWidth
		);

		return new ActionButton(buttonData, null);
	}

	private Component parseButtonLabel(String pingName, Component offlineStatus, boolean isSpecial) {
		Component special = Component.empty();

		if (isSpecial) {
			special = ConfigManager.messages()
				.dialog()
				.getSpecial()
				.parse();
		}

		return ConfigManager.messages()
			.dialog()
			.getButton()
			.parse(pingName, offlineStatus, special);
	}
}
