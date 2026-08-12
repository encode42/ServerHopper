package dev.encode42.serverhopper.commands.move;

import com.mojang.brigadier.tree.LiteralCommandNode;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandSource;
import dev.encode42.serverhopper.commands.ExecutableCommand;
import dev.encode42.serverhopper.permissions.CommandPermission;
import dev.encode42.serverhopper.permissions.Permission;

public class MoveCommand extends ExecutableCommand {
	public static final Permission PERMISSION = new CommandPermission("admin", "move");

	public MoveCommand() {
		super("move");
	}

	@Override
	public BrigadierCommand createCommand() {
		MovePlayerArgument playerArgument = new MovePlayerArgument();
		MoveServerArgument serverArgument = new MoveServerArgument();

		LiteralCommandNode<CommandSource> commandNode = BrigadierCommand.literalArgumentBuilder(this.commandName)
			.requires(MoveCommand.PERMISSION::hasPermission)
			.then(
				playerArgument.createArgument()
					.then(serverArgument.createArgument())
			)
			.build();

		return new BrigadierCommand(commandNode);
	}
}
