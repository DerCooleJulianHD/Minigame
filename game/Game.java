package de.xcuzimsmart.pluginhelper.code.git.branch.game;

import de.xcuzimsmart.pluginhelper.code.git.branch.game.events.GameJoinEvent;
import de.xcuzimsmart.pluginhelper.code.git.branch.game.events.GameQuitEvent;
import de.xcuzimsmart.pluginhelper.code.git.branch.game.map.GameMap;
import de.xcuzimsmart.pluginhelper.code.git.branch.game.utils.GameState;
import de.xcuzimsmart.pluginhelper.code.git.branch.main.configuration.YamlConfigurator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Game {

    protected final Plugin plugin;

    protected final String name;

    protected final File dir;
    protected final YamlConfigurator config;

    protected final List<Player> onlinePlayers = new ArrayList<>();

    protected GameMap gameMap;
    protected GameState gameState;

    protected final PlayerManager playerManager;

    public Game(Plugin plugin, String name) {
        this.plugin = plugin;
        this.dir = new File(GameManager.getDir(), name);
        this.config = new YamlConfigurator(dir, "game.yml");
        this.name = config.isSet("name") ? config.readString("name") : name;
        this.playerManager = new PlayerManager(plugin);
    }

    public void loadGameMap(GameMap gameMap) {
        if (hasGameMap()) return;
        if (!gameMap.isLoaded()) gameMap.load();
        this.gameMap = gameMap;
    }

    public void join(Player player) {
        this.onlinePlayers.add(player);

        playerManager.readyPlayer(player);

        PluginManager manager = Bukkit.getPluginManager();
        manager.callEvent(new GameJoinEvent(plugin, player));
    }

    public void quit(Player player) {
        if (!isOnlinePlayer(player)) return;
        this.onlinePlayers.remove(player);
        PluginManager manager = Bukkit.getPluginManager();
        manager.callEvent(new GameQuitEvent(plugin, player));
    }

    public boolean isOnlinePlayer(Player player) {
        return this.onlinePlayers.contains(player);
    }

    public boolean hasGameMap() {
        return this.gameMap != null;
    }

    public String getName() {
        return name;
    }

    public int getMinPlayers() {
        return config.isSet("min-players") ? config.readInt("min-players") : 0;
    }

    public int getMaxPlayers() {
        return config.isSet("max-players") ? config.readInt("max-players") : plugin.getServer().getMaxPlayers();
    }

    public File getDir() {
        return dir;
    }

    public YamlConfigurator getConfig() {
        return config;
    }

    public List<Player> getOnlinePlayers() {
        return onlinePlayers;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameMap getGameMap() {
        return gameMap;
    }
}
