package dev.encode42.serverhopper.messages.connection;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.velocitypowered.api.command.VelocityBrigadierMessage;
import dev.encode42.serverhopper.messages.Exception;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

public class InvalidPermissionException extends Exception {
	public static final String DEFAULT_VALUE = "<red>You do not have permission to connect to <server>.";

	private final DynamicCommandExceptionType exception = new DynamicCommandExceptionType(
		serverName -> VelocityBrigadierMessage.tooltip(
			this.parse(serverName.toString())
		)
	);

	public InvalidPermissionException(String message) {
		super(message);
	}

	public Component parse(String serverName) {
		return super.parse(Placeholder.unparsed("server", serverName));
	}

	public CommandSyntaxException error(String serverName) {
		return this.exception.create(serverName);
	}
}
