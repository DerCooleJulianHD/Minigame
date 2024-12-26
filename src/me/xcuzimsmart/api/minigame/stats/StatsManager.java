package me.xcuzimsmart.api.minigame.stats;

import com.avaje.ebean.validation.NotNull;
import me.xcuzimsmart.api.minigame.Minigame;
import me.xcuzimsmart.utils.file.FileManager;
import me.xcuzimsmart.utils.storages.JsonStorage;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.UUID;

public class StatsManager {

    @NotNull
    public static Stats getPlayerStats(Minigame minigame, UUID uuid) {
        final File file = StatsManager.getPlayerStatsFile(minigame, uuid);
        if (!file.exists()) return null;
        final JsonStorage storage = new JsonStorage(file);
        return (Stats) storage.parse(Stats.class);
    }


    public static void savePlayerStats(Minigame minigame, UUID uuid, Stats stats) {
        final File file = getPlayerStatsFile(minigame, uuid);

        if (!file.exists()) {
            FileManager.createFile(file);
        }

        final JsonStorage storage = new JsonStorage(file);
        storage.set(stats);
        storage.save();
    }


    public static void updatePlayerStats(Minigame minigame, UUID uuid, Stats newStats) {
        final File file = StatsManager.getPlayerStatsFile(minigame, uuid);

        if (!file.exists()) {
            StatsManager.savePlayerStats(minigame, uuid, newStats);
            return;
        }

        final Stats current = StatsManager.getPlayerStats(minigame, uuid);
        if (current != null) {
            final int roundsPlayed = (current.roundsPlayed() + newStats.roundsPlayed());
            final int kills = (current.kills() + newStats.kills());
            final int deaths = (current.deaths() + newStats.deaths());
            final int bedsDestroyed = (current.bedsDestroyed() + newStats.bedsDestroyed());
            final int wins = (current.wins() + newStats.wins());
            final String playerName = current.playerName();
            final Stats result = new Stats(current.uuid(), playerName, roundsPlayed, kills, deaths, bedsDestroyed, wins);

            StatsManager.savePlayerStats(minigame, uuid, result);
        }
    }

    public static Stats getNewPlayerStats(Minigame minigame, Player player) {
        final UUID uuid = player.getUniqueId();
        final Stats stats = new Stats(uuid, player.getName(), 0, 0, 0, 0, 0);
        savePlayerStats(minigame, uuid, stats);
        return stats;
    }


    public static void deletePlayerStats(Minigame minigame, UUID uuid) {
        final File file = getPlayerStatsFile(minigame, uuid);
        FileManager.delete(file);
    }


    private static File getPlayerStatsFile(Minigame minigame, UUID uuid) {
        final File destination = new File(minigame.getMinecraftPlugin().getPlugin().getDataFolder(), "stats");
        return new File(destination, uuid.toString() + ".json");
    }
}
