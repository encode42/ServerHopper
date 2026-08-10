package dev.encode42.serverhopper.permissions;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.permission.Tristate;
import dev.encode42.serverhopper.ServerHopper;

import java.util.ArrayList;
import java.util.List;

public class Permission {
	private static final String ID = ServerHopper.ID;

	protected final List<String> nodes;

	public Permission(String... nodes) {
		this(List.of(nodes));
	}

	public Permission(List<String> nodes) {
		this.nodes = new ArrayList<>(nodes);
	}

	public Permission append(String... appendedNodes) {
		return this.append(List.of(appendedNodes));
	}

	public Permission append(List<String> appendedNodes) {
		List<String> combinedNodes = new ArrayList<>(this.nodes);

		combinedNodes.addAll(appendedNodes);

		return new Permission(combinedNodes);
	}

	public String toString() {
		return Permission.ID + "." + String.join(".", this.nodes);
	}

	public boolean hasPermission(CommandSource commandSource) {
		return commandSource.hasPermission(this.toString());
	}

	public boolean hasDefaultPermission(CommandSource commandSource) {
		return commandSource.getPermissionValue(this.toString()) != Tristate.FALSE;
	}
}
