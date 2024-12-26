package me.xcuzimsmart.api.minigame.game.events;

import me.xcuzimsmart.api.minigame.Minigame;
import me.xcuzimsmart.api.minigame.game.Game;
import me.xcuzimsmart.utils.listener.PluginListener;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class LobbyListener extends PluginListener {

    private final Minigame minigame;
    private final Game parent;

    public LobbyListener(Minigame minigame, Game parent) {
		super(minigame.getMinecraftPlugin());
        this.minigame = minigame;
        this.parent = parent;

        LobbyListener.setInstance(this);
    }
	

    @EventHandler
    public void handleInteractItem(PlayerInteractEvent event) {
        if (event.getAction() == null) return;
        switch (event.getAction()) {
            case RIGHT_CLICK_AIR, RIGHT_CLICK_BLOCK: {
                Player player = event.getPlayer();
                if (event.getItem() == null) return;
                if (event.getItem().getItemMeta() == null) return;
                if (event.getItem().getType() != Material.SLIME_BALL) return;
                player.kickPlayer(ChatColor.RED + "Disconnected");
                event.setCancelled(true);
                break;
            }
        }
    }
	

    @EventHandler
    public void handlePlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
		// player join logic
    }
	

    @EventHandler
    public void handlePlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
		// player quit logic
    }
	

    @EventHandler
    public void handleBlockPlace(BlockPlaceEvent event) {
        if (event.getPlayer().isOp()) {
            event.setCancelled(false);
            return;
        }
		
        event.setCancelled(true);
    }
	

    @EventHandler
    public void handleBlockBreak(BlockBreakEvent event) {
        if (event.getPlayer().isOp()) {
            event.setCancelled(false);
            return;
        }
		
        event.setCancelled(true);
    }
	

    @EventHandler
    public void handleEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) event.setCancelled(true);
    }
	

    @EventHandler
    public void handleBlockBurn(BlockBurnEvent event) {
        event.setCancelled(true);
    }
	

    @EventHandler
    public void handleFoodLevelChange(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }
	

    @EventHandler
    public void handleBlockExplode(BlockExplodeEvent event) {
        event.setCancelled(true);
    }
	

    @EventHandler
    public void handleBlockPhysics(BlockPhysicsEvent event) {
        event.setCancelled(true);
    }


	public Minigame getMinigame() {
		return minigame;
	}

    public Game getParent() {
        return parent;
    }
}
