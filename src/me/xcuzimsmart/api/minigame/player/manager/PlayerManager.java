package me.xcuzimsmart.minigame.player.manager;

import me.xcuzimsmart.minigame.player.equipment.PlayerEquipment;
import me.xcuzimsmart.minigame.player.equipment.manager.EquipmentManager;
import me.xcuzimsmart.minigame.player.respawn.PlayerRespawnDelay;
import me.xcuzimsmart.utils.MinecraftPlugin;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import java.util.Map;

public class PlayerManager {

    public static void equip(Player target, PlayerEquipment equipment) {
        // setting armor equipment and items in Hotbar.
        final Map<Integer, ItemStack> items = EquipmentManager.getHotbarItems().get(target.getUniqueId());
        EquipmentManager.setPlayerEquipment(target, equipment, items);
    }

    public void respawn(MinecraftPlugin plugin, Player player) {
        // time of player respawn delay
        final int delay = 5;
        // start te respawn delay for player
        new PlayerRespawnDelay(plugin, player, delay);
    }
}
