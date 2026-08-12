package dev.encode42.serverhopper.commands.hop;

import com.mojang.brigadier.tree.LiteralCommandNode;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandSource;
import dev.encode42.serverhopper.commands.ExecutableCommand;
import dev.encode42.serverhopper.permissions.CommandPermission;
import dev.encode42.serverhopper.permissions.Permission;

public class HopCommand extends ExecutableCommand {
	public static final Permission PERMISSION = new CommandPermission("hop");

	public HopCommand() {
		super("hop");
	}

	@Override
	public BrigadierCommand createCommand() {
		HopServerArgument serverArgument = new HopServerArgument();

		LiteralCommandNode<CommandSource> commandNode = BrigadierCommand.literalArgumentBuilder(this.commandName)
			.requires(HopCommand.PERMISSION::hasDefaultPermission)
			.then(
				serverArgument.createArgument()
			)
			.build();

		return new BrigadierCommand(commandNode);
	}
}
