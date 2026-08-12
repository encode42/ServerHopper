package dev.encode42.serverhopper.commands;

import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.velocitypowered.api.command.CommandSource;

public abstract class ExecutableArgument<ArgumentType> extends Executable {
	public abstract RequiredArgumentBuilder<CommandSource, ArgumentType> createArgument();
}
