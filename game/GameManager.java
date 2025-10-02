package de.xcuzimsmart.pluginhelper.code.git.branch.game;

import de.xcuzimsmart.pluginhelper.code.git.branch.game.map.GameMap;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nullable;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class GameManager {

    final Plugin plugin;

    static File dir;
    final String[] names;

    public GameManager(Plugin plugin, File dir) {
        this.plugin = plugin;
        GameManager.dir = dir;
        if (!dir.exists()) dir.mkdirs();

        this.names = dir.list();
    }

    public void loadGame(String name, @Nullable GameMap map) {
        final Game game = new Game(plugin, name);
        if (map != null) game.loadGameMap(map);
    }

    public void loadGame(int id) {
        if (names.length == 0) return;
        if (id >= 0 && id < names.length || id == names.length) {
            final String name = this.list().get(id);
            if (name != null)
                loadGame(name, null);
        }
    }

    public void loadRandomGame() {
        final Random random = new Random();
        final int id = random.nextInt(names.length);
        this.loadGame(id);
    }

    public static File getDir() {
        return dir;
    }

    public String[] getNames() {
        return names;
    }

    public List<String> list() {
        return names == null ? new ArrayList<>() : Arrays.stream(names).toList();
    }
}

