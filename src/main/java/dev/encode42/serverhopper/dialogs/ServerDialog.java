package dev.encode42.serverhopper.dialogs;

import com.github.retrooper.packetevents.protocol.chat.clickevent.RunCommandClickEvent;
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
import dev.encode42.serverhopper.ServerHopper;
import dev.encode42.serverhopper.connection.*;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.List;

public class ServerDialog {
	private final String serverName;
	private final Component connectedTooltip;
	private final Component offlineTooltip;

	private final int buttonWidth;
	private final int totalColumns;

	public ServerDialog(Player player) {
		this(player, 150, 2);
	}

	public ServerDialog(Player player, int buttonWidth, int totalColumns) {
		ServerConnection serverConnection = ConnectionManager.getConnection(player);

		if (serverConnection == null) {
			this.serverName = "unknown";
		} else {
			RegisteredServer server = serverConnection.getServer();
			ServerInfo serverInfo = server.getServerInfo();

			this.serverName = serverInfo.getName();
		}

		this.connectedTooltip = ServerHopper.messages()
			.dialog()
			.getConnectedTooltip()
			.parse(this.serverName);

		this.offlineTooltip = ServerHopper.messages()
			.dialog()
			.getOfflineTooltip()
			.parse(this.serverName);

		this.buttonWidth = buttonWidth;
		this.totalColumns = totalColumns;
	}

	public Dialog create() {
		Component dialogTitle = ServerHopper.messages()
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
		Component dialogBody = ServerHopper.messages()
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

		for (PingInfo pingInfo : PingCache.getAll()) {
			ActionButton button = createButton(pingInfo);

			buttons.add(button);
		}

		return buttons;
	}

	private ActionButton createButton(PingInfo pingInfo) {
		if (pingInfo.isOnline()) {
			return this.createOnlineButton((OnlinePingInfo) pingInfo);
		} else {
			return this.createOfflineButton((OfflinePingInfo) pingInfo);
		}
	}

	private ActionButton createOnlineButton(OnlinePingInfo pingInfo) {
		String pingName = pingInfo.getName();

		boolean isConnected = pingName.equals(this.serverName);

		Component onlineStatus = ServerHopper.messages()
			.dialog()
			.getOnline()
			.parse(
				pingInfo.getConnectedPlayers(),
				pingInfo.getMaxPlayers()
			);

		Component buttonLabel = ServerHopper.messages()
			.dialog()
			.getButton()
			.parse(pingName, onlineStatus);

		CommonButtonData buttonData = new CommonButtonData(
			buttonLabel,
			isConnected ? this.connectedTooltip : null,
			this.buttonWidth
		);

		if (isConnected) {
			return new ActionButton(buttonData, null);
		}

		RunCommandClickEvent clickEvent = new RunCommandClickEvent("/server %s".formatted(pingName));
		StaticAction clickAction = new StaticAction(clickEvent);

		return new ActionButton(buttonData, clickAction);
	}

	private ActionButton createOfflineButton(OfflinePingInfo pingInfo) {
		String pingName = pingInfo.getName();

		Component offlineStatus = ServerHopper.messages()
			.dialog()
			.getOffline()
			.parse();

		Component buttonLabel = ServerHopper.messages()
			.dialog()
			.getButton()
			.parse(pingName, offlineStatus);

		CommonButtonData buttonData = new CommonButtonData(
			buttonLabel,
			this.offlineTooltip,
			this.buttonWidth
		);

		return new ActionButton(buttonData, null);
	}
}
