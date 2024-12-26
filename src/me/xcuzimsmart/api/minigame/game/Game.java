package me.xcuzimsmart.api.minigame.game;

import me.xcuzimsmart.api.minigame.Minigame;
import me.xcuzimsmart.api.minigame.game.manager.GameManager;
import me.xcuzimsmart.api.minigame.game.map.Map;
import me.xcuzimsmart.api.minigame.game.state.GameState;
import me.xcuzimsmart.utils.storages.YamlStorage;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.List;

public interface Game {

    Minigame getMinigame();
    GameManager getGameManager();
    File getDataFolder();
    YamlStorage settings();
    Map map();
    void sendStartingMessage();
    void sendStoppingMessage();
    boolean isOnline();
    GameState getGameState();
    void setGameState(GameState gameState);
    void load();
    String name();
    List<Player> getOnlinePlayers();
}
