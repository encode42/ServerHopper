package dev.encode42.serverhopper.connection;

import com.velocitypowered.api.proxy.server.PingOptions;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import com.velocitypowered.api.proxy.server.ServerPing;
import dev.encode42.serverhopper.ServerHopper;

import java.time.Duration;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class PingCache {
	private static final Duration REFRESH_INTERVAL = Duration.ofSeconds(30);
	private static final OfflinePingInfo OFFLINE_SERVER = new OfflinePingInfo("unknown");

	private static final PingOptions PING_OPTIONS = PingOptions.builder()
		.timeout(Duration.ofSeconds(5))
		.build();

	private static final Map<RegisteredServer, PingInfo> cache = new ConcurrentHashMap<>();
	private static final Map<RegisteredServer, CompletableFuture<ServerPing>> pings = new ConcurrentHashMap<>();

	public static void init() {
		PingCache.refreshAll();

		ServerHopper.proxy()
			.getScheduler()
			.buildTask(ServerHopper.instance(), PingCache::checkStale)
			.delay(PingCache.REFRESH_INTERVAL)
			.repeat(PingCache.REFRESH_INTERVAL)
			.schedule();
	}

	public static PingInfo get(RegisteredServer server) {
		return PingCache.cache.getOrDefault(server, PingCache.OFFLINE_SERVER);
	}

	public static Collection<PingInfo> getAll() {
		return PingCache.cache.values();
	}

	public static void remove(RegisteredServer server) {
		PingCache.cache.remove(server);
	}

	public static void refresh(RegisteredServer server) {
		if (
			ServerHopper.config().isIgnored(server)
				|| ServerHopper.queue().isPaused(server)
		) {
			return;
		}

		PingCache.pings.computeIfAbsent(server, serverKey ->
			server.ping(PingCache.PING_OPTIONS)
				.handle(((serverPing, throwable) -> {
					Optional<ServerPing.Players> optionalPlayers = serverPing.getPlayers();

					if (optionalPlayers.isEmpty()) {
						PingCache.cache.put(server, new OfflinePingInfo(
							server.getServerInfo().getName()
						));

						return serverPing;
					}

					ServerPing.Players players = optionalPlayers.get();

					PingCache.cache.put(server, new OnlinePingInfo(
						server.getServerInfo().getName(),
						players.getMax(),
						players.getOnline()
					));

					return serverPing;
				}))
				.exceptionally((throwable) -> {
					PingCache.cache.put(server, new OfflinePingInfo(
						server.getServerInfo().getName()
					));

					return null;
				})
				.whenComplete(((unused1, unused2) ->
					PingCache.pings.remove(serverKey))
				)
		);
	}

	public static void refreshAll() {
		for (RegisteredServer registeredServer : ServerHopper.proxy().getAllServers()) {
			PingCache.refresh(registeredServer);
		}
	}

	private static void checkStale() {
		long now = System.currentTimeMillis();
		long refreshInterval = PingCache.REFRESH_INTERVAL.getSeconds();

		for (Map.Entry<RegisteredServer, PingInfo> entry : PingCache.cache.entrySet()) {
			PingInfo pingInfo = entry.getValue();

			long updateDifference = (now - pingInfo.getUpdated()) / 1000;
			if (updateDifference < refreshInterval) {
				continue;
			}

			RegisteredServer registeredServer = entry.getKey();

			PingCache.refresh(registeredServer);
		}
	}
}
