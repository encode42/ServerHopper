package dev.encode42.serverhopper.commands.invite;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ServerConnection;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import dev.encode42.serverhopper.commands.ExecutableArgument;
import dev.encode42.serverhopper.commands.arguments.PlayerArgument;
import dev.encode42.serverhopper.commands.join.JoinCommand;
import dev.encode42.serverhopper.connection.ConnectionManager;
import dev.encode42.serverhopper.data.ConfigManager;
import dev.encode42.serverhopper.permissions.ServerPermission;

public class InvitePlayerArgument extends ExecutableArgument<String> {
	public RequiredArgumentBuilder<CommandSource, String> createArgument() {
		RequiredArgumentBuilder<CommandSource, String> argumentBuilder = PlayerArgument.create();

		argumentBuilder.executes(this::execute);

		return argumentBuilder;
	}

	public int execute(CommandContext<CommandSource> commandContext) throws CommandSyntaxException {
		Player executingPlayer = this.getPlayer(commandContext);
		Player targetPlayer = PlayerArgument.parse(commandContext);

		String executingPlayerName = executingPlayer.getUsername();
		String targetPlayerName = targetPlayer.getUsername();

		if (!JoinCommand.PERMISSION.hasDefaultPermission(targetPlayer)) {
			throw ConfigManager.messages()
				.invites()
				.getInvalidInvite()
				.error(targetPlayerName);
		}

		ServerConnection executingServerConnection = ConnectionManager.getConnection(executingPlayer);

		RegisteredServer executingServer = executingServerConnection.getServer();
		String executingServerName = executingServer.getServerInfo().getName();

		if (!ServerPermission.hasDefaultPermission(targetPlayer, executingServerName)) {
			throw ConfigManager.messages()
				.invites()
				.getInvalidServerInvite()
				.error(targetPlayerName, executingServerName);
		}

		targetPlayer.sendMessage(
			ConfigManager.messages()
				.invites()
				.getInviteReceived()
				.parse(executingPlayerName, executingServerName)
		);

		executingPlayer.sendMessage(
			ConfigManager.messages()
				.invites()
				.getInviteSent()
				.parse(targetPlayerName, executingServerName)
		);

		return Command.SINGLE_SUCCESS;
	}
}
