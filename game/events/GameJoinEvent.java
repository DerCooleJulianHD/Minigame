package de.xcuzimsmart.pluginhelper.code.git.branch.game.events;

import de.xcuzimsmart.pluginhelper.code.git.branch.main.utils.MessageBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class GameJoinEvent extends PlayerJoinEvent {

    final Plugin plugin;

    public GameJoinEvent(Plugin plugin, Player player) {
        super(player, MessageBuilder.build(plugin, "§a+ §7" + player.getName()));
        this.plugin = plugin;
    }

    public Plugin getPlugin() {
        return plugin;
    }
}
