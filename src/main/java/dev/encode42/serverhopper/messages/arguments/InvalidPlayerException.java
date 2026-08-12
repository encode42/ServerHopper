package dev.encode42.serverhopper.messages.arguments;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.velocitypowered.api.command.VelocityBrigadierMessage;
import dev.encode42.serverhopper.messages.Exception;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

public class InvalidPlayerException extends Exception {
	public static final String DEFAULT_VALUE = "<red>Player \"<username>\" could not be found. Are they still online?";

	private final DynamicCommandExceptionType exception = new DynamicCommandExceptionType(
		username -> VelocityBrigadierMessage.tooltip(
			this.parse(username.toString())
		)
	);

	public InvalidPlayerException(String message) {
		super(message);
	}

	public Component parse(String username) {
		return super.parse(Placeholder.unparsed("username", username));
	}

	public CommandSyntaxException error(String username) {
		return this.exception.create(username);
	}
}
