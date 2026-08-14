package dev.encode42.serverhopper.commands.join;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ServerConnection;
import dev.encode42.serverhopper.commands.ExecutableArgument;
import dev.encode42.serverhopper.commands.arguments.PlayerArgument;
import dev.encode42.serverhopper.data.ConfigManager;
import dev.encode42.serverhopper.helpers.connections.ConnectionHelper;
import dev.encode42.serverhopper.helpers.connections.ConnectionStatus;

public class JoinPlayerArgument extends ExecutableArgument<String> {
	@Override
	public RequiredArgumentBuilder<CommandSource, String> createArgument() {
		RequiredArgumentBuilder<CommandSource, String> argumentBuilder = PlayerArgument.create();

		argumentBuilder.executes(this::execute);

		return argumentBuilder;
	}

	public int execute(CommandContext<CommandSource> commandContext) throws CommandSyntaxException {
		Player executingPlayer = this.getPlayer(commandContext);
		Player targetPlayer = PlayerArgument.parse(commandContext);

		if (!JoinCommand.PERMISSION.hasDefaultPermission(targetPlayer)) {
			throw ConfigManager.messages()
				.join()
				.invalid()
				.error(targetPlayer.getUsername());
		}

		ConnectionStatus connectionStatus = ConnectionHelper.connect(executingPlayer, targetPlayer);

		if (connectionStatus == ConnectionStatus.SUCCESS) {
			return Command.SINGLE_SUCCESS;
		}

		if (connectionStatus == ConnectionStatus.NO_PERMISSION) {
			ServerConnection serverConnection = ConnectionHelper.getConnection(targetPlayer);

			if (serverConnection != null) {
				throw ConfigManager.messages()
					.connection()
					.invalidPermission()
					.error(serverConnection.getServerInfo().getName());
			}
		}

		throw ConfigManager.messages()
			.connection()
			.executorConnected()
			.error();
	}
}
