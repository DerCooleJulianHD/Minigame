package me.xcuzimsmart.minigame.player.equipment.models;

import me.xcuzimsmart.minigame.player.equipment.manager.EquipmentManager;
import me.xcuzimsmart.minigame.player.equipment.utils.EquipmentType;
import me.xcuzimsmart.minigame.player.equipment.PlayerEquipment;
import org.apache.commons.lang.Validate;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;

public class SimpleEquipment implements PlayerEquipment {
    public final String DISPLAYNAME_FORMAT = ChatColor.AQUA.toString() + ChatColor.ITALIC;

    private final Player player;
    private final EquipmentType type;

    public SimpleEquipment(Player player, EquipmentType type) {
        Validate.isTrue(type == EquipmentType.LEATHER, "SimpleEquipment can not be a type of 'LEATHER'.");
        this.player = player;
        this.type = type;
    }

    @Override
    public Player player() {
        return player;
    }

    @Override
    public EquipmentType type() {
        return type;
    }

    @Override
    public ItemStack helmet() {
        return Objects.requireNonNull(EquipmentManager.getPlayerEquipment(player)).helmet();
    }

    @Override
    public ItemStack jacket() {
        return Objects.requireNonNull(EquipmentManager.getPlayerEquipment(player)).jacket();
    }

    @Override
    public ItemStack pants() {
        // creating item & meta
        final ItemStack item = new ItemStack(Material.getMaterial(type.name() + "_LEGGINGS"));
        final ItemMeta meta = item.getItemMeta();

        // setting up item peripherals
        meta.setDisplayName(DISPLAYNAME_FORMAT + "Pants");
        // set peripherals to our itemStack
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public ItemStack boots() {
        // creating item & meta
        final ItemStack item = new ItemStack(Material.getMaterial(type.name() + "_BOOTS"));
        final ItemMeta meta = item.getItemMeta();

        // setting up item peripherals
        meta.setDisplayName(DISPLAYNAME_FORMAT + "Boots");
        // set peripherals to our itemStack
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public List<ItemStack> list() {
        return List.of(helmet(), jacket(), pants(), boots());
    }
}
