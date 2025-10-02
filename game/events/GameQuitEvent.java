package de.xcuzimsmart.pluginhelper.code.git.branch.game.events;

import de.xcuzimsmart.pluginhelper.code.git.branch.main.utils.MessageBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class GameQuitEvent extends PlayerQuitEvent {

    final Plugin plugin;

    public GameQuitEvent(Plugin plugin, Player player) {
        super(player, MessageBuilder.build(plugin, "§c- §7" + player.getName()));
        this.plugin = plugin;
    }

    public Plugin getPlugin() {
        return plugin;
    }
}
