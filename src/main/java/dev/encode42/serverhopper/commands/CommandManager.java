package dev.encode42.serverhopper.commands;

import dev.encode42.serverhopper.commands.hop.HopCommand;
import dev.encode42.serverhopper.commands.invite.InviteCommand;
import dev.encode42.serverhopper.commands.join.JoinCommand;
import dev.encode42.serverhopper.commands.move.MoveCommand;
import dev.encode42.serverhopper.commands.root.RootCommand;
import dev.encode42.serverhopper.commands.servers.ServersCommand;

import java.util.Set;

public class CommandManager {
	private static final Set<ExecutableCommand> COMMANDS = Set.of(
		new RootCommand(),
		new HopCommand(),
		new InviteCommand(),
		new JoinCommand(),
		new MoveCommand(),
		new ServersCommand()
	);

	public static void registerAll() {
		for (ExecutableCommand command : CommandManager.COMMANDS) {
			command.register();
		}
	}
}
