package dev.encode42.serverhopper.commands.move;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import dev.encode42.serverhopper.ServerHopper;
import dev.encode42.serverhopper.commands.ExecutableArgument;
import dev.encode42.serverhopper.commands.arguments.PlayerArgument;
import dev.encode42.serverhopper.commands.arguments.ServerArgument;
import dev.encode42.serverhopper.data.ConfigManager;
import dev.encode42.serverhopper.helpers.connections.ConnectionHelper;
import dev.encode42.serverhopper.helpers.connections.ConnectionStatus;

public class MoveServerArgument extends ExecutableArgument<String> {
	@Override
	public RequiredArgumentBuilder<CommandSource, String> createArgument() {
		RequiredArgumentBuilder<CommandSource, String> argumentBuilder = ServerArgument.create();

		argumentBuilder.executes(this::execute);

		return argumentBuilder;
	}

	public int execute(CommandContext<CommandSource> commandContext) throws CommandSyntaxException {
		RegisteredServer server = ServerArgument.parse(commandContext);

		try {
			Player player = PlayerArgument.parse(commandContext);

			return this.executeSingle(commandContext, player, server);
		} catch (CommandSyntaxException exception) {
			String argument = PlayerArgument.getArgument(commandContext);

			if (!argument.equals("*")) {
				throw exception;
			}

			return this.executeAll(commandContext, server);
		}
	}

	private int executeSingle(CommandContext<CommandSource> commandContext, Player player, RegisteredServer server) throws CommandSyntaxException {
		String playerName = player.getUsername();
		String serverName = server.getServerInfo().getName();

		ConnectionStatus connectionStatus = this.movePlayer(player, server);

		if (connectionStatus != ConnectionStatus.SUCCESS) {
			throw ConfigManager.messages()
				.connection()
				.getTargetConnected()
				.error(playerName, serverName);
		}

		commandContext.getSource().sendMessage(
			ConfigManager.messages()
				.move()
				.getSent()
				.parse(playerName, serverName)
		);

		return Command.SINGLE_SUCCESS;
	}

	private int executeAll(CommandContext<CommandSource> commandContext, RegisteredServer server) throws CommandSyntaxException {
		String serverName = server.getServerInfo().getName();

		for (Player player : ServerHopper.proxy().getAllPlayers()) {
			this.movePlayer(player, server);
		}

		commandContext.getSource().sendMessage(
			ConfigManager.messages()
				.move()
				.getSentAll()
				.parse(serverName)
		);

		return Command.SINGLE_SUCCESS;
	}

	private ConnectionStatus movePlayer(Player player, RegisteredServer server) {
		String serverName = server.getServerInfo().getName();

		ConnectionStatus connectionStatus = ConnectionHelper.connect(player, server);

		if (connectionStatus == ConnectionStatus.SUCCESS) {
			player.sendMessage(
				ConfigManager.messages()
					.move()
					.getReceived()
					.parse(serverName)
			);
		}

		return connectionStatus;
	}
}
