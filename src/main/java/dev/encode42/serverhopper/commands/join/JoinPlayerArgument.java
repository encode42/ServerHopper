package dev.encode42.serverhopper.commands.join;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import dev.encode42.serverhopper.commands.ExecutableArgument;
import dev.encode42.serverhopper.commands.arguments.PlayerArgument;
import dev.encode42.serverhopper.connection.ConnectionManager;
import dev.encode42.serverhopper.connection.ConnectionStatus;
import dev.encode42.serverhopper.data.ConfigManager;

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
				.getInvalid()
				.error(targetPlayer.getUsername());
		}

		ConnectionStatus connectionStatus = ConnectionManager.connect(executingPlayer, targetPlayer);

		if (connectionStatus != ConnectionStatus.SUCCESS) {
			throw ConfigManager.messages()
				.connection()
				.getExecutorConnected()
				.error();
		}

		return Command.SINGLE_SUCCESS;
	}
}
