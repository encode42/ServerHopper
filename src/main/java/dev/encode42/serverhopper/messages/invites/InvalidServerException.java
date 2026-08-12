package dev.encode42.serverhopper.messages.invites;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.velocitypowered.api.command.VelocityBrigadierMessage;
import dev.encode42.serverhopper.messages.Exception;
import dev.encode42.serverhopper.messages.placeholders.ServerTranslation;
import dev.encode42.serverhopper.utilities.Pair;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

public class InvalidServerException extends Exception {
	public static final String DEFAULT_VALUE = "<red>Player <username> cannot receive invites to <server>.";

	private final DynamicCommandExceptionType exception = new DynamicCommandExceptionType(
		argument -> {
			@SuppressWarnings("unchecked")
			Pair<String, String> pair = (Pair<String, String>) argument;

			return VelocityBrigadierMessage.tooltip(
				this.parse(pair.one(), pair.two())
			);
		}
	);

	public InvalidServerException(String message) {
		super(message);
	}

	public Component parse(String username, String serverName) {
		return super.parse(
			Placeholder.unparsed("username", username),
			Placeholder.unparsed("server", serverName),
			ServerTranslation.resolve(serverName)
		);
	}

	public CommandSyntaxException error(String username, String serverName) {
		Pair<String, String> pair = new Pair<>(username, serverName);

		return this.exception.create(pair);
	}
}
