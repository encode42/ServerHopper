package dev.encode42.serverhopper.helpers;

import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.velocitypowered.api.command.CommandSource;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SuggestionsHelper {
	public static SuggestionProvider<CommandSource> merge(SuggestionProvider<CommandSource> providerOne, SuggestionProvider<CommandSource> providerTwo) {
		return (commandContext, suggestionsBuilder) -> {
			CompletableFuture<Suggestions> futuresOne = providerOne.getSuggestions(commandContext, suggestionsBuilder.restart());
			CompletableFuture<Suggestions> futuresTwo = providerTwo.getSuggestions(commandContext, suggestionsBuilder.restart());

			return futuresOne.thenCombine(futuresTwo, (suggestionsOne, suggestionsTwo) ->
				Suggestions.merge(suggestionsBuilder.getInput(), List.of(suggestionsOne, suggestionsTwo))
			);
		};
	}
}
