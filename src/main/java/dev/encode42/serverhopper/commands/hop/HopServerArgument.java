package dev.encode42.serverhopper.commands.hop;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import dev.encode42.serverhopper.commands.ExecutableArgument;
import dev.encode42.serverhopper.commands.arguments.ServerArgument;
import dev.encode42.serverhopper.data.ConfigManager;
import dev.encode42.serverhopper.helpers.connections.ConnectionHelper;
import dev.encode42.serverhopper.helpers.connections.ConnectionStatus;
import dev.encode42.serverhopper.permissions.ServerPermission;

public class HopServerArgument extends ExecutableArgument<String> {
	@Override
	public RequiredArgumentBuilder<CommandSource, String> createArgument() {
		RequiredArgumentBuilder<CommandSource, String> argumentBuilder = ServerArgument.create();

		argumentBuilder.executes(this::execute);

		return argumentBuilder;
	}

	public int execute(CommandContext<CommandSource> commandContext) throws CommandSyntaxException {
		Player sourcePlayer = this.getPlayer(commandContext);
		RegisteredServer server = ServerArgument.parse(commandContext);

		String serverName = server.getServerInfo().getName();

		if (!ServerPermission.hasDefaultPermission(sourcePlayer, serverName)) {
			throw ConfigManager.messages()
				.connection()
				.getInvalidPermission()
				.error(serverName);
		}

		ConnectionStatus connectionStatus = ConnectionHelper.connect(sourcePlayer, server);

		if (connectionStatus != ConnectionStatus.SUCCESS) {
			throw ConfigManager.messages()
				.connection()
				.getExecutorConnected()
				.error();
		}

		return Command.SINGLE_SUCCESS;
	}
}
