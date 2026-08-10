package dev.encode42.serverhopper.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import dev.encode42.serverhopper.config.ConfigManager;

public abstract class Executable {
	public boolean isPlayer(CommandSource commandSource) {
		return commandSource instanceof Player;
	}

	public Player getPlayer(CommandContext<CommandSource> commandContext) throws CommandSyntaxException {
		CommandSource commandSource = commandContext.getSource();

		if (commandSource instanceof Player player) {
			return player;
		}

		throw ConfigManager.messages()
			.arguments()
			.getNotPlayer()
			.error();
	}
}
