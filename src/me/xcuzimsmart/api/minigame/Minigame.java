package me.xcuzimsmart.api.minigame;

import me.xcuzimsmart.api.minigame.game.Game;
import me.xcuzimsmart.api.minigame.game.GamePlayable;
import me.xcuzimsmart.api.minigame.game.manager.GameManager;
import me.xcuzimsmart.utils.MinecraftPlugin;

public class Minigame {

    private final MinecraftPlugin plugin;
    private final GameManager gameManager;
    private Game playable;

    private final String name;

    public Minigame(MinecraftPlugin plugin, String name) {
        this.plugin = plugin;
        this.gameManager = new GameManager(this);
        this.playable = null;
        this.name = name;
    }

    public String name() {
        return name;
    }

    public Game getPlayable() {
        return playable;
    }

    public void loadGame() {
        GamePlayable gamePlayable = gameManager.load(this);
        if (gamePlayable == null) return;
        this.setPlayable(gamePlayable);
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public MinecraftPlugin getMinecraftPlugin() {
        return plugin;
    }

    protected void setPlayable(Game playable) {
        this.playable = playable;
    }
}
