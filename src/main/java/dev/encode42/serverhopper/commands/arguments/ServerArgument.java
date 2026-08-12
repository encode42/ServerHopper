package dev.encode42.serverhopper.commands.arguments;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import com.velocitypowered.api.proxy.server.ServerInfo;
import dev.encode42.serverhopper.ServerHopper;
import dev.encode42.serverhopper.data.ConfigManager;
import dev.encode42.serverhopper.permissions.ServerPermission;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class ServerArgument {
	private static final String ARGUMENT_NAME = "server";

	public static RequiredArgumentBuilder<CommandSource, String> create() {
		return BrigadierCommand.requiredArgumentBuilder(ARGUMENT_NAME, StringArgumentType.string())
			.suggests(ServerArgument::suggest);
	}

	public static CompletableFuture<Suggestions> suggest(CommandContext<CommandSource> commandContext, SuggestionsBuilder suggestionsBuilder) {
		CommandSource commandSource = commandContext.getSource();

		for (RegisteredServer server : ServerHopper.proxy().getAllServers()) {
			if (!ServerPermission.hasDefaultPermission(commandSource, server)) {
				continue;
			}

			ServerInfo serverInfo = server.getServerInfo();

			suggestionsBuilder.suggest(serverInfo.getName());
		}

		return suggestionsBuilder.buildFuture();
	}

	public static RegisteredServer parse(CommandContext<CommandSource> commandContext) throws CommandSyntaxException {
		String argument = ServerArgument.getArgument(commandContext);

		Optional<RegisteredServer> optionalServer = ServerHopper.proxy().getServer(argument);

		if (optionalServer.isEmpty()) {
			throw ConfigManager.messages()
				.arguments()
				.getInvalidServer()
				.error(argument);
		}

		return optionalServer.get();
	}

	public static String getArgument(CommandContext<CommandSource> commandContext) {
		return commandContext.getArgument(ARGUMENT_NAME, String.class);
	}
}
