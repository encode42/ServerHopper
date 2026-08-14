package dev.encode42.serverhopper.commands.move;

import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.velocitypowered.api.command.CommandSource;
import dev.encode42.serverhopper.commands.ExecutableArgument;
import dev.encode42.serverhopper.commands.arguments.PlayerArgument;
import dev.encode42.serverhopper.helpers.SuggestionsHelper;

import java.util.concurrent.CompletableFuture;

public class MovePlayerArgument extends ExecutableArgument<String> {
	@Override
	public RequiredArgumentBuilder<CommandSource, String> createArgument() {
		return PlayerArgument.create()
			.suggests(SuggestionsHelper.merge(this::suggest, PlayerArgument::suggest));
	}

	public CompletableFuture<Suggestions> suggest(CommandContext<CommandSource> commandContext, SuggestionsBuilder suggestionsBuilder) {
		suggestionsBuilder.suggest("\"*\"");

		return suggestionsBuilder.buildFuture();
	}
}
