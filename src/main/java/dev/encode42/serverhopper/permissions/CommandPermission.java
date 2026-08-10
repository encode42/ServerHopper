package dev.encode42.serverhopper.permissions;

import java.util.List;

public class CommandPermission extends Permission {
	public CommandPermission(String... nodes) {
		this(List.of(nodes));
	}

	public CommandPermission(List<String> nodes) {
		super(nodes);

		this.nodes.addFirst("commands");
	}
}
