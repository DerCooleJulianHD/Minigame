package me.xcuzimsmart.minigame.player.equipment.manager;

import me.xcuzimsmart.minigame.player.equipment.utils.EquipmentType;
import me.xcuzimsmart.minigame.player.equipment.PlayerEquipment;
import me.xcuzimsmart.minigame.player.equipment.models.SimpleEquipment;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EquipmentManager {
    private static final Map<UUID, PlayerEquipment> equipments = new HashMap<>();
    private static final Map<UUID, Map<Integer, ItemStack>> hotbarItems = new HashMap<>();


    // sets player the full Equipment.
    public static void setPlayerEquipment(Player player, PlayerEquipment equipment, Map<Integer, ItemStack> hotbarItems) {
        // calling default-method to improve code-style and performance to setting armor.
        setPlayerEquipment(player, equipment);
        // filling up hotbar items
        setPlayerHotbarItems(player, hotbarItems);
    }

    public static void setPlayerHotbarItems(Player player, @Nullable Map<Integer, ItemStack> map) {
        final PlayerInventory inventory = player.getInventory();

        //asking if map hasn't been initialized.
        if (map == null) {
            if (inventory.getContents().length != 0) inventory.clear();
            return;
        }

        // filling up hotbar items
        hotbarItems.put(player.getUniqueId(), map);
        map.forEach(inventory::setItem);
    }

    public static void setPlayerEquipment(Player player, @Nullable PlayerEquipment equipment) {
        final PlayerInventory inventory = player.getInventory();

        // asking if equipment parameter is null. While true, it will set our armor to nothing and clear.
        if (equipment != null) {
            // putting the parameters in map to save equipment-state
            equipments.put(player.getUniqueId(), equipment);

            // set the PlayerEquipment to players inventory armor

            inventory.setHelmet(equipment.helmet());
            inventory.setChestplate(equipment.jacket());
            inventory.setLeggings(equipment.pants());
            inventory.setBoots(equipment.boots());
            return;
        }

        // asking if player has any equipment set
        if (getPlayerEquipment(player) != null) {
            // remove and clear all armor pieces
            equipments.remove(player.getUniqueId());
            inventory.setArmorContents(null);
        }
    }


    @Nullable
    public static PlayerEquipment getPlayerEquipment(Player player) {
        // ask for if player already has an PlayerEquipment set and returning null when player isn't set in map yet.
        if (!equipments.containsKey(player.getUniqueId())) return null;

        // else returning the PlayerEquipment which was set into map before
        return equipments.get(player.getUniqueId());
    }

    // sets the material to a new type
    public static void setEquipmentType(Player player, EquipmentType type) {
        // asking if player has equipment set
        if (getPlayerEquipment(player) == null) return;

        // creating the new equipment
        final PlayerEquipment equipment = new SimpleEquipment(player, type);
        setPlayerEquipment(player, equipment);
    }

    public void setProtectionLevel(Player player, int level) {
        final PlayerInventory inventory = player.getInventory();
        final ItemStack[] contents = inventory.getArmorContents();

        for (ItemStack item : contents) {
            final ItemMeta meta = item.getItemMeta();
            // adding the protection enchantment to each item.
            meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, level, true);
            item.setItemMeta(meta);
        }

        // set player the updated armor contents
        inventory.setArmorContents(contents);
        player.updateInventory();
    }

    // returns the map
    public static Map<UUID, PlayerEquipment> getEquipments() {
        return equipments;
    }

    // returns the hotbar items of a specific player
    public static Map<UUID, Map<Integer, ItemStack>> getHotbarItems() {
        return hotbarItems;
    }
}


