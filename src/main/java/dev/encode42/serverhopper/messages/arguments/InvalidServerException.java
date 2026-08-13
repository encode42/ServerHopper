package dev.encode42.serverhopper.messages.arguments;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.velocitypowered.api.command.VelocityBrigadierMessage;
import dev.encode42.serverhopper.messages.Exception;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

public class InvalidServerException extends Exception {
	public static final String DEFAULT_VALUE = "<red>Server \"<server>\" could not be found.";

	private final DynamicCommandExceptionType exception = new DynamicCommandExceptionType(
		serverName -> VelocityBrigadierMessage.tooltip(
			this.parse(serverName.toString())
		)
	);

	public InvalidServerException(String message) {
		super(message);
	}

	public Component parse(String serverName) {
		return super.parse(Placeholder.unparsed("server", serverName));
	}

	public CommandSyntaxException error(String serverName) {
		return this.exception.create(serverName);
	}
}
