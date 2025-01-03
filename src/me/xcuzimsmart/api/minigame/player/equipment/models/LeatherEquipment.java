package me.xcuzimsmart.minigame.player.equipment.models;

import me.xcuzimsmart.minigame.player.equipment.utils.EquipmentType;
import me.xcuzimsmart.minigame.player.equipment.PlayerEquipment;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.List;

public class LeatherEquipment implements PlayerEquipment {

    public final String DISPLAYNAME_FORMAT = ChatColor.AQUA.toString() + ChatColor.ITALIC;

    private final Player player;
    private final Color color;

    public LeatherEquipment(Player player, Color color) {
        this.player = player;
        this.color = color;
    }

    @Override
    public Player player() {
        return player;
    }

    @Override
    public EquipmentType type() {
        return EquipmentType.LEATHER;
    }

    @Override
    public ItemStack helmet() {
        // creating item & meta
        final ItemStack item = new ItemStack(Material.LEATHER_HELMET);
        final LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();

        // setting up item peripherals
        meta.setColor(color);
        meta.setDisplayName(DISPLAYNAME_FORMAT + "Helmet");
        meta.addEnchant(Enchantment.DAMAGE_UNDEAD, 1, true);

        // set peripherals to our itemStack
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public ItemStack jacket() {
        // creating item & meta
        final ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE);
        final LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();

        // setting up item peripherals
        meta.setColor(color);
        meta.setDisplayName(DISPLAYNAME_FORMAT + "Jacket");

        // set peripherals to our itemStack
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public ItemStack pants() {
        // creating item & meta
        final ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS);
        final LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();

        // setting up item peripherals
        meta.setColor(color);
        meta.setDisplayName(DISPLAYNAME_FORMAT + "Pants");

        // set peripherals to our itemStack
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public ItemStack boots() {
        // creating item & meta
        final ItemStack item = new ItemStack(Material.LEATHER_BOOTS);
        final LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();

        // setting up item peripherals
        meta.setColor(color);
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
