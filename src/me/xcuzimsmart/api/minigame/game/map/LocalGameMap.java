package me.xcuzimsmart.api.minigame.game.map;

import me.xcuzimsmart.api.minigame.Minigame;
import me.xcuzimsmart.api.minigame.game.Game;
import org.bukkit.*;
import org.bukkit.metadata.FixedMetadataValue;

import java.io.File;
import java.io.IOException;

public class LocalGameMap implements Map {
    private final Minigame minigame;
    private final Game parent;
    private final String name;
    private World bukkitWorld;

    public LocalGameMap(Minigame minigame, Game parent, String name) {
        this.minigame = minigame;
        this.parent = parent;
        this.name = name;
    }


    @Override
    public Game parent() {
        return parent;
    }


    @Override
    public World getWorld() {
        return bukkitWorld;
    }


    public void setWorld(World world) {
        this.bukkitWorld = world;
    }


    @Override
    public boolean load() throws IOException {
        if (isLoaded()) return true;
        final Server server = minigame.getMinecraftPlugin().getPlugin().getServer();
        final File source = new File(server.getWorldContainer(), name);
        if (!source.exists()) throw new IOException("missing original world.");
        final World world = server.createWorld(WorldCreator.name(name));
        setWorldParameters(world);
        setWorld(world);
        return isLoaded();
    }

    private void setWorldParameters(World world) {
        world.setPVP(true);
        world.setAutoSave(false);
        world.setTime(8000);
        world.setDifficulty(Difficulty.PEACEFUL);
        world.setStorm(false);
        world.setThundering(false);
        world.setGameRuleValue("doDaylightCycle", "false");
        world.setMetadata("world:{name=" + name + "}", new FixedMetadataValue(minigame.getMinecraftPlugin().getPlugin(), world));
    }

    @Override
    public void unload() {
        if (!isLoaded()) return;
        final Server server = minigame.getMinecraftPlugin().getPlugin().getServer();
        server.unloadWorld(bukkitWorld, false);
        this.bukkitWorld = null;
    }


    @Override
    public boolean reset() throws IOException {
        unload();
        load();
        return this.isLoaded();
    }


    @Override
    public boolean isLoaded() {
        return Bukkit.getWorld(name) != null;
    }


    @Override
    public Minigame getMinigame() {
        return minigame;
    }
}
