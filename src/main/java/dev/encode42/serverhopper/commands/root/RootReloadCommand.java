package dev.encode42.serverhopper.commands.root;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandSource;
import dev.encode42.serverhopper.commands.ExecutableSubcommand;
import dev.encode42.serverhopper.data.ConfigManager;
import dev.encode42.serverhopper.permissions.Permission;

public class RootReloadCommand extends ExecutableSubcommand {
	public static Permission PERMISSION = RootCommand.PERMISSION.append("reload");

	public RootReloadCommand(String parentCommand) {
		super(parentCommand, "reload");
	}

	@Override
	public LiteralCommandNode<CommandSource> createSubcommand() {
		return BrigadierCommand.literalArgumentBuilder(this.commandName)
			.requires(RootReloadCommand.PERMISSION::hasPermission)
			.executes(this::execute)
			.build();
	}

	public int execute(CommandContext<CommandSource> commandContext) {
		boolean isOk = ConfigManager.reload();

		if (!isOk) {
			commandContext.getSource().sendMessage(
				ConfigManager.messages()
					.meta()
					.getFailedReload()
					.parse()
			);

			return 1;
		}

		commandContext.getSource().sendMessage(
			ConfigManager.messages()
				.meta()
				.getSuccessfulReload()
				.parse()
		);

		return Command.SINGLE_SUCCESS;
	}
}
