package dev.encode42.serverhopper.messages.placeholders;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

public class ServerTranslation {
	public static TagResolver resolve(String serverName) {
		return TagResolver.resolver("server_translation", ((argumentQueue, context) -> {
			Tag.Argument argument = argumentQueue.popOr("No arguments provided for server_translation");

			String key = argument.value().replace("<server>", serverName);

			return Tag.selfClosingInserting(Component.translatable(key));
		}));
	}
}
