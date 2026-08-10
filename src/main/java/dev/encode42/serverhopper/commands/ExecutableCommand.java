package dev.encode42.serverhopper.commands;

import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandMeta;
import dev.encode42.serverhopper.ServerHopper;

public abstract class ExecutableCommand extends Executable {
	protected final String commandName;

	private final CommandManager commandManager = ServerHopper.proxy().getCommandManager();

	public ExecutableCommand(String commandName) {
		this.commandName = commandName;
	}

	public abstract BrigadierCommand createCommand();

	private CommandMeta createMeta() {
		return this.commandManager.metaBuilder(this.commandName)
			.plugin(ServerHopper.instance())
			.build();
	}

	public void register() {
		BrigadierCommand command = this.createCommand();
		CommandMeta meta = this.createMeta();

		this.commandManager.register(meta, command);
	}
}
