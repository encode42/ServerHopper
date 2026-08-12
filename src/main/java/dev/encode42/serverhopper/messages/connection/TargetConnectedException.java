package dev.encode42.serverhopper.messages.connection;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.velocitypowered.api.command.VelocityBrigadierMessage;
import dev.encode42.serverhopper.messages.Exception;
import dev.encode42.serverhopper.messages.placeholders.ServerTranslation;
import dev.encode42.serverhopper.utilities.Pair;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

public class TargetConnectedException extends Exception {
	public static final String DEFAULT_VALUE = "<red><username> is already connected to <server>!";

	private final DynamicCommandExceptionType exception = new DynamicCommandExceptionType(
		argument -> {
			@SuppressWarnings("unchecked")
			Pair<String, String> pair = (Pair<String, String>) argument;

			return VelocityBrigadierMessage.tooltip(
				this.parse(pair.one(), pair.two())
			);
		}
	);

	public TargetConnectedException(String message) {
		super(message);
	}

	public Component parse(String username, String server) {
		return super.parse(
			Placeholder.unparsed("username", username),
			Placeholder.unparsed("server", server),
			ServerTranslation.resolve(server)
		);
	}

	public CommandSyntaxException error(String username, String server) {
		Pair<String, String> pair = new Pair<>(username, server);

		return this.exception.create(pair);
	}
}
