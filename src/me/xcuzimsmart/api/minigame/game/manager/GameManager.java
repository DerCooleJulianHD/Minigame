package me.xcuzimsmart.api.minigame.game.manager;

import com.avaje.ebean.validation.NotNull;
import me.xcuzimsmart.api.minigame.Minigame;
import me.xcuzimsmart.api.minigame.game.GamePlayable;
import me.xcuzimsmart.utils.file.FileManager;

import java.io.File;
import java.util.*;

public class GameManager {
    private final Minigame minigame;
    private File sourceFolder;

    public GameManager(Minigame minigame) {
        this.minigame = minigame;
        this.setup();
    }


    public void setup() {
        File collectionFolder = new File(minigame.getMinecraftPlugin().getPlugin().getDataFolder(), "games");
        FileManager.createDirectory(collectionFolder);
        this.sourceFolder = collectionFolder;
    }


    public GamePlayable load(String name ) {
        return new GamePlayable(minigame, name, true);
    }

    public List<String> collect() {
        final String[] names = this.sourceFolder.list();
        if (names == null) return Collections.emptyList();
        if (names.length == 0) return Collections.emptyList();
        return Arrays.stream(names).toList();
    }

    @NotNull
    public GamePlayable load(Minigame minigame) {
        final Random random = new Random();
        final List<String> collection = collect();
        if (collection.isEmpty()) return null;
        final String name = collection.get(random.nextInt());
        return load(name);
    }

    public File getSourceFolder() {
        return sourceFolder;
    }
}
