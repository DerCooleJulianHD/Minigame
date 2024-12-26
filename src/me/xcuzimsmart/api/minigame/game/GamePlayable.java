package me.xcuzimsmart.api.minigame.game;

import me.xcuzimsmart.api.minigame.Minigame;
import me.xcuzimsmart.api.minigame.game.events.LobbyListener;
import me.xcuzimsmart.api.minigame.game.manager.GameManager;
import me.xcuzimsmart.api.minigame.game.map.LocalGameMap;
import me.xcuzimsmart.api.minigame.game.map.Map;
import me.xcuzimsmart.api.minigame.game.state.GameState;
import me.xcuzimsmart.utils.factory.Message;
import me.xcuzimsmart.utils.storages.YamlStorage;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GamePlayable implements Game {

    private final Minigame minigame;
    private final GameManager gameManager;
    private final File dataFolder;
    private final YamlStorage storage;
    private Map map;
    private GameState state;
    private boolean online;
    private List<Player> onlinePlayers;

    public GamePlayable(Minigame minigame, String name, boolean start) {
        this.minigame = minigame;
        this.gameManager = minigame.getGameManager();
        this.dataFolder = new File(gameManager.getSourceFolder(), name);
        final File storageFile = new File(dataFolder, "config.yaml");
        this.storage = new YamlStorage(storageFile);
        if (start) {
            sendStartingMessage();
            load();
        }
    }


    @Override
    public void load() {
        final Server server = minigame.getMinecraftPlugin().getPlugin().getServer();
        final String name = this.name();
        this.map = new LocalGameMap(minigame, this, name);

        try {
            boolean b = map.load();
            if (b) server.getConsoleSender().sendMessage(Message.factorizeMessage(minigame.getMinecraftPlugin(), Message.MessageType.SUCCESS_ACTION, "Game " + name + " has been loaded successfully"));
        } catch (IOException e) {
            server.getConsoleSender().sendMessage(Message.factorizeMessage(minigame.getMinecraftPlugin(), Message.MessageType.ERROR, "Could not load '" + name + "' because " + e.getMessage()));
            throw new RuntimeException(e);
        }

        this.onlinePlayers = new ArrayList<>();
        this.setGameState(GameState.LOBBY);
        new LobbyListener(minigame, this);
        this.setOnline(true);
    }

    @Override
    public String name() {
        return settings().configuration().getString("name");
    }

    @Override
    public List<Player> getOnlinePlayers() {
        return onlinePlayers;
    }


    @Override
    public File getDataFolder() {
        return dataFolder;
    }


    @Override
    public YamlStorage settings() {
        return storage;
    }


    @Override
    public Map map() {
        return map;
    }


    @Override
    public void sendStartingMessage() {
        final Server server = minigame.getMinecraftPlugin().getPlugin().getServer();
        final String message = minigame.getMinecraftPlugin().prefix() + ChatColor.GRAY + "Game '" + ChatColor.AQUA + ChatColor.ITALIC + this.name() + ChatColor.GRAY + "' is now " + ChatColor.GREEN + "starting.";

        server.getConsoleSender().sendMessage(message);
        server.getOnlinePlayers().forEach(player -> {
            if (player.isOp()) {
                player.sendMessage(message);
            }
        });
    }


    @Override
    public void sendStoppingMessage() {
        final Server server = minigame.getMinecraftPlugin().getPlugin().getServer();
        final String message = minigame.getMinecraftPlugin().prefix() + ChatColor.GRAY + "Game '" + ChatColor.AQUA + ChatColor.ITALIC + this.name() + ChatColor.GRAY + "' is now " + ChatColor.RED + "stopping.";

        server.getConsoleSender().sendMessage(message);
        server.getOnlinePlayers().forEach(player -> {
            if (player.isOp()) {
                player.sendMessage(message);
            }
        });
    }


    @Override
    public boolean isOnline() {
        return online;
    }


    @Override
    public GameState getGameState() {
        return state;
    }


    @Override
    public void setGameState(GameState gameState) {
        this.state = gameState;
    }


    public Minigame getMinigame() {
        return minigame;
    }

    @Override
    public GameManager getGameManager() {
        return gameManager;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
