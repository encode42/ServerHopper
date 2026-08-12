package dev.encode42.serverhopper.commands.invite;

import com.mojang.brigadier.tree.LiteralCommandNode;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandSource;
import dev.encode42.serverhopper.commands.ExecutableCommand;
import dev.encode42.serverhopper.permissions.CommandPermission;
import dev.encode42.serverhopper.permissions.Permission;

public class InviteCommand extends ExecutableCommand {
	public static final Permission PERMISSION = new CommandPermission("invite");

	public InviteCommand() {
		super("invite");
	}

	@Override
	public BrigadierCommand createCommand() {
		InvitePlayerArgument playerArgument = new InvitePlayerArgument();

		LiteralCommandNode<CommandSource> commandNode = BrigadierCommand.literalArgumentBuilder(this.commandName)
			.requires(this::isPlayer)
			.requires(InviteCommand.PERMISSION::hasDefaultPermission)
			.then(playerArgument.createArgument())
			.build();

		return new BrigadierCommand(commandNode);
	}
}
