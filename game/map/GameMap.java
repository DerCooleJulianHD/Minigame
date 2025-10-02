package de.xcuzimsmart.pluginhelper.code.git.branch.game.map;

import de.xcuzimsmart.pluginhelper.code.git.branch.game.GameManager;
import de.xcuzimsmart.pluginhelper.code.git.branch.main.utils.FileManager;
import de.xcuzimsmart.pluginhelper.code.git.branch.main.utils.Loadable;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

// world for any minigame
public abstract class GameMap implements Loadable {

    final Plugin plugin;
    final boolean originalWorld;

    File sourceFolder;
    File activeWorldFolder;
    World bukkitWorld;

    boolean loaded;

    public GameMap(Plugin plugin, File sourceFolder, File activeWorldFolder, boolean originalWorld) {
        this.plugin = plugin;
        this.originalWorld = originalWorld;
        this.sourceFolder = sourceFolder;
        this.activeWorldFolder = activeWorldFolder;
        this.bukkitWorld = null;
        this.loaded = false;
    }

    @Override
    // creates the world that will be used.
    public void load() {
        if (!sourceFolder.exists()) return;

        // creates the temporary worldFolder and imports all Files from the Original.
        if (!isOriginalWorld()) FileManager.copyInnerFiles(sourceFolder, activeWorldFolder);

        this.createWorld(); // creates the BukkitWorld and load it on the Server.
        this.setWorldParameters(); // does set individual settings for a GameMap.
    }

    public void setWorldParameters() {
        if (!isLoaded()) return;

        bukkitWorld.setMetadata(activeWorldFolder.getName(), new FixedMetadataValue(plugin, bukkitWorld));
        bukkitWorld.setAutoSave(false);
        bukkitWorld.setPVP(true);
        bukkitWorld.setWeatherDuration(0);
        bukkitWorld.setDifficulty(Difficulty.NORMAL);
        bukkitWorld.setKeepSpawnInMemory(false);
    }

    public void createWorld() {
        if (activeWorldFolder == null || !activeWorldFolder.exists()) return;
        if (Bukkit.getWorld(activeWorldFolder.getName()) != null) return;
        this.bukkitWorld = Bukkit.createWorld(WorldCreator.name(activeWorldFolder.getName()));
        this.setLoaded(Bukkit.getWorld(activeWorldFolder.getName()) != null);
    }

    @Override
    // unloads the world and deleting all files.
    public void unload() {
        if (!isLoaded()) return;

        bukkitWorld.getPlayers().forEach(p -> p.kickPlayer(""));

        Bukkit.unloadWorld(bukkitWorld, false);
        if (!isOriginalWorld()) FileManager.deleteFile(activeWorldFolder);
        this.bukkitWorld = null;
        setLoaded(false);
    }

    public static void zip(File sourceFolder) {
        final File dir = new File(GameManager.getDir(), "zipped_maps");
        if (!dir.exists()) dir.mkdirs();

        final File zip = new File(dir, sourceFolder + ".zip");

        try {
            if (!zip.exists()) zip.createNewFile();
            final File importFolder = new File(zip, sourceFolder.getName());
            importFolder.mkdirs();
            FileManager.copyInnerFiles(sourceFolder, importFolder);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isOriginalWorld() {
        return originalWorld;
    }

    @Override
    // return true when the world is created and ready to play.
    public boolean isLoaded() {
        return loaded && bukkitWorld != null;
    }

    @Override
    public void setLoaded(boolean b) {
        this.loaded = b;
    }

    public File getSourceFolder() {
        return sourceFolder;
    }

    public World getBukkitWorld() {
        return bukkitWorld;
    }

    public File getActiveWorldFolder() {
        return activeWorldFolder;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public void setActiveWorldFolder(File activeWorldFolder) {
        this.activeWorldFolder = activeWorldFolder;
    }

    public void setBukkitWorld(World bukkitWorld) {
        this.bukkitWorld = bukkitWorld;
    }

    public void setSourceFolder(File sourceFolder) {
        this.sourceFolder = sourceFolder;
    }
}


