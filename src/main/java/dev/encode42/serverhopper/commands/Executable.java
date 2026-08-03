package dev.encode42.serverhopper.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import dev.encode42.serverhopper.ServerHopper;

public abstract class Executable {
	protected static String createPermission(String... nodes) {
		return ServerHopper.ID + "." + String.join(".", nodes);
	}

	public Player getExecutor(CommandContext<CommandSource> commandContext) throws CommandSyntaxException {
		CommandSource commandSource = commandContext.getSource();

		if (commandSource instanceof Player player) {
			return player;
		}

		throw ServerHopper.messages()
			.arguments()
			.getNotPlayer()
			.error();
	}
}
