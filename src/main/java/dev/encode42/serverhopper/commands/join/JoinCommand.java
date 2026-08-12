package dev.encode42.serverhopper.commands.join;

import com.mojang.brigadier.tree.LiteralCommandNode;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandSource;
import dev.encode42.serverhopper.commands.ExecutableCommand;
import dev.encode42.serverhopper.permissions.CommandPermission;
import dev.encode42.serverhopper.permissions.Permission;

public class JoinCommand extends ExecutableCommand {
	public static final Permission PERMISSION = new CommandPermission("join");

	public JoinCommand() {
		super("join");
	}

	@Override
	public BrigadierCommand createCommand() {
		JoinPlayerArgument playerArgument = new JoinPlayerArgument();

		LiteralCommandNode<CommandSource> commandNode = BrigadierCommand.literalArgumentBuilder(this.commandName)
			.requires(this::isPlayer)
			.requires(JoinCommand.PERMISSION::hasDefaultPermission)
			.then(playerArgument.createArgument())
			.build();

		return new BrigadierCommand(commandNode);
	}
}
