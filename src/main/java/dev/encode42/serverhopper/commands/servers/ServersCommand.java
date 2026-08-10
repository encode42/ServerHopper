package dev.encode42.serverhopper.commands.servers;

import com.github.retrooper.packetevents.protocol.dialog.Dialog;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerShowDialog;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import dev.encode42.serverhopper.ServerHopper;
import dev.encode42.serverhopper.commands.ExecutableCommand;
import dev.encode42.serverhopper.dialogs.ServerDialog;
import dev.encode42.serverhopper.permissions.CommandPermission;
import dev.encode42.serverhopper.permissions.Permission;

public class ServersCommand extends ExecutableCommand {
	public static final Permission PERMISSION = new CommandPermission("servers");

	public ServersCommand() {
		super("servers");
	}

	@Override
	public BrigadierCommand createCommand() {
		LiteralCommandNode<CommandSource> commandNode = BrigadierCommand.literalArgumentBuilder(this.commandName)
			.requires(this::isPlayer)
			.requires(ServersCommand.PERMISSION::hasDefaultPermission)
			.executes(this::execute)
			.build();

		return new BrigadierCommand(commandNode);
	}

	public int execute(CommandContext<CommandSource> commandContext) throws CommandSyntaxException {
		Player player = this.getPlayer(commandContext);

		ServerDialog serverDialog = new ServerDialog(player);
		Dialog dialog = serverDialog.create();

		ServerHopper.packetEvents()
			.getPlayerManager()
			.sendPacket(
				player,
				new WrapperPlayServerShowDialog(dialog)
			);

		return Command.SINGLE_SUCCESS;
	}
}
