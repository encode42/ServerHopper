package dev.encode42.serverhopper.messages.invites;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.velocitypowered.api.command.VelocityBrigadierMessage;
import dev.encode42.serverhopper.messages.Exception;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

public class InvalidPlayerException extends Exception {
	public static final String DEFAULT_VALUE = "<red>Player <username> cannot receive invites.";

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
