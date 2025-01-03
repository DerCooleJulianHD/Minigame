package me.xcuzimsmart.minigame.player.respawn;

import me.xcuzimsmart.utils.MinecraftPlugin;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerRespawnDelay extends BukkitRunnable {

    private final MinecraftPlugin plugin;
    private final Player target;
    private int time;

    public PlayerRespawnDelay(MinecraftPlugin plugin, Player target, int time) {
        this.plugin = plugin;
        this.target = target;
        this.time = time;
        this.runTaskTimer(plugin.getPlugin(), 5, 20);
    }

    @Override
    public void run() {
        // asking if delay is zero
        if (this.getTime() == 0) {
            // respawning player instantly
            this.respawnAutomatically(target);
            // stopping this loop
            cancel();
        }

        // sending player titles
        sendTitle();

        // decrease 1 second every time while looping.
        setTime(getTime() - 1);
    }

    // do respawn the player instantly
    private void respawnAutomatically(Player player) {
        player.sendTitle(ChatColor.GREEN.toString() + ChatColor.ITALIC + "Respawned", "");
    }

    public void sendTitle() {
        final String title = ChatColor.RED.toString() + ChatColor.BOLD + "Respawning";
        final String subTitle = ChatColor.GRAY.toString() + ChatColor.ITALIC + "Respawn in: " + ChatColor.AQUA + getTime() + ChatColor.GRAY + ChatColor.ITALIC + " seconds.";

        // sending player title messages.
        target.sendTitle(title, subTitle);
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public MinecraftPlugin getPlugin() {
        return plugin;
    }
}
