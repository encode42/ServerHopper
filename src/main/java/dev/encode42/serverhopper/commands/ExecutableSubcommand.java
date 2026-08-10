package dev.encode42.serverhopper.commands;

import com.mojang.brigadier.tree.LiteralCommandNode;
import com.velocitypowered.api.command.CommandSource;

public abstract class ExecutableSubcommand extends Executable {
	protected final String parentCommandName;
	protected final String commandName;

	public ExecutableSubcommand(String parentCommandName, String commandName) {
		super();

		this.parentCommandName = parentCommandName;
		this.commandName = commandName;
	}

	public abstract LiteralCommandNode<CommandSource> createSubcommand();
}
