package de.xcuzimsmart.pluginhelper.code.git.branch.game.map;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.Date;

public final class LocalGameMap extends GameMap {

    public LocalGameMap(Plugin plugin, File sourceFolder, String name) {
        super(plugin, sourceFolder, new File(Bukkit.getWorldContainer().getParentFile(), name + "_active_" + new Date()), false);
    }
}

