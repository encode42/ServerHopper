package dev.encode42.serverhopper.commands.root;

import com.mojang.brigadier.tree.LiteralCommandNode;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandSource;
import dev.encode42.serverhopper.ServerHopper;
import dev.encode42.serverhopper.commands.ExecutableCommand;
import dev.encode42.serverhopper.permissions.CommandPermission;
import dev.encode42.serverhopper.permissions.Permission;

public class RootCommand extends ExecutableCommand {
	public static final Permission PERMISSION = new CommandPermission("root");

	public RootCommand() {
		super(ServerHopper.ID);
	}

	@Override
	public BrigadierCommand createCommand() {
		RootReloadCommand reloadCommand = new RootReloadCommand(this.commandName);

		LiteralCommandNode<CommandSource> commandNode = BrigadierCommand.literalArgumentBuilder(ServerHopper.ID)
			.requires(RootCommand.PERMISSION::hasDefaultPermission)
			.then(reloadCommand.createSubcommand())
			.build();

		return new BrigadierCommand(commandNode);
	}
}
