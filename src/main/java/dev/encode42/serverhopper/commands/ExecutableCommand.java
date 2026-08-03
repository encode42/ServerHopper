package dev.encode42.serverhopper.commands;

import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandMeta;
import dev.encode42.serverhopper.ServerHopper;

public abstract class ExecutableCommand extends Executable {
	private static final CommandManager commandManager = ServerHopper.proxy().getCommandManager();

	protected final String commandName;

	public ExecutableCommand(String commandName) {
		this.commandName = commandName;
	}

	protected abstract BrigadierCommand createCommand();

	private CommandMeta createMeta() {
		return commandManager.metaBuilder(this.commandName)
			.plugin(ServerHopper.instance())
			.build();
	}

	public void register() {
		BrigadierCommand command = this.createCommand();
		CommandMeta meta = this.createMeta();

		commandManager.register(meta, command);
	}
}
