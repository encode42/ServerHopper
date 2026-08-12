package dev.encode42.serverhopper.commands.arguments;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import dev.encode42.serverhopper.ServerHopper;
import dev.encode42.serverhopper.data.ConfigManager;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class PlayerArgument {
	private static final String ARGUMENT_NAME = "player";

	public static RequiredArgumentBuilder<CommandSource, String> create() {
		return BrigadierCommand.requiredArgumentBuilder(ARGUMENT_NAME, StringArgumentType.string())
			.suggests(PlayerArgument::suggest);
	}

	public static CompletableFuture<Suggestions> suggest(CommandContext<CommandSource> commandContext, SuggestionsBuilder suggestionsBuilder) {
		CommandSource commandSource = commandContext.getSource();

		UUID executingPlayerUniqueId = null;

		if (commandSource instanceof Player executingPlayer) {
			executingPlayerUniqueId = executingPlayer.getUniqueId();
		}

		for (Player player : ServerHopper.proxy().getAllPlayers()) {
			if (player.getUniqueId().equals(executingPlayerUniqueId)) {
				continue;
			}

			suggestionsBuilder.suggest(player.getUsername());
		}

		return suggestionsBuilder.buildFuture();
	}

	public static Player parse(CommandContext<CommandSource> commandContext) throws CommandSyntaxException {
		String argument = PlayerArgument.getArgument(commandContext);

		Optional<Player> optionalPlayer = ServerHopper.proxy().getPlayer(argument);

		if (optionalPlayer.isEmpty()) {
			throw ConfigManager.messages()
				.arguments()
				.getInvalidPlayer()
				.error(argument);
		}

		return optionalPlayer.get();
	}

	public static String getArgument(CommandContext<CommandSource> commandContext) {
		return commandContext.getArgument(ARGUMENT_NAME, String.class);
	}
}
