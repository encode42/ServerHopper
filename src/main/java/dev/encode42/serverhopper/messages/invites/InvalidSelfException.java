package dev.encode42.serverhopper.messages.invites;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.velocitypowered.api.command.VelocityBrigadierMessage;
import dev.encode42.serverhopper.messages.Exception;
import net.kyori.adventure.text.Component;

public class InvalidSelfException extends Exception {
	public static final String DEFAULT_VALUE = "<red>You cannot invite yourself!";

	private final SimpleCommandExceptionType exception = new SimpleCommandExceptionType(
		VelocityBrigadierMessage.tooltip(
			this.parse()
		)
	);

	public InvalidSelfException(String message) {
		super(message);
	}

	public Component parse() {
		return super.parse();
	}

	public CommandSyntaxException error() {
		return this.exception.create();
	}
}
