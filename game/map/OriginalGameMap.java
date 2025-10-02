package de.xcuzimsmart.pluginhelper.code.git.branch.game.map;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.File;

public final class OriginalGameMap extends GameMap {

    public OriginalGameMap(Plugin plugin, String name) {
        super(plugin, new File(Bukkit.getWorldContainer().getParentFile(), name), new File(Bukkit.getWorldContainer().getParentFile(), name), true);
    }
}

