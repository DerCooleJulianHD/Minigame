package de.xcuzimsmart.pluginhelper.code.git.branch.game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;

public final class PlayerManager {
    
    final Plugin plugin;
    final List<Player> spectators = new ArrayList<>();

    public PlayerManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void readyPlayer(Player player) {
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        player.setHealth(20D);
        player.setHealthScale(20D);
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        if (!player.isOp()) player.setAllowFlight(false);
        player.setFlying(false);
        player.setExp(0F);
        player.setLevel(0);
        player.setGameMode(GameMode.SURVIVAL);
        player.setPlayerListName(player.getName());
        player.setSaturation(20F);
        player.setFoodLevel(20);
        player.getActivePotionEffects().forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));
        player.setFireTicks(0);
    }

    public List<Player> getSpectators() {
        return spectators;
    }

    public boolean isSpectator(Player player) {
        return spectators.contains(player);
    }

    public void setSpectator(Player player) {
        Bukkit.getOnlinePlayers().remove(player);
        spectators.add(player);
        setSpectorTeam(player);
    }

    private void setSpectorTeam(Player player) {
        player.setGameMode(GameMode.ADVENTURE);

        final Scoreboard scoreboard = player.getScoreboard();
        org.bukkit.scoreboard.Team team = scoreboard.getTeam("spectator");
        if (team == null) {
            team = scoreboard.registerNewTeam("spectator");
            team.setPrefix(ChatColor.GRAY.toString());
            team.setSuffix(ChatColor.DARK_GRAY + " [" + ChatColor.DARK_RED + "X" + ChatColor.DARK_GRAY + "]");
            team.setAllowFriendlyFire(true);
            team.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
        }

        player.getActivePotionEffects().forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 255, true, false));

        for (Player target : Bukkit.getOnlinePlayers()) {
            if (team.hasEntry(target.getName())) continue;
            target.hidePlayer(player);
        }
    }
}


