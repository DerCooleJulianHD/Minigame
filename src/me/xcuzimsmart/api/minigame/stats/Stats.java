package me.xcuzimsmart.api.minigame.stats;

import java.util.UUID;

public class Stats {

    private final UUID uuid;
    private final String playerName;

    private final double kd;
    private final int kills;
    private final int deaths;
    private final int bedsDestroyed;
    private final int roundsPlayed;
    private final int wins;

    public Stats(UUID uuid, String playerName, int roundsPlayed, int kills, int deaths, int bedsDestroyed, int wins) {
        this.uuid = uuid;
        this.playerName = playerName;
        this.roundsPlayed = roundsPlayed;
        this.kills = kills;
        this.deaths = deaths;
        this.bedsDestroyed = bedsDestroyed;
        this.wins = wins;
        this.kd = (double) kills / deaths;
    }

    public UUID uuid() {
        return uuid;
    }

    public String playerName() {
        return playerName;
    }

    public double kd() {
        return kd;
    }

    public int kills() {
        return kills;
    }

    public int bedsDestroyed() {
        return bedsDestroyed;
    }

    public int roundsPlayed() {
        return roundsPlayed;
    }

    public int wins() {
        return wins;
    }

    public int deaths() {
        return deaths;
    }
}
