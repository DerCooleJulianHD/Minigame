package me.xcuzimsmart.api.minigame.game.map;

import me.xcuzimsmart.api.minigame.Minigame;
import me.xcuzimsmart.api.minigame.game.Game;
import org.bukkit.World;

import java.io.IOException;

public interface Map {
    Game parent();
    World getWorld();
    boolean load() throws IOException;
    void unload();
    boolean reset() throws IOException;
    boolean isLoaded();
    Minigame getMinigame();
}
