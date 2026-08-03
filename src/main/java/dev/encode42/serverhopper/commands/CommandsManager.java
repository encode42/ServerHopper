package dev.encode42.serverhopper.commands;

import dev.encode42.serverhopper.commands.servers.ServersCommand;

import java.util.Set;

public class CommandsManager {
	private static final Set<ExecutableCommand> COMMANDS = Set.of(
		new ServersCommand()
	);

	public static void registerAll() {
		for (ExecutableCommand command : CommandsManager.COMMANDS) {
			command.register();
		}
	}
}
